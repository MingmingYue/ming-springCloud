package com.service.common.jpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public abstract class JPAFactoryImpl {

    // JPA查询工厂
    protected JPAQueryFactory queryFactory;

    @Autowired
    protected EntityManager em;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(em);
    }
}
