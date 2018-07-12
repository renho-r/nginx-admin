package com.renho.nginx.agent.exception;

/**
 * @author: renho
 * @since: 1.0.0
 */
public class AgentException extends RuntimeException {

    private String errorMsg;

    public AgentException() {}

    public AgentException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
