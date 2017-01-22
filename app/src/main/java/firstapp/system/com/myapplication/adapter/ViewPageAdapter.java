package firstapp.system.com.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.fragment.TechnologyFragment;

import java.util.List;

/**
 * Created by user on 2017/1/20.
 */

public class ViewPageAdapter extends FragmentPagerAdapter
{
    private String[] tab_list;
    private List<Fragment> list;
    public ViewPageAdapter(FragmentManager fm, List<Fragment> list, Context context)
    {
        super(fm);
        this.list = list;
        tab_list = context.getResources().getStringArray(R.array.tab_list);
    }

    @Override
    public Fragment getItem(int position)
    {

        return list.get(position);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tab_list[position];
    }
}
