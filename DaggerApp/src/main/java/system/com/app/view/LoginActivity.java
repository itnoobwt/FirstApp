package system.com.app.view;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import system.com.app.R;
import system.com.app.dagger.component.DaggerMainComponent;
import system.com.app.dagger.module.MainModule;
import system.com.app.presenter.LoginPresenterCompl;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements ILoginView
{
    @Inject
    LoginPresenterCompl presenterCompl;   //此处注意添加@Inject注解的变量不能被private修饰
    @BindView(R.id.user_et)
    TextInputEditText userEt;
    @BindView(R.id.pwd_et)
    TextInputEditText pwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public void onClearText()
    {
        userEt.setText("");
        pwdEt.setText("");
        Toast.makeText(this,"clear",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginResult(Boolean result, int code)
    {
        if(result){
            Toast.makeText(this,"登陆成功: "+code,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登陆失败："+code,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_login)
    public void login()
    {
        presenterCompl.doLogin("user", "123456");
    }
    @OnClick(R.id.btn_clear)
    public void clear(){
        presenterCompl.clear();
    }
}
