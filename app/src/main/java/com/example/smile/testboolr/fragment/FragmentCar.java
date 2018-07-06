package com.example.smile.testboolr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.dao.CarDao;
import com.example.smile.testboolr.dao.MyDatabaseHelper;
import com.example.smile.testboolr.domain.Car;
import com.example.smile.testboolr.domain.ShoppingCartBean;
import com.example.smile.testboolr.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class FragmentCar extends Fragment implements View.OnClickListener {

	Button btnBack;
	//全选
	CheckBox ckAll;
	//总额
	TextView tvShowPrice;
	//结算
	TextView tvSettlement;
	//编辑
	TextView btnEdit;//tv_edit
	ListView list_shopping_cart;
	private FragmentCarAdapter shoppingCartAdapter;
	private boolean flag = false;
	private List<ShoppingCartBean> shoppingCartBeanList ;
	private boolean mSelect;
	private double totalPrice = 0.00;// 购买的商品总价
	private int totalCount = 0;// 购买的商品总数量
	private View view;

	//加载数据库
	private MyDatabaseHelper dbHelper;
	//查询数据
	private List<Car> cars;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

	dbHelper = new MyDatabaseHelper(this.getActivity(),"bookshop.db",null,1);

	//查询数据


	view=inflater.inflate(R.layout.fragment_car,container,false);
	Intent intent = getActivity().getIntent();
	String user = intent.getStringExtra("user");
	//判断是否有用户登陆
	if(user!=null){
		searchData(dbHelper);
		initview();
	}else{
		ToastUtil.showS(getActivity(),"请先登陆");

	}


	return view;
}

//查car表中的数据
	private void searchData(MyDatabaseHelper dbHelper) {
		cars = new CarDao().selectCar(dbHelper);
	}

	private void initview() {

		ckAll= (CheckBox) view.findViewById(R.id.ck_all);
		tvShowPrice= (TextView) view.findViewById(R.id.tv_show_price);
		tvSettlement= (TextView) view.findViewById(R.id.tv_settlement);
		btnEdit= (TextView) view.findViewById(R.id.bt_header_right);
		list_shopping_cart= (ListView) view.findViewById(R.id.list_shopping_cart);



		initData();
	}

	private void initData() {

	shoppingCartBeanList = new ArrayList<>();
	//购物车中有数据
	if(cars!=null){

		for (int i = 0; i < cars.size(); i++) {
			ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
			Car car = cars.get(i);
			shoppingCartBean.setShoppingName(car.getName());
			shoppingCartBean.setAttribute(car.getAuthor());
			shoppingCartBean.setPrice(car.getPrice());
			shoppingCartBean.setDressSize(20);
			shoppingCartBean.setId(i);
			shoppingCartBean.setImageUrl(car.getImage());
			shoppingCartBean.setCount(1);
			//shoppingCartBean.setImageUrl("https://img.alicdn.com/bao/uploaded/i2/TB1YfERKVXXXXanaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
			shoppingCartBeanList.add(shoppingCartBean);
		}
		shoppingCartAdapter = new FragmentCarAdapter(getActivity());
//		shoppingCartAdapter.setCheckInterface(this);
//		shoppingCartAdapter.setModifyCountInterface(this);
		list_shopping_cart.setAdapter(shoppingCartAdapter);
		shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);

		btnEdit.setOnClickListener(this);
		ckAll.setOnClickListener(this);
		tvSettlement.setOnClickListener(this);
	}else{
		ToastUtil.showL(getActivity(),"购物车中无挑选的书籍");
	}

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			//全选按钮
			case R.id.ck_all:
				if (shoppingCartBeanList.size() != 0) {
					if (ckAll.isChecked()) {
						for (int i = 0; i < shoppingCartBeanList.size(); i++) {
							shoppingCartBeanList.get(i).setChoosed(true);
						}
						shoppingCartAdapter.notifyDataSetChanged();
					} else {
						for (int i = 0; i < shoppingCartBeanList.size(); i++) {
							shoppingCartBeanList.get(i).setChoosed(false);
						}
						shoppingCartAdapter.notifyDataSetChanged();
					}
				}
				statistics();
				break;
			case R.id.bt_header_right:
				flag = !flag;
				if (flag) {
					btnEdit.setText("完成");
					//shoppingCartAdapter.isShow(false);
				} else {
					btnEdit.setText("编辑");
					//shoppingCartAdapter.isShow(true);
				}
				break;
			case R.id.tv_settlement: //结算
				lementOnder();
				break;

		}
	}

	private void statistics() {
		totalCount = 0;
		totalPrice = 0.00;
		for (int i = 0; i < shoppingCartBeanList.size(); i++) {
			ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
			if (shoppingCartBean.isChoosed()) {
				totalCount++;
				totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
			}
		}
		tvShowPrice.setText("合计:" + totalPrice);
		tvSettlement.setText("结算(" + totalCount + ")");
	}

	//结算方法
	private void lementOnder() {
		//选中的需要提交的商品清单
		for (ShoppingCartBean bean:shoppingCartBeanList ){
			boolean choosed = bean.isChoosed();
			if (choosed){
				String shoppingName = bean.getShoppingName();
				int count = bean.getCount();
				double price = bean.getPrice();
				int size = bean.getDressSize();
				String attribute = bean.getAttribute();
				int id = bean.getId();
				Log.d("",id+"----id---"+shoppingName+"---"+count+"---"+price+"--size----"+size+"--attr---"+attribute);
			}
		}
		ToastUtil.showL(getActivity(),"总价："+totalPrice+"请到订单页面支付");
		new CarDao().deleteCar(dbHelper);
		shoppingCartBeanList.clear();
		//Log.i("price","总价:"+totalPrice);

	}
}
