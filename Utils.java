import java.util.ArrayList;
import java.util.Collections;

public class Utils {

    public static ArrayList generateOrder(int min, int max) {
	if (max < min) {
	    int temp = min;
	    min = max;
	    max = temp;
	}
	ArrayList list = new ArrayList(max-min);
	for(int i = min; i<max; i++) {
	    list.add(new Integer(i));
	}
	Collections.shuffle(list);
	return list;
    }
}
