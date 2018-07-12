package com.renho.nginx.agent.support.service.initcomponent;

import com.renho.nginx.agent.model.InitContent;

/**
 * @author: renho
 * @since: 1.0.0
 */
public interface INeedInit {

    String SERVER_PATH = "agent/servers";
    String LOCATION_PATH = "agent/servers/locations";
    void make(InitContent initContent);

}
