package firstapp.system.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import firstapp.system.com.myapplication.R;

import java.util.List;

/**
 * Created by user on 2016/9/13.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.HolderView>
{
    private Context context;
    private List<String> list;
    public MainAdapter(Context context,List<String> list)
    {
        this.context = context;
        this.list = list;
    }
    private OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
        this.onItemClickLitener = onItemClickLitener;
    }

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }



    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.itme,parent,false);
        HolderView holderView = new HolderView(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position)
    {
        holder.tv.setText(list.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onItemClickLitener.onItemClick(view,position);
            }
        });
        holder.tv.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                onItemClickLitener.onItemLongClick(view,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class HolderView extends RecyclerView.ViewHolder{
        TextView tv;
        public HolderView(View itemView)
        {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

    }
}
