package com.yupi.luoj.judge.strategy;

import com.yupi.luoj.model.dto.question.JudgeCase;
import com.yupi.luoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.luoj.model.entity.Question;
import com.yupi.luoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 *   上下文
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
