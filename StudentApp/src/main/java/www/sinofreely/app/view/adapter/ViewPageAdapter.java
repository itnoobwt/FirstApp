package www.sinofreely.app.view.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import www.sinofreely.app.R;

import java.util.List;

/**
 * Created by user on 2017/4/20.
 */

public class ViewPageAdapter extends FragmentPagerAdapter
{
    private String[] tab_list;
    private List<Fragment> list;
    private int[] icon ;
    private TabLayout tabLayout;
    public ViewPageAdapter(FragmentManager fm, List<Fragment> list, Context context, TabLayout tabLayout,int[] icon)
    {
        super(fm);
        this.list = list;
        tab_list = context.getResources().getStringArray(R.array.tab_list);
        this.tabLayout = tabLayout;
        this.icon = icon;
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
        CharSequence charSequence = tab_list[position];
        return charSequence;
    }

}
