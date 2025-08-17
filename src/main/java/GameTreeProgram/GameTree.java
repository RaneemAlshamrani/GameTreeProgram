//Raneem alshomrani
//2208199
//GCS
//ralshamrani0098@stu.kau.edu.sa
//-------------------------------
package GameTreeProgram;

import java.io.*;
// This class represents the binary tree data structure
// It stores the root node and provides methods to perform operations on the tree
public class GameTree {
    public PlayerNode rootNode;

    // Constructor to create an empty tree
    public GameTree() {
        rootNode = null;
    }

    // Method to add a new player to the tree
    public void addPlayer(int id, String name, int stamina) {
        rootNode = insertRecursively(rootNode, id, name, stamina);
    }

    // Recursive helper method to insert a new player into the tree
    private PlayerNode insertRecursively(PlayerNode root, int id, String name, int stamina) {
        // Base case: if the current node is null, create a new node and return it
        if (root == null) {
            root = new PlayerNode(id, name, stamina);
            return root;
        }
        // Recursive case: compare the ID with the current node's ID
        // If the new ID is smaller, insert into the left subtree
        // If the new ID is larger, insert into the right subtree
        if (id < root.playerId)
            root.leftChild = insertRecursively(root.leftChild, id, name, stamina);
        else if (id > root.playerId)
            root.rightChild = insertRecursively(root.rightChild, id, name, stamina);
        return root;
    }

    // Method to find a player in the tree by ID
    public PlayerNode findPlayer(int id) {
        return searchRecursively(rootNode, id);
    }

    // Recursive helper method to search for a player in the tree
    private PlayerNode searchRecursively(PlayerNode root, int id) {
        // Base case: if the current node is null or the ID matches, return the current node
        if (root == null || root.playerId == id)
            return root;
        // Recursive case: search in the left or right subtree based on the ID
        if (id < root.playerId)
            return searchRecursively(root.leftChild, id);
        return searchRecursively(root.rightChild, id);
    }

    // Method to display all players in the tree, ordered by ID (inorder traversal)
    public void displayPlayers(PlayerNode node, FileWriter writer) throws IOException {
        if (node != null) {
            // Only display players with positive stamina levels
            if(node.staminaLevel > 0) {
                writer.write("\t" + node.playerId + "\t\t" + node.playerName + "\t\t" + node.staminaLevel + "\t\n");
            }
            // Recursively traverse the left subtree
            displayPlayers(node.leftChild, writer);
            // Recursively traverse the right subtree
            displayPlayers(node.rightChild, writer);
        }
    }

    // Method to modify the stamina level of a player
    public void modifyStamina(PlayerNode node, int id, FileWriter writer) throws IOException {
        if (node != null) {
            // Recursively traverse the left subtree
            modifyStamina(node.leftChild, id, writer);

            // If the current node's ID matches the target ID
            if (node.playerId == id) {
                // Reduce the stamina level by 5
                node.staminaLevel -= 5;
                // If the stamina level reaches 0 or below
                if (node.staminaLevel <= 0) {
                    // Write a message indicating the player has left the game
                    writer.write("The stamina level for the player <" + node.playerName + "> reaches zero and <" + node.playerName + "> left the game!\n");
                    writer.write("===================================================================================================\n\n");

                    // Remove the player from the tree (commented out for now)
                    // rootNode = removePlayer(rootNode, id);
                    return;
                }
                // Write a message indicating the stamina level has been reduced
                writer.write("The player <" + node.playerName + "> received a hit and the stamina level reduced by 5\n");
                writer.write("===================================================================================================\n\n");
                return;
            }

            // Recursively traverse the right subtree
            modifyStamina(node.rightChild, id, writer);
        }
    }

    // Method to remove a player from the tree
    public PlayerNode removePlayer(PlayerNode root, int id) {
        // Base case: if the tree is empty, return null
        if (root == null)
            return root;

        // Recursive case: search for the node to be removed
        if (id < root.playerId)
            root.leftChild = removePlayer(root.leftChild, id);
        else if (id > root.playerId)
            root.rightChild = removePlayer(root.rightChild, id);
        else {
            // Case 1: The node has no left child
            if (root.leftChild == null)
                return root.rightChild;
                // Case 2: The node has no right child
            else if (root.rightChild == null)
                return root.leftChild;
            // Case 3: The node has both left and right children
            // Replace the node with the minimum value from the right subtree
            root.playerId = findMinValue(root.rightChild);
            // Remove the node with the minimum value from the right subtree
            root.rightChild = removePlayer(root.rightChild, root.playerId);
        }
        return root;
    }

    // Helper method to find the minimum value in a subtree
    private int findMinValue(PlayerNode root) {
        int minVal = root.playerId;
        while (root.leftChild != null) {
            minVal = root.leftChild.playerId;
            root = root.leftChild;
        }
        return minVal;
    }
}

