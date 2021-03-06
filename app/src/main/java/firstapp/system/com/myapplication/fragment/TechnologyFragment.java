package firstapp.system.com.myapplication.fragment;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.*;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.activity.LastingActivity;
import firstapp.system.com.myapplication.adapter.DividerGridItemDecoration;
import firstapp.system.com.myapplication.adapter.MainAdapter;
import firstapp.system.com.refreshlibrary.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/1/20.
 */

/**
 * 技术列表文章
 */
public class TechnologyFragment extends BaseFragment implements MainAdapter.OnItemClickLitener,SwipeRefreshLayout.OnRefreshListener
{
    @BindView(R.id.tab_title)
    TabLayout tabTitle;
    @BindView(R.id.it_rc)
    RecyclerView rv;
    private View view;
    public MainAdapter adapter;
    public List<String> list = new ArrayList<String>();
    @BindView(R.id.swipRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_technology, container, false);
        bind(view);
        InitData();
        return view;
    }

    public void InitData()
    {
        list.clear();
        list.add("Retrofit结合OKHTTP使用");
        list.add("C++");
        list.add("PHP");
        list.add("C语言");
        list.add("Android 7.0多窗体");
        list.add("AudioManager简介");
        list.add("Preference示例");
        list.add("指纹");
        list.add("广播");
        list.add("Service使用");
        list.add("Vibrator使用");
        list.add("视频压缩FFmpeg");
        list.add("持久化数据");
        tabTitle.addTab(tabTitle.newTab().setText("Java"));
        tabTitle.addTab(tabTitle.newTab().setText("Android"));
        tabTitle.addTab(tabTitle.newTab().setText("PHP"));
        tabTitle.addTab(tabTitle.newTab().setText("Python"));
        tabTitle.addTab(tabTitle.newTab().setText("JavaScript"));
        tabTitle.addTab(tabTitle.newTab().setText("Ruby"));
        tabTitle.addTab(tabTitle.newTab().setText("Swift"));
        tabTitle.addTab(tabTitle.newTab().setText("PL/SQL"));
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new MainAdapter(getActivity(), list);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerGridItemDecoration(getActivity(), OrientationHelper.VERTICAL));
        rv.setAdapter(adapter);
        tabTitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show();
        if (list.get(position).equals("Retrofit结合OKHTTP使用"))
        {
            startActivity(new Intent(getActivity(), ProductActivity.class));
        }else if(list.get(position).equals("Android 7.0多窗体")){
            startActivity(new Intent(getActivity(), FormActivity.class));
        }else if(list.get(position).equals("AudioManager简介")){
            startActivity(new Intent(getActivity(), AudioManagerActivity.class));
        }else if(list.get(position).equals("Preference示例")){
            startActivity(new Intent(getActivity(), PreferenceActivity.class));
        }else if(list.get(position).equals("指纹")){
            startActivity(new Intent(getActivity(), FingerprintActivity.class));
        }else if(list.get(position).equals("广播")){
            startActivity(new Intent(getActivity(), BroadCastActivity.class));
        }else if(list.get(position).equals("Service使用")){
            startActivity(new Intent(getActivity(), ServiceActivity.class));
        }else if(list.get(position).equals("Vibrator使用")){
            startActivity(new Intent(getActivity(), VibratorActivity.class));
        }else if(list.get(position).equals("视频压缩FFmpeg")){
            startActivity(new Intent(getActivity(), VideoActivity.class));
        }else if(list.get(position).equals("持久化数据")){
            startActivity(new Intent(getActivity(), LastingActivity.class));
        }
    }

    @Override
    public void onItemLongClick(View view, int position)
    {

    }

    @Override
    public void onRefresh()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable()
        {
            @Override
            public void run()
            {
                list.add("Retrofit结合OKHTTP使用");
                list.add("C++");
                list.add("PHP");
                list.add("C语言");
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
