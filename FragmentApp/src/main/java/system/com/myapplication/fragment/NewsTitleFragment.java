package system.com.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import system.com.myapplication.R;
import system.com.myapplication.adapter.NewsAdapter;
import system.com.myapplication.entity.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/4/6.
 */

public class NewsTitleFragment extends Fragment
{
    private boolean isTwoPane;
    private NewsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        adapter = new NewsAdapter();
        adapter.setmNewsList(getNews());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<News> getNews(){
        List<News> list = new ArrayList<News>();
        for (int i = 0; i <= 10; i++){
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengContent("This is news content "+i));
            list.add(news);
        }
        return list;
    }

    private String getRandomLengContent(String content){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= 10; i++){
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(getActivity().findViewById(R.id.news_content_fragment)!=null){
            isTwoPane = true;         //双页模式
        }else{
            isTwoPane =false;        //单页模式
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(getActivity().findViewById(R.id.news_content_fragment)!=null){
            isTwoPane = true;         //双页模式
        }else{
            isTwoPane =false;        //单页模式
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_fragment)!=null){
            isTwoPane = true;         //双页模式
        }else{
            isTwoPane =false;        //单页模式
        }
        adapter.setTwoPane(isTwoPane);
    }

}
