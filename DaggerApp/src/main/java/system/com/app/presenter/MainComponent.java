package system.com.app.presenter;

import dagger.Component;
import system.com.app.MainActivity;
import system.com.app.model.MainModel;
import system.com.app.model.PoetryModule;

/**
 * Created by user on 2017/4/18.
 */

//用@Component表示这个接口是一个连接器，能用@Component注解的只
//能是interface或者抽象类
@Component(modules = {MainModel.class,PoetryModule.class})
public interface MainComponent {

    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    void inject(MainActivity activity);
}
