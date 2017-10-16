/*
 * Created by MightyJoeYoung on 10/3/2017.
 */
public class AVLTree {
    Node root;
    private Node searchData(int data, Node node)
    {
        Node nodeToReturn;
        nodeToReturn = null;

        if (node == null)
        {
            return null;
        }
        else if (data < node.data)
        {
            searchData(data, node.left);
        }
        else if (data > node.data)
        {
            searchData(data, node.right);
        }
        else
        {
            nodeToReturn = node;
        }
        System.out.println("Made is here");
        return nodeToReturn;
    }

    public static Node insert(int data, Node node)
    {
        if (node == null)
        {
            return new Node(data);
        }
        else if (data > node.data)
        {
            System.out.println("data is greater than");
            node.right = insert(data, node.right);
            node.count++;
        }
        else
        {
            System.out.println("data is less than");
            node.left = insert(data, node.left);
            node.count++;
        }
        return node;
    }
    public static void print(Node node)
    {
        System.out.println("At beginning of print");
        if(node != null){
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }
    public static void main(String args[])
    {
        int testArray[] = {25, 35, 20, 65, 75, 90, 100};
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 7; ++i) {
            tree.root = tree.insert(testArray[i], tree.root);
        }
        tree.print(tree.root);
        System.out.println("So does this");
    }
}
