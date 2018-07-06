package com.example.smile.testboolr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.domain.OrderBean;

import java.util.ArrayList;


/**
 * Created by Smile on 2018/7/4.
 */

public class AllFragment extends Fragment {
    private RecyclerView rv;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_all, null);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        String url = "https://www.zhaoapi.cn/product/getOrders?uid=71";
        OrderBean orderBean = new OrderBean();
        orderBean.setMsg("msg");
        orderBean.setCode("code");


        ArrayList<OrderBean.DataBean> dataBeans = new ArrayList<>();
        OrderBean.DataBean dataBean = new OrderBean.DataBean();
        dataBean.setCreatetime("2018-7-3");
        dataBean.setPrice(90);
        dataBean.setTitle("订单号：100001");
        dataBean.setOrderid(100001);
        OrderBean.DataBean dataBean2 = new OrderBean.DataBean();
        dataBean.setCreatetime("2018-7-4");
        dataBean.setPrice(90);
        dataBean.setTitle("订单号：");
        dataBean.setOrderid(100002);

        dataBeans.add(dataBean);
        dataBeans.add(dataBean2);
        orderBean.setData(dataBeans);

        RvAllAdapter adapter = new RvAllAdapter(getContext(), orderBean.getData());
        rv.setAdapter(adapter);


//        OkHttpUtils.getOkHttpUtils().doGet(url, new Call.Callback() {
//
//            public void onFailure(Call call, IOException e) {
//            }
//
//
//            public void onResponse(Call call, Response response) throws IOException {
//                String string = response.body().string();
//                final OrderBean orderBean = new Gson().fromJson(string, OrderBean.class);
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        RvAllAdapter adapter = new RvAllAdapter(getContext(), orderBean.getData());
//                        rv.setAdapter(adapter);
//                    }
//                });
//            }
//        });
        return view;
    }
}