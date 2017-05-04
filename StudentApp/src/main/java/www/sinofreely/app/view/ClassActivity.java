package www.sinofreely.app.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.BindView;
import www.sinofreely.app.R;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends BaseActivity
{
    @BindView(R.id.listview_school)
    ListView listviewSchool;
    @BindView(R.id.listview_class)
    ListView listviewClass;
    private List<String> list = new ArrayList<>();
    private List<String> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHeaderTitle(getString(R.string.registered_class_tv));
        isBack(true);
        setContentView(R.layout.activity_class);
    }

    @Override
    protected void initView()
    {
        super.initView();

    }

    @Override
    protected void loadData()
    {
        super.loadData();
        for (int i = 0; i < 10; i++)
        {
            list.add("学校" + i);
        }
        for (int i = 0; i < 10; i++)
        {
            mlist.add("一年级" + i + "班");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                mlist);
        listviewClass.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                list);
        listviewSchool.setAdapter(adapter1);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
