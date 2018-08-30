package rates.structuring.asciidraw;

/**
 * Command interface specifying an operation to fill an array palette.
 */
public interface Command {
  
  /**
   * Populates an array with characters for display.
   * 
   * @param palette the array to populate
   */
  public void fillPalette(char[][] palette);
}