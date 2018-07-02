package zqb.com.alipay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * 创建时间:2018/3/24 00:17
 * 作者:zhangqianbin
 * 邮箱:zhangqianbin3@foxmail.com
 * 功能描述:
 * 修改时间:
 * 修改描述:
 */
public class AlipayActivity extends AppCompatActivity {

    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL51jaxQhxW9PnWpW+nz6yJ76tp9eGFXmfGnuxMK+Pmx/qavdsewXOLBfI2OSCR39TzxwMYvCmUrnrt0fVSa7mblbNos2FnMM9ijnx8bsAAhm+i7BKhuaHMunJKH69L+D753zH3P1YIh0ly5DnAr3WPqHydp384qBvb8NS9Tay0HAgMBAAECgYB82PIVknP6fCMFXg8yPQJViIVa1ASlSpdPIXQv93FdvKABA+QI4kMBIXRUFoCT506KtK55OzzFNOLIXoQJgcXj69z0l6pmjJJgXMaBW/9rOzelot13CiGatrIrGngEZO+bCBTud/jQA598zjZ1g182tT+FLDL7GIftW2hC8GqtAQJBAN+XrYsyfL+uSmLdAVEz1vzziU1naGr10Msm1jMnnO/JYdB+84j7FSHxsQ4YOgsmeN5YVsJcVfc/CReOxknns38CQQDaEHnVPDt+Z7sqT7bN0UKh0/CrqkDTiIjhz1lJyIIoqVRoeJjJn1wlEKBV5R9gkTJutQTVU19XFtblMEnOy6p5AkEAw170rEmMSa0QoHw+d2bVtydR1QnDapqqO6kOx5oYfkm4J4eWYx4J5CQdMpSmuzF9scL85E3sa+NvnV8LEm7cHwJALtXzFPWG4bNt47yTSslzQka/Hl/G5Kginj1mtA44xnr4AihEyKlNpThY95nqj1cgOd7vVtI9W/sv1LH2aFAeIQJBAIqXbMc6xGVfuiFAJKtg+AFNMBP0UOEgMEoKo4RPFp21nBhFgL9/WYM4ZjyHUdr45rCySAqQovw4DCHLfQZC23I=";
    private String mOrderInfo;
    private String mSign;

    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                CommonDialog.showToast(activity1, true,"支付成功");
                ToastUtils.showLong("支付成功");
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            }
        }

        ;
    };

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AlipayActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);

        /**
         * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
         *
         */
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);

        mOrderInfo = "partner=\"2088011085074233\"&seller_id=\"917356107@qq.com\"&out_trade_no=\"" + key + "\"&subject=\"测试的商品\"&body=\"该测试商品的详细描述\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"";

        /**
         * sign the order info. 对订单信息进行签名
         *
         * @param content
         *            待签名订单信息
         */
        mSign = SignUtils.sign(mOrderInfo, RSA_PRIVATE);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            mSign = URLEncoder.encode(mSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Thread payThread = new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(AlipayActivity.this);
                Map<String, String> result = alipay.payV2(mOrderInfo + "&sign=\"" + mSign + "\"&sign_type=\"RSA\"", true);

                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        });
        payThread.start();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
