package www.sinofreely.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import www.sinofreely.app.R;

/**
 * Created by user on 2017/4/20.
 */

public class MainFragment extends Fragment
{
    @BindView(R.id.main_rcv)
    RecyclerView mainRcv;
    @BindView(R.id.main_srl)
    SwipeRefreshLayout mainSrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
