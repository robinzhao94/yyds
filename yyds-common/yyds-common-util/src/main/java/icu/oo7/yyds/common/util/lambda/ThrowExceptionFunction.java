package icu.oo7.yyds.common.util.lambda;

/**
 * 抛异常接口
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 抛出异常信息
     */
   void throwMessage(String message);

}
