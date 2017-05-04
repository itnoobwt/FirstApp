package www.sinofreely.app.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import www.sinofreely.app.R;

/**
 * Created by user on 2017/4/26.
 */

public class OperationAdapter extends RecyclerView.Adapter<ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_operation,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.ada_operation_time.setText("2017-04-12 08:30:45");
        holder.ada_operation_name.setText("王老师");
        holder.ada_operation_lesson.setText("课程名称：书法碑帖 第五课时 第二次作业");
    }

    @Override
    public int getItemCount()
    {
        return 9;
    }
}
class ViewHolder extends RecyclerView.ViewHolder{
    TextView ada_operation_time,ada_operation_name,ada_operation_lesson;
    public ViewHolder(View itemView)
    {
        super(itemView);
        ada_operation_time = (TextView) itemView.findViewById(R.id.ada_operation_time);
        ada_operation_name = (TextView) itemView.findViewById(R.id.ada_operation_name);
        ada_operation_lesson = (TextView) itemView.findViewById(R.id.ada_operation_lesson);
    }
}
