package map;

import org.junit.Test;
import static java.lang.System.out;
import java.util.HashMap;


/**
 * Created by alext on 3/10/15.
 */
public class KMapTest {

    public static final int kSize=3;

    @Test
    public void testKMap(){


         //Initial node
        final HashMap<Character,KMap> root=new HashMap<Character, KMap>(4,4);
        //Fill in with atgc
        for(Character character:KMap.ALF){
            root.put(character,new KMap(character,kSize-2));
        }
    }
}
