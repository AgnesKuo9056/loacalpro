package com.example.myapplication3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Bean.Book;
import com.example.myapplication3.Adapter.BookAdapter;
import com.example.myapplication3.JDBCHelper;
import com.example.myapplication3.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BookAdapter adapter;
    private ListView listView;
    Button insertbyn;
    private Handler handler;
    private JDBCHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建新的数据库
        //bookDB = bookDB.getInstance(this);

        //添加点击事件
       insertbyn= findViewById(R.id.book_insert_bym);
       insertbyn.setOnClickListener(this);
      //创建分配器对象
        adapter = new BookAdapter(this);

        //连接数据库
        helper = JDBCHelper.getInstance();

        //给前端 ListView 绑定 Adaptor
        listView = findViewById(R.id.book_list);
        listView.setAdapter(adapter);
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        //handler接收消息并处理
                        adapter.setBookList((List<Book>) msg.obj);
                        break;
                }

            }
        };


        //初始化界面
        initView();

    }

    private void initView() {
        //获取数据
        helper.listAllBook(handler);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this , BookInsertActivity.class);
        //   intent.putExtra("book", book);//传值
        MainActivity.this.startActivity(intent); //跳转

    }

}


