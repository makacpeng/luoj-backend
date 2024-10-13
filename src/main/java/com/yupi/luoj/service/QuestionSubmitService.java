package com.yupi.luoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.luoj.model.dto.question.QuestionQueryRequest;
import com.yupi.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.luoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.luoj.model.entity.Question;
import com.yupi.luoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.luoj.model.entity.User;
import com.yupi.luoj.model.vo.QuestionSubmitVO;
import com.yupi.luoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
     QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
