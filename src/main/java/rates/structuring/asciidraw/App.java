package rates.structuring.asciidraw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ascii drawing application.
 */
public class App
{
  public static void main(String[] args) {
    CommandExecutor commandExecutor = new CommandExecutor();
    
    try (Scanner sc = new Scanner(System.in);) {
      boolean programTerminated = false;
      while (!programTerminated) {
        System.out.print("enter command: ");
        // Blocking call. Awaits next input
        if (sc.hasNextLine()) {
          String input = sc.nextLine();
          try {
            programTerminated = !onInput(input, commandExecutor);            
          } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Command is invalid!!\n");
          }
        }
      }
    }
  }

  
  /**
   * Action on input - determine the command, then output the display
   * based on this input combined with any previous commands.
   * 
   * @param input the command to draw, remove or quit
   * @param commandExecutor controller for Command objects
   * @return false if the key indicates program termination, true otherwise
   */
  public static boolean onInput(String input, CommandExecutor commandExecutor) {
      char key = input.charAt(0);

      // Exit on 'Q'
      if ('Q' == key) {
        System.out.println("Exiting...");
        return false;
      } 
      
      // Add or remove commands
      if ('D' == key) {
        // Remove last
        commandExecutor.removeLastCommand();
      } else {
        // Extract the input to a command
        commandExecutor.addCommand(key, extractCommandParams(input));
      }

      //Output Display
      commandExecutor.execute(new Display());
      return true;
  }
  
  public static int[] extractCommandParams(String input) {
    String params = input.substring(1, input.length());
    String[] paramArray = params.trim().split("\\s+");
    return Arrays.stream(paramArray).mapToInt(Integer::parseInt).toArray();
  }
}
