package icu.oo7.yyds.admin.entity;

import lombok.Data;

/**
 * 系统部门实体
 */
@Data
public class SysDept {

    private Long id;

    private String name;

    private String parentId;

    private String treePath;

    private Integer sort;

    private Integer status;

    private Boolean isDeleted;

}
