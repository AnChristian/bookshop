package com.example.smile.testboolr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.dao.BookDao;
import com.example.smile.testboolr.dao.CarDao;
import com.example.smile.testboolr.dao.MyDatabaseHelper;
import com.example.smile.testboolr.domain.Book;
import com.example.smile.testboolr.domain.Car;
import com.example.smile.testboolr.util.ToastUtil;

public class FragmentBook extends Fragment {
	//数据
//	private int[] imagesId={R.drawable.yourname};
//	private String author="新海诚";
//	private String publisher = "新华出版社";
//	private int price=45;
//	private int sales=30;
//	private int store=0;
//	private String information="小说《你的名字。》是同名动画电影的改编作。" +
//			"电影紧锣密鼓制作之际，新海诚在家和工作室创作了该部小说，时间份额各占一半。" +
//			"由于电影的作画导演安藤雅司的卖力工作减轻了新海诚的负担，使他得以抽出空来安心创作。" +
//			"由于该小说是电影的小说化，但完成之际电影尚未完成，" +
//			"所以新海诚戏称“电影和小说哪个才是原作还真不好说”。";
//	private String commont = "暂无评论";

	//匹配图片
	private int[] icons={R.drawable.ruguo_1,R.drawable.zisi_2,R.drawable.mengxiang_3, R.drawable.tx_4,R.drawable.pengran_5, R.drawable.suoyou_6, R.drawable.suanfa_7, R.drawable.nide_8};
	private String[] images={"ruguo_1","zisi_2","mengxiang_3","tx_4","pengran_5","suoyou_6","suanfa_7","nide_8"};

	//加载数据库
	private MyDatabaseHelper dbHelper;
	//查询数据
	private Book book;
	private String[] books ={"你的名字","算法的乐趣","所有明亮的地方","怦然心动","腾讯传","梦想会证明一切","自私的基因","如果没有遇见你"};


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

	dbHelper = new MyDatabaseHelper(this.getActivity(),"bookshop.db",null,1);

	//生成随机下标
	int index = (int) (Math.random() * books.length);
	String str=books[index];
	book = searchData(dbHelper, str);

	View view= inflater.inflate(R.layout.fragment_book , container, false);
	ImageView vimage = view.findViewById(R.id.bookname);
	TextView vauthor =view.findViewById(R.id.author);
	TextView vpublisher =view.findViewById(R.id.publisher);
	TextView vprice =view.findViewById(R.id.price);
	TextView vsales =view.findViewById(R.id.sales);
	TextView vstore =view.findViewById(R.id.store);
	TextView vinformation=view.findViewById(R.id.information);
	//TextView vcommont = view.findViewById(R.id.commont1);

	String image = book.getImage();
	int icon=0;
	//找与图片相匹配的id
	for(int j=0;j<images.length;j++){
		if(image.equals(images[j])){
			icon=j;
			break;
		}
	}

	vimage.setImageResource(icons[icon]);
	vauthor.setText("作者："+book.getAuthor());
	vpublisher.setText("出版社："+book.getPublisher());
	vprice.setText("价格："+book.getPrice());
	vsales.setText("销量："+book.getSales());
	vstore.setText("库存："+book.getStore());
	vinformation.setText(book.getInfo());
	//vcommont.setText(commont);

	//添加到购物车
	Button add_book = view.findViewById(R.id.add_book);
	add_book.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			//封闭数据
			Car car = new Car();
			car.setName(book.getName());
			car.setPrice(book.getPrice());
			car.setImage(book.getImage());
			car.setAuthor(book.getAuthor());
			//调用数据库
			new CarDao().insetCar(dbHelper,car);
			ToastUtil.showL(getActivity(),"添加购物车成功！！！");
		}
	});


	return view;

	//return inflater.inflate(R.layout.fragment_book, container, false);
}

	private Book searchData(MyDatabaseHelper dbHelper, String str) {
		return new BookDao().selectBook(dbHelper, str);
	}
}
