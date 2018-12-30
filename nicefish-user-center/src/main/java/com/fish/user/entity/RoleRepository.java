package com.fish.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    @Override
    RoleEntity findOne(Integer integer);
}
