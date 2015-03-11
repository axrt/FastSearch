package seq;

import java.util.Random;

/**
 * Created by alext on 3/11/15.
 */
public class SequenceHelper {


    private final static Random random=new Random();

    /**
     * Returns random letter for the given alphabet;
     * @param alf {@link java.lang.Character[]} array representing the alphabet
     * @return random {@link java.lang.Character} from the given alphabet
     */
    public static Character random(final Character[]alf){
        return alf[random.nextInt(alf.length)];
    }



}
