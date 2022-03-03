package icu.oo7.yyds.common.util.exception;

/**
 * 系统业务异常类
 *
 * @author peng.zhao
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5535111126670819149L;

    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
