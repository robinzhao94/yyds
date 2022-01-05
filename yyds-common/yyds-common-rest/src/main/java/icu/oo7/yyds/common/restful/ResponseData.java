package icu.oo7.yyds.common.restful;

import lombok.Data;

import java.time.Instant;

/**
 * Restful 响应工具类
 * @author peng.zhao
 * @param <T>
 */
@Data
public class ResponseData<T> {

    /**
     * 状态码
     */
    private int status;

    /**
     * 状态消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 接口调用时间
     */
    private long timestamp;

    public ResponseData () {
        this.timestamp = Instant.now().toEpochMilli();
    }

    /**
     * 处理成功响应
     */
    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(ResponseCode.RC200.getCode());
        responseData.setMessage(ResponseCode.RC200.getMessage());
        responseData.setData(data);
        return responseData;
    }

    /**
     * 处理失败响应
     */
    public static <T> ResponseData<T> failure(int code, String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(code);
        responseData.setMessage(message);
        return responseData;
    }

}
