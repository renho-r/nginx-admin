package com.renho.nginx.agent.support.controller;

import com.renho.nginx.agent.exception.AgentException;
import com.renho.nginx.agent.model.InitContent;
import com.renho.nginx.agent.model.InitResult;
import com.renho.nginx.agent.model.NginxEnv;
import com.renho.nginx.agent.model.SimpleStatusType;
import com.renho.nginx.agent.model.request.InitAgentRequest;
import com.renho.nginx.agent.model.response.InitAgentResponse;
import com.renho.nginx.agent.support.service.INginxAgentSupport;
import com.renho.nginx.agent.util.NginxEnvConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: renho
 * @since: 1.0.0
 */
@RestController
@RequestMapping("/api/agent")
public class AgentSupportController {

    @Autowired
    private INginxAgentSupport nginxAgentSupport;

    @PostMapping("init")
    public InitAgentResponse init(@RequestBody InitAgentRequest initRequest) {
        InitAgentResponse initAgentResponse = new InitAgentResponse();
        try {
            NginxEnv nginxEnv = NginxEnvConfigUtil.getNginxEnvConfig(initRequest);

            InitContent initContent = new InitContent();
            initContent.setNginxEnv(nginxEnv);
            initContent.setInitDirs(initRequest.getInitDirs());
            initContent.setInitFiles(initRequest.getInitFiles());
            InitResult initResult = nginxAgentSupport.init(initContent);
            initAgentResponse.setStatus(initResult.getStatus());
        } catch (AgentException e) {
            e.printStackTrace();
        }
        return initAgentResponse;
    }

    @PostMapping("initself")
    public InitAgentResponse init() {
        InitAgentResponse initAgentResponse = new InitAgentResponse();
        try {
            InitContent initContent = nginxAgentSupport.initContentMyself();
            InitResult initResult = nginxAgentSupport.init(initContent);
            initAgentResponse.setStatus(initResult.getStatus());
        } catch (AgentException e) {
            e.printStackTrace();
            initAgentResponse.setStatus(SimpleStatusType.FAIL);
            initAgentResponse.setMsg(e.getErrorMsg());
        }
        return initAgentResponse;
    }

}
