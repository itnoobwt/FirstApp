package firstapp.system.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import firstapp.system.com.myapplication.service.ProductServiceImp;
import firstapp.system.com.myapplication.utils.LogUtils;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.io.InputStream;

public class ProductActivity extends BaseActivity
{

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.text_content)
    TextView textContent;
    public static String TAG = "ProductActivity";
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

        final String[] names = new String[]{"Hi","Hello","ok"};
        Subscriber<Integer> subscriber = new Subscriber<Integer>()
        {
            @Override
            public void onCompleted()
            {
                //结束
                LogUtils.e(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                //报错
                LogUtils.e(TAG,"onError");
            }

            @Override
            public void onNext(Integer s)
            {
                //开始
                LogUtils.e(TAG,"onNext  "+s+" "+Thread.currentThread().getId());
            }
        };
//        Observable.from(names).subscribeOn(Schedulers.io())
//                .map(new Func1<String, Integer>()
//                {
//                    @Override
//                    public Integer call(String s)
//                    {
//                        return 0;
//                    }
//                })
//                .subscribe(subscriber);

        Observable.from(names).subscribeOn(Schedulers.io()).observeOn(Schedulers.immediate())
                .flatMap(new Func1<String, Observable<Integer>>()
                {
                    @Override
                    public Observable<Integer> call(String s)
                    {
                        Integer[] number = new Integer[]{1,2,3};
                        return Observable.from(number);
                    }
                }).subscribe(subscriber);

    }
}
