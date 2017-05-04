package www.sinofreely.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.RadioButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.sinofreely.app.R;

/**
 * 注册界面
 */
public class RegisteredActivity extends BaseActivity
{

    @BindView(R.id.registered_rbt)
    RadioButton registeredRbt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHeaderTitle(getResources().getString(R.string.registered));
        isBack(true);
        setContentView(R.layout.activity_registered);
        registeredRbt.setText(Html.fromHtml("我已阅读并同意<font color=\"#FF0000\">《用户服务协议》</font>"));
    }

    @OnClick(R.id.registered_line_class)
    public void startclass()
    {
        startActivity(new Intent(this, ClassActivity.class));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
