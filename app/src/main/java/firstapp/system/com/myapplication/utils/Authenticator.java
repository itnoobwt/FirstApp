package firstapp.system.com.myapplication.utils;

import android.accounts.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import firstapp.system.com.myapplication.LoginActivity;

/**
 * Created by user on 2017/2/23.
 */

public class Authenticator extends AbstractAccountAuthenticator
{
    private Context mContext;
    public Authenticator(Context context)
    {
        super(context);
        this.mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType)
    {
        return null;
    }

    /**
     * 当用户打算登录并在一个设备上新建账户时，会调用这个方法。
     * 或者在手机设置 中，用户点击“添加新用户“时被调用addAccount
     * @param response
     * @param accountType
     * @param authTokenType
     * @param requiredFeatures
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType, String authTokenType,
                             String[] requiredFeatures, Bundle options) throws NetworkErrorException
    {
        Intent intent = new Intent(mContext, LoginActivity.class);
//        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE,accountType);
//        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE,authTokenType);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT,true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,response);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT,intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException
    {
        return null;
    }

    /**
     * getAuthToken可以获取存储在设备上的已经登陆成功用户的auth-token。
     * 如果auth-token不存在，将会提示用户登录。
     * 在成功登陆之后，请求auth-token的app会“长等待“此token。
     * 为了避免此情况，我们应该通过 AccountManager#peekAuthToken()
     * 来检查 AccountManager 是否已经存在一个有效的auth-token。
     * 如果没有，我们应该返回与 addAccount() 相同的结果
     * @param response
     * @param account
     * @param authTokenType
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response,
                               Account account, String authTokenType,
                               Bundle options) throws NetworkErrorException
    {
        AccountManager accountManager = AccountManager.get(mContext);
        String authToken = accountManager.peekAuthToken(account,authTokenType);
        if(TextUtils.isEmpty(authToken)){
            String password = accountManager.getPassword(account);
            if(password!=null){
                //获取authToken
            }
        }
        if(!TextUtils.isEmpty(authToken)){
            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME,account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE,account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN,authTokenType);
            return result;
        }
        Intent intent = new Intent(mContext,LoginActivity.class);
//        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE,accountType);
//        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE,authTokenType);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT,true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,response);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT,intent);
        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType)
    {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException
    {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException
    {
        return null;
    }
}
