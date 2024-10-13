package com.yupi.luoj.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.luoj.common.ErrorCode;
import com.yupi.luoj.common.PageRequest;
import com.yupi.luoj.exception.BusinessException;
import com.yupi.luoj.judge.codesandbox.CodeSandbox;
import com.yupi.luoj.judge.codesandbox.CodeSandboxFactory;
import com.yupi.luoj.judge.codesandbox.CodeSandboxProxy;
import com.yupi.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.luoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.luoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.luoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yupi.luoj.judge.strategy.JudgeContext;
import com.yupi.luoj.judge.strategy.JudgeStrategy;
import com.yupi.luoj.model.dto.question.JudgeCase;
import com.yupi.luoj.model.dto.question.JudgeConfig;
import com.yupi.luoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.luoj.model.entity.Question;
import com.yupi.luoj.model.entity.QuestionSubmit;
import com.yupi.luoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.luoj.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.luoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.luoj.model.vo.QuestionSubmitVO;
import com.yupi.luoj.service.QuestionService;
import com.yupi.luoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.event.ListDataEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1、传入题目的提交id，获取对应的题目、提交信息
        // 2、调用沙箱，获取执行结果
        // 3、根据沙箱的执行结果，设置题目的判题状态和信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 调用代码沙箱
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult= questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
