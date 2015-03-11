package seq;
import static java.lang.System.out;

import map.KMap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by alext on 3/11/15.
 */
public class SequenceHelperTest {


    @Test
    public void test(){

        for(int i=0;i<5;i++){
            out.println(SequenceHelper.random(SequenceHelper.NUCL));
        }
    }


    @Test
    public void testRandomString(){

        out.print(SequenceHelper.randomString(SequenceHelper.NUCL,100));
    }

    @Test
    public void parseToKMersTest(){

       final String randSeq= SequenceHelper.randomString(SequenceHelper.NUCL,3);
       out.println(randSeq);
       final Map<Character,KMap> testMap=SequenceHelper.parseToKMers(SequenceHelper.NUCL, randSeq, 3);
       for(Character character:SequenceHelper.NUCL){
           for(Character character1:SequenceHelper.NUCL){
               for(Character character2:SequenceHelper.NUCL){
                   out.print(character);
                   out.print(character1);
                   out.print(character2);
                   out.print(": ");
                   out.println(testMap.get(character).getChar(character1).getChar(character2).getCount());
               }
           }
       }

    }

}
