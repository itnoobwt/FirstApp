package firstapp.system.com.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import firstapp.system.com.myapplication.fragment.PreFragment;

public class PreferenceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        PreFragment preFragment = new PreFragment();
        transaction.add(R.id.settings_fragment_container,preFragment);
        transaction.commit();
    }
}
