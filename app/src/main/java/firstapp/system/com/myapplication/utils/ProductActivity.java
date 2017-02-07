package firstapp.system.com.myapplication.utils;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.service.ProductServiceImp;

public class ProductActivity extends BaseActivity
{

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.text_content)
    TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

    @OnClick(R.id.btn_get)
    public void getNetWork()
    {
        ProductServiceImp.getProduct(textContent);
    }
}
