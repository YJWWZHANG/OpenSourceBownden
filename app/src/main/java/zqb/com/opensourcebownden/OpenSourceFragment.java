package zqb.com.opensourcebownden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenSourceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_source, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_realm, R.id.btn_green_dao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_realm:
//                RealmActivity.launch(getActivity());
                break;
            case R.id.btn_green_dao:
//                GreenDaoActivity.launch(getActivity());
                break;
        }
    }
}
