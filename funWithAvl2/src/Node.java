/*
 * Created by MightyJoeYoung on 10/3/2017.
 */
public class Node {
    Node left;
    Node right;
    int data;
    int count;

    Node(int data)
    {
        left = null;
        right = null;
        this.data = data;
        count = 0;
    }
}
