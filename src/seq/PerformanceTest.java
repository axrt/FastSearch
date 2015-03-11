package seq;

import map.KMap;
import org.junit.Test;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alext on 3/11/15.
 */
public class PerformanceTest {

    @Test
    public void writeTest() {
        final int testSize = 10000000;
        final String query = SequenceHelper.randomString(SequenceHelper.NUCL, testSize);
        final int windowSize = 3;


        long kdif = writeKMapTest(query, windowSize);
        out.println("Kmap write: " + kdif);

        long pmdif = writePlainMapTest(query, windowSize);
        out.println("Plain map write: " + pmdif);

        out.println((double) pmdif / kdif);
        final List<String> kmers = new ArrayList<String>();
        for (Character character : SequenceHelper.NUCL) {
            for (Character character1 : SequenceHelper.NUCL) {
                for (Character character2 : SequenceHelper.NUCL) {
                   kmers.add("" + character + character1 + character2);
                }
            }
        }
        final Map<Character, KMap> kmap = getKmap(query, windowSize);
        final Map<String, Integer> pmap = getPlainMap(query, windowSize);
        kdif = readKMapTest(kmap, kmers, testSize);
        out.println("Kmap read: " + kdif);
        pmdif = readPlainMap(pmap, kmers, testSize);
        out.println("Plain map read: " + pmdif);
        out.println((double) pmdif / kdif);
    }

    public static long writeKMapTest(final String query, final int windowSize) {
        final long startKMap = System.currentTimeMillis();
        getKmap(query, windowSize);
        final long stopKMap = System.currentTimeMillis();
        return stopKMap - startKMap;
    }

    public static long writePlainMapTest(final String query, final int windowSize) {
        final long startPlainMap = System.currentTimeMillis();
        getPlainMap(query, windowSize);
        final long stopPlainMap = System.currentTimeMillis();
        return stopPlainMap - startPlainMap;
    }

    public static long readKMapTest(final Map<Character, KMap> testMap,final List<String> kmers, final int testNum) {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            for (String s : kmers) {
                testMap.get(s.charAt(0)).getChar(s.charAt(1)).getChar(s.charAt(2)).getCount();
            }

        }
        final long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static long readPlainMap(final Map<String, Integer> testMap, final List<String> kmers, final int testNum) {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            for (String s : kmers) {
                testMap.get(s);
            }
        }
        final long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static Map<Character, KMap> getKmap(final String query, final int windowSize) {
        return SequenceHelper.parseToKMers(SequenceHelper.NUCL, query, windowSize);
    }

    public static Map<String, Integer> getPlainMap(final String query, final int windowSize) {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < query.length() - windowSize + 1; i++) {
            final String window = query.substring(i, i + windowSize);
            Integer count;
            if ((count = map.get(window)) != null) {
                count = count + 1;
                map.put(window, count);
            } else {
                map.put(window, 0);
            }
        }
        return map;
    }
}
