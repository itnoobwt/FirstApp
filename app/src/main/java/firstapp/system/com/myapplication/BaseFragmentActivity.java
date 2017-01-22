package firstapp.system.com.myapplication;

import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2017/1/21.
 */

public class BaseFragmentActivity extends FragmentActivity
{
    private Unbinder unbinder;
    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this); //绑定资源
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind();  //释放资源
    }
}
