package icu.oo7.yyds.common.util.lambda;

import icu.oo7.yyds.common.util.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * 分支处理工具类
 *
 * @author peng.zhao
 */
public class BranchUtils {

    public static BranchHandler isTureOrFalse(boolean condition) {
        return ((trueHandler, falseHandler) -> {
            if (condition) {
                trueHandler.run();
            } else {
                falseHandler.run();
            }
        });
    }

    public static PresentOrElseHandler<?> isEmptyOrElse(String str) {
        return ((consumer, emptyAction) -> isTureOrFalse(StringUtils.isEmpty(str)).trueOrFalseHandle(emptyAction, () -> consumer.accept(str)));
    }

    public static ThrowExceptionFunction throwServiceException(boolean isOccurServiceException) {
        return (message -> {
            if (isOccurServiceException) {
                throw new ServiceException(500, message);
            }
        });
    }

}
