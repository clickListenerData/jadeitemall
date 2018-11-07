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

import com.microple.jademall.R;


/**
 * Created by JacobHHH on 2018/4/22.
 */

public class YunFeiDialog extends Dialog implements View.OnClickListener {

    private Context mContext;



    public YunFeiDialog(@NonNull Context context) {
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
    View view;
    private void initView() {
         view = View.inflate(mContext, R.layout.view_yunfei, null);
        view.findViewById(R.id.ll_daofu).setOnClickListener(this);
        view.findViewById(R.id.ll_xianfu).setOnClickListener(this);
        setContentView(view);
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
            case R.id.ll_daofu:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(1);
                }
                break;
            case R.id.ll_xianfu:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel(2);
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
