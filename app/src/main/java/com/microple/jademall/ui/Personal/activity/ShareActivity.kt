package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.microple.jademall.R
import kotlinx.android.synthetic.main.item_title.*

/**
 * author: xiaoguagnfei
 * date: 2018/8/14
 * describe:分享
 */
class ShareActivity : AppCompatActivity() {
    companion object {
        fun startShareActivityMerchantEntryActivity(context: Context){
            val intent = Intent(context,ShareActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        tv_title.text="分享"
    }
}
