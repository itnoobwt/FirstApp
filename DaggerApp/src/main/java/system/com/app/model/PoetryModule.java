package system.com.app.model;

import dagger.Module;
import dagger.Provides;
import system.com.app.presenter.Poetry;

/**
 * Created by user on 2017/4/18.
 */
@Module
public class PoetryModule
{
    // 这个方法需要一个String参数，在Dagger2注入中，这些参数也是注入形式的，也就是
    // 要有其他对方提供参数poems的生成，不然会造成编译出错
    @Provides
    public Poetry providePoetry(String pomes){
        return new Poetry(pomes);
    }

    @Provides
    public String providePoems(){
        return "只有意志坚强的人，才能到达彼岸";
    }
}
