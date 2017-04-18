package system.com.app.presenter;

import javax.inject.Inject;

/**
 * Created by user on 2017/4/18.
 */

public class Poetry
{
    private String mPemo;
    @Inject
    Poetry(){
        mPemo ="生活就像海洋";
    }

    public Poetry(String pomes){
        mPemo =pomes;
    }

    public String getPoems(){
        return mPemo;
    }
}
