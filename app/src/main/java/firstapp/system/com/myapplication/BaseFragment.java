package firstapp.system.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2017/1/21.
 */

public class BaseFragment extends Fragment
{
    private Unbinder unbinder;
    /**
     * 绑定资源
     * @param view
     */
    public void bind(View view){
        unbinder = ButterKnife.bind(this,view);
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        unbinder.unbind();
    }
}
