package icu.oo7.yyds.common.restful;

import icu.oo7.yyds.common.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * @author peng.zhao
 */
@Slf4j
@RestControllerAdvice
public class RestfulExceptionHandler {

    /**
     * 默认全局异常处理方法
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<String> handleException(Exception e) {
        log.error("服务器异常：{}", e.getMessage(), e);
        return ResponseData.failure(ResponseCode.RC500.getCode(), e.getMessage());
    }

    /**
     * 全局处理404，生效需更新引用模块的mvc配置
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseData<Void> handleNotFound(NoHandlerFoundException e) {
        log.error("资源接口不存在：{}", e.getMessage(), e);
        return ResponseData.failure(ResponseCode.RC404.getCode(), ResponseCode.RC404.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Map<String, String>> handleValidArguments(MethodArgumentNotValidException e) {
        log.error("参数校验异常信息：{}, 异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> error = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> error.put(item.getField(), item.getDefaultMessage()));
        ResponseData<Map<String, String>> responseData = ResponseData.failure(ResponseCode.RC400.getCode(), ResponseCode.RC400.getMessage());
        responseData.setData(error);
        return responseData;
    }

    /**
     * 业务异常处理方法
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseData<Void> handleServiceException(ServiceException e) {
        log.error("业务异常：{}", e.getMessage(), e);
        return ResponseData.failure(e.getCode(), e.getMessage());
    }

}