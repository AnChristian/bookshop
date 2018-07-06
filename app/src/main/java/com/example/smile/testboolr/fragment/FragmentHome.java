package com.example.smile.testboolr.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.dao.BookDao;
import com.example.smile.testboolr.dao.MyDatabaseHelper;
import com.example.smile.testboolr.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentHome extends Fragment{
	//加载数据库
	private MyDatabaseHelper dbHelper;
	//查询数据
	private List<Book> booklist;

	private ListView listView;

	private int[] icons={R.drawable.ruguo_1,R.drawable.zisi_2,R.drawable.mengxiang_3, R.drawable.tx_4,R.drawable.pengran_5, R.drawable.suoyou_6, R.drawable.suanfa_7, R.drawable.nide_8};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		dbHelper = new MyDatabaseHelper(this.getActivity(),"bookshop.db",null,1);

		//查询数据
		searchData(dbHelper);

		View view= inflater.inflate(R.layout.fragment_home , container, false);
		listView = (ListView)view.findViewById(R.id.listView1);
		List<Map<String, Object>> list=getData();
		listView.setAdapter(new homeListViewAdapter(getActivity(), list));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Log.i("adapterView",adapterView+" ");
				Log.i("View",view+" ");
				int id = view.getId();

				Log.i("i",i+" ");
				Log.i("l",l+" ");
			}
		});
		return view;


	}

	private void searchData(MyDatabaseHelper dbHelper) {
		booklist = new ArrayList<Book>();
		booklist = new BookDao().selectBookList(dbHelper);
	}

	public List<Map<String, Object>> getData(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < booklist.size(); i++) {
			Book book = booklist.get(i);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("face", icons[i]);
			map.put("name", book.getName());
			map.put("mark", book.getAuthor());
			list.add(map);
		}
		return list;
	}

}


class homeListViewAdapter extends BaseAdapter {

	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public homeListViewAdapter(Context context,List<Map<String, Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	/**
	 * 组件集合，对应list.xml中的控件
	 * @author Administrator
	 */
	public final class Zujian{
		public ImageView face;
		public TextView name;

		public TextView mark;
	}
	@Override
	public int getCount() {
		return data.size();
	}
	/**
	 * 获得某一位置的数据
	 */
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}
	/**
	 * 获得唯一标识
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Zujian zujian=null;
		if(convertView==null){
			zujian=new Zujian();
			//获得组件，实例化组件
			convertView=layoutInflater.inflate(R.layout.fragment_home_listitem, null);
			zujian.face=(ImageView)convertView.findViewById(R.id.face);
			zujian.name=(TextView)convertView.findViewById(R.id.name);
			zujian.mark=(TextView) convertView.findViewById(R.id.mark);

			convertView.setTag(zujian);
		}else{
			zujian=(Zujian)convertView.getTag();
		}
		//绑定数据
		zujian.face.setBackgroundResource((Integer)data.get(position).get("face"));
		zujian.name.setText((String)data.get(position).get("name"));
		zujian.mark.setText((String)data.get(position).get("mark"));
		return convertView;
	}

}
