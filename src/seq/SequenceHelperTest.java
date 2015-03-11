package seq;
import static java.lang.System.out;

import map.KMap;
import org.junit.Test;

/**
 * Created by alext on 3/11/15.
 */
public class SequenceHelperTest {


    @Test
    public void test(){

        for(int i=0;i<5;i++){
            out.println(SequenceHelper.random(KMap.ALF));
        }
    }


}
