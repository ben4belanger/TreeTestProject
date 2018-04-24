;
import java.util.Collections;

public class Tester{
    public static void main(String[] args) {
        ArrayList list = generateOrder(0,100);
        ArrayList protectedNodes = new ArrayList(50);

        TNode temp = new TNode(rightPad("" + list.get(0), 5, '0'), 1, null, null, null);
        Tree tree = new Tree(temp);
        for (int i = 1; i < 50; i++) {
            protectedNodes.add(rightPad("" + list.get(i), 5, '0'));
            tree.insertNode(new TNode(rightPad("" + list.get(i), 5, '0'), 1, null, null, null));
            //System.out.println(rightPad("" + list.get(i), 5, '0'));
        }
        //System.out.println(Utils.arrayListToString(protectedNodes));


        for (int i = 50; i < 100; i++) {
            tree.insertNode(new TNode(rightPad("" + list.get(i), 5, '0'), 1, null, null, null));
            TNode[] arr = arrayListToTNode(list);
            Tree constructed = buildTreeFromArray(arr, i);
            System.out.println(checkConnectionsAndHeight(tree, constructed));
        }
    }
    public static String rightPad(String text , int desiredLength, char paddingItem) {
        int difference = desiredLength - text.length();
        for (int i = 0; i < difference; i++) {
            text =  "" + paddingItem + text;
        }
        return text;

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

    public static Tree buildTreeFromArray(TNode lst[], int size){
        Tree tree = new Tree(lst[0]);
        TNode curr = tree.getRoot();
        for(int i = 1; i <= size; i++) tree.insertNode(lst[i]);
        return tree;
    }

    public static boolean checkConnectionsAndHeight(Tree given, Tree constructed){

        boolean ok = true;

        if(given.getRoot() == null || constructed.getRoot() == null){
            return (given.getRoot() == null) == (constructed.getRoot() == null);
        }

        TNode givenLeft = given.getRoot().getLeft();
        TNode givenRight = given.getRoot().getRight();
        TNode constLeft = constructed.getRoot().getLeft();
        TNode constRight = constructed.getRoot().getRight();

        if((givenLeft == null) == (constLeft != null)) ok = false;
        else if(givenLeft != null){
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

            ok &= checkConnectionsAndHeight(newGiven, newConst);
        }

        if((givenRight == null) == (constRight != null)) ok = false;
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

            ok &= checkConnectionsAndHeight(newGiven, newConst);
        }

        ok &= given.getRoot().getHeight() == constructed.getRoot().getHeight();

        return ok;
    }
}
