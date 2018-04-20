//NEED TO LEFT PAD
import java.util.ArrayList;
public class TestingOfTree{
    public static void main(String[] args) {
	ArrayList list = Utils.generateOrder(0,10000);
	ArrayList protectedNodes = new ArrayList(50);
	
	TNode temp = new TNode(Utils.rightPad("" + list.get(0), 5, '0'), 1, null, null, null);
	Tree tree = new Tree(temp);
	for (int i = 1; i < 50; i++) {
	    protectedNodes.add(Utils.rightPad("" + list.get(i), 5, '0'));
	    tree.insertNode(new TNode(Utils.rightPad("" + list.get(i), 5, '0'), 1, null, null, null));
	    System.out.println(Utils.rightPad("" + list.get(i), 5, '0'));
	}
	//System.out.println(Utils.arrayListToString(protectedNodes));
	
	TNode[] arr = Utils.arrayListToTNode(list);
	 
	for (int i = 50; i < 10000; i++) {
	    /*for(int j = 0; j < 10000; j++){
		if(i == j) continue;
		if(list.get(i) == list.get(j)) System.out.println("REE");
	    } */
	    tree.insertNode(new TNode(Utils.rightPad("" + list.get(i), 5, '0'), 1, null, null, null)); 
	    Tree constructed = buildTreeFromArray(arr, i);
	    System.out.println(checkConnectionsAndHeight(tree, constructed));
	}
	//System.out.println(Utils.arrayListToString(list));
	//tree.printTree(0);
    }
    public static Tree buildTreeFromArray(TNode lst[], int size){
	Tree tree = new Tree(lst[0]);
	TNode curr = tree.getRoot();
	for(int i = 1; i <= size; i++) tree.insertNode(lst[i]);
	return tree;
    }
    
    public static boolean checkConnectionsAndHeight(Tree given, Tree constructed){
	boolean ok = true;
	
	TNode givenLeft = given.getRoot().getLeft();
	TNode givenRight = given.getRoot().getRight();
	TNode constLeft = constructed.getRoot().getLeft();
	TNode constRight = constructed.getRoot().getRight();
	
	if((givenLeft != null) != (constLeft != null)) ok = false;
	else if(given.getRoot().getLeft() != null){
	    Tree newGiven = new Tree(givenLeft);
	    Tree newConst = new Tree(constLeft);
	    
	    //check connections using identification of each connection between left, right, parent, and itself
	    try{
		ok &= givenLeft.getId().equals(constLeft.getId());
		ok &= givenLeft.getParent().getId().equals(constLeft.getParent().getId());
		ok &= givenLeft.getRight().getId().equals(constLeft.getRight().getId());
		ok &= givenLeft.getLeft().getId().equals(constLeft.getLeft().getId());

	    }catch(Exception e){
		ok = false;
	    }
	    
	    
	    ok &= checkConnectionsAndHeight(newGiven, newConst);
	}
	
	if((givenRight != null) != (constRight != null)) ok = false;
	else if(givenRight != null){
	    Tree newGiven = new Tree(givenLeft);
	    Tree newConst = new Tree(constLeft);    
	
	    try{
		ok &= givenRight.getId().equals(constRight.getId());
		ok &= givenRight.getParent().getId().equals(constRight.getParent().getId());
		ok &= givenRight.getRight().getId().equals(constRight.getRight().getId());
		ok &= givenRight.getLeft().getId().equals(constRight.getLeft().getId());
		
	    }catch(Exception e){
		ok = false;
	    }
	    
	    ok &= checkConnectionsAndHeight(newGiven, newConst);
	}
	
	ok &= given.getRoot().getHeight() == constructed.getRoot().getHeight();
	
	return ok;
    }
}
