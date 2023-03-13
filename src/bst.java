// Pre Order = process root node, then its left/right subtrees
// In Order = process left subtree, then root node, then right
// Post Order = process left/right subtrees, then root node

import java.util.Scanner;

public class bst {
    Node root;
    public static void main(String[] args) {

        bst mainframe = new bst();

        System.out.println("Welcome to the user database");
        while(true){
        Scanner user = new Scanner(System.in);
        System.out.println("What would you like to access?");
        System.out.println("(A) Add");
        System.out.println("(D) Delete");
        System.out.println("(L) Lookup");
        System.out.println("(M) Modify");
        System.out.println("(Any Other Key) Quit");

        char option = user.next().charAt(0);
        option = Character.toUpperCase(option);

        if (option == 'A'){
        Scanner scKey = new Scanner(System.in);
        System.out.print("Enter key: ");
        int key = scKey.nextInt();

        Scanner scName = new Scanner(System.in);
        System.out.print("Enter Name (First and Last): ");
        String name = scName.nextLine();

        Scanner scAddress = new Scanner(System.in);
        System.out.print("Enter Address (Street address, City, State, and Zip): ");
        String address = scAddress.nextLine();

        Scanner scEmail = new Scanner(System.in);
        System.out.print("Enter Email: ");
        String email = scEmail.nextLine();

        Scanner scPhone = new Scanner(System.in);
        System.out.print("Enter Phone Number: ");
        String phone = scPhone.nextLine();

        mainframe.add(key, name, address, email, phone);

        } 
        
        else if (option == 'D'){
        Scanner scDelete = new Scanner(System.in);
        System.out.println("Please enter the key for the entry you wish to delete");
        int key = scDelete.nextInt();

        Node deleteResult = mainframe.search(key);
        if (deleteResult == null){
            System.out.println("No result found for the key: " + key);
        } else{
         mainframe.delete(key);   
        }

        }else if (option == 'L'){
        Scanner scLookup = new Scanner(System.in);
        System.out.println("Please enter the key you wish to look up");
        int key = scLookup.nextInt();

        Node searchResult = mainframe.search(key);
        if (searchResult != null) {
            System.out.println("Found result: Key: " + searchResult.key + ", Name: " + searchResult.name+ ", Address: " + searchResult.address + ", Email: " + searchResult.email + ", Phone: " + searchResult.phone);
        } else {
            System.out.println("No result found for the key: " + key);
        }

        }else if (option == 'M'){
            Scanner scMod = new Scanner(System.in);
            System.out.println("Please enter the key you wish to modify");
            int key = scMod.nextInt();
    
            Node ModNode = mainframe.search(key);
            if (ModNode == null) {
                System.out.println("No result found for the key: " + key);
            } else {
        Scanner scName = new Scanner(System.in);
        System.out.print("Enter Name (First and Last): ");
        String name = scName.nextLine();
        ModNode.name = name;

        Scanner scAddress = new Scanner(System.in);
        System.out.print("Enter Address (Street address, City, State, and Zip): ");
        String address = scAddress.nextLine();
        ModNode.address = name;

        Scanner scEmail = new Scanner(System.in);
        System.out.print("Enter Email: ");
        String email = scEmail.nextLine();
        ModNode.email = email;
        
        Scanner scPhone = new Scanner(System.in);
        System.out.print("Enter Phone Number: ");
        String phone = scPhone.nextLine();
        ModNode.phone = phone;
            }
        } else{
            break;
        }
    }
    }


    public bst(){
        root = null;
    }

    public void add(int key, String name, String address, String email, String phone){
        root = addNode(root, key, name, address, email, phone);
    }

    public Node addNode(Node root, int key, String name, String address, String email, String phone){
            if (root == null){
                root = new Node(key, name, address, email, phone);
                return root;
            }
            else if (key < root.key){
                root.left = addNode(root.left, key, name, address, email, phone);
            }
            else if (key > root.key){
                root.right = addNode(root.right, key, name, address, email, phone);
            }
            return root;
    }

    public void delete(int key){
        root = deleteNode(root, key);
    }
    public Node deleteNode(Node root, int key){
        if (root == null){
            return root;
        }
        if (key < root.key){
            root.left = deleteNode(root.left, key);
        } else if (key > root.key){
            root.right = deleteNode(root.right, key);
        }
        else {
            if (root.left == null){ //code if node only has one child
                return root.right; //replace with other child
            }
            else if (root.right == null){
                return root.left;
            }
            // node with two children
            root.key = minValue(root.right);
            // delete the inorder successor
            root.right = deleteNode(root.right, root.key);
        }

        return root;
    }
    public int minValue(Node root){
        int minv = root.key;
        while(root.left != null){
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public Node modify(){
        return root;
    }
    public Node search(int key){
        return searchNode(root, key);
    }
    public Node searchNode(Node root, int key){
        if (root == null || root.key == key){
            return root;
        }
        if (root.key > key){
            return searchNode(root.left, key);
        }
        else{
            return searchNode(root.right ,key);
        }
    }

    public void preOrder(Node root){
        
    }
    public void inOrder(Node root){
        if (root != null){
            inOrder(root.left);
            System.out.println("Key: " + root.key + ", Name: " + root.name + ", Address"+ root.address +
            ", Email: " + root.email + ", Phone: " + root.phone);
            inOrder(root.right);
        }
    }
    public void postOrder(Node root){

    }
}

class Node{
    int key;
    String name; //first and last
    String address; // address, city, state, zip
    String email;
    String phone;
    Node left;
    Node right;

    public Node(int key, String name, String address, String email, String phone){
        this.key = key;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        left = null;
        right = null;
    }
}
