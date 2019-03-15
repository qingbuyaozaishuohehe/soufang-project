package com.xu.soufang.repository;


import com.xu.soufang.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色数据DAO
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface RoleRepository extends CrudRepository<Role,Integer> {

    List<Role> findRolesByUserId(Integer userId);

}
