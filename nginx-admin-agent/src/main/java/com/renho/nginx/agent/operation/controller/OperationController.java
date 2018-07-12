package com.renho.nginx.agent.operation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: renho
 * @since: 1.0.0
 */
@RestController
@RequestMapping("/api/nginx")
public class OperationController {

//    @PostMapping("start")
//    public NginxOperationResponse start() throws InterruptedException, TimeoutException, IOException {
//        String output = new ProcessExecutor().command("nginx")
//                .readOutput(true).execute()
//                .outputUTF8();
//
//        NginxOperationResponse nginxOperationResponse = new NginxOperationResponse();
//        nginxOperationResponse.setMsg(output);
//        return nginxOperationResponse;
//    }

}
