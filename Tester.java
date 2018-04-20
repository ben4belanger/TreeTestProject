import java.util.List;

public class Tester {
    Tree tree = null;
    public void buildTreeFromArrayList(TNode lst[]){
	tree = new Tree(lst[0]);
	TNode curr = tree.getRoot();
	for(int i = 1; i < lst.length; i++) tree.insertNode(lst[i]);
    }
    
    public boolean checkConnectionsAndHeight(Tree given, Tree constructed){
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
		ok &= givenLeft.getIdentification().equals(constLeft.getIdentification());
		ok &= givenLeft.getParent().getIdentification().equals(constLeft.getParent().getIdentification());
		ok &= givenLeft.getRight().getIdentification().equals(constLeft.getRight().getIdentification());
		ok &= givenLeft.getLeft().getIdentification().equals(constLeft.getLeft().getIdentification());

	    }catch(Exception e){
		ok = false;
	    }
	    
	    
	    ok &= checkConnections(newGiven, newConst);
	}
	
	if((givenRight != null) != (constRight != null)) ok = false;
	else if(givenRight != null){
	    Tree newGiven = new Tree(givenLeft);
	    Tree newConst = new Tree(constLeft);    
	
	    try{
		ok &= givenRight.getIdentification().equals(constRight.getIdentification());
		ok &= givenRight.getParent().getIdentification().equals(constRight.getParent().getIdentification());
		ok &= givenRight.getRight().getIdentification().equals(constRight.getRight().getIdentification());
		ok &= givenRight.getLeft().getIdentification().equals(constRight.getLeft().getIdentification());
		
	    }catch(Exception e){
		ok = false;
	    }
	    
	    ok &= checkConnections(newGiven, newConst);
	}
	
	ok &= given.getRoot().getHeight() == constructed.getRoot().getHeight();
	
	return ok;
    }
}
