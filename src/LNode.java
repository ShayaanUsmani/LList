public class LNode
{
    private int val;

    private LNode prev;
    private LNode next;

    public LNode(int n, LNode prev, LNode nxt){
        val = n;
        this.prev = prev;
        next = nxt;
    }


    public int getVal (){
        return val;
    }
    public void setVal (int v){
        val = v;
    }

    public LNode getPrev() { return prev; }
    public void setPrev(LNode prev) { this.prev = prev; }

    public LNode getNext (){
        return next;
    } // returns the next linked node
    public void setNext (LNode n){
        next = n;
    } // sets the next node

    public String address (){
        return ""+hashCode();
    } // returns the hashCode of the node

    public String toString (){ // returns the node's value as a string
        String ans = "";
        if (prev==null){
            ans+="null :";
        }
        else{
            ans += "@"+prev.address()+" :";
        }
        ans += val;
        if(next==null){ // if the next node does not exist, the current node is the tail
            ans += ": null"; // and since we set the value of the last node to null, we let the user know
        }
        else{ // otherwise, tell the user where in memory address the next node is located
            ans += ": @"+next.address();
        }
        return "<" + ans + ">";
    }

}
