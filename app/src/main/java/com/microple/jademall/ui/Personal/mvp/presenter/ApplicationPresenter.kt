package com.microple.jademall.ui.Personal.mvp.presenter

import com.microple.jademall.ui.Personal.mvp.contract.ApplicationContract
import com.microple.jademall.ui.Personal.mvp.model.ApplicationModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.bangbang.common.isPhone
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class ApplicationPresenter:ApplicationContract.Presenter() {
    override fun getImage(upload_img: String) {
        getModel().getImage(upload_img).ui({
            getView()?.getImage(it.data!!)
        },{
            getView()?.showToast(it.message)
        })
    }

    override fun apply(token: String, company_name: String, email: String, phone: String, inviter: String, license: String, attach: String, supplier_name: String, head_img: String, intro: String) {
        if (company_name.isNullOrEmpty()){
            getView()?.showToast("请输入公司名称")
            getView()?.dismissLoadingDialog()
            return
        }else if (phone.isNullOrEmpty()){
            getView()?.showToast("请输入联系方式")
            getView()?.dismissLoadingDialog()
            return
        }else if (supplier_name.isNullOrEmpty()){
            getView()?.showToast("请输入商家名称")
            getView()?.dismissLoadingDialog()
            return
        }else if (license.isNullOrEmpty()){
            getView()?.showToast("请上传营业执照")
            getView()?.dismissLoadingDialog()
            return
        }else if (attach.isNullOrEmpty()){
            getView()?.showToast("请上传附件")
            getView()?.dismissLoadingDialog()
            return
        }
        getModel().apply(token,company_name,email,phone,inviter,license,attach,supplier_name,head_img,intro).ui({
            getView()?.apply(it.msg!!)
        },{
            getView()?.dismissLoadingDialog()
            getView()?.showToast(it)
        })
    }

    override fun createModel(): ApplicationContract.Model =ApplicationModel()
}