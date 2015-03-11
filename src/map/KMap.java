package map;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by alext on 3/10/15.
 */
public class KMap {

    protected final Character C;
    protected volatile int count;
    protected final int depth;
    protected final Map<Character,KMap>children;


    public KMap(Character c, int depth,final Character[]alf) {
        this.C = c;
        this.depth = depth;
        this.count=0;
        if(depth>0){
            this.children=new HashMap<Character, KMap>(alf.length,alf.length);
            for(Character character:alf) {
              this.children.put(character,new KMap(character,this.depth-1,alf));
            }
        }else{
           this.children=null;
        }
    }


    public void parse(final String str){
        if(this.depth==0){
            this.count++;
        }else{
            this.children.get(str.charAt(0)).parse(str.substring(1));
        }
    }

    public Character getC() {
        return C;
    }

    public int getCount() {
        return count;
    }

    public KMap getChar(final Character character){
        return this.children.get(character);
    }

}
