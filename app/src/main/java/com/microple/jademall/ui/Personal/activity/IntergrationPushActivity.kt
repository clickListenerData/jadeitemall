package com.microple.jademall.ui.Personal.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.microple.jademall.R
import com.microple.jademall.bean.AccountIinfo
import com.microple.jademall.bean.UserInfo
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.adapter.IntergrationPushAdapter
import com.microple.jademall.ui.Personal.mvp.contract.IntergrationPushContract
import com.microple.jademall.ui.Personal.mvp.presenter.IntergrationPushPresenter
import com.microple.jademall.uitls.loadHeadImag
import com.microple.jademall.uitls.loadImag
import com.microple.jademall.weight.PwdEditText
import com.weibiaogan.bangbang.common.isPhone
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_intergration_push.*
import kotlinx.android.synthetic.main.item_title.*
/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:积分转账
 */
class IntergrationPushActivity : BaseMvpActivity<IntergrationPushPresenter>(),IntergrationPushContract.View {
    override fun pushInfo(userInfo: UserInfo) {
        iv_head.loadImag(userInfo.head_img)
        tv_name.text=userInfo.user_name
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): IntergrationPushPresenter = IntergrationPushPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_intergration_push

    /**
     * 初始化数据状态
     */
    var adapter=IntergrationPushAdapter(arrayListOf())
    override fun initData() {
        tv_title.setText("积分转账")
        getPresenter().getAccout(Constants.getToken())
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter
        recyclerView.isNestedScrollingEnabled = false
        adapter.setOnItemClickListener { adapter, view, position ->
            et_zhanhao.setText((adapter as IntergrationPushAdapter).data[position].phone)
            tv_name.text=adapter.data[position].to_user
            iv_head.loadHeadImag(adapter.data[position].head_img)
        }
    }


    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener{
            finish()
        }
        tv_dongjie.setOnClickListener {
            var intent=Intent(this,IgRecordActivity::class.java)
            this.startActivityForResult(intent,2)
        }
        tv_sure.setOnClickListener{
            if (tv_keyong.text.toString().isNullOrEmpty()&&tv_dongjie.text.toString().isNullOrEmpty()){
                showToast("请输入转账积分")
            }else if (!tv_keyong.text.isNullOrEmpty()&&pay_points.toDouble()<tv_keyong.text.toString().toDouble()){
                            showToast("请输入积分小于可用的")
            }
//            else if (!tv_dongjie.text.isNullOrEmpty()&&pay_dongjie.toDouble()<tv_dongjie.text.toString().toDouble()){
//                    showToast("请输入积分小于拥有的")
//            }
            else{
                showDialog()

            }
        }
        et_zhanhao.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (et_zhanhao.text.toString().isPhone()){
                    getPresenter().pushInfo(Constants.getToken(),et_zhanhao.text.toString())
                }

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

    }
    var pay_points=""
    var pay_dongjie=""
    override fun getAccout(accoutInfo: AccountIinfo) {
        tv_keyong.hint="可用积分剩余"+accoutInfo.user_info.pay_points
        pay_points=accoutInfo.user_info.pay_points
        pay_dongjie=accoutInfo.user_info.frozen_points
        tv_dongjie.hint="冻结积分剩余"+accoutInfo.user_info.frozen_points
        adapter.setNewData(accoutInfo.record)
        loading.visibility= View.GONE
    }

    override fun push() {
        showToast("转账成功")
        dismissLoadingDialog()
        finish()
    }

    companion object {
        fun startIntergrationPushActivity(context: Context){
            val intent = Intent(context,IntergrationPushActivity::class.java)
            context.startActivity(intent)
        }
    }
    var dialog: AlertDialog?=null
    fun showDialog() {
        var view = View.inflate(mContext, R.layout.view_input_pay_psw_dialog, null)
        var psw_view=view.findViewById<PwdEditText>(R.id.psw_view)
        var bt_quxiao=view.findViewById<TextView>(R.id.bt_quxiao)
        var tv_price=view.findViewById<TextView>(R.id.tv_price)
        var price=0.00
        if (tv_keyong.text.toString().isNullOrEmpty()){
            price=tv_dongjie.text.toString().toDouble()
        }else if(tv_dongjie.text.toString().isNullOrEmpty()){
            price=tv_keyong.text.toString().toDouble()
        }else{
            price=tv_dongjie.text.toString().toDouble()+tv_keyong.text.toString().toDouble()
        }

        tv_price.setText(""+price)
        bt_quxiao.setOnClickListener{
            dialog!!.dismiss()
        }
        psw_view.setOnInputFinishListener{
            showLoadingDialog()
            getPresenter().push(Constants.getToken(),et_zhanhao.text.toString(),tv_keyong.text.toString(),fp_id,it)

        }
        dialog = AlertDialog.Builder(mContext).create()
        dialog!!.setView(view)
        dialog!!.show()
    }
    var points=""
    var fp_id=""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==2&&resultCode==2){
            points=data?.getStringExtra("points")!!
            fp_id=""+data?.getIntExtra("fp_id",0)
            tv_dongjie.text=points
        }
    }

}
