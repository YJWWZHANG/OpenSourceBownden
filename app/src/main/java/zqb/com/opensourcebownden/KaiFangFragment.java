package zqb.com.opensourcebownden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zqb.com.baidumap.BaiduMapActivity;
import zqb.com.gaodemap.GaodeActivity;

public class KaiFangFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kaifang, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_alipush, R.id.btn_baidumap, R.id.btn_gaodemap, R.id.btn_alipay,
            R.id.btn_wechatpay, R.id.btn_rongyun_im, R.id.btn_huanxin, R.id.btn_wyyunxin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_alipush:
//                AliPushActivity.launch(getActivity());
                break;
            case R.id.btn_baidumap:
                BaiduMapActivity.launch(getActivity());
                break;
            case R.id.btn_gaodemap:
                GaodeActivity.launch(getActivity());
                break;
            case R.id.btn_alipay:
//                AlipayActivity.launch(getActivity());
                break;
            case R.id.btn_wechatpay:
//                WechatPayActivity.launch(getActivity());
                break;
            case R.id.btn_rongyun_im:
//                RongYunActivity.launch(getActivity());
//                SubConversationListActivtiy.launch(getActivity());
                break;
            case R.id.btn_huanxin:
//                HuanXinActivity.launch(getActivity());
                break;
            case R.id.btn_wyyunxin:
//                YunXinActivity.launch(getActivity());
                break;
            default:
                break;
        }
    }
}
