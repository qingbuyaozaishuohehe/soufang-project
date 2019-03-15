package com.xu.soufang.service.user.userServiceImpl;

import com.xu.soufang.entity.Role;
import com.xu.soufang.entity.User;
import com.xu.soufang.repository.RoleRepository;
import com.xu.soufang.repository.UserRepository;
import com.xu.soufang.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * user 业务接口实现类
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);

        if (user == null){
            return null;
        }

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()){
            throw new DisabledException("非法权限");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
