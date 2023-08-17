package com.example.myapplication3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication3.Bean.Book;
import com.example.myapplication3.JDBCHelper;
import com.example.myapplication3.R;

import org.apache.commons.lang3.StringUtils;


public class BookInsertActivity extends BaseActivity {
    private Book book ;
    private EditText nameText;
    private EditText typeText;
    private EditText courseText;
    private Button insertbutton;
    private JDBCHelper Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_insert);

        //得到页面传递的参数
       // book = (Book) getIntent().getSerializableExtra("book");
        //System.out.println(book.getName());

        //绑定元素
        nameText = findViewById(R.id.book_new_bname);
        typeText = findViewById(R.id.book_new_type);
        courseText = findViewById(R.id.book_new_course);
        insertbutton = findViewById(R.id.book_insert_bym);



        //初始化页面
        //nameText.setText(book.getName());
        // typeText.setText(book.getType());
        //courseText.setText(book.getCourse());
    }

    public void insertbook(View view) {
        book = new Book();
        book = insertBookInfo();

        Helper = JDBCHelper.getInstance();
        Helper.addBook(book);
        //下方出现消息显示修改成功
        Intent intent;
        intent= new Intent();
        intent.setClass(BookInsertActivity.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(BookInsertActivity.this,"增添书籍信息成功",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private Book insertBookInfo() {
        String name = nameText.getText().toString().trim();
        String type = typeText.getText().toString().trim();
        String course = courseText.getText().toString().trim();

        if(StringUtils.isNoneBlank(name) && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(course)){
            book.setName(name);
            book.setType(type);
            book.setCourse(course);
        }
        else {
            return null;
        }

        return book;
    }

    @Override
    protected int setLayout() {
        return 0;
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public void onClick(View view) {

    }
}
