package rates.structuring.asciidraw;

/**
 * Command used to create a straight line in the x or y dimension.
 */
public class CreateLineCommand implements Command {

  public static char LINE_FILL = 'x'; 
  
  /* 
   * x1 represents a position on the x-axis, and therefore a value
   * that changes only in the secondary array dimension with respect to a constant y
   */
  int x1;
  /*
   * y1 represents a position on the y-axis, and therefore a value
   * that changes only in the primary array dimension with respect to a constant x
   */
  int y1;
  /* As above for x1 */
  int x2;
  /* As above for y1 */
  int y2;
  
  public CreateLineCommand(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  /*
   * Fills the display palette array based on the line parameters.
   * 
   * For a horizontal line, where y1 and y2 are equal, any y value therefore 
   * specifies the primary array - palette[y], with the fill moving between
   * x positions in the array located at palette[y].
   * E.g. For y1 == y2 == 1, and x1 = 0 and x2 = 3, then
   * []...
   * [x][x][x][x][][][]
   * []...
   * 
   * For a vertical line, where x1 and x2 are equal, any x value therefore specifies
   * the constant secondary array position for the range y1 to y2.
   * E.g. For x1 == x2 == 1, and y1 = 1 and y2 = 2, then
   * [][]...
   * [][x]...
   * [][x]...
   * [][]...
   */
  @Override
  public void fillPalette(char[][] palette) {
    // Horizontal Line
    if (y1 == y2) {
      //Fill from x1 to x2 for palette[y1]
      for(int i = x1; i <= x2; i++) {
        palette[y1][i] = LINE_FILL; 
      }
    }
    
    // Vertical Line
    if (x1 == x2) {
      //Fill from y1 to y2 for palette[i][x1]
      for(int i = y1; i <= y2; i++) {
        palette[i][x1] = LINE_FILL; 
      }
    }
  }

}
