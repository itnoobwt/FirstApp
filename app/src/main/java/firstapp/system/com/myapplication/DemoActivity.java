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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
