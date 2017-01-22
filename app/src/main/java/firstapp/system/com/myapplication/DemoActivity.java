package firstapp.system.com.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import butterknife.BindView;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.adapter.ViewPageAdapter;
import firstapp.system.com.myapplication.fragment.TechnologyFragment;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends BaseFragmentActivity
{
    @BindView(R.id.content_main_viewpage)
    ViewPager viewPage;
    @BindView(R.id.content_main_tablayout)
    TabLayout tablayout;
    private ViewPageAdapter adapter;
    private List<Fragment> list = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        TechnologyFragment fragment = new TechnologyFragment();
        list.add(fragment);
        adapter = new ViewPageAdapter(getSupportFragmentManager(),list,this);
        viewPage.setAdapter(adapter);
//        tablayout.setupWithViewPager(viewPage);
//        tablayout.addTab(tablayout.newTab().setText("主页"));
//        TabLayout.Tab tab = tablayout.getTabAt(0);
//        tab.setIcon(R.mipmap.aa);
//        tablayout.getTabAt(1).setIcon(R.mipmap.bb);
//        tablayout.getTabAt(2).setIcon(R.mipmap.cc);
//        tablayout.getTabAt(3).setIcon(R.mipmap.dd);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
