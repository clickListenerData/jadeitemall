package com.microple.jademall.ui.Personal

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.microple.jademall.R
import com.microple.jademall.bean.*
import com.microple.jademall.ui.Personal.adapter.PersonlCommonAdapter
import com.microple.jademall.ui.Personal.adapter.PersonlHelpAdapter
import com.microple.jademall.ui.Personal.adapter.PersonlOrderAdapter
import com.microple.jademall.ui.Personal.adapter.PersonlSettingAdapter
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.activity.*
import com.microple.jademall.ui.Personal.mvp.contract.PersonalContract
import com.microple.jademall.ui.Personal.mvp.presenter.PersonalPresenter
import com.microple.jademall.uitls.loadHeadImag
import com.microple.jademall.uitls.loadImag
import com.xx.baseuilibrary.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_personl.*

/**
 * author: linfeng
 * date: 2018/8/8.
 * describe:
 */
class PersonlFragment : BaseMvpFragment<PersonalContract.Model,PersonalContract.View,PersonalPresenter>(),PersonalContract.View {
    override fun getInfo(personalInfo: PersonInfo) {
        loading.visibility=View.GONE
        iv_head.loadHeadImag(personalInfo.info.head_img)
        if (personalInfo.info.user_name.isNullOrEmpty()){
            tv_name.text="请设置昵称"

        }else{
            tv_name.text=personalInfo.info.user_name
        }
        tv_phone.text=personalInfo.info.phone
        Constants.putPersonal(personalInfo.info)
        Constants.putHeadImg(personalInfo.info.head_img)
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): PersonalPresenter = PersonalPresenter()

    private lateinit var mOrderAdapter: PersonlOrderAdapter//我的订单
    private lateinit var mSettingAdapter: PersonlSettingAdapter//设置
    private lateinit var mCommonAdapter: PersonlCommonAdapter//通用功能
    private lateinit var mHelpAdapter: PersonlHelpAdapter//帮助
    override fun getFragmentLayoutId(): Int = R.layout.fragment_personl

