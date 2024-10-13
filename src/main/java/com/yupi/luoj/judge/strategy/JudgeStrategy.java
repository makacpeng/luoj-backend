package com.yupi.luoj.judge.strategy;

import com.yupi.luoj.model.dto.questionsubmit.JudgeInfo;

/**
 *  判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
