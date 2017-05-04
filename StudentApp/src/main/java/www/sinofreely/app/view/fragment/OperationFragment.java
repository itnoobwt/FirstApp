package www.sinofreely.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import www.sinofreely.app.R;
import www.sinofreely.app.utils.LogUtils;
import www.sinofreely.app.view.adapter.OperationAdapter;

/**
 * Created by user on 2017/4/20.
 */

public class OperationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{

    @BindView(R.id.operation_rcv)
    RecyclerView operationRcv;
    @BindView(R.id.operation_srl)
    SwipeRefreshLayout operationSrl;
    private OperationAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operation_fragment, container, false);
        ButterKnife.bind(this,view);
        adapter = new OperationAdapter();
        operationSrl.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        operationRcv.setLayoutManager(manager);
        operationRcv.setAdapter(adapter);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            operationRcv.setOnScrollChangeListener(new View.OnScrollChangeListener()
            {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
                {
                    LogUtils.e("位置","x:"+scrollX+" y:"+scrollY+"  oldX:"+oldScrollX+" oldY:"+oldScrollY);
                }

            });
        }
        return view;
    }

    @Override
    public void onRefresh()
    {
        operationSrl.setRefreshing(false);
    }
}
