package com.service.common.tree;

import com.core.commons.tree.TreeNode;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门树形结构
 *
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Setter
@Getter
public class DeptTree extends TreeNode {

    private static final long serialVersionUID = 2764058970186728117L;

    private String name;

    private Integer pos = 0;

}
