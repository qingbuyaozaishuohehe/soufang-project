package com.xu.soufang.service.user;

import com.xu.soufang.entity.User;

/**
 * user 业务接口
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface IUserService {

    User findUserByName(String userName);

}
