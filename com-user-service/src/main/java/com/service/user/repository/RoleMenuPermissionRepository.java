package com.service.user.repository;

import com.service.user.entity.RoleMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface RoleMenuPermissionRepository extends JpaRepository<RoleMenuPermission, Integer> {

}
