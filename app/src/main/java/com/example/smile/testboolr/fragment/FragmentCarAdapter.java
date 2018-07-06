package com.example.smile.testboolr.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smile.testboolr.R;
import com.example.smile.testboolr.domain.ShoppingCartBean;

import java.util.List;

/**
 * Created by Smile on 2018/7/1.
 */

public class FragmentCarAdapter extends BaseAdapter{

    private boolean isShow = true;//是否显示编辑/完成
    private List<ShoppingCartBean> shoppingCartBeanList;
    private ShoppingCartBean shoppingCartBean;
//    private CheckInterface checkInterface;
//    private ModifyCountInterface modifyCountInterface;
    private Context context;



    public FragmentCarAdapter(Context context){
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return shoppingCartBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        shoppingCartBean = shoppingCartBeanList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_car_item_shopping_cart_layout, parent, false);
            holder = new ViewHolder(convertView,shoppingCartBean);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        boolean choosed = shoppingCartBean.isChoosed();
        if (choosed){
            holder.ckOneChose.setChecked(true);
        }else{
            holder.ckOneChose.setChecked(false);
        }



        return convertView;
    }
}

//初始化控件
class ViewHolder {
    ImageView ivShowPic, tvCommodityDelete;
    TextView tvCommodityName, tvCommodityAttr, tvCommodityPrice, tvCommodityNum, tvCommodityShowNum, ivSub, ivAdd;
    CheckBox ckOneChose;
    LinearLayout rlEdit;
    //匹配图片
    private int[] icons={R.drawable.ruguo_1,R.drawable.zisi_2,R.drawable.mengxiang_3, R.drawable.tx_4,R.drawable.pengran_5, R.drawable.suoyou_6, R.drawable.suanfa_7, R.drawable.nide_8};
    private String[] image={"ruguo_1","zisi_2","mengxiang_3","tx_4","pengran_5","suoyou_6","suanfa_7","nide_8"};

    public ViewHolder(View itemView,ShoppingCartBean shoppingCartBean) {
        ckOneChose = (CheckBox) itemView.findViewById(R.id.ck_chose);
        ivShowPic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
        ivSub = (TextView) itemView.findViewById(R.id.iv_sub);
        ivAdd = (TextView) itemView.findViewById(R.id.iv_add);
        tvCommodityName = (TextView) itemView.findViewById(R.id.tv_commodity_name);
        tvCommodityAttr = (TextView) itemView.findViewById(R.id.tv_commodity_attr);
        tvCommodityPrice = (TextView) itemView.findViewById(R.id.tv_commodity_price);
        tvCommodityNum = (TextView) itemView.findViewById(R.id.tv_commodity_num);
        tvCommodityShowNum = (TextView) itemView.findViewById(R.id.tv_commodity_show_num);
        tvCommodityDelete = (ImageView) itemView.findViewById(R.id.tv_commodity_delete);
        rlEdit = (LinearLayout) itemView.findViewById(R.id.rl_edit);


        tvCommodityName.setText(shoppingCartBean.getShoppingName());
        tvCommodityAttr.setText(shoppingCartBean.getAttribute());
        tvCommodityPrice.setText(shoppingCartBean.getPrice()+"");

        String str = shoppingCartBean.getImageUrl();
        int icon=0;
        //找与图片相匹配的id
        for(int j=0;j<image.length;j++){
            if(str.equals(image[j])){
                icon=j;
                break;
            }
        }
        ivShowPic.setImageResource(icons[icon]);
    }
}
