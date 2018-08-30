package rates.structuring.asciidraw;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Executor for Command objects.
 * 
 * Maintains a history of commands, and executes each in turn
 * based on insertion order.
 */
public class CommandExecutor {

  private Deque<Command> commandHistory = new LinkedList<>();

  public CommandExecutor() { 
  }

  /**
   * Executes the full history of commands, supplying the 
   * output for display.
   * 
   * @param display the object an operations to display the command output
   */
  public void execute(Display display) {
    
    Command command = commandHistory.peek();
    // Validate that the canvas is created first
    if (!CreateCanvasCommand.class.isInstance(command)) {
      throw new RuntimeException();
    }
    // Initialise the palette
    CreateCanvasCommand com = (CreateCanvasCommand) command;
    char[][] palette = new char[com.getHeight() + 2][com.getWidth() + 2]; // Plus edges
    
    //Fill the palette and output to the canvas
    commandHistory.forEach(c -> {
      try {
        c.fillPalette(palette);
      } catch (Exception e) {
        /* Command has failed. Remove from the command queue.
         * Command instances do not implement a custom equals method, 
         * but removal based on object instance reference is sufficient here. */
        commandHistory.remove(c);
        throw e;
      }
    });
    
    // Output to the canvas
    display.draw(palette, System.out::println);
  }
  
  /**
   * Creates and adds a command to the queue.
   * 
   * @param key the specifier for the command type
   * @param params the parameters for the command
   */
  public void addCommand(char key, int[] params) {
    switch (key) {
      case 'C' :
        ensureOrdering();
        validateArgs(params, 2);
        commandHistory.add(new CreateCanvasCommand(params[0], params[1]));
        break;
      case 'L' :
        validateArgs(params, 4);
        commandHistory.add(new CreateLineCommand(params[0], params[1], params[2], params[3]));
        break;
      case 'R' :
        validateArgs(params, 4);
        commandHistory.add(new CreateRectangleCommand(params[0], params[1], params[2], params[3]));
        break;
      default:
        throw new RuntimeException();
    }
  }

  /**
   * Remove the last command added to the command history.
   */
  public void removeLastCommand() {
    if (!commandHistory.isEmpty()) {
      commandHistory.removeLast();
    }
  }

  private void validateArgs(int[] values, int expectedSize) {
    if(values.length != expectedSize) {
      throw new IllegalArgumentException();
    }
  }

  private void ensureOrdering() {
    if(!commandHistory.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }

  public Deque<Command> getCommandHistory() {
    return commandHistory;
  }

  /* package */ void setCommandHistory(Deque<Command> commandHistory) {
    this.commandHistory = commandHistory;
  }
}
