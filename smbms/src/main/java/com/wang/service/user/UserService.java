package com.wang.service.user;


import com.wang.pojo.User;

public interface UserService {
    /**
     * 用户的登录身份验证
     * @param userCode ：用户账号
     * @param  userPassWord 用户密码在，注意密码判断在service层中判断
     *                     Dao只进行简单的数据库的操作，没有代码的逻辑，在service层中
     *                      只是接受和转发请求和控制视图的跳转，而对于service就是来实现业务代码的逻辑
     */
    public User login(String userCode, String userPassWord);
}
