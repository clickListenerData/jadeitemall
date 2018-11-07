package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/11/6
 * describe:
 */
public class Daifu {

    private List<RecordBean> record;

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * pay_user_phone : 15915872606
         * head_img : /uploads/20181015/252201547389659.jpg
         * user_name : 年少有為
         */

        private String pay_user_phone;
        private String head_img;
        private String user_name;

        public String getPay_user_phone() {
            return pay_user_phone;
        }

        public void setPay_user_phone(String pay_user_phone) {
            this.pay_user_phone = pay_user_phone;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
