package firstapp.system.com.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.adapter.ViewPageAdapter;
import firstapp.system.com.myapplication.fragment.NewsFragment;
import firstapp.system.com.myapplication.fragment.TechnologyFragment;
import firstapp.system.com.myapplication.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{

    @BindView(R.id.content_main_viewpage)
    ViewPager viewPage;
    @BindView(R.id.content_main_tablayout)
    TabLayout tablayout;
    @BindView(R.id.textView)
    TextView textView;
    private ViewPageAdapter adapter;
    private List<Fragment> list = new ArrayList<Fragment>();
    private long exitTime = 0;
    private SimpleDraweeView simpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitData();
        InitView();
    }

    public void InitData(){
        TechnologyFragment fragment = new TechnologyFragment();
        NewsFragment newsFragment = new NewsFragment();
        VideoFragment videoFragment = new VideoFragment();
        list.add(fragment);
        list.add(newsFragment);
        list.add(videoFragment);
    }

    /**
     * 获取控件
     */
    public void InitView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("FirstApp");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                    }
                }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.imageView);
        simpleDraweeView.setImageURI(Uri.parse("http://avatar.csdn.net/A/E/5/1_csdnwt.jpg"));

        adapter = new ViewPageAdapter(getSupportFragmentManager(),list,this,tablayout);
        viewPage.setAdapter(adapter);
        viewPage.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(viewPage);
//        for (int i = 0; i<list.size(); i++){
//            tablayout.getTabAt(i).setIcon(R.mipmap.aa);
//        }
//        tablayout.getTabAt(0).setIcon(R.mipmap.aa);
//        tablayout.getTabAt(1).setIcon(R.mipmap.bb);
//        tablayout.getTabAt(2).setIcon(R.mipmap.cc);
        viewPage.setCurrentItem(0);
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            requestPermission();
            return true;
        }
        else if (id == R.id.nav_gallery)
        {
            photo();
            return true;
        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void requestPermission()
    {
        super.requestPermission();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);
                    dialogBuilder
                            .withTitle("权限访问")  //设置标题
                            .withTitleColor("#FFFFFF")         //设置背景
                            .withDividerColor("#11000000")                              //def
                            .withMessage("系统正在申请相机权限yes/no?")     //设置内容
                            .withMessageColor("#FFFFFFFF")              //设置内容背景
                            .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                            .withIcon(getDrawable(R.mipmap.icon))
                            .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                            .withDuration(700)                                          //def
                            .withEffect(Effectstype.Shake)      //启动dialog模式
                            .withButton1Text("OK")
                            .withButton2Text("Cancel")
                            //                .setCustomView(R.layout.custom_view,this)           //添加布局
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    camera();
                                    dialogBuilder.dismiss();
                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogBuilder.dismiss();
                                }
                            })
                            .show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        switch (requestCode){
            case CAMERA_NUM:
                cropIma(uri);
                break;
            case PHOTO_NUM:
//                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                simpleDraweeView.setImageURI(data.getData());
                break;
            case CUT_NUM:
                simpleDraweeView.setImageURI(uri);//file:///storage/emulated/0/CaseAndroid/image/08276444-dc23-4cd6-a4b9-8ca11fac38f2.jpg
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == android.R.id.home){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 5000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
