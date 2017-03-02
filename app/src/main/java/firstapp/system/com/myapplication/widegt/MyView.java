package firstapp.system.com.myapplication.widegt;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import firstapp.system.com.myapplication.R;

/**
 * Created by user on 2017/3/1.
 */

public class MyView extends View
{
    public MyView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DogStyle,defStyleAttr,0);
        String name = typedArray.getString(R.styleable.DogStyle_dogName);
    }

    //    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
//    {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
