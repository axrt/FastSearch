package seq;

import map.KMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alext on 3/11/15.
 */
public class SequenceHelper {

    /**
     * Nucleotide alphabet
     */
    public static final Character[] NUCL = {'A', 'T', 'G', 'C'};
    /**
     * {@link java.util.Random}
     */
    private final static Random random=new Random();

    /**
     * Returns random letter for the given alphabet;
     * @param alf {@link java.lang.Character[]} array representing the alphabet
     * @return random {@link java.lang.Character} from the given alphabet
     */
    public static Character random(final Character[]alf){
        return alf[random.nextInt(alf.length)];
    }

    /**
     * Returns a random string for a given alphabet and size
     * @param alf {@link java.lang.Character[]} array representing the alphabet
     * @param size {@code int} desired size of the string
     * @return random {@link java.lang.String} for a given alphabet and size
     */
    public static String randomString(final Character[]alf,final int size){
        final StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<size;i++){
            stringBuilder.append(random(alf));
        }
        return stringBuilder.toString();
    }

    //
    public static Map<Character, KMap> parseToKMers(final Character[]alf,final String input,final int windowSize){
        final HashMap<Character,KMap> root=new HashMap<Character, KMap>(input.length(),alf.length);

        for(Character character:alf){
            root.put(character,new KMap(character,windowSize-1,alf));
        }

        for(int i=0;i<input.length()-windowSize+1;i++){
            final String window=input.substring(i,i+windowSize);
            root.get(window.charAt(0)).parse(window.substring(1));
        }
        return root;
    }

}
