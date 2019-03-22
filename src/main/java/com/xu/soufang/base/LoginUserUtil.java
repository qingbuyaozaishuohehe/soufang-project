package com.xu.soufang.base;


import com.xu.soufang.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author xuzhenqin 19/03/22
 * 登陆用户工具类
 */
public class LoginUserUtil {

    public static User load(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof User){
            return (User) principal;
        }
        return null;
    }

    public static Integer getLoginUserId(){
        User user = load();
        if (user == null){
            return -1;
        }
        return user.getId();
    }
}
