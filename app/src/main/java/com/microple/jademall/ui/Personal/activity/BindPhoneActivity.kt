package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import cn.jpush.android.api.JPushInterface
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.microple.jademall.R
import com.microple.jademall.bean.Login
import com.microple.jademall.bean.PersonInfo
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.mvp.contract.BindPhoneContract
import com.microple.jademall.ui.Personal.mvp.presenter.BindPhonePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_bind_phone.*
import kotlinx.android.synthetic.main.item_title.*

class BindPhoneActivity : BaseMvpActivity<BindPhonePresenter>(),BindPhoneContract.View {
    private var time = 60//验证码时间
    var index=0//判断是否第一次请求banding接口
    private var mHandler : Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            time--
            if (time > 0) {
                tv_getcode.setText(time.toString() + " s")
                tv_getcode.setEnabled(false)
                sendEmptyMessageDelayed(1,1000)
            } else {
                tv_getcode.setText("重新获取")
                time = 60
                tv_getcode.setEnabled(true)
            }
        }
    }
    override fun getInfo(personalInfo: PersonInfo) {
        Constants.putPersonal(personalInfo.info)
        Constants.putHeadImg(personalInfo.info.head_img)
        dismissLoadingDialog()
        showToast("绑定成功")
        Constants.login()
        JPushInterface.setAlias(this,1,""+personalInfo.info.user_id)
        finish()
    }
    override fun bindPhone(login: Login) {
        if (login.is_phone==1){
            showBindDialog()
        }else{
            Constants.putToken(login.token)
            Constants.putHeadImg(login.head_img)
            getPresenter().getInfo(login.token)
        }

    }

    override fun getCode() {
        dismissLoadingDialog()
        showToast("发送成功")
        mHandler.sendEmptyMessage(1)
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): BindPhonePresenter = BindPhonePresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_bind_phone

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_title.text="绑定手机"
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener{
            finish()
        }
        tv_submint.setOnClickListener {
            showLoadingDialog()
            index=1
            getPresenter().bindPhone(intent.getStringExtra("openid"),et_phone.text.toString(),et_code.text.toString(),et_code_invite.text.toString(),intent.getStringExtra("userName"),intent.getStringExtra("userImg"),"")
        }
        tv_getcode.setOnClickListener{
            showLoadingDialog()
            getPresenter().getCode(et_phone.text.toString())
        }
    }
    fun showBindDialog(){
            var normalDialog= NormalDialog(this)
            normalDialog.isTitleShow(true).title("绑定手机").titleLineColor(resources.getColor(R.color.black_333333))
                    .titleTextSize(18f)
                    .titleTextColor(resources.getColor(R.color.black_333333))

                    .content("您的手机已经注册过，是否继续绑定？")
                    .style(NormalDialog.STYLE_TWO)
                    .contentTextColor(resources.getColor(R.color.black_333333))
                    .contentTextSize(16f)
                    .btnTextSize(14f)
                    .setCancelable(false)
            normalDialog.setCanceledOnTouchOutside(false)
            normalDialog.btnNum(2).btnText("取消","确定")
                    .btnTextColor(resources.getColor(R.color.color3078EF),resources.getColor(R.color.color3078EF))?.show()
            normalDialog.setOnBtnClickL(OnBtnClickL {
                normalDialog.dismiss()
            }, OnBtnClickL {
                normalDialog.dismiss()
                showLoadingDialog()
                getPresenter().bindPhone(intent.getStringExtra("openid"),et_phone.text.toString(),et_code.text.toString(),et_code_invite.text.toString(),intent.getStringExtra("userName"),intent.getStringExtra("userImg"),"1")
            })


    }


    companion object {
        fun startBindPhoneActivity(context: Context,openid:String,userImg:String,userName:String){
            val intent = Intent(context, BindPhoneActivity::class.java)
            intent.putExtra("openid",openid)
            intent.putExtra("userImg",userImg)
            intent.putExtra("userName",userName)
            context.startActivity(intent)
        }
    }

}
