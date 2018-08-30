package rates.structuring.asciidraw;

/**
 * Command used to create a bordered representation of a display canvas.
 */
public class CreateCanvasCommand implements Command {

  public static char TOKEN_HORIZONTAL = '-'; 
  public static char TOKEN_VERTICAL = '|';
  public static char TOKEN_SPACER = ' ';

  private int width;
  private int height;

  public CreateCanvasCommand(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public void fillPalette(char[][] palette) {
    // Iterate over each row i
    for(int i = 0; i < palette.length; i++) {
      // Iterate over each column j in row i
      for(int j = 0; j < palette[i].length; j++) {
        // Fill all columns in the first row, and the last row
        if(i == 0 || i == palette.length - 1){
          palette[i][j] = TOKEN_HORIZONTAL;
        } 
        // Fill the first column, and the last column in all rows
        else if (j == 0 || j == palette[i].length - 1) {
          palette[i][j] = TOKEN_VERTICAL;
        } else {
          // Spacer fill
          palette[i][j] = TOKEN_SPACER;
        }
      }
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
