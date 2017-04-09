package firstapp.system.com.myapplication;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Toast;
import butterknife.BindView;
import firstapp.system.com.myapplication.activity.BaseActivity;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FormActivity extends BaseActivity
{
    @BindView(R.id.glsv)
    GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Android7.0多窗体");
        setContentView(R.layout.activity_form);
        boolean bl = isInMultiWindowMode();
        Toast.makeText(this, bl + "", Toast.LENGTH_SHORT).show();


        GLSurfaceView.Renderer renderer = new GLSurfaceView.Renderer()
        {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config)
            {
                //关闭抗抖动
                gl.glDisable(GL10.GL_DITHER);
                //设置系统对透视进行修正
                gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
                gl.glClearColor(0, 0, 0, 0);
                //设置阴影平滑模式
                gl.glShadeModel(GL10.GL_SMOOTH);
                //启用深度测试
                gl.glEnable(GL10.GL_DEPTH_TEST);
                //设置深度测试的类型
                gl.glDepthFunc(GL10.GL_LEQUAL);
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height)
            {
                // 设置3D视窗的大小及位置
                gl.glViewport(0, 0, width, height);
                //将当前矩阵模式设为投影矩阵
                gl.glMatrixMode(GL10.GL_PROJECTION);
                //初始化单位矩阵
                gl.glLoadIdentity();
                //计算透视视窗的宽度、高度比
                float ratio = (float) width / height;
                //调用此方法设置透视视窗的空间大小
                gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
            }

            @Override
            public void onDrawFrame(GL10 gl)
            {
                // 清除屏幕缓存和深度缓存
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            }

        };
        glSurfaceView.setEGLConfigChooser(8,8,8,8,16,0);
        glSurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        glSurfaceView.setDebugFlags(1);
        glSurfaceView.setRenderer(renderer);
    }

}
