package com.service.user.repository;

import com.service.user.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}