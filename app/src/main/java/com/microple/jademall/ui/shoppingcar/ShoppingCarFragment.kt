package com.microple.jademall.ui.shoppingcar

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.microple.jademall.MainActivity
import com.microple.jademall.R
import com.microple.jademall.bean.Shop
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.activity.LoginActivity
import com.microple.jademall.ui.home.activity.GoodsDetailActivity
import com.microple.jademall.ui.home.activity.ImOrderActivity
import com.microple.jademall.ui.shoppingcar.adapter.ShoppingAdapter
import com.microple.jademall.ui.shoppingcar.mvp.contract.ShoppingCarContract
import com.microple.jademall.ui.shoppingcar.mvp.presenter.ShoppingCarPresenter
import com.microple.jademall.uitls.loadHeadImag
import com.xx.baseuilibrary.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_shoppingcar.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*
import kotlinx.android.synthetic.main.item_login.*

/**
 * author: linfeng
 * date: 2018/8/8.
 * describe:购物车
 */
class ShoppingCarFragment : BaseMvpFragment<ShoppingCarContract.Model,ShoppingCarContract.View,ShoppingCarPresenter>(),ShoppingCarContract.View{
   //记录当前删除的位置
    var current=0
    var shop:Shop?=null
    override fun shop(shop: Shop) {
        if (shop.is_on_sale==0){
            tv_content.visibility=View.VISIBLE
            tv_index.visibility=View.VISIBLE
        }else{
            tv_content.visibility=View.GONE
            tv_index.visibility=View.GONE
        }
        loading.visibility=View.GONE
        if (shop.shopp_info.size==0){
            tv_tishi.visibility=View.VISIBLE
            tv_index.visibility=View.GONE
            tv_content.visibility=View.GONE
            tv_bianji.visibility=View.GONE
        }else{
            tv_tishi.visibility=View.GONE
            tv_bianji.visibility=View.VISIBLE

        }

        this.shop=shop
        adapter.setNewData(shop.shopp_info)
        tv_price.text="购物袋合计     ￥"+shop.total_fee
    }

    override fun delShop() {
        getPresenter().updateShop(Constants.getToken())
        adapter.remove(current)
    }

    override fun updateShop(shop: Shop) {
        dismissLoadingDialog()
        if (shop.shopp_info.size==0){
            tv_tishi.visibility=View.VISIBLE
            tv_index.visibility=View.GONE
            tv_bianji.visibility=View.GONE
        }else{
            tv_tishi.visibility=View.GONE
            tv_index.visibility=View.VISIBLE
            tv_bianji.visibility=View.VISIBLE
        }
        adapter.setNewData(shop.shopp_info)

        tv_price.text="购物袋合计     ￥"+shop.total_fee
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): ShoppingCarPresenter = ShoppingCarPresenter()

    var index:Int=1
    var adapter=ShoppingAdapter(arrayListOf())
    override fun getFragmentLayoutId(): Int = R.layout.fragment_shoppingcar

    override fun init(view: View?) {
        iv_head.setOnClickListener {
            (activity as MainActivity).setSelect(4)
        }

        if (Constants.isLogin())
            iv_head.loadHeadImag(Constants.getPersonal().head_img)
        tv_index.setOnClickListener{
            tv_content.visibility=View.GONE
            tv_index.visibility=View.GONE
            showLoadingDialog()
            getPresenter().updateShop(Constants.getToken())
        }
        tv_bianji.setOnClickListener{
            adapter.upDateAdapter(index)
            if (index==1){
                index=0
            }else{
                index=1
            }
        }
        tv_submint.setOnClickListener{
            var sb_id=""
            if (shop?.shopp_info?.size!=0){

            for (i in 0..shop?.shopp_info!!.size-1){
                if (i==0){
                    sb_id=""+shop?.shopp_info!![i].sb_id
                }else{
                    sb_id=sb_id+","+shop?.shopp_info!![i].sb_id
                }
            }
            ImOrderActivity.startImOrderActivity(context!!,sb_id,"")
            }
        }
        tv_login.setOnClickListener{
            LoginActivity.startLoginActivity(context!!)
        }
        scrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.i("NestedScrollView",""+scrollX+","+scrollY+","+oldScrollX+","+oldScrollY)
            if (scrollY>=90){
                tv_title.visibility=View.VISIBLE
                tv_title.text="购物袋"
            }else{
                tv_title.visibility=View.GONE
            }
        }
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        cl_right.layoutManager= LinearLayoutManager(context)
        cl_right.adapter=adapter
        cl_right.isNestedScrollingEnabled=false
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id==R.id.ll_type){
                current=position
                showLoadingDialog()
                getPresenter().delShop(Constants.getToken(),""+(adapter as ShoppingAdapter).data[position].sb_id)

            }else if (view.id==R.id.ll_content){
                GoodsDetailActivity.startGoodsDetailActivity(context!!,""+(adapter as ShoppingAdapter).data[position].goods_sn)
            }
        }

    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            if (Constants.isLogin()){
                Log.i("Constantssss", Constants.getToken())
                getPresenter().shop(Constants.getToken())
                view_login.visibility=View.GONE
                iv_head.loadHeadImag(Constants.getPersonal().head_img)
            }else{
                view_login.visibility=View.VISIBLE
                iv_head.setImageResource(R.drawable.datouxiang_)
                LoginActivity.startLoginActivity(context!!)
                showToast("请先登录")
            }
        }
    }
    override fun onResume( ) {
        super.onResume()
        if (!isHidden){
            if (Constants.isLogin()){
                Log.i("Constantssss", Constants.getToken())
                getPresenter().shop(Constants.getToken())
                view_login.visibility=View.GONE
                iv_head.loadHeadImag(Constants.getPersonal().head_img)
            }else{
                view_login.visibility=View.VISIBLE
                iv_head.setImageResource(R.drawable.datouxiang_)
            }
        }
    }


}