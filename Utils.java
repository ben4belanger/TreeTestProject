    import java.util.ArrayList;
import java.util.Collections;
public class Utils {
    //A delaying Method
    public static void delay(int ms) {
	try{
	    Thread.sleep(ms);
	}
	catch(InterruptedException ex){
	    Thread.currentThread().interrupt();
	}
    }
    
    //The Number Checking Method    
    public static boolean isANumber(String s) {
	boolean itIsANumber = true;
	for (int i = 0; i < s.length() && itIsANumber; i++) {
	    itIsANumber = s.charAt(i) >= '0' && s.charAt(i) <='9';
	}
	return itIsANumber; 
    }
    //The Left Padding Method
    public static String leftPad(String text , int desiredLength, char paddingItem) {
	int difference = desiredLength - text.length();
	for (int i = 0; i < difference; i++) {
	    text =  text + paddingItem;
	}
	return text;
    
    }
    public static String rightPad(String text , int desiredLength, char paddingItem) {
	int difference = desiredLength - text.length();
	for (int i = 0; i < difference; i++) {
	    text =  "" + paddingItem + text;
	}
	return text;
    
    }
    
    public static String removeChars(String text, char c) {
	String s = "";
	for (int i = 0; i < text.length(); i++) {
	    if (text.charAt(i) != c) {
		s = s + text.charAt(i);
	    }
	}
	return s;
    }
    /*public static String intToBytesStr(int num) {
	return "" + (char) 
    }*/
    
    public static String longToBytesStr(long num) {
	return "" + (char) (num >> 56) + 
		    (char) ((num & 0xFF000000000000L) >> 48) +
		    (char) ((num & 0xFF0000000000L) >> 40) +
		    (char) ((num & 0xFF00000000L) >> 32) +
		    (char) ((num & 0xFF000000L) >> 24) +
		    (char) ((num & 0xFF0000L) >> 16) +
		    (char) ((num & 0xFF00L) >> 8) +
		    (char) ((num & 0xFFL));
	
    }

    public static ArrayList generateOrder(int min, int max) {
	if (max < min) {
	    int temp = min;
	    min = max;
	    max = temp;
	}
	ArrayList list = new ArrayList();
	for(int i = min; i < max; i++) {
	    list.add(new Integer(i));
	}
	Collections.shuffle(list);
	return list;
    }
    public static TNode[] arrayListToTNode(ArrayList a) {
	TNode[] arr = new TNode[a.size()];
	for (int i = 0; i < a.size(); i++) {
	    arr[i] = new TNode ("" + a.get(i), 1, null, null, null);
	}
	return arr;
    }
    public static String arrayListToString(ArrayList a) {
	String s = "";
	for (int i = 0; i < a.size(); i++) {
	    s = s + "\n" + a.get(i);
	}
	return s;
    }
}
