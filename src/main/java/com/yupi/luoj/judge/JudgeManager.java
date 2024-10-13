package com.yupi.luoj.judge;

import com.yupi.luoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.luoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yupi.luoj.judge.strategy.JudgeContext;
import com.yupi.luoj.judge.strategy.JudgeStrategy;
import com.yupi.luoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.luoj.model.entity.Question;
import com.yupi.luoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 *   判题管理
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
