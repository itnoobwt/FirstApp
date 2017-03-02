package firstapp.system.com.myapplication.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import firstapp.system.com.myapplication.R;

import java.util.List;

/**
 * Created by user on 2017/1/20.
 */

public class ViewPageAdapter extends FragmentPagerAdapter
{
    private String[] tab_list;
    private List<Fragment> list;
    private TabLayout tabLayout;
    private int[] icon = new int[]{R.mipmap.aa,R.mipmap.bb,R.mipmap.cc};
    public ViewPageAdapter(FragmentManager fm, List<Fragment> list, Context context,TabLayout tabLayout)
    {
        super(fm);
        this.list = list;
        tab_list = context.getResources().getStringArray(R.array.tab_list);
        this.tabLayout = tabLayout;
    }

    @Override
    public Fragment getItem(int position)
    {
        getIconResId(position);
        return list.get(position);
    }

    public TabLayout.Tab getIconResId(int index)
    {
        return tabLayout.getTabAt(index).setIcon(icon[index]);
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
