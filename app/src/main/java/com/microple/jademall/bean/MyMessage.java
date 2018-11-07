package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/9/3
 * describe:
 */
public class MyMessage {

    private List<MessagesBean> messages;

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * msg_id : 15
         * user_id : 86
         * msg_content : 老哥，帮我付一下
         * msg_time : 2018-11-05 19:09:31
         * type : 2
         * is_push : 1
         * msg_param : {"userId":85,"sb_id":"","send":"fcyhl1313/fcsb124","live":"fcjj1005","cabinet":"","address_id":"68","freight_pay":"","incr_type1":"","incr_type2":"","incr_type3":"","head_img":"/uploads/20181015/252201740563792.jpg","user_name":"有话好好说","total_fee":29003.03}
         */

        private String msg_id;
        private String user_id;
        private String msg_content;
        private String msg_time;
        private int type;
        private String is_push;
        private MsgParamBean msg_param;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public void setMsg_content(String msg_content) {
            this.msg_content = msg_content;
        }

        public String getMsg_time() {
            return msg_time;
        }

        public void setMsg_time(String msg_time) {
            this.msg_time = msg_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIs_push() {
            return is_push;
        }

        public void setIs_push(String is_push) {
            this.is_push = is_push;
        }

        public MsgParamBean getMsg_param() {
            return msg_param;
        }

        public void setMsg_param(MsgParamBean msg_param) {
            this.msg_param = msg_param;
        }

        public static class MsgParamBean {
            /**
             * userId : 85
             * sb_id :
             * send : fcyhl1313/fcsb124
             * live : fcjj1005
             * cabinet :
             * address_id : 68
             * freight_pay :
             * incr_type1 :
             * incr_type2 :
             * incr_type3 :
             * head_img : /uploads/20181015/252201740563792.jpg
             * user_name : 有话好好说
             * total_fee : 29003.03
             */

            private int userId;
            private String sb_id;
            private String send;
            private String live;
            private String cabinet;
            private String address_id;
            private String freight_pay;
            private String incr_type1;
            private String incr_type2;
            private String incr_type3;
            private String head_img;
            private String user_name;
            private double total_fee;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getSb_id() {
                return sb_id;
            }

            public void setSb_id(String sb_id) {
                this.sb_id = sb_id;
            }

            public String getSend() {
                return send;
            }

            public void setSend(String send) {
                this.send = send;
            }

            public String getLive() {
                return live;
            }

            public void setLive(String live) {
                this.live = live;
            }

            public String getCabinet() {
                return cabinet;
            }

            public void setCabinet(String cabinet) {
                this.cabinet = cabinet;
            }

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getFreight_pay() {
                return freight_pay;
            }

            public void setFreight_pay(String freight_pay) {
                this.freight_pay = freight_pay;
            }

            public String getIncr_type1() {
                return incr_type1;
            }

            public void setIncr_type1(String incr_type1) {
                this.incr_type1 = incr_type1;
            }

            public String getIncr_type2() {
                return incr_type2;
            }

            public void setIncr_type2(String incr_type2) {
                this.incr_type2 = incr_type2;
            }

            public String getIncr_type3() {
                return incr_type3;
            }

            public void setIncr_type3(String incr_type3) {
                this.incr_type3 = incr_type3;
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

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }
        }
    }
}
