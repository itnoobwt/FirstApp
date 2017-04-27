package system.com.app.presenter;

import system.com.app.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by user on 2017/4/27.
 */

public class LoginPresenterCompl implements ILoginPresenter
{
    ILoginView loginView ;
    @Inject
    public LoginPresenterCompl(ILoginView view){
        this.loginView  = view;
    }


    @Override
    public void clear()
    {
        loginView.onClearText();
    }

    @Override
    public void doLogin(String name, String pwd)
    {
        boolean result = false;
        int code;
        if(name.equals("wt")&&pwd.equals("123456")){
            result = true;
            code = 1;
        }else{
            result = false;
            code = 0;
        }
        loginView.onLoginResult(result,code);
    }
}
