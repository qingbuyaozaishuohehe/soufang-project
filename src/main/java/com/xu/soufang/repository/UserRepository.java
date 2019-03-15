package com.xu.soufang.repository;

import com.xu.soufang.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 启动类
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface UserRepository extends CrudRepository<User,String> {

    /**
     * 查找用户 根据姓名
     * @param username
     * @return
     */
    User findByName(String username);
}
