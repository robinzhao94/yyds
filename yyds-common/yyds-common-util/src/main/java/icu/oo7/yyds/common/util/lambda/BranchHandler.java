package icu.oo7.yyds.common.util.lambda;

/**
 * if-else 分支处理接口
 * @author peng.zhao
 */
@FunctionalInterface
public interface BranchHandler {

    /**
     * 分支操作
     * @param trueHandler true 时要执行的操作
     * @param falseHandler false 时要执行的操作
     */
    void trueOrFalseHandle(Runnable trueHandler, Runnable falseHandler);

}
