package icu.oo7.yyds.common.restful;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5535111126670819149L;

    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
        this.code = ResponseCode.RC202.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
