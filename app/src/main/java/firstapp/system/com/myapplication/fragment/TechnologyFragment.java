package firstapp.system.com.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
import firstapp.system.com.myapplication.BaseFragment;
import firstapp.system.com.myapplication.ProductActivity;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.adapter.DividerGridItemDecoration;
import firstapp.system.com.myapplication.adapter.MainAdapter;
import firstapp.system.com.refreshlibrary.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/1/20.
 */

/**
 * 技术列表文章
 */
public class TechnologyFragment extends BaseFragment implements MainAdapter.OnItemClickLitener
{
    @BindView(R.id.tab_title)
    TabLayout tabTitle;
    @BindView(R.id.it_rc)
    RecyclerView rv;
    private View view;
    public MainAdapter adapter;
    public List<String> list = new ArrayList<String>();

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
        tabTitle.addTab(tabTitle.newTab().setText("Java"));
        tabTitle.addTab(tabTitle.newTab().setText("Android"));
        tabTitle.addTab(tabTitle.newTab().setText("PHP"));
        tabTitle.addTab(tabTitle.newTab().setText("Python"));
        tabTitle.addTab(tabTitle.newTab().setText("JavaScript"));
        tabTitle.addTab(tabTitle.newTab().setText("Ruby"));
        tabTitle.addTab(tabTitle.newTab().setText("Swift"));
        tabTitle.addTab(tabTitle.newTab().setText("PL/SQL"));
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
        }
    }

    @Override
    public void onItemLongClick(View view, int position)
    {

    }
}
