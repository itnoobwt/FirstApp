package system.com.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import system.com.myapplication.MainActivity;
import system.com.myapplication.R;
import system.com.myapplication.activity.NewsContentActivity;
import system.com.myapplication.entity.News;
import system.com.myapplication.fragment.NewsContentFragment;

import java.util.List;

/**
 * Created by user on 2017/4/6.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    private List<News> mNewsList;
    private boolean isTwoPane;
    public void setmNewsList(List<News> mNewsList){
        this.mNewsList = mNewsList;
    }
    public void setTwoPane(boolean isTwoPane){
        this.isTwoPane = isTwoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                News news = mNewsList.get(viewHolder.getAdapterPosition());
                if(isTwoPane){
                    NewsContentFragment newsContentFragment = (NewsContentFragment)((MainActivity)parent.getContext())
                            .getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(news.getTitle(),news.getContent());
                }else{
                    ((MainActivity)parent.getContext()).actionStart(
                            parent.getContext(),news.getTitle(),
                            news.getContent());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        News news = mNewsList.get(position);
        holder.newsTitleText.setText(news.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return mNewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitleText;
        public ViewHolder(View itemView)
        {
            super(itemView);
            newsTitleText = (TextView) itemView.findViewById(R.id.news_title);
        }
    }
}
