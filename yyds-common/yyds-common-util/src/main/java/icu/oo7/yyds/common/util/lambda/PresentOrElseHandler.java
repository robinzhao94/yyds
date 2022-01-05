package icu.oo7.yyds.common.util.lambda;

import java.util.function.Consumer;

/**
 * 存在或不存在函数式接口
 * @author peng.zhao
 */
@FunctionalInterface
public interface PresentOrElseHandler<T> {

    /**
     * 值不为空时执行消费操作
     * 值为空时执行其他的操作
     */
    void presentOrElseHandle(Consumer<? super T> consumer, Runnable emptyAction);
}
