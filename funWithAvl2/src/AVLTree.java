/*
 * Created by MightyJoeYoung on 10/3/2017.
 * Error somewhere in the balanceing of the node check the left right and right left rot
  */
import static java.lang.Math.abs;

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
            return nodeToReturn;
        }

        public Node insert(int data, Node node)
        {
            if (node == null)
                return new Node(data);

            else if (data > node.data) {
                node.right = insert(data, node.right);
                node.count++;
            }
            else {
                node.left = insert(data, node.left);
                node.count--;
            }
            // i think i need some guards against the nullptr
            System.out.println("The abs value is" + abs(node.count));
            if (abs(node.count) > 1) {
                if (data > node.data && data > node.right.data){
//                    System.out.println("Entering the right right");
                    node = balanceRightRight(node, node.right, node.right.right);
                }
                else if (data < node.data && data < node.left.data){
//                    System.out.println("Entering the left left");
                    node = balanceLeftLeft(node, node.left, node.left.left);
                }
                else if (data < node.data && data > node.right.data) {
//                    System.out.println("Entering the left right");
                    node = balLeftRightRo(node, node.left, node.left.right);
                }
                else if (data > node.data && data < node.left.data) {
//                    System.out.println("Entering the right left");
                    node = balRightLeftRo(node, node.right, node.right.left);
                }
            }
            return node;
        }

        public Node balanceRightRight(Node granny, Node parent, Node child)
        {
            Node temp;

            temp = parent.left;
            parent.left = granny;
            granny.right = temp;

            return parent;
        }

        public Node balanceLeftLeft(Node granny, Node parent, Node child)
        {
            Node temp;

            temp = parent.right;
            parent.right = granny;
            granny.left = temp;
            return parent;
        }

        public Node balLeftRightRo(Node granny, Node parent, Node child)
        {
            Node tempLeft;
            Node tempRight;
            System.out.println("Made it to balLeftRightRo");
            tempLeft = child.left;
            tempRight = child.right;

            child.right = granny;
            child.left = parent;
            parent.right = tempLeft;
            granny.left = tempRight;  // error somewhere here


            return child;

        }

        public Node balRightLeftRo(Node granny, Node parent, Node child)
        {
            Node tempLeft;
            Node tempRight;

            tempLeft = child.left;
            tempRight = child.right;

            child.left = granny;
            child.right = parent;
            granny.left = tempRight;  // may be the assigning of child nodes
            parent.right = tempLeft;  // error somewhere here

            return child;
        }

        public void print(Node node)
        {
            if(node != null){
                print(node.left);
                print(node.right);
                System.out.print(node.data + " ");

            }
        }
    public static void main(String args[]) {
        int testArray[] = {25, 15, 45, 2, 75, 7, 5};
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 7; ++i) {
            tree.root = tree.insert(testArray[i], tree.root);
        }
        tree.print(tree.root);
    }
}

