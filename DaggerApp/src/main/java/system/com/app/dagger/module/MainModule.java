package system.com.app.dagger.module;

import dagger.Module;
import dagger.Provides;
import system.com.app.view.ILoginView;

/**
 * Created by user on 2017/4/27.
 */
@Module
public class MainModule
{
    private ILoginView view;
    public MainModule(ILoginView view){
        this.view = view;
    }

    @Provides
    ILoginView provideILogView(){
        return view;
    }
}