    override fun init(view: View?) {
        iv_setting.setOnClickListener{
            var normalDialog=NormalDialog(context)
            normalDialog.isTitleShow(false).content("您是否确定退出登录？")
                    .style(NormalDialog.STYLE_TWO)
                    .contentTextColor(resources.getColor(R.color.black_333333))
                    .contentTextSize(17f)
                    .btnTextSize(14f)
                      .setCancelable(false)
            normalDialog.setCanceledOnTouchOutside(false)
            normalDialog.btnNum(2).btnText("取消","确定")
                    .btnTextColor(resources.getColor(R.color.color3078EF),resources.getColor(R.color.color3078EF))?.show()
            normalDialog.setOnBtnClickL(OnBtnClickL {
                normalDialog.dismiss()
            }, OnBtnClickL {
                Constants.loginOut()
                normalDialog.dismiss()
                LoginActivity.startLoginActivity(context!!)
            })

        }
        iv_head.setOnClickListener {
            PersonalSettingActivity.startPersonalSettingActivity(context!!)
        }
        scrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY>=90){
                tv_title.visibility=View.VISIBLE
                tv_title.text="个人中心"
            }else{
                tv_title.visibility=View.GONE
            }
        }
        //我的订单
        rv_myOrder.layoutManager = LinearLayoutManager(context)
        mOrderAdapter = PersonlOrderAdapter(R.layout.item_personal, PersonOrderBean.createList())
        rv_myOrder.adapter = mOrderAdapter
        rv_myOrder.isNestedScrollingEnabled = false
        //设置
        rv_setting.layoutManager = LinearLayoutManager(context)
        mSettingAdapter = PersonlSettingAdapter(R.layout.item_personal, PersonSettingBean.createList())
        rv_setting.adapter = mSettingAdapter
        rv_setting.isNestedScrollingEnabled = false

        //通用功能
        rv_common.layoutManager = LinearLayoutManager(context)
        mCommonAdapter = PersonlCommonAdapter(R.layout.item_personal, PersonCommonBean.createList())
        rv_common.adapter = mCommonAdapter
        rv_common.isNestedScrollingEnabled = false

        //帮助
        rv_help.layoutManager = LinearLayoutManager(context)
        mHelpAdapter = PersonlHelpAdapter(R.layout.item_personal, PersonHelpBean.createList())
        rv_help.adapter = mHelpAdapter
        rv_help.isNestedScrollingEnabled = false
        iv_message.setOnClickListener{
            if (Constants.isLogin()){
                MessageActivity.startMessageActivity(context!!)
            }else{
                LoginActivity.startLoginActivity(context!!)
            }
        }
        mOrderAdapter.setOnItemClickListener { adapter, view, position ->
          if (!Constants.isLogin()){
              LoginActivity.startLoginActivity(context!!)
              return@setOnItemClickListener
          }
           when(position){//我的订单
               0->{
                   AllOrderActivity.startAllOrderActivity(context!!,0)
               }
               1->{
                   AllOrderActivity.startAllOrderActivity(context!!,1)

               }
               2->{
                   AllOrderActivity.startAllOrderActivity(context!!,3)
               }
               3->{
                   AllOrderActivity.startAllOrderActivity(context!!,4)
               }
               4->{
                   AllOrderActivity.startAllOrderActivity(context!!,5)

               }
               5->{
                   CustomerOrderActivity.startCustomerOrderActivity(context!!)
               }
           }
        }
        mSettingAdapter.setOnItemClickListener { adapter, view, position ->
            if (!Constants.isLogin()){
                LoginActivity.startLoginActivity(context!!)
                return@setOnItemClickListener
            }
            when(position){
                0->{
                    PersonalSettingActivity.startPersonalSettingActivity(context!!)
                }
                1->{
                    AddressActivity.startAddressActivity(context!!)
                }
                2->{
                    SafeSettingActivity.startSafeSettingActivity(context!!)
                }
            }
        }
        mCommonAdapter.setOnItemClickListener { adapter, view, position ->
            if (!Constants.isLogin()){
                LoginActivity.startLoginActivity(context!!)
                return@setOnItemClickListener
            }
            when(position){
                0->{
                    MyCollectionActivity.startMyCollectionActivity(context!!,1)
                }
                1->{
                    MyAttentionActivity.startMyAttentionActivity(context!!)
                }
                2->{
                    MyCollectionActivity.startMyCollectionActivity(context!!,2)
                }
                3->{
                    MyAppointmentActivity.startMyAppointmentActivity(context!!)
                }
                4->{
                    MyAssetActivity.startMyAssetActivity(context!!)
                }
                5->{
                    ShareActivity.startShareActivityMerchantEntryActivity(context!!)
                }
                6->{
                    MyTeamActivity.startMyTeamActivity(context!!)
                }
                7->{
                    MerchantEntryActivity.startMerchantEntryActivity(context!!)
                }
            }
        }
        mHelpAdapter.setOnItemClickListener { adapter, view, position ->
            if (!Constants.isLogin()){
                LoginActivity.startLoginActivity(context!!)
                return@setOnItemClickListener
            }
            when(position){
                0->{
                    AskQuestionTypeActivity.startAskQuestionTypeActivity(context!!)
                }
                1->{
                    CustomerServiceAcitivity.startCustomerServiceAcitivity(context!!)
                }
                2->{
                    FeedBackActivity.startFeedBackActivity(context!!)
                }
                3->{
                    AboutMeActivity.startAboutMeActivity(context!!)
                }
            }
        }
        tv_login.setOnClickListener{
            LoginActivity.startLoginActivity(context!!)
        }
        ll_login.setOnClickListener {  }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            if (Constants.isLogin()){
                Log.i("Constantssss",Constants.getToken())
                getPresenter().getInfo(Constants.getToken())
                ll_login.visibility=View.GONE
            }else{
                ll_login.visibility=View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isHidden){
            if (Constants.isLogin()){
                Log.i("Constantssss",Constants.getToken())
                getPresenter().getInfo(Constants.getToken())
                ll_login.visibility=View.GONE
            }else{
                ll_login.visibility=View.VISIBLE
                loading.visibility=View.GONE
            }

        }
    }
}