package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.microple.jademall.R
import com.microple.jademall.bean.EmeraldsDetail
import com.microple.jademall.bean.JifenDetail
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.mvp.contract.EmeraldsDetailContract
import com.microple.jademall.ui.Personal.mvp.presenter.EmeraldsDetailPresenter
import com.microple.jademall.ui.home.activity.ImageDetailActivity
import com.microple.jademall.uitls.loadImag
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_orderdetail_two.*
import kotlinx.android.synthetic.main.item_title.*
/**
 * author: xiaoguagnfei
 * date: 2018/8/14
 * describe:翡翠订单详情
 */
class EmeraldsDetailActivity : BaseMvpActivity<EmeraldsDetailPresenter>(),EmeraldsDetailContract.View {
    override fun getjifenDetail(jifenDetail: JifenDetail) {
        loading.visibility= View.GONE
        iv_goodsImage.loadImag(jifenDetail.increment.goods.goods_img)
        tv_goodsName.text=jifenDetail.increment.goods.goods_name
        tv_goodsNum.text=jifenDetail.increment.goods.goods_sn
        tv_time.text="购买时间    "+jifenDetail.increment.add_time
        tv_number.text="订单号         "+jifenDetail.increment.order_sn
        tv_price.text="购买价格    "+jifenDetail.increment.order_amount
        tv_fangshi.text="购买方式    "+jifenDetail.increment.pay_type
    }

    var index=1//1代表翡翠柜详情。2代表积分柜详情
    override fun exchange() {
        showToast("兑换成功")
        dismissLoadingDialog()
        finish()
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): EmeraldsDetailPresenter = EmeraldsDetailPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_orderdetail_two

    /**
     * 初始化数据状态
     */
    override fun initData() {
        index=intent.getIntExtra("index",1)
        if (index==2){
            tv_title.text="积分柜详情"
            tv_tihuo.visibility=View.GONE
            getPresenter().getjifenDetail(Constants.getToken(),intent.getStringExtra("incr_id"))
            tv_jifen.setOnClickListener{
                showDialog(this,intent.getStringExtra("incr_id"))
            }
        }else{
            tv_title.text="翡翠柜详情"
            tv_shouyi.visibility=View.GONE
            var ct_id=intent.getStringExtra("ct_id")
            getPresenter().getEmeraldsDetail(Constants.getToken(),ct_id)
            tv_jifen.setOnClickListener{
                showDialog(this,ct_id)
            }
        }
        (application as App).addActivity(this)



    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_shouyi.setOnClickListener{
            IntergrationGetActivity.startEmeraldsDetailActivity(this,intent.getStringExtra("ct_id"),""+emeraldsDetail?.cabinet?.goods_id)
        }
        tv_tihuo.setOnClickListener{
            PickGoodsActivity.startPickGoodsActivity(this,intent.getStringExtra("ct_id"))
        }
        iv_back.setOnClickListener{
            finish()
        }
        tv_img.setOnClickListener{
            ImageDetailActivity.startImageDetailActivity(this,""+emeraldsDetail?.cabinet?.goods_id)
        }
    }
        var emeraldsDetail:EmeraldsDetail?=null
    override fun getEmeraldsDetail(emeraldsDetail: EmeraldsDetail) {
        loading.visibility= View.GONE
        this.emeraldsDetail=emeraldsDetail
        if (emeraldsDetail.cabinet.goods.goods_img!=null)
        iv_goodsImage.loadImag(emeraldsDetail.cabinet.goods.goods_img)
        tv_goodsName.text=emeraldsDetail.cabinet.goods.goods_name
        tv_goodsNum.text=emeraldsDetail.cabinet.goods.goods_sn
        tv_time.text="购买时间    "+emeraldsDetail.cabinet.add_time
        tv_number.text="订单号         "+emeraldsDetail.cabinet.order_sn
        tv_price.text="购买价格    "+emeraldsDetail.cabinet.order_amount
        tv_fangshi.text="购买方式    "+emeraldsDetail.cabinet.pay_type
    }
    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }

    companion object {//ct_id翡翠柜ID，incr_id积分柜ID
        fun startOrderDetailActivity(context: Context,ct_id:String,incr_id:String ,index:Int){
            val intent = Intent(context,EmeraldsDetailActivity::class.java)
            intent.putExtra("ct_id",ct_id)
            intent.putExtra("index",index)
        intent.putExtra("incr_id",incr_id)
            context.startActivity(intent)
        }
    }

    fun showDialog(context: Context,ct_id:String){
        var dialog = NormalDialog(context)
        dialog.style(NormalDialog.STYLE_TWO)
                .content("积分兑换")
                .isTitleShow(false)
                .style(NormalDialog.STYLE_TWO)
                .contentTextSize(17f)
                .titleTextSize(17f)
                .contentTextColor(context.resources.getColor(R.color.black_444444))
                .titleTextColor(context.resources.getColor(R.color.black_333333))
                .btnNum(2)
                .btnText("取消","确定")
                .btnTextColor(context.resources.getColor(R.color.color3078EF))
                .setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        dialog.setOnBtnClickL(OnBtnClickL {
            dialog.dismiss()
        }, OnBtnClickL {
            showLoadingDialog()
            if (index==1)
            getPresenter().exchange(Constants.getToken(),ct_id)
            else
                getPresenter().jifenEx(Constants.getToken(),ct_id)
            dialog.dismiss()
        })
    }
}
