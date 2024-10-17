package com.yupi.luoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.luoj.common.BaseResponse;
import com.yupi.luoj.common.ErrorCode;
import com.yupi.luoj.common.ResultUtils;
import com.yupi.luoj.exception.BusinessException;
import com.yupi.luoj.model.dto.question.QuestionQueryRequest;
import com.yupi.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.luoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.luoj.model.entity.Question;
import com.yupi.luoj.model.entity.QuestionSubmit;
import com.yupi.luoj.model.entity.User;
import com.yupi.luoj.model.vo.QuestionSubmitVO;
import com.yupi.luoj.service.PostService;
import com.yupi.luoj.service.QuestionService;
import com.yupi.luoj.service.QuestionSubmitService;
import com.yupi.luoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 */
@RestController
//@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {
    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;

//    /**
//     * 提交题目
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return 提交记录的id
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//                                               HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能点赞
//        final User loginUser = userService.getLoginUser(request);
//        long questionId = questionSubmitAddRequest.getQuestionId();
//        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(questionSubmitId);
//    }
//
//    /**
//     * 分页获取题目提交列表（仅管理员和提交用户）
//     *
//     * @param questionSubmitQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest  ,
//                                                                         HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        //从数据库中查询原始的题目提交分页信息
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        final User loginUser = userService.getLoginUser(request);
//        //返回脱敏信息
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
//    }
}



