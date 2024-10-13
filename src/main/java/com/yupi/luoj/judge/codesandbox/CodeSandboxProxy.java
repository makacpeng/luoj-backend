package com.yupi.luoj.judge.codesandbox;

import com.yupi.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.luoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("沙箱代码请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("沙箱代码响应信息：" + executeCodeResponse .toString());
        return executeCodeResponse;
    }
}
