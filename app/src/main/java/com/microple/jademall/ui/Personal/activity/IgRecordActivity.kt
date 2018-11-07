package com.microple.jademall.ui.Personal.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.microple.jademall.R
import com.microple.jademall.bean.IgRecord
import com.microple.jademall.ui.Personal.adapter.IgRecordAdapter
import com.microple.jademall.ui.Personal.mvp.contract.IgRecordContract
import com.microple.jademall.ui.Personal.mvp.model.IgRecordPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_ig_record.*
import kotlinx.android.synthetic.main.item_title.*

class IgRecordActivity : BaseMvpActivity<IgRecordPresenter>(),IgRecordContract.View {
    override fun igRecord(igRecord: IgRecord) {
        igRecordAdapter.setNewData(igRecord.frozen_info)
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): IgRecordPresenter = IgRecordPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_ig_record

    /**
     * 初始化数据状态
     */
    var igRecordAdapter=IgRecordAdapter(arrayListOf())
    override fun initData() {
        tv_title.text="积分记录"
        iv_back.setOnClickListener { finish() }
        getPresenter().igRecord()
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=igRecordAdapter
        igRecordAdapter.setOnItemClickListener { adapter, view, position ->
            var intent=Intent()
            intent.putExtra("fp_id",(adapter as IgRecordAdapter).data[position].fp_id)
            intent.putExtra("points",(adapter as IgRecordAdapter).data[position].points)
            setResult(2,intent)
            finish()
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }

}
