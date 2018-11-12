package com.microple.jademall.ui.Personal.mvp.presenter

import android.text.TextUtils
import com.microple.jademall.ui.Personal.mvp.contract.AboutMeContract
import com.microple.jademall.ui.Personal.mvp.contract.LoginContract
import com.microple.jademall.ui.Personal.mvp.model.AboutMelModel
import com.microple.jademall.ui.Personal.mvp.model.LoginModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui
import com.xx.baseutilslibrary.network.exception.ApiFaileException

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class LoginPresenter:LoginContract.Presenter() {
    override fun threeLogin(openid: String) {
            getModel().threeLogin(openid).ui({
                getView()?.loginSucceful(it.data!!)
            },{
                if ((it is ApiFaileException)){
                        getView()?.threeFaid()
                }else{
                    getView()?.showToast(it)
                }
            })
    }

    override fun getCode(phone: String) {
        if (TextUtils.isEmpty(phone)){
            getView()?.showToast("请输入手机号")
            getView()?.dismissLoadingDialog()
            return
        }else if (11!=phone.length){
            getView()?.showToast("请输入正确的手机号")
            getView()?.dismissLoadingDialog()
            return
        }

        getModel().getCode(phone).ui({
            getView()?.getCode()

        },{
            getView()?.dismissLoadingDialog()
            getView()?.showToast(it.message)
        })
    }

    override fun pwLogin(phone: String, password: String) {
        if (TextUtils.isEmpty(phone)){
            getView()?.showToast("请输入手机号")
            getView()?.dismissLoadingDialog()
            return
        }else if (11!=phone.length){
            getView()?.showToast("请输入正确的手机号")
            getView()?.dismissLoadingDialog()
            return
        }
        if (TextUtils.isEmpty(password)||password.length<6||password.length>16){
            getView()?.showToast("请输入6到16位密码")
            getView()?.dismissLoadingDialog()
            return
        }

        getModel().pwLogin(phone,password).ui({
            getView()?.loginSucceful(it.data!!)
        },{
            getView()?.dismissLoadingDialog()
            getView()?.showToast(it.message)
        })

    }

    override fun codeLogin(phone: String, phone_code: String) {
        if (TextUtils.isEmpty(phone)){
            getView()?.showToast("请输入手机号")
            getView()?.dismissLoadingDialog()
            return
        }else if (11!=phone.length){
            getView()?.showToast("请输入正确的手机号")
            getView()?.dismissLoadingDialog()
            return
        }
        if (TextUtils.isEmpty(phone_code)){
            getView()?.showToast("请输入验证码")
            getView()?.dismissLoadingDialog()
            return
        }else if (6!=phone_code.length){
            getView()?.showToast("请输入6位验证码")
            getView()?.dismissLoadingDialog()
            return
        }
        getModel().codeLogin(phone,phone_code).ui({
            getView()?.loginSucceful(it.data!!)
        },{
            getView()?.dismissLoadingDialog()
            getView()?.showToast(it.message)
        })

    }
    override fun getInfo(token: String) {
        getModel().getInfo(token).ui({
            getView()?.getInfo(it.data!!)
        },{
            getView()?.showToast(it)
        })

    }


    override fun createModel(): LoginContract.Model =LoginModel()
}