package com.example.smile.testboolr.dao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.smile.testboolr.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smile on 2018/7/2.
 */

public class BookDao {

    //模糊查找
    public List<Book> selectBookList(MyDatabaseHelper dbHelper,String key){

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //指明去查询Book表。
            Cursor cursor = db.query("book",null," name like ?",new String[]{"%"+key+"%"},null,null,null);
            List<Book> list;

            //调用moveToFirst()将数据指针移动到第一行的位置。
            if (cursor.moveToFirst()){
                    list = new ArrayList<Book>();
                do {
                    Book book = new Book();
                    //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
                    int sales = cursor.getInt(cursor.getColumnIndex("sales"));
                    int price = cursor.getInt(cursor.getColumnIndex("price"));
                    String store = cursor.getString(cursor.getColumnIndex("store"));
                    String image = cursor.getString(cursor.getColumnIndex("image"));
                    String info = cursor.getString(cursor.getColumnIndex("info"));

                    book.setId(id);
                    book.setName(name);
                    book.setAuthor(author);
                    book.setPublisher(publisher);
                    book.setSales(sales);
                    book.setPrice(price);
                    book.setStore(store);
                    book.setImage(image);
                    book.setInfo(info);

                    list.add(book);
                }while(cursor.moveToNext());
                cursor.close();
                return list;
            }


        return null;
    };

    //查询所有
    public List<Book> selectBookList(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //指明去查询Book表。
        Cursor cursor = db.query("book",null,null,null,null,null,null);
        List<Book> list;

        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()){
            list = new ArrayList<Book>();
            do {
                Book book = new Book();
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
                int sales = cursor.getInt(cursor.getColumnIndex("sales"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                String store = cursor.getString(cursor.getColumnIndex("store"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                String info = cursor.getString(cursor.getColumnIndex("info"));

                book.setId(id);
                book.setName(name);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setSales(sales);
                book.setPrice(price);
                book.setStore(store);
                book.setImage(image);
                book.setInfo(info);

                list.add(book);
            }while(cursor.moveToNext());
            cursor.close();
            return list;
        }


        return null;
    };


    //查找单个
    public Book selectBook(MyDatabaseHelper dbHelper,String key){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //指明去查询Book表。
       // Cursor cursor = db.query("book",null," name = ",new String[]{key},null,null,null);
        String sql = "select * from book where name = ? ";
        Cursor cursor= db.rawQuery(sql,new String[]{key});
       // Cursor cursor1 = db.rawQuery("select * from Book where name =",new String[]{key});
        Book book;

        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()){

            do {
                book = new Book();
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
                int sales = cursor.getInt(cursor.getColumnIndex("sales"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                String store = cursor.getString(cursor.getColumnIndex("store"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                String info = cursor.getString(cursor.getColumnIndex("info"));

                book.setId(id);
                book.setName(name);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setSales(sales);
                book.setPrice(price);
                book.setStore(store);
                book.setImage(image);
                book.setInfo(info);


            }while(cursor.moveToNext());
            cursor.close();
            return book;
        }


        return null;
    };
}
