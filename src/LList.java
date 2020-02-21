// SHAYAAN USMANI
// A Linked list (doubly or singly linked) able to act as a stack or a queue.
// either type (LIFO or FIFO) can add nodes and remove nodes.
// Doubly linked can add nodes, remove nodes, remove duplicates, insert an element sorted, reverse, and create a copy of itself.
public class LList
{
    private LNode head;
    private LNode tail;

    public LList (){
        head = null;
    }

    // adds a node with given data at the start of the linked list
    public void push(int n){
        LNode tmp = new LNode(n, null, null);
        if(head!=null) {
            tmp.setNext(head);
        }
        head = tmp;
    }
    // removes the node at start of the linked list, returning it
    public LNode pop(){
        if(head!=null) {
            LNode node = head;
            head = head.getNext();
            return node;
        }
        else {
            return null;
        }
    }

    // adds an element at the tail of the Linked List
    public void enqueue(int n){
        if(head!=null){ // if the head is not null / the list isn't empty, create a new node, set the current last node's next to it,
            // set the new node's previous to the last node and make the new node the last node/tail of the list
            LNode node = new LNode(n, null, null);
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        else {
            add(n);
        }
    }

    // removes head of the linked list
    public LNode dequeue(){
        // make a reference to the head node
        LNode node = head;
        // if the list is not empty
        if(head!=null){
            // if the head of the list has a next node
            // then set the second node's previous to null
            // and make the second node the new head
            if(head.getNext()!=null) {
                head.getNext().setPrev(null);
                head = head.getNext();
            }
            else {
                head = null;
            }
        }
        return node;
    }

    // delete's a node in a doubly linked list given the node
    public void delete(LNode node){
        // if the node is the only node
        if(node.getNext() == null && node.getPrev() == null){
            head = null;
            tail = null;
        }
        // if the node is the head
        else if(head == node){
            dequeue();
        }
        // if the node is the tail
        else if(tail == node){
            // set the prev's next to null
            node.getPrev().setNext(null);
            // set the tail to the node previous of the tail node
            tail = node.getPrev();
        }
        // if the node is somewhere in the middle
        else {
            // set the node's prev's next to the node's next
            node.getPrev().setNext(node.getNext());
            // set the node's next's prev to the node's prev
            node.getNext().setPrev(node.getPrev());
        }
    }

    // deletes a node given it's value/data
    public void delete(int n){
        if(head==null){
            return;
        }
        // if the node is the head
        if(head.getVal() == n){
            // if the curr node is the only one just set the head and tail to null
            if(head.getNext() == null){
                head = null;
                tail = null;
            }
            // otherwise set the next node's previous to null and make that next node the head
            else {
                head.getNext().setPrev(null);
                head = head.getNext();
            }
            return;
        }

        // if the node is the tail
        if(tail!=null && tail.getVal() == n){
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
            return;
        }

        // if the node is somewhere in the middle
        LNode curr = head; // current node we are on
        // this loop will stop when the next node of the curr node is the node with value n
        while(curr.getNext().getVal() != n){
            curr = curr.getNext();
        }
        // set the next next node's prev node to the curr node
        curr.getNext().getNext().setPrev(curr);
        // set the next node of the current node to the next next node meaning nothing is pointing to the curr's current
        // next node
        curr.setNext(curr.getNext().getNext());
    }

    // deletes a node given it's index
    public void deleteAt(int i){
        LNode curr = head;
        // the loop will end when we are at the node to be deleted
        for(int k = 0; k<i; k++){
            curr = curr.getNext();
        }
        delete(curr);
    }

    // inserts a node sorted into an already sorted list
    public void sortedInsert(int n){
        if(head==null){
            add(n);
            return;
        }

        LNode node = new LNode(n, null, null);

        // if the head is the same value or greater than n
        if(head.getVal()>=n){
            head.setPrev(node);
            node.setNext(head);
            head = node;
            return;
        }

        LNode curr = head; // the current node we are assessing

        // loop ends when we are at the node which we want to insert our new node after
        while (curr.getVal()<n){
            // if the next node to the current node exists and its val is less than n, make it the current node
            if(curr.getNext()!=null && curr.getNext().getVal()<n){
                curr = curr.getNext();
            }
            else{
                break;
            }
        }

        // the node we created will be after the current node
        node.setPrev(curr);

        // if the current node is the head and tail
        if(curr == head && curr == tail){
            curr.setNext(node);
            tail = node;
        }
        // if the current node is only the head
        else if(curr == head){
            node.setNext(curr.getNext());
            curr.setNext(node);
            node.getNext().setPrev(node);
        }
        // if the current node is only the tail
        else if(curr == tail){
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
        // if the node is somewhere in the middle
        else{
            node.setPrev(curr);
            node.setNext(curr.getNext());
            curr.setNext(node);
            node.getNext().setPrev(node);
        }
    }

    // eliminates nodes with the same data, leaving only 1 node
    public void removeDuplicates(){
        // if list empty
        if(head == null){
            return;
        }
        // start at the head of the list
        LNode curr = head;
        // while the current node is not the null / past the tail
        while(curr!=null){
            // create a reference to the node next to the curr node which will compare the value with to see if it
            // is a duplicate
            LNode compareNode = curr.getNext();
            // while the compareNode has not went past the tail, if it's value is a match with the curr node, delete it
            // Note that when we delete a node, it still points to the next node so we can move to the next node in the list
            while(compareNode!=null){
                if(compareNode.getVal() == curr.getVal()){
                    delete(compareNode);
                }
                compareNode = compareNode.getNext();
            }
            curr = curr.getNext();
        }
    }

    // reverses the order of the nodes in a doubly linked list
    public void reverse(){
        if(head==null){
            return;
        }
        // Start at the head and set its previous node to its next node
        // then set its next to it's previous, going down until the curr node
        // has gone past the tail
        LNode curr = head;
        while(curr!=null) {
            LNode oldPrev = curr.getPrev();
            curr.setPrev(curr.getNext());
            curr.setNext(oldPrev);
            curr = curr.getPrev();
        }
        LNode oldHead = head;
        head = tail;
        tail = oldHead;

    }
    // makes a clone of the linked list
    public LList clone(){
        if(head==null){
            return null;
        }
        LList list = new LList();
        LNode curr = head;
        while (curr!=null){
            list.add(curr.getVal());
            curr = curr.getNext();
        }
        // we reverse the list since the add method adds the element to the head
        list.reverse();
        return list;
    }
    // add a node at the start of the linked list making it the new head and its next node the old head,
    // if the list is empty then the next node will be null but the node we created will still be the new head
    public void add (int n){
        LNode node = new LNode(n, null, null);
        if(head!=null) {
            node.setNext(head);
            head.setPrev(node);
        }
        else{
            // if there was no head then the list was empty so the added node will be both head and tail
            tail = node;
        }
        head = node;
    }

    public String toString (){ // prints all nodes below and including the referred node
        LNode tmp = head;
        String ans = "";
        while (tmp != null){
            ans += tmp+", ";
            tmp = tmp.getNext ();
        }
        if(head != null){
            ans = ans.substring(0,ans.length()-2);
        }
        return "["+ans+"]";
    }

    public String reverseString (){
        LNode tmp = tail;
        String ans = "[";
        while (tmp != null){
            ans += tmp+", ";
            tmp = tmp.getPrev();
        }
        return ans.substring(0,ans.length()-2)+"]";
    }
}
