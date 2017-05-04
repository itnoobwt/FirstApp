package www.sinofreely.app.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;
import butterknife.BindView;
import www.sinofreely.app.R;
import www.sinofreely.app.view.adapter.ViewPageAdapter;
import www.sinofreely.app.view.fragment.MainFragment;
import www.sinofreely.app.view.fragment.OperationFragment;
import www.sinofreely.app.view.fragment.ParentsFragment;
import www.sinofreely.app.view.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
{
    @BindView(R.id.main_tab)
    TabLayout main_tab;
    @BindView(R.id.main_viewpage)
    ViewPager main_viewpage;
    private ViewPageAdapter adapter;
    private List<Fragment> list = new ArrayList<>();
    private long firstTime = 0;
    private int[] icon = new int[]{R.mipmap.home_tab_copybook, R.mipmap.home_tab1_operation,R.mipmap
            .home_tab1_parents,R.mipmap.home_tab1_person};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHeaderTitle(getString(R.string.main_name));
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView()
    {
        super.initView();
        MainFragment mainFragment = new MainFragment();
        OperationFragment operationFragment = new OperationFragment();
        ParentsFragment  parentsFragment = new ParentsFragment();
        PersonFragment personFragment = new PersonFragment();
        list.add(mainFragment);
        list.add(operationFragment);
        list.add(parentsFragment);
        list.add(personFragment);
        adapter = new ViewPageAdapter(getSupportFragmentManager(),list,this,main_tab,icon);
        main_viewpage.setAdapter(adapter);
        main_tab.setupWithViewPager(main_viewpage);
        main_viewpage.setOffscreenPageLimit(3);
        main_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                String str = "";

                if (tab == main_tab.getTabAt(0)) {
                    str = "全部字帖";
                    main_tab.getTabAt(0).setIcon(R.mipmap.home_tab_copybook);
                } else if (tab == main_tab.getTabAt(1)) {
                    str = "作业";
                    main_tab.getTabAt(1).setIcon(R.mipmap.home_tab_operation);
                } else if (tab == main_tab.getTabAt(2)) {
                    str = "家长圈";
                    main_tab.getTabAt(2).setIcon(R.mipmap.home_tab_parents);
                }else if (tab == main_tab.getTabAt(3)){
                    str = "我的";
                    main_tab.getTabAt(3).setIcon(R.mipmap.home_tab_person);
                }
                setTool_title(str);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                for (int i = 0;i<main_tab.getTabCount();i++){
                    if(i == 0){
                        main_tab.getTabAt(i).setIcon(R.mipmap.home_tab1_copybook);
                    }else{
                        main_tab.getTabAt(i).setIcon(icon[i]);
                    }

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

    }

    @Override
    public void onBackPressed()
    {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        int count = adapter.getCount();
        for (int i = 0;i<count;i++){
            main_tab.getTabAt(i).setIcon(icon[i]);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }
}
