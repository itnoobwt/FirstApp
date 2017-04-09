package firstapp.system.com.myapplication;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.Button;
import firstapp.system.com.myapplication.activity.BaseActivity;

public class LoginActivity extends AccountAuthenticatorActivity
{
    private AccountManager accountManager;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccountManager.get(this);
        btn = (Button) findViewById(R.id.btn_login);
    }

    public void submit(){

    }
}
