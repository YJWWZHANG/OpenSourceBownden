package com.zqb.huanxinim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class HuanXinActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, HuanXinActivity.class));
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
//            ToastUtils.showLong(messages.get(0).toString());
            ToastUtils.showLong(messages.get(0).getBody().toString());
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            ToastUtils.showLong("收到透传消息");
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
            ToastUtils.showLong("收到已读回执");
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
            ToastUtils.showLong("收到已送达回执");
        }
//        @Override
//        public void onMessageRecalled(List<EMMessage> messages) {
//            //消息被撤回
//        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            ToastUtils.showLong("消息状态变动");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huanxin);

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true);
        //初始化
        EMClient.getInstance().init(Utils.getApp(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

//        //注册失败会抛出HyphenateException
//        try {
//            EMClient.getInstance().createAccount("zqb1", "734057028");//同步方法
//        } catch (HyphenateException e) {
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {
            public void run() {
                try {
                    EMClient.getInstance().createAccount("zqb1", "734057028");
                } catch (final HyphenateException ignored) {

                }
            }
        }).start();


        EMClient.getInstance().login("zqb1","734057028",new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                ToastUtils.showLong("登录聊天服务器成功");
            }

            @Override
            public void onProgress(int progress, String status) {
                ToastUtils.showLong("登录聊天服务器中...");
            }

            @Override
            public void onError(int code, String message) {
                ToastUtils.showLong("登录聊天服务器失败");
            }
        });
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
