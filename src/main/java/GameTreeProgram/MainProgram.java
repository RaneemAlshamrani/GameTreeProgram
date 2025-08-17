//Raneem alshomrani
//2208199
//GCS
//ralshamrani0098@stu.kau.edu.sa
//------------------------------
package GameTreeProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Main class that executes the program
public class MainProgram {
    public static void main(String[] args) {
        try {
            // Read initial information from a file
            Scanner initialScanner = new Scanner(new File("C:\\Users\\ranee\\OneDrive\\Dokumente\\NetBeansProjects\\assignment3_2208199_RaneemAlshomrani_GCS\\IntialInformationProgram#3.txt"));
            // Read commands from a file
            Scanner commandScanner = new Scanner(new File("C:\\Users\\ranee\\OneDrive\\Dokumente\\NetBeansProjects\\assignment3_2208199_RaneemAlshomrani_GCS\\CommandsProgram#3.txt"));
            // Create a FileWriter to write output to a file
            FileWriter writer = new FileWriter("output.txt");

            // Create a new GameTreeProgram.GameTree instance
            GameTree gameTree = new GameTree();

            // Read initial information and create the tree
            int numPlayers = initialScanner.nextInt();
            initialScanner.nextLine(); // consume newline
            for (int i = 0; i < numPlayers; i++) {
                int id = initialScanner.nextInt();
                String name = initialScanner.next();
                gameTree.addPlayer(id, name, 15); // default stamina level 15
            }

            // Write welcome message to the output file
            writer.write("\tWelcome to Game Tree Program\n");
            writer.write("\t---------------------------------------------------------\n");

            // Execute commands
            while (commandScanner.hasNextLine()) {
                String command = commandScanner.next();
                switch (command) {
                    case "STARTUP":
                        break;
                    case "ADD_PLAYER":
                        int id = commandScanner.nextInt();
                        String name = commandScanner.next();
                        gameTree.addPlayer(id, name, 15); // default stamina level 15
                        break;
                    case "DISPLAY_PLAYER_INFO":
                        int searchId = commandScanner.nextInt();
                        PlayerNode player = gameTree.findPlayer(searchId);
                        if (player != null) {
                            writer.write("Player with ID <" + player.playerId + "> is <" + player.playerName + "> and the stamina level is <" + player.staminaLevel + ">\n");
                            writer.write("===================================================================================================\n\n");
                        } else {
                            writer.write("Not found any player with ID number <" + searchId + ">\n");
                            writer.write("===================================================================================================\n\n");
                        }
                        break;

                    case "DISPLAY_ALL_PLAYERS":
                        writer.write("The players existed in the game are:\n\n");
                        writer.write("       PlayerID         Player Name      Stamina level\n");
                        writer.write("---------------------------------------------------\n");
                        gameTree.displayPlayers(gameTree.rootNode, writer);
                        writer.write("===================================================================================================\n\n");
                        break;

                    case "NUM_OF_PLAYERS":
                        writer.write("Number of players : " + numPlayers + "\n");
                        writer.write("===================================================================================================\n\n");
                        break;

                    case "UPDATE_PLAYER_INFO":
                        int updateId = commandScanner.nextInt();
                        PlayerNode playe = gameTree.findPlayer(updateId);
                        if (playe != null) {
                            gameTree.modifyStamina(gameTree.rootNode, updateId, writer);
                        } else {
                            writer.write("Not found any player with ID number <" + updateId + ">\n");
                            writer.write("===================================================================================================\n\n");
                        }
                        break;

                    case "DELETE_PLAYER":
                        int deleteId = commandScanner.nextInt();
                        PlayerNode deletedPlayer = gameTree.findPlayer(deleteId);
                        if (deletedPlayer != null) {
                            writer.write("The player <" + deletedPlayer.playerName + "> left the game\n");
                            writer.write("===================================================================================================\n\n");
                            gameTree.rootNode = gameTree.removePlayer(gameTree.rootNode, deleteId);
                        } else {
                            writer.write("Not found any player with ID number <" + deleteId + ">\n");
                            writer.write("===================================================================================================\n\n");
                        }
                        break;

                    case "QUIT":
                        writer.write("\n\n\t\t-------------------------------------\n");
                        writer.write("\t\t|         Good Bye                 |\n");
                        writer.write("\t\t-------------------------------------\n");
                        writer.close();
                        return;

                    default:
                        break;
                }
                commandScanner.nextLine(); // consume newline
            }
            initialScanner.close();
            commandScanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
