package com.microple.jademall.bean;

/**
 * author: xiaoguagnfei
 * date: 2018/8/29
 * describe:
 */
public class Login {

    /**
     * token : 0e804fed10b0c63433a4e19fcf8d000c
     * code : Yf0wWq
     */

    private String token;
    private String code;
    private String head_img;
    private String im_identifier;
    private String im_user_sig;
    private int is_phone;

    public int getIs_phone() {
        return is_phone;
    }

    public void setIs_phone(int is_phone) {
        this.is_phone = is_phone;
    }

    public String getIm_identifier() {
        return im_identifier;
    }

    public void setIm_identifier(String im_identifier) {
        this.im_identifier = im_identifier;
    }

    public String getIm_user_sig() {
        return im_user_sig;
    }

    public void setIm_user_sig(String im_user_sig) {
        this.im_user_sig = im_user_sig;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
