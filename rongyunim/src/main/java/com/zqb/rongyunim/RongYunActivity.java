package com.zqb.rongyunim;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import io.rong.imkit.MainActivity;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class RongYunActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, RongYunActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rongyun);
        RongIM.init(Utils.getApp());


        RongIM.connect("WLGsFNqJ+srKUAP9I8RTDNqahzpEga7VlYa6p0i9XTUcIsAmUSYSDrOpJHcCECrsi7prh0T0Ujk=", new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                LogUtils.w("connect", "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.w("connect", "onSuccess userid:" + s);

                ConversationListFragment listFragment = new ConversationListFragment();
                listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
                listFragment.setUri(Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build());
                getSupportFragmentManager().beginTransaction().replace(R.id.conversationlist, listFragment).commit();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.w("connect", "onError errorcode:" + errorCode.getValue());
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }

//    /**
//     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link #init(Context)} 之后调用。</p>
//     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
//     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
//     *
//     * @param token    从服务端获取的用户身份令牌（Token）。
//     * @param callback 连接回调。
//     * @return RongIM  客户端核心类的实例。
//     */
//    private void connect(String token) {
////        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {
//
//            RongIM.connect(token, new RongIMClient.ConnectCallback() {
//
//                /**
//                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//                 */
//                @Override
//                public void onTokenIncorrect() {
//
//                }
//
//                /**
//                 * 连接融云成功
//                 * @param userid 当前 token 对应的用户 id
//                 */
//                @Override
//                public void onSuccess(String userid) {
//                    LogUtils.w("LoginActivity", "--onSuccess" + userid);
////                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                    finish();
//                }
//
//                /**
//                 * 连接融云失败
//                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
//                 */
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//                    LogUtils.w("LoginActivity", "--onError:" + errorCode.getMessage());
//                }
//            });
////        }
//    }
}
