package com.wang.dao;




import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {


    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;


    static {
        //静态代码块
        // 1.创建properties对象
        Properties properties = new Properties();
        // 2.通过加载器对象加载资源将文件作为字节流输入
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DRIVER = properties.getProperty("DRIVER");
        URL = properties.getProperty("URL");
        USERNAME = properties.getProperty("USERNAME");
        PASSWORD = properties.getProperty("PASSWORD");

    }


    //1、编写连接数据库的正确方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //1、加载驱动类
            Class.forName(DRIVER);
            // 2、获取连接对象
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("*******************"+conn.toString()+"*******************创建数据库连接成功");
        return conn;

    }

    // 2、编写查询公共类的方法
    public static ResultSet executeQuery(PreparedStatement pstmt, Connection conn, ResultSet rs, String sql, Object[] params) {
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 1; i < params.length; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    // 3、编写修改公共类的方法
    public static int executeUpdate(PreparedStatement pstmt, Connection conn, String sql, Object[] params) {
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 1; i < params.length; i++) {
                pstmt.setObject(i, params[i - 1]);

            }
            result = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    // 4、关闭连接
    public static boolean close(Connection conn,PreparedStatement pstmt,ResultSet rs){
        boolean flag = true;
        if(rs!= null){
            try {
                rs.close();
                rs = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }

        if (pstmt != null){
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag =false;
            }
        }

        if (conn != null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }

        return flag;

    }
}
