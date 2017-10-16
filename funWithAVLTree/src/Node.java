/*
 * Created by MightyJoeYoung on 10/2/2017.
 */
public class Node {
    Node left;
    Node right;
    int count;
    int data;

    Node()
    {
        this.data = 0;
        this.count = 0;
        this.left = null;
        this.right = null;
    }

    Node(int data)
    {
        this.data = data;
        this.count = 0;
        this.left = null;
        this.right = null;
    }
}
