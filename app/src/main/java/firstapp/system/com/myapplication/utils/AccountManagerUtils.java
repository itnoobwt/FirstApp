package firstapp.system.com.myapplication.utils;

import android.accounts.AccountManager;
import android.content.Context;

/**
 * Created by user on 2017/2/23.
 */

public class AccountManagerUtils
{
    private AccountManager accountManager;
    private Context cx;

    public void init(Context context)
    {
        this.cx = context;
        accountManager = AccountManager.get(context);
    }

}
