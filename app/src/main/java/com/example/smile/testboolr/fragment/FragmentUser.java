package com.example.smile.testboolr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smile.testboolr.LoginActivity;
import com.example.smile.testboolr.MainActivity;
import com.example.smile.testboolr.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentUser extends Fragment {
	private TextView mTvAll;
	private TextView mTvWait;
	private LinearLayout mLl;
	private ViewPager mVp;
	private ImageView h_head;
	private boolean flag=true;
	private String user="fail";
	private List<Fragment> list = new ArrayList<>();
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view= inflater.inflate(R.layout.fragment_user , container, false);

	Intent intent = getActivity().getIntent();
	user = intent.getStringExtra("user");
	Log.i("user _user: ",user+"");

	//用户点击登陆
	h_head=view.findViewById(R.id.h_head);
	h_head.setImageResource(R.drawable.login_button);
	h_head.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			if(flag){
				Intent intent = new Intent(getActivity(),LoginActivity.class);

				startActivity(intent);
				getActivity().finish();
			}

		}
	});

	if(user!=null && user.equals("success")){
		flag = false;
		initView(view);
		h_head.setImageResource(R.drawable.cute_81x81_ps);
		list.add(new AllFragment());
		list.add(new WaitFragment());
		mVp.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
	}


	return view;
}
	private void initView(View view) {
		mTvAll = (TextView)view.findViewById(R.id.tvAll);
		mTvWait = (TextView) view.findViewById(R.id.tvWait);
		mLl = (LinearLayout) view.findViewById(R.id.ll);
		mVp = (ViewPager) view.findViewById(R.id.vp);

	}

	class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}
	}
}
