package map;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by alext on 3/10/15.
 */
public class KMap {

    public static final Character[] ALF = {'A', 'T', 'G', 'C'};

    protected final Character C;
    protected volatile int count;
    protected final int depth;
    protected final Map<Character,KMap>children;


    public KMap(Character c, int depth) {
        this.C = c;
        this.depth = depth;
        this.count=0;
        if(depth>0){
            this.children=new HashMap<Character, KMap>(4,4);
            for(Character character:ALF) {
              this.children.put(character,new KMap(character,this.depth-1));
            }
        }else{
           this.children=null;
        }
    }


    public void parse(final String str){
        if(this.children==null){
            this.count++;
        }else{
            this.children.get(str.charAt(0)).parse(str.substring(1));
        }
    }

}
