//NEED TO LEFT PAD
import java.util.ArrayList;
public class TestingOfTree{
    public static void main(String[] args) {
	ArrayList list = Utils.generateOrder(0,100000);
	ArrayList protectedNodes = new ArrayList(50);
	
	Tree tree = new Tree();
	for (int i = 0; i < 50; i++) {
	    protectedNodes.add(Utils.leftPad("" + list.get(i), 5, '0'));
	    tree.insertNode(new TNode(Utils.leftPad("" + list.get(i), 5, '0'), 1, null, null, null));
	    System.out.println(Utils.leftPad("" + list.get(i), 5, '0'));
	}
	//System.out.println(Utils.arrayListToString(protectedNodes));
	
	 
	for (int i = 50; i < 10000; i++) {
	    tree.insertNode(new TNode(Utils.leftPad("" + list.get(i), 5, '0'), 1, null, null, null)); 
	    //for (int j = 0; j < 50; j++) {
		//if (Utils.leftPad("" + list.get(j), 5, '0').compareTo() == 0) {
		   // System.out.println("FOUND AT: " + list.get(j));
		//}
	    //}
	}
	//System.out.println(Utils.arrayListToString(list));
	tree.printTree(0);
    }
}
