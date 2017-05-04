package firstapp.system.com.myapplication.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.utils.LogUtils;

/**
 * Created by user on 2017/3/23.
 */

public class PreFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.myseting);
        getPreferenceManager().setSharedPreferencesName("mysetting");
        Preference mylocation = findPreference("mylocation");
        LogUtils.e("","");
    }
}