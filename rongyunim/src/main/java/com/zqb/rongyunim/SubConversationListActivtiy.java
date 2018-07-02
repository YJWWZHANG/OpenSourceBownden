package com.zqb.rongyunim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class SubConversationListActivtiy extends FragmentActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SubConversationListActivtiy.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subconversationlist);
        RongIM.init(Utils.getApp());
        connect("WLGsFNqJ+srKUAP9I8RTDNqahzpEga7VlYa6p0i9XTUcIsAmUSYSDrOpJHcCECrsi7prh0T0Ujk=");
    }

    private void connect(String token) {
//        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                LogUtils.w("LoginActivity", "--onSuccess" + userid);
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.w("LoginActivity", "--onError:" + errorCode.getMessage());
            }
        });
//        }
    }

}