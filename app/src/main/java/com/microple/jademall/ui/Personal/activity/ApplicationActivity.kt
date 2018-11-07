package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.EncodeUtils
import com.microple.jademall.R
import com.microple.jademall.bean.Image
import com.microple.jademall.common.Constants
import com.microple.jademall.dialog.PhoteDialog
import com.microple.jademall.ui.Personal.mvp.contract.ApplicationContract
import com.microple.jademall.ui.Personal.mvp.presenter.ApplicationPresenter
import com.microple.jademall.weight.ImageChooseHelper
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.item_title.*
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:商家入驻申请
 */
class ApplicationActivity : BaseMvpActivity<ApplicationPresenter>(),ApplicationContract.View {


    var license=""//营业执照
    var attach=""//附件
    var simage=""//商家头像
    var index=0
    var flag=0
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): ApplicationPresenter = ApplicationPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_application

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_title.text="商家入驻"
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener{
            finish()
        }
        tv_submint.setOnClickListener{
            showLoadingDialog()
            if (et_gongsi.text.isNullOrEmpty()){
                showToast("请输入公司名称")
                dismissLoadingDialog()
            }else if (et_phone.text.isNullOrEmpty()){
                showToast("请输入联系方式")
                dismissLoadingDialog()
            }else if (et_sname.text.isNullOrEmpty()){
                showToast("请输入商家名称")
                dismissLoadingDialog()
            }else if (simage.isNullOrEmpty()){
                showToast("请添加商家头像")
                dismissLoadingDialog()
            }else if (license.isNullOrEmpty()){
                showToast("请添加营业执照")
                dismissLoadingDialog()
            }else if (attach.isNullOrEmpty()){
                showToast("请添加附件")
                dismissLoadingDialog()
            } else {
                getPresenter().getImage(license)
            }

        }
        rl_yingye.setOnClickListener {
            index=1
            showEditAvatarDialog()
        }

        rl_fujian.setOnClickListener{
            index=2
            showEditAvatarDialog()
        }
        rl_sImage.setOnClickListener {
            index=3
            showEditAvatarDialog()
        }
    }

    override fun apply(msg: String) {
        dismissLoadingDialog()
        showToast(msg)
        finish()
    }
    override fun getImage(image: Image) {
        if (flag==0){
            flag++
            license=image.image_data
            getPresenter().getImage(attach)
        }else if (flag==1){
            flag++
            attach=image.image_data
            getPresenter().getImage(simage)
        }else{
            Log.i("licensess",license)
            Log.i("attachsss",attach)
            simage=image.image_data
            getPresenter().apply(Constants.getToken(),et_gongsi.text.toString(),et_email.text.toString(),
                    et_phone.text.toString(),et_invite.text.toString(),license,attach,et_sname.text.toString(),simage,tv_content.text.toString())

        }


    }
    companion object {
        fun startApplicationActivity(context: Context){
            val intent = Intent(context,ApplicationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageChooseHelper = ImageChooseHelper(this,{
            if (index==1){
                license= EncodeUtils.base64Encode2String(File(it.compressPath).readBytes())
                iv_yingye.setImageURI(Uri.fromFile(File(it.compressPath)))
                iv_add_yingye.visibility=View.GONE
            }else if (index==2){
                attach=EncodeUtils.base64Encode2String(File(it.compressPath).readBytes())
                iv_fujian.setImageURI(Uri.fromFile(File(it.compressPath)))
                iv_add_fujian.visibility=View.GONE
            }else{
                simage=EncodeUtils.base64Encode2String(File(it.compressPath).readBytes())
                iv_add_sImage.visibility=View.GONE
                iv_sImage.setImageURI(Uri.fromFile(File(it.compressPath)))
            }

        })

        imageChooseHelper.takePhoto.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        imageChooseHelper.takePhoto.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imageChooseHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private lateinit var imageChooseHelper: ImageChooseHelper
    /**
     * 显示修改头像弹窗
     */
    private fun showEditAvatarDialog() {

        var photoDialog=PhoteDialog(this)
        photoDialog.show()
        photoDialog.setOnBtnClickListener {
            if (it==1){
                photoDialog.dismiss()
            }else if (it==2){//相册
                imageChooseHelper.selectPicker()
                photoDialog.dismiss()

            }else {
                imageChooseHelper.takePhoto()
                photoDialog.dismiss()

            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        imageChooseHelper.takePhoto.onActivityResult(requestCode, resultCode, data)
    }
}
