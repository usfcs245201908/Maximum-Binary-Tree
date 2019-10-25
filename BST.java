import java.util.*;
import java.lang.*;

public class BST<T>
{
    Node root;

    class Node<T>
    {
        Comparable data;
        Node left;
        Node right;
        int instance;

        Node(Comparable item)
        {
            data = item;
            instance = 1;
        }
        public Comparable getData()
        {
            return data;
        }
        public  Node getLeft()
        {
            return left;
        }
        public Node getRight()
        {
            return right;
        }
        public int getInstance()
        {
            return instance;
        }
        public void setData(Comparable item)
        {
            data = item;
        }
        public void setLeft(Node node)
        {
            left = node;
        }
        public void setRight(Node node)
        {
            right = node;
        }
        public void instanceCounter()
        {
            instance++;
        }
    }
    public boolean find(Comparable item)
    {
        return find(item, root);
    }
    private boolean find(Comparable item, Node node) //Traverses the node until you find the item. If you can't find it and it reaches the bottom (which is null) 
    {
        if(node == null)
            return false;
        if(node.data.compareTo(item) == 0) //If it is item return true
        {
            return true;
        }
        else if(node.data.compareTo(item) < 0) //search the right 
        {
            return find(item, node.right);
        }
        else
        {
            return find(item, node.left); //search the left
        }
    }
    public void insert(Comparable item)
    {
        root = insert(item, root);
    }
    private Node insert(Comparable item, Node node)
    {
        if(node == null) //if the tree is empty insert it 
        {
            return new Node(item);
        }
        if(node.data == item)
        {
            node.instance++;
        }
        if(node.data.compareTo(item) < 0) //insert the item down the right side and reestablish links
        {
            node.right = insert(item, node.right);
        }
        else
        {
            node.left = insert(item, node.left); //insert down the left side and reestablish links
        }
        return node;
    }
    public void delete(Comparable item)
    {
        root = delete(item, root);
    }
    private Node delete(Comparable item, Node node)
    {
        if(node == null) 
        {
            return null;
        }
        if(node.data.compareTo(item) == 0) 
        { 
            if(node.left == null) //if no left node then there is a right node
            {
                return node.right;
            }
            else if(node.right == null) //if no right node then there is a left node. will return null if either
            {
                return node.left;
            }
            else
            {
                if(node.right.left == null)
                {
                    node.data = node.right.data;
                    node.right = node.right.right;
                    return node;
                }
                else
                {
                    node.data = moveSmallest(node.right);
                    return node;
                }
            }
        }
        else if(node.data.compareTo(item) < 0)
        {
            node.right = delete(item, node.right); //travel down the tree recursively to keep searching for the node to delete
            return node;                            
        }
        else
        {
            node.left = delete(item, node.left);
            return node;
        }
    }
    private Comparable moveSmallest(Node node)//will take the smallest value of node we want to delete and replace the node with that smallest node value
    {
        if(node.left.left == null)
        {
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        else {
            return moveSmallest(node.left);
        }
    }
    public void print()
    {
        print(root);
    }
    private void print(Node root)// left this right implementation. Print left child first and then itself and then right child.
    {
        if(root == null)
        {
            return;
        }
        print(root.left);
        System.out.println(root.data);
        print(root.right);
    }
}