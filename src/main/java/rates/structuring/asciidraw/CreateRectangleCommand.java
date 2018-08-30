package rates.structuring.asciidraw;

/**
 * Command used to create a rectangle with upper left point at (x1, y1)
 * and the lower right point at (x2, y2).
 */
public class CreateRectangleCommand implements Command {

  public static char RECT_FILL = 'x'; 
  
  /* x1 represents the upper left position on the x-axis for a rectangle. */
  int x1;
  /* y1 represents the upper left position on the y-axis for a rectangle. */
  int y1;
  /* x2 represents the lower right position on the x-axis for a rectangle. */
  int x2;
  /* y2 represents the lower right position on the y-axis for a rectangle. */
  int y2;
  
  public CreateRectangleCommand(int x1, int y1, int x2, int y2) {
    super();
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  /* 
   * Fills the array palette both horizontally and vertically for the lines
   * dictating the edges of a rectangle, as specified by (x1, y1) and (x2, y2).
   */
  @Override
  public void fillPalette(char[][] palette) {
    // Horizontal line fills
    for(int i = x1; i <= x2; i++) {
      // Horizontal line, filling the array located at y1 from x1 to x2
      palette[y1][i] = RECT_FILL;
      // Horizontal line, filling the array located at y2 from x1 to x2
      palette[y2][i] = RECT_FILL;
    }
    
    // Vertical line fills
    for(int i = y1; i <= y2; i++) {
      // Vertical line, filling each secondary array for a single value of x1
      palette[i][x1] = RECT_FILL; 
      // Vertical line, filling each secondary array for a single value of x2
      palette[i][x2] = RECT_FILL; 
    }
  }

}
