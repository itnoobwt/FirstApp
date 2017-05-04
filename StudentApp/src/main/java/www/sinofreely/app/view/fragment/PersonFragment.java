package www.sinofreely.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import www.sinofreely.app.R;

/**
 * Created by user on 2017/4/20.
 */

public class PersonFragment extends Fragment
{
    @BindView(R.id.persion_icon)
    SimpleDraweeView persionIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        ButterKnife.bind(this, view);
        persionIcon.setImageURI("http://avatar.csdn.net/A/E/5/1_csdnwt.jpg");
        return view;
    }

}
