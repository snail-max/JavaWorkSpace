package com.wang.dao.user;


import com.wang.pojo.User;

import java.sql.Connection;

public interface UserDao {
    /**
     * 得到的进行登录的用户
     * @param conn:数据库连接
     * @param userCode: 通过用户名userCode查找用户数据
     * @return
     */
    public User getLoginUserInfo(Connection conn, String userCode);
}
