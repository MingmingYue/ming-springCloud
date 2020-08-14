package com.service.user.repository;

import com.service.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, QuerydslPredicateExecutor<UserRole> {

}
