package www.sinofreely.app.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.sinofreely.app.R;

public class LoginActivity extends BaseActivity
{

    @BindView(R.id.login_user_actv)
    AutoCompleteTextView loginUserActv;
    String[] data = new String[]{"aaa","bbb","ccc","bac","adc"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                data);
        loginUserActv.setAdapter(adapter);
        loginUserActv.setThreshold(1);
    }

    @OnClick(R.id.login_btn)
    public void login()
    {

        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.login_registered)
    public void registered()
    {
        startActivity(new Intent(this, RegisteredActivity.class));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
