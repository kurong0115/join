package com.matrix.join.protocol;

/**
 * @ClassName JoinBizException
 * @Description 业务异常
 * @Author Administrator
 * @Date 2020/2/1 15:07
 * @Version 1.0
 */
public class JoinBizException extends RuntimeException{

    private static final long serialVersionUID = -8585326085578717092L;

    public JoinBizException() {
    }

    public JoinBizException(String message) {
        super(message);
    }

    public JoinBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoinBizException(Throwable cause) {
        super(cause);
    }

    public JoinBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
