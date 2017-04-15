package firstapp.system.com.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextSwitcher;
import android.widget.Toast;
import butterknife.BindView;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.adapter.ViewPageAdapter;
import firstapp.system.com.myapplication.fragment.TechnologyFragment;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends BaseFragmentActivity implements TextWatcher
{
    @BindView(R.id.act)
    AutoCompleteTextView atc;
    private String[] res = {"beijing1", "beijing2", "beijing3", "shanghai1", "shanghai2", "guangzhou1", "shenzhen"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        atc.addTextChangedListener(this);
        atc.setAdapter(adapter);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterTextChanged(Editable s)
    {
    }
}
