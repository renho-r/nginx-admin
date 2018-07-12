package com.renho.nginx.agent.support.service;

import com.renho.nginx.agent.model.InitContent;
import com.renho.nginx.agent.model.InitResult;

/**
 * @author: renho
 * @since: 1.0.0
 */
public interface INginxAgentSupport {

    InitResult init(InitContent initContent);

    InitContent initContentMyself();
}
