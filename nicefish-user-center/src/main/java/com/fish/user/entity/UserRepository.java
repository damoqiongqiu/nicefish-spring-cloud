package com.fish.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByEmail(String email);
    UserEntity findByMobile(String mobile);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByMobileAndPassword(String mobile, String password);

    void deleteByEmail(String email);

    /**
     * User-->Role-->Permission 之间有两层映射，JPQL写起来比较麻烦，这里直接一条原生SQL查出User对应的Permission
     * @param email
     * @return
     */
    @Query(value="select distinct p.permission_name " +
            "from auth_user u " +
            "inner join auth_user_role r_u on u.id=r_u.user_id " +
            "inner join auth_role r on r_u.role_id=r.id " +
            "inner join auth_role_permission r_p on r_p.role_id=r.id " +
            "inner join auth_permission p on p.id=r_p.permission_id where u.email=?"
            ,nativeQuery = true)
    List<String> findAuthoritiesByEmail(String email);
}