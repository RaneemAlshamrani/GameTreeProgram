//Raneem alshomrani
//2208199
//GCS
//ralshamrani0098@stu.kau.edu.sa
//-------------------------------
package GameTreeProgram;

// This class represents a node in the binary tree
// It stores the player's ID, name, stamina level, and references to the left and right child nodes
public class PlayerNode {
    int playerId;
    String playerName;
    int staminaLevel;
    PlayerNode leftChild, rightChild;

    // Constructor to create a new GameTreeProgram.PlayerNode
    public PlayerNode(int id, String name, int stamina) {
        this.playerId = id;
        this.playerName = name;
        this.staminaLevel = stamina;
        leftChild = rightChild = null;
    }
}