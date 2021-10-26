package com.wang.service.user;


import com.wang.dao.BaseDao;
import com.wang.dao.user.UserDao;
import com.wang.dao.user.UserDaoImpl;
import com.wang.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UserServiceImpl implements UserService {
    private UserDao userDao; // 业务需要使用dao，所以将dao作为一个私有成员使用

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

//    public UserServiceImpl() {
//        this.userDao = userDao;
//    }

    @Override
    public User login(String userCode, String userPassWord) {
        Connection conn = null;
        User user = null;

        conn = BaseDao.getConnection(); // 获取数据库连接对象

        user = userDao.getLoginUserInfo(conn, userCode);
        BaseDao.close(conn, null, null);

        return user;

    }


    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "123456");
        System.out.println(admin.getUserPassword());
    }

}