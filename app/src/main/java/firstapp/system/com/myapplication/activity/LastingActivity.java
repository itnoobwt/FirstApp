package firstapp.system.com.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.utils.PreferencesUtils;

import java.io.*;

public class LastingActivity extends BaseActivity
{

    @BindView(R.id.edit_view)
    TextInputEditText editView;
    @BindView(R.id.btn_data)
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("持久化数据");
        setContentView(R.layout.activity_lasting);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String conent;
                    StringBuffer buffer = new StringBuffer();
                    FileInputStream fos = openFileInput("message");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fos));
                    while((conent = br.readLine())!=null){
                        buffer.append(conent);
                    }
                    br.close();
                    fos.close();
                    editView.setText(buffer.toString());
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void save(){
        BufferedWriter bw=null;
        FileOutputStream fos=null;
        try
        {
            fos = openFileOutput("message",MODE_PRIVATE);

            String mes = editView.getText().toString();
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(mes);
            bw.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            if(bw!=null){

                try
                {
                    bw.close();
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }


        }
    }

    @Override
    protected void onDestroy()
    {
        save();
        super.onDestroy();

    }
}
