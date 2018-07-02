package zqb.com.touchevent;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqb on 2018/3/11.
 */

public class TouchEventAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TouchEventAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public TouchEventAdapter(@Nullable List<String> data) {
        super(data);
    }

    public TouchEventAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
