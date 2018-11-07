package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
public class Attention {

    private List<FollowBean> follow;

    public List<FollowBean> getFollow() {
        return follow;
    }

    public void setFollow(List<FollowBean> follow) {
        this.follow = follow;
    }

    public static class FollowBean {
        /**
         * follow_id : 1
         * user_id : 86
         * supplier_id : 1
         * add_time : 1540972715
         * supplier_name : 拼多多
         * live_list : [{"live_id":37,"live_title":"fcys1301切石直播","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_18a291ffda?bizid=33550&txSecret=ed527ac601f75a106fd93891438092e8&txTime=5BC75C7F","desc":"翡翠王","live_time":1539739800,"supplier_id":1,"cover_img":"/uploads/20181017/4879dd588e93d5316579b01c9ffb2a75.jpeg","live_code":"33550_18a291ffda","end_time":1539790200,"live_type":2,"live_stone":"5","group_id":"@TGS#3RKKGGPFN"},{"live_id":38,"live_title":"测试 10-17","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_76c40785fa?bizid=33550&txSecret=32207bcd8d1e20c9b7533e2b0153543a&txTime=5BC75C7F","desc":"绿色翡翠","live_time":1539757800,"supplier_id":1,"cover_img":"/uploads/20181017/f6542b90ea4749d2928947c6e19c81e8.jpeg","live_code":"33550_76c40785fa","end_time":1539791100,"live_type":3,"live_stone":"","group_id":"@TGS#3C5RGGPFO"},{"live_id":44,"live_title":"直播测试1026","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_12fc5cf900?bizid=33550&txSecret=b61657c55729180f38ceb9a66c0a39b8&txTime=5BD339FF","desc":"测试1026","live_time":1540546800,"supplier_id":1,"cover_img":"/uploads/20181026/29a173989c9ea7c3581e530b1ad7bbf4.jpg","live_code":"33550_12fc5cf900","end_time":1540554000,"live_type":1,"live_stone":"","group_id":"@TGS#3PUPEUPFH"},{"live_id":46,"live_title":"直播测试10-31","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_c232d43212?bizid=33550&txSecret=14d41fb277b0e8ea30e6ae48a0d636b4&txTime=5BD9D17F","desc":"测试111","live_time":1540976700,"supplier_id":1,"cover_img":"/uploads/20181031/54f57af2d0773423f1b465f435327fa1.jpg","live_code":"33550_c232d43212","end_time":1540987500,"live_type":1,"live_stone":"","group_id":"@TGS#3FXBE4PFP"},{"live_id":47,"live_title":"直播测试1810","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_6d19eba328?bizid=33550&txSecret=56feb4f6fad3d992270f5d932a8a033a&txTime=5BD9D17F","desc":"测试","live_time":1540980720,"supplier_id":1,"cover_img":"/uploads/20181031/869f4803f1085352de914df6bf36ef0a.jpg","live_code":"33550_6d19eba328","end_time":1540987920,"live_type":1,"live_stone":"","group_id":"@TGS#3ZMHH4PFC"},{"live_id":48,"live_title":"直播测试11-1","push_url":"rtmp://33550.livepush.myqcloud.com/live/33550_8b36538b7b?bizid=33550&txSecret=eb29f0fac015025774e2f6038eaa19a9&txTime=5BDB22FF","desc":"ceshi","live_time":1541037300,"supplier_id":1,"cover_img":"/uploads/20181101/5da0e4aa9b6be1b118db2025adfecd34.jpg","live_code":"33550_8b36538b7b","end_time":1541073300,"live_type":1,"live_stone":"","group_id":"@TGS#3F3XK5PFH"}]
         */

        private String follow_id;
        private String user_id;
        private String supplier_id;
        private String add_time;
        private String supplier_name;
        private String head_img;
        private List<LiveListBean> live_list;

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getFollow_id() {
            return follow_id;
        }

        public void setFollow_id(String follow_id) {
            this.follow_id = follow_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public List<LiveListBean> getLive_list() {
            return live_list;
        }

        public void setLive_list(List<LiveListBean> live_list) {
            this.live_list = live_list;
        }

        public static class LiveListBean {
            /**
             * live_id : 37
             * live_title : fcys1301切石直播
             * push_url : rtmp://33550.livepush.myqcloud.com/live/33550_18a291ffda?bizid=33550&txSecret=ed527ac601f75a106fd93891438092e8&txTime=5BC75C7F
             * desc : 翡翠王
             * live_time : 1539739800
             * supplier_id : 1
             * cover_img : /uploads/20181017/4879dd588e93d5316579b01c9ffb2a75.jpeg
             * live_code : 33550_18a291ffda
             * end_time : 1539790200
             * live_type : 2
             * live_stone : 5
             * group_id : @TGS#3RKKGGPFN
             */

            private String live_id;
            private String live_title;
            private String push_url;
            private String desc;
            private String live_time;
            private String supplier_id;
            private String cover_img;
            private String live_code;
            private String end_time;
            private String live_type;
            private String live_stone;
            private String group_id;

            public String getLive_id() {
                return live_id;
            }

            public void setLive_id(String live_id) {
                this.live_id = live_id;
            }

            public String getLive_title() {
                return live_title;
            }

            public void setLive_title(String live_title) {
                this.live_title = live_title;
            }

            public String getPush_url() {
                return push_url;
            }

            public void setPush_url(String push_url) {
                this.push_url = push_url;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getLive_time() {
                return live_time;
            }

            public void setLive_time(String live_time) {
                this.live_time = live_time;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getLive_code() {
                return live_code;
            }

            public void setLive_code(String live_code) {
                this.live_code = live_code;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getLive_type() {
                return live_type;
            }

            public void setLive_type(String live_type) {
                this.live_type = live_type;
            }

            public String getLive_stone() {
                return live_stone;
            }

            public void setLive_stone(String live_stone) {
                this.live_stone = live_stone;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }
        }
    }
}
