package com.microple.jademall.ui.Personal.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.microple.jademall.R
import com.microple.jademall.bean.Attention
import com.microple.jademall.uitls.loadImag
import de.hdodenhof.circleimageview.CircleImageView

/**
 * author: xiaoguagnfei
 * date: 2018/8/15
 * describe:
 */
class AttentionAdapter(data:List<Attention.FollowBean>):BaseQuickAdapter<Attention.FollowBean,BaseViewHolder>(R.layout.item_attention,data) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder?, item: Attention.FollowBean?) {
        helper?.setText(R.id.tv_goodsName,item?.supplier_name)
                ?.setText(R.id.tv_goodsNum,"添加时间:"+item?.add_time)
        helper?.getView<ImageView>(R.id.iv_goodsImage)!!.loadImag(item?.head_img!!)
    }
}