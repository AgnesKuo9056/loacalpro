package com.example.myapplication3;

import android.os.Handler;
import android.util.Log;

import com.example.myapplication3.Bean.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static android.net.wifi.WifiEnterpriseConfig.Eap.PWD;

public class JDBCHelper{
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://www.ylxteach.net:3366/ydbc2021";
    private final String USER = "Administrator";
    private final String PWD = "XWClassroom20202023";
    //工具类，采用单例模式
    private static JDBCHelper instance;

    private JDBCHelper() {
        //创建数据库
        try {
            Class.forName(DRIVER);
            Log.d("调试输出","加载了数据库驱动");
            Connection conn = DriverManager.getConnection(URL, USER, PWD);

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JDBCHelper getInstance() {
        if(instance == null){
            instance = new JDBCHelper();
        }
        return instance;
    }


    //增加数据库
    public void addBook(final Book book){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(DRIVER);
                    Connection conn = DriverManager.getConnection(URL, USER, PWD);
                    PreparedStatement statement = conn.prepareStatement(
                            "INSERT INTO gpy_user (name, type, course) VALUES (? , ?, ?)");
                    statement.setString(1, book.getName());
                    statement.setString(2, book.getType());
                    statement.setString(3, book.getCourse());
                    statement.executeUpdate();
                    statement.close();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //修改或更新数据库
    public void updateBook(final Book book){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(DRIVER);
                    Connection conn = DriverManager.getConnection(URL, USER, PWD);
                    PreparedStatement statement = conn.prepareStatement(
                            "update gpy_user  set name=? ,type=? ,course=? where _id=?");
                    statement.setString(1, book.getName());
                    statement.setString(2, book.getType());
                    statement.setString(3, book.getCourse());
                    statement.setInt(4,book.getId());
                    statement.executeUpdate();
                    statement.close();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //delete数据库
    public void deleteBook(final Book book){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(DRIVER);
                    Connection conn = DriverManager.getConnection(URL, USER, PWD);
                    PreparedStatement statement = conn.prepareStatement(
                            "delete from gpy_user where _id=?");
                    statement.setInt(1, book.getId());
                    statement.executeUpdate();
                    statement.close();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void listAllBook(final Handler handler){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Book> list = new LinkedList<>();
                    Class.forName(DRIVER);
                    Connection conn = DriverManager.getConnection(URL, USER, PWD);
                    Statement statement = (Statement) conn.createStatement();
                    ResultSet res = statement.executeQuery("SELECT * FROM gpy_user");
                    Log.d("调试输出","向jdbc获取数据");
                    while (res.next()){
                        Book book = new Book();
                        book.setId(res.getInt("_id"));
                        book.setName(res.getString("name"));
                        book.setType(res.getString("type"));
                        book.setCourse(res.getString("course"));
                        list.add(book);
                    }
                    statement.close();
                    conn.close();

                    //利用handler向UI线程发送查询数据
                     handler.obtainMessage(1, list).sendToTarget();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
