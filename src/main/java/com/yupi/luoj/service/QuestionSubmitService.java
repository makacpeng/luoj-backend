package com.yupi.luoj.service;

import com.yupi.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.luoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.luoj.model.entity.User;

/**
* @author chendepeng
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-10-03 01:19:31
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
