package com.service.user.repository;

import com.service.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {

    User findUserByUsername(String username);

    User findUserByMobile(String mobile);

    User findUserByOpenId(String openId);

    User findUserByUserId(Integer userId);
}