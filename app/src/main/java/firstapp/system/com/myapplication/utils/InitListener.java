package firstapp.system.com.myapplication.utils;

/**
 * Created by karan on 13/2/15.
 */
public interface InitListener {
    public void onLoadSuccess();
    public void onLoadFail(String reason);
}