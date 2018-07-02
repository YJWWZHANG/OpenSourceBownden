package zqb.com.opensourcebownden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zqb.com.touchevent.TouchEventActivity;

public class LearnFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({ R.id.btn_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_event:
                TouchEventActivity.launch(getActivity());
                break;
        }
    }

}
