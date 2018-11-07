package com.microple.jademall.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microple.jademall.R;


/**
 * Created by JacobHHH on 2018/4/22.
 */

public class BuytypeDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private LinearLayout ll_zhibo;
    private LinearLayout ll_feicui;
    private LinearLayout ll_youji,ll_one,ll_two,ll_five,ll_youji_dao;


    public BuytypeDialog(@NonNull Context context) {
        super(context);
        this.mContext=context;
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        this.setCanceledOnTouchOutside(false);
        this.getWindow().setGravity(Gravity.BOTTOM);
        Window window = this.getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                //设置dialog 在布局中的位置
                attr.gravity = Gravity.BOTTOM;

                window.setAttributes(attr);
            }

        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.view_buy_type, null);
        view.findViewById(R.id.ll_youji).setOnClickListener(this);
        view.findViewById(R.id.ll_feicui).setOnClickListener(this);
        view.findViewById(R.id.ll_zhibo).setOnClickListener(this);
        ll_youji = view.findViewById(R.id.ll_youji);
        ll_feicui = view.findViewById(R.id.ll_feicui);
        ll_zhibo = view.findViewById(R.id.ll_zhibo);
        ll_one=view.findViewById(R.id.ll_one);
        ll_two=view.findViewById(R.id.ll_two);
        ll_five=view.findViewById(R.id.ll_five);
        ll_youji_dao=view.findViewById(R.id.ll_youji_dao);
        ll_one.setOnClickListener(this);
        ll_youji_dao.setOnClickListener(this);
        ll_two.setOnClickListener(this);
        ll_five.setOnClickListener(this);
        setContentView(view);
    }
    public void setvisb(int tyle){
        if (tyle==2){
            ll_zhibo.setVisibility(View.VISIBLE);
            ll_one.setVisibility(View.VISIBLE);
            ll_two.setVisibility(View.VISIBLE);
            ll_five.setVisibility(View.GONE);
            ll_feicui.setVisibility(View.VISIBLE);
        } else{
            ll_zhibo.setVisibility(View.GONE);
            ll_one.setVisibility(View.GONE);
            ll_two.setVisibility(View.GONE);
            ll_five.setVisibility(View.VISIBLE);
            ll_feicui.setVisibility(View.GONE);
        }

    }



    @Override
    public void show() {
        if (!((Activity)mContext).isFinishing()){
            super.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_youji:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(1);
                }
                break;
            case R.id.ll_zhibo:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(2);
                }
                break;
            case R.id.ll_feicui:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(3);
                }
                break;
            case R.id.ll_one:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(4);
                }
                break;
            case R.id.ll_two:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(5);
                }
                break;
            case  R.id.ll_five:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(6);
                }
                break;
            case R.id.ll_youji_dao:
                    if (mOnBtnClickListener!=null){
                        mOnBtnClickListener.cancel(7);
                    }
                break;

        }
    }

    private OnBtnClickListener mOnBtnClickListener;

    public interface OnBtnClickListener{
        void cancel(int index);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener){
        this.mOnBtnClickListener=listener;
    }
}
