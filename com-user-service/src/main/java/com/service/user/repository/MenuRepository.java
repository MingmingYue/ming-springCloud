package com.service.user.repository;

import com.service.user.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>, QuerydslPredicateExecutor<Menu> {

}
