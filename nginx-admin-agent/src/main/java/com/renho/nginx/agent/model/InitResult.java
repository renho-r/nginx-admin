package com.renho.nginx.agent.model;

/**
 * @author: renho
 * @since: 1.0.0
 */
public class InitResult {

    private String status;
    private String errorMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
