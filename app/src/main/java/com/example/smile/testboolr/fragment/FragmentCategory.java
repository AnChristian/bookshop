package com.example.smile.testboolr.fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.dao.BookDao;
import com.example.smile.testboolr.dao.MyDatabaseHelper;
import com.example.smile.testboolr.domain.Book;
import com.example.smile.testboolr.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentCategory extends Fragment {
	//加载数据库
	private MyDatabaseHelper dbHelper;
	//查询数据
	private List<Book> bookList;

	private View view;

	private SearchView mSearchView;
	private ListView listView;

	private int[] icons={R.drawable.ruguo_1,R.drawable.zisi_2,R.drawable.mengxiang_3, R.drawable.tx_4,R.drawable.pengran_5, R.drawable.suoyou_6, R.drawable.suanfa_7, R.drawable.nide_8};
	private String[] image={"ruguo_1","zisi_2","mengxiang_3","tx_4","pengran_5","suoyou_6","suanfa_7","nide_8"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		dbHelper = new MyDatabaseHelper(this.getActivity(),"bookshop.db",null,1);

		view= inflater.inflate(R.layout.fragment_category_main , container, false);

		mSearchView = view.findViewById(R.id.search);
		//搜索框设置
		mSearchView.setIconifiedByDefault(true);
		mSearchView.setSubmitButtonEnabled(true);
		mSearchView.onActionViewExpanded();
		mSearchView.setBackgroundColor(0x22ff00ff);
		mSearchView.setIconifiedByDefault(true);
		//搜索框事件
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			//用户点击确定时 开始搜索
			@Override
			public boolean onQueryTextSubmit(String s) {

				//查询数据
				boolean flag = searchData(dbHelper, s);

				if(flag){
					listView = (ListView)view.findViewById(R.id.listView1);
					List<Map<String, Object>> list=getData();
					listView.setAdapter(new categoryListViewAdapter(getActivity(), list));
					listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
							TextView name = view.findViewById(R.id.name);
							CharSequence text = name.getText();


						}
					});
				}else{
					ToastUtil.showL(getActivity(),"你输入的书名不存在");
				}



				return false;
			}

			@Override
			public boolean onQueryTextChange(String s) {

				return false;
			}
		});





		return view;


	}

	private boolean searchData(MyDatabaseHelper dbHelper,String s) {
		bookList = new BookDao().selectBookList(dbHelper, s);
		if(bookList == null)
			return false;
		return true;
	}

	public List<Map<String, Object>> getData(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

		int icon=0;
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			String str1 =book.getImage();

			//找与图片相匹配的id
			for(int j=0;j<image.length;j++){
				if(str1.equals(image[j])){

					icon=j;
					break;
				}
			}


			Map<String, Object> map=new HashMap<String, Object>();
			map.put("face", icons[icon]);
			map.put("name", book.getName());
			map.put("mark", book.getAuthor() );
			list.add(map);
		}
		return list;
	}

}


 class categoryListViewAdapter extends BaseAdapter {

	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public categoryListViewAdapter(Context context,List<Map<String, Object>> data){
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
			convertView=layoutInflater.inflate(R.layout.fragment_category_listitem, null);
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