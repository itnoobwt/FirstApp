package firstapp.system.com.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import firstapp.system.com.myapplication.R;

/**
 * Created by user on 2017/1/21.
 */

/**
 * 新闻碎片
 */
public class NewsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        return view;
    }
}
