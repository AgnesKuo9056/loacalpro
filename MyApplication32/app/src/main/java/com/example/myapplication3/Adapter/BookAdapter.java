package com.example.myapplication3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.Activity.BookEditActivity;
import com.example.myapplication3.Activity.MainActivity;
import com.example.myapplication3.Bean.Book;
import com.example.myapplication3.JDBCHelper;
import com.example.myapplication3.R;

import java.util.LinkedList;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context; //语境
    private List<Book> data; //数据集




    public BookAdapter(Context context){
        data = new LinkedList <>();
        this.context = context;
    }

    //getter and setter of data
    public List<Book> getBookList() {
        return data;
    }
    public void setBookList(List<Book> bookList) {
        this.data.clear();//因为不能改变this.data的地址,否则,会导致无法刷新数据
        this.data.addAll(bookList);//因此先把远本传入的数据clear掉 再把数据放进去
        notifyDataSetChanged();//再刷新到界面上
    }

    //重写父类的各个方法
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            //加载数据单项layout: book_item.xml
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, null);

            //根据绑定界面元素
            viewHolder = new ViewHolder();
            viewHolder.bookName = convertView.findViewById(R.id.book_bname);
            viewHolder.bookType = convertView.findViewById(R.id.book_type);
            viewHolder.course = convertView.findViewById(R.id.book_course);
            viewHolder.editBtn = convertView.findViewById(R.id.book_change_btm);
            viewHolder.deleteBtn = convertView.findViewById(R.id.book_delete_btm);


            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //向前端渲染数据
       final Book book = data.get(position);
        viewHolder.bookName.setText(book.getName());
        viewHolder.bookType.setText("类型："+book.getType());
        viewHolder.course.setText("课程："+book.getCourse());

//绑定点击事件

        //修改
        viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookEditActivity.class);
                intent.putExtra("book", book); //传值
                context.startActivity(intent); //跳转

                //下方出现消息显示修改成功

                Toast.makeText(context ,"修改书籍信息成功",Toast.LENGTH_SHORT).show();

            }
        });

        //删除
        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JDBCHelper Helper;
                Helper = JDBCHelper.getInstance();
                Helper.deleteBook(book);
                //下方出现消息显示修改成功
                Intent intent;
                intent= new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                Toast.makeText(context ,"删除书籍信息成功",Toast.LENGTH_SHORT).show();

            }
        });



        return convertView;
    }

    //ViewHolder类，关联前端界面元素
    class ViewHolder{
        TextView bookName, bookType, course;
        Button editBtn, deleteBtn;
    }
}
