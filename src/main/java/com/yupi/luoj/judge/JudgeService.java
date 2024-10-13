package com.yupi.luoj.judge;

import com.yupi.luoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.luoj.model.entity.QuestionSubmit;
import com.yupi.luoj.model.vo.QuestionSubmitVO;

public interface JudgeService {

    QuestionSubmit doJudge(long questionSubmitId);
}
