package firstapp.system.com.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import firstapp.system.com.myapplication.service.ProductServiceImp;
import firstapp.system.com.myapplication.utils.LogUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ProductActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener
{

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.text_content)
    TextView textContent;
    public static String TAG = "ProductActivity";
    @BindView(R.id.swipRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.head_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
    }

    public void initView(){
        toolbar.setNavigationIcon(R.mipmap.home_up_btn);
        toolbar.setTitle("详情");
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE,Color.CYAN);
//        swipeRefreshLayout.setBackgroundColor(Color.YELLOW);
        //设置初始时的大小
//        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置向下拉多少出现刷新
//        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置刷新出现的位置
//        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }

    @OnClick(R.id.btn_get)
    public void getNetWork()
    {
        ProductServiceImp.getProduct(textContent);
    }

    @OnClick(R.id.btn_okget)
    public void getOKHTTP(){
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final String txt = OKhttpClientManager.getInstance().OKhttpRequest("http://192.168.1" +
                        ".142:8080/HttpTest/ProductServlet");
                textContent.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        textContent.setText(txt);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onRefresh()
    {
        textContent.setText("正在刷新");
        textContent.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(false);
                textContent.setText("刷新完成");
            }
        },2000);


    }
}
