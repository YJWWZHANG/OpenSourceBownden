package zqb.com.opensourcebownden;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

class VpMainAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mMainTitles = new ArrayList<String>() {
        {
            add("开放平台");
            add("开源项目");
            add("学习");
        }
    };

    private ArrayList<Fragment>  mMainFragments = new ArrayList<Fragment>() {
        {
            add(new KaiFangFragment());
            add(new OpenSourceFragment());
            add(new LearnFragment());
        }
    };

    public VpMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mMainFragments.get(position);
    }

    @Override
    public int getCount() {
        return mMainTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMainTitles.get(position);
    }
}
