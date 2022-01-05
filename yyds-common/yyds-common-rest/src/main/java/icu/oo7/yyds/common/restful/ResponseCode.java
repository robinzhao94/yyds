package icu.oo7.yyds.common.restful;

/**
 * Restful接口统一响应码
 * @author peng.zhao
 */
public enum ResponseCode {

    /**
     * 200
     */
    RC200(200, "处理成功"),

    /**
     * 202
     */
    RC202(202, "处理失败"),

    /**
     * 204
     */
    RC204(204, "无内容"),

    /**
     * 205
     */
    RC205(205, "服务开启限流保护，请稍后再试"),

    /**
     * 206
     */
    RC206(206, "服务开启降级保护，请稍后再试"),

    /**
     * 207
     */
    RC207(207, "热点参数限流，请稍后再试"),

    /**
     * 400
     */
    RC400(400, "请求参数校验不通过"),

    /**
     * 401
     */
    RC401(401, "请求要求用户的身份认证"),

    /**
     * 402
     */
    RC402(402, "用户身份认证不通过"),

    /**
     * 403
     */
    RC403(403, "资源权限不足，请联系管理员授予权限"),

    /**
     * 资源不存在
     */
    RC404(404, "资源不存在"),

    /**
     * 访问令牌不合法
     */
    RC405(405, "访问令牌不合法"),

    /**
     * 服务异常
     */
    RC500(500, "服务器内部异常，无法完成处理"),
    ;

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应状态消息
     */
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
