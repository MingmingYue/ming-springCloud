package com.service.common.permission;

/**
 * 方法上标注需要的权限
 *
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface Functional {

    /**
     * 新增功能权限
     */
    String ADD = "add";

    /**
     * 更新、修改功能权限
     */
    String UPD = "upd";

    /**
     * 查看功能权限
     */
    String VIEW = "view";

    /**
     * 删除功能权限
     */
    String DEL = "del";

    /**
     * 导出功能权限
     */
    String EXPORT = "export";

    /**
     * 导入功能权限
     */
    String IMPORT = "import";
}