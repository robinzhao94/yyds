package icu.oo7.yyds.common.restful;


import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询请求参数封装对象
 * @author peng.zhao
 */
@Getter
@Setter
public class PagingRequestParam<T> {

    /**
     * 页码
     */
    private int page;

    /**
     * 页记录数
     */
    private int pageSize;

    private T requestVo;

    public PagingRequestParam(int page, int pageSize, T requestVo) {
        this.page = page;
        this.pageSize = pageSize;
        this.requestVo = requestVo;
    }
}
