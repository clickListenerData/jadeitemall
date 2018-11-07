package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/9/20
 * describe:
 */
public class LiveDetail {

    /**
     * detail : {"live_id":1,"live_title":"3g66572号切石直播","desc":"测试测试测试","cover_img":"/uploads/20180918/81dcad2de5b548ecdee253bafd97ea9e.jpg","live_time":"2018-09-19 14:40","live_code":"31624_4c24b1219e","is_order":1,"play_url":["rtmp://31624.liveplay.myqcloud.com/live/31624_4c24b1219e","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.flv","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.m3u8"]}
     */

    private DetailBean detail;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {

        /**
         * live_id : 44
         * live_title : 直播测试1026
         * desc : 测试1026
         * cover_img : /uploads/20181026/29a173989c9ea7c3581e530b1ad7bbf4.jpg
         * live_time : 2018-10-26 17:40
         * live_code : 33550_12fc5cf900
         * group_id : @TGS#3PUPEUPFH
         * supplier_id : 1
         * supplier_name :
         * head_img :
         * intro :
         * is_order : 1
         * play_url : ["rtmp://33550.liveplay.myqcloud.com/live/33550_12fc5cf900","http://33550.liveplay.myqcloud.com/live/33550_12fc5cf900.flv","http://33550.liveplay.myqcloud.com/live/33550_12fc5cf900.m3u8"]
         */

        private int live_id;
        private String live_title;
        private String desc;
        private String cover_img;
        private String live_time;
        private String live_code;
        private String group_id;
        private int supplier_id;
        private String supplier_name;
        private String head_img;
        private String intro;
        private int is_order;
        private List<String> play_url;

        public int getLive_id() {
            return live_id;
        }

        public void setLive_id(int live_id) {
            this.live_id = live_id;
        }

        public String getLive_title() {
            return live_title;
        }

        public void setLive_title(String live_title) {
            this.live_title = live_title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }

        public String getLive_code() {
            return live_code;
        }

        public void setLive_code(String live_code) {
            this.live_code = live_code;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getIs_order() {
            return is_order;
        }

        public void setIs_order(int is_order) {
            this.is_order = is_order;
        }

        public List<String> getPlay_url() {
            return play_url;
        }

        public void setPlay_url(List<String> play_url) {
            this.play_url = play_url;
        }
    }


}
