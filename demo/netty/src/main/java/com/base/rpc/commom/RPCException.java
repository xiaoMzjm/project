package com.base.rpc.commom;

/**
 * @author:小M
 * @date:2020/10/13 12:47 AM
 */
public class RPCException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public RPCException(String message, Throwable cause) {
        super(message, cause);
    }
    public RPCException(String message) {
        super(message);
    }
    public RPCException(Throwable cause) {
        super(cause);
    }
}
