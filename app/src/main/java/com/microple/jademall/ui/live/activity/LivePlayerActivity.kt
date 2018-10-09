package com.microple.jademall.ui.live.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.microple.jademall.BuildConfig
import com.microple.jademall.R
import com.microple.jademall.bean.LiveShare
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.dialog.ShareDialog
import com.microple.jademall.ui.live.adapter.MessageAdapter
import com.microple.jademall.ui.live.mvp.contract.LivePlayerContract
import com.microple.jademall.ui.live.mvp.presenter.LivePlayerPresenter
import com.tencent.imsdk.*
import com.tencent.rtmp.ITXLivePlayListener
import com.tencent.rtmp.TXLiveBase
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayer
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_live_player.*

class LivePlayerActivity : BaseMvpActivity<LivePlayerPresenter>(),LivePlayerContract.View {
    var messageAdapter=MessageAdapter()
    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_goods.setOnClickListener {
            LiveGoodsActivity.startGoodsActivity(this,intent.getStringExtra("live_id"))
        }
        iv_share.setOnClickListener {
            showLoadingDialog()
            getPresenter().liveShare(intent.getStringExtra("live_id"))

        }
        et_send.setOnEditorActionListener { view, i, keyEvent ->
            var textElem=TIMTextElem()
            textElem.text=view.text.toString()
            var message=TIMMessage()
            message.addElement(textElem)
            var conversation=TIMManager.getInstance().getConversation(TIMConversationType.Group,"")
            conversation.sendMessage(message, object : TIMValueCallBack<TIMMessage> {
                override fun onSuccess(p0: TIMMessage?) {
                    showToast("发送成功")
                }

                override fun onError(p0: Int, p1: String?) {
                    showToast("发送失败")
                }

            })
            return@setOnEditorActionListener  true
        }

    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): LivePlayerPresenter = LivePlayerPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_live_player


    /**
     * 初始化数据状态
     */
    override fun initData() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        (application as App).addActivity(this)
        play()
        hideStatusBar()

    }

    override fun liveShare(liveShare: LiveShare) {
        dismissLoadingDialog()
        var dialog=ShareDialog(this)
        dialog.show()
        dialog.setOnBtnClickListener(object : ShareDialog.OnBtnClickListener {
            override fun QQShare() {
                share(SHARE_MEDIA.QQ.toSnsPlatform(),liveShare)
            }

            override fun weiboShare() {
                share(SHARE_MEDIA.SINA.toSnsPlatform(),liveShare)

            }

            override fun wxShare() {
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform(),liveShare)

            }

            override fun wxwcShare() {
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform(),liveShare)

            }

            override fun cancel() {
                dialog.dismiss()

            }

        })
    }

    var mLivePlayer:TXLivePlayer?=null
    fun play(){
        loading_progress.visibility=View.VISIBLE
        val sdkver = TXLiveBase.getSDKVersionStr()
        Log.d("liteavsdk", "liteav sdk version is : $sdkver")
        mLivePlayer=TXLivePlayer(this)
        mLivePlayer?.setPlayerView(video_view)
        val flvUrl = intent.getStringExtra("play_url")//"rtmp://30381.liveplay.myqcloud.com/live/30381_83c9d76844"
        mLivePlayer?.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP)
        mLivePlayer?.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN)
        mLivePlayer?.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT)
        mLivePlayer?.setPlayListener(object : ITXLivePlayListener {
            override fun onPlayEvent(event: Int, param: Bundle?) {
                Log.i("eventevent","event"+event)
                when(event){
                    TXLiveConstants.PLAY_ERR_NET_DISCONNECT->{
                        loading_progress.visibility=View.VISIBLE
                        showToast("网络已断开")
                        Log.i("TXLiveConstants","网络已断开")
                    }
                    TXLiveConstants.PLAY_EVT_PLAY_LOADING->{
                        loading_progress.visibility=View.VISIBLE
                        Log.i("TXLiveConstants","视频播放再加载")
                    }
                    TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN->{
                        loading_progress.visibility=View.GONE
                        Log.i("TXLiveConstants","视频播放开始")
                    }
                    TXLiveConstants.PLAY_EVT_PLAY_END->{
                        loading_progress.visibility=View.GONE
                        tv_jieshu.visibility=View.VISIBLE
                        Log.i("TXLiveConstants","视频播放结束")
                    }
                    TXLiveConstants.PLAY_EVT_CONNECT_SUCC->{
                        Log.i("TXLiveConstants","已经连接服务器")
                    }
                    TXLiveConstants.PLAY_ERR_NET_DISCONNECT->{
//                        loading_progress.visibility=View.VISIBLE

                    }
                    TXLiveConstants.PLAY_EVT_PLAY_BEGIN->{
                        loading_progress.visibility=View.GONE
                    }
                    TXLiveConstants.PLAY_WARNING_READ_WRITE_FAIL->{
                        loading_progress.visibility=View.GONE
                        tv_jieshu.visibility=View.VISIBLE
                        tv_jieshu.text="直播未开始"
                    }

                }
            }

            override fun onNetStatus(p0: Bundle?) {
            }

        })
        //聊天室
        if (Constants.isLogin()){
            rl_message.layoutManager=LinearLayoutManager(this)
            rl_message.adapter=messageAdapter
            TIMManager.getInstance().addMessageListener {
                messageAdapter.addData(it)
                return@addMessageListener true
            }
            rl_message.scrollToPosition(messageAdapter.getItemCount()-1)
            imLogin()
        }
        joinRoom("")
        iv_close.setOnClickListener{
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mLivePlayer?.stopPlay(true)
        video_view.onDestroy()
        TIMManager.getInstance().logout(null)
        (application as App).deleteActivity(this)
    }
    companion object {
        fun startLivePlayerActivity(context: Context,live_id:String,play_url:String){
            val intent = Intent(context, LivePlayerActivity::class.java)
            intent.putExtra("live_id",live_id)
            intent.putExtra("play_url",play_url)
            context.startActivity(intent)
        }
    }
    //只透明状态栏
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
    private fun share(platform: SnsPlatform,liveShare: LiveShare){
        showLoadingDialog()
        if (liveShare!=null){
            var web = UMWeb(liveShare?.share?.link)
            web.setTitle(liveShare?.share?.live_title)
            web.setThumb( UMImage(this, BuildConfig.DEV_DOMAIN+"/api/"+liveShare?.share?.cover_img))
            web.setDescription(liveShare?.share?.desc)
            ShareAction(this)
                    .withMedia(web)
                    .setPlatform(platform.mPlatform)
                    .setCallback(umShareListener).share()
        }else{
            showToast("没有分享内容")
        }
        dismissLoadingDialog()
    }
    //登录聊天室
    private fun imLogin(){
        var timeUser=TIMUserConfig()
        TIMManager.getInstance().login(Constants.getImIndent(),Constants.getImuser(),timeUser, object : TIMCallBack {
            override fun onError(code: Int, desc: String?) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.d("ImLogin", "login failed. code: " + code + " errmsg: " + desc)
            }

            override fun onSuccess() {
                showToast("登陆成功")
            }
        })
    }
    //加入聊天室
    private fun joinRoom(groupID:String){
        TIMGroupManager.getInstance().applyJoinGroup(groupID,"", object : TIMCallBack {
            override fun onError(p0: Int, p1: String?) {
                Log.i("groupID","加入失败")
             }

            override fun onSuccess() {
                Log.i("groupID","加入成功")
            }
        })
    }

    private var umShareListener= object : UMShareListener {
        override fun onResult(p0: SHARE_MEDIA?) {
            showToast("分享成功")
        }

        override fun onCancel(p0: SHARE_MEDIA?) {
            showToast("分享取消")
        }

        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
            showToast("分享失败")
        }

        override fun onStart(p0: SHARE_MEDIA?) {
        }
    }

}
