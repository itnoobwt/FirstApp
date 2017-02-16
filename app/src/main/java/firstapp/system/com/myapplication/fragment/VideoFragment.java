package firstapp.system.com.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import firstapp.system.com.myapplication.BaseFragment;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.refreshlibrary.ILoadingLayout;
import firstapp.system.com.refreshlibrary.PullToRefreshBase;
import firstapp.system.com.refreshlibrary.PullToRefreshScrollView;

/**
 * Created by user on 2017/1/21.
 */

public class VideoFragment extends BaseFragment
{
    @BindView(R.id.pull_refresh_listview)
    PullToRefreshScrollView pullRefreshListview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        bind(view);
        initIndicator(pullRefreshListview);
        return view;
    }
    public void initIndicator(PullToRefreshScrollView view) {
        ILoadingLayout startLabels = view.getLoadingLayoutProxy(false, true);
        startLabels.setPullLabel("下拉可以刷新");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在刷新...");// 刷新时
        startLabels.setReleaseLabel("松开立即刷新");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = view.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉可以加载更多数据");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在加载...");// 刷新时
        endLabels.setReleaseLabel("松开立即加载更多数据");// 下来达到一定距离时，显示的提示
    }
    @OnClick(R.id.pull_refresh_listview)
    public void setPullRefreshListview(){
        PullToRefreshBase.OnRefreshListener2<ScrollView> onRefreshListener = new PullToRefreshBase
                .OnRefreshListener2<ScrollView>()
        {


            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView)
            {
                Toast.makeText(getActivity(), "刷新1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView)
            {
                Toast.makeText(getActivity(), "刷新2", Toast.LENGTH_SHORT).show();
            }
        };
        pullRefreshListview.setOnRefreshListener(onRefreshListener);
    }
}
