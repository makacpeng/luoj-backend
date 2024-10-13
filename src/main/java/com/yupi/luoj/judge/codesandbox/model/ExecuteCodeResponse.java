package com.yupi.luoj.judge.codesandbox.model;

import com.yupi.luoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> outputList;

    /**
     *  接口返回信息
     */
    private String message;

    /**
     *  执行状态
     */
    private Integer status;

    /**
     *  判题信息
     */
    private JudgeInfo judgeInfo;


}
