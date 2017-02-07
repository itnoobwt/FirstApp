package firstapp.system.com.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    volatile int x = 0;
    volatile int y = 0;
    @Test
    public void addition_isCorrect() throws Exception
    {
//        assertEquals(4, 2 + 2);
        System.out.print("123");
    }
    @Test
    public void writer(){
        x = 1;
        y = 2;
        System.out.println(x+"   "+y);
    }
    @Test
    public void reader(){
        int r1 = x;
        int r2 = y;
        System.out.println(r1+"   "+r2);
    }


}