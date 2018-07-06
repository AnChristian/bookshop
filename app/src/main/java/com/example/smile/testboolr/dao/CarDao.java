package com.example.smile.testboolr.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.smile.testboolr.domain.Book;
import com.example.smile.testboolr.domain.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smile on 2018/7/3.
 */

public class CarDao {

    //查询购物车里所有
    public List<Car> selectCar(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //指明去查询Book表。
        Cursor cursor = db.query("car",null,null,null,null,null,null);
        List<Car> list;

        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()){
            list = new ArrayList<Car>();
            do {
                Car car = new Car();
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));

                int price = cursor.getInt(cursor.getColumnIndex("price"));

                String image = cursor.getString(cursor.getColumnIndex("image"));


                car.setName(name);
                car.setAuthor(author);

                car.setPrice(price);

                car.setImage(image);

                list.add(car);
            }while(cursor.moveToNext());
            cursor.close();
            return list;
        }


        return null;
    }


    //添加购物车
    public void insetCar(MyDatabaseHelper dbHelper,Car car){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",car.getName());
        cv.put("price",car.getPrice());
        cv.put("image",car.getImage());
        cv.put("author",car.getAuthor());
        long flag = db.insert("car", null, cv);
        Log.i("flag",flag+"");

    }

    //清空购物车
    public void deleteCar(MyDatabaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from car");
    }

}
