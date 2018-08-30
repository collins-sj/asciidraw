package rates.structuring.asciidraw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static rates.structuring.asciidraw.CreateCanvasCommand.TOKEN_HORIZONTAL;
import static rates.structuring.asciidraw.CreateCanvasCommand.TOKEN_SPACER;
import static rates.structuring.asciidraw.CreateCanvasCommand.TOKEN_VERTICAL;

import org.junit.Test;

import rates.structuring.asciidraw.CreateCanvasCommand;

/**
 * Tests for {@link CreateCanvasCommand}.
 */
public class CreateCanvasCommandTest {

  @Test
  public void testCreateCanvas() {
    int width = 3;
    int height = 4;

    CreateCanvasCommand command = new CreateCanvasCommand(width, height);
    char[][] palette = new char[height+2][width+2];
    command.fillPalette(palette);

    //Validate the palette size
    assertEquals(6, palette.length);
    assertEquals(5, palette[0].length);
    
    // Test the horizontal row array indexed at 0
    assertTrue(palette[0][0] == TOKEN_HORIZONTAL);
    assertTrue(palette[0][1] == TOKEN_HORIZONTAL);
    assertTrue(palette[0][2] == TOKEN_HORIZONTAL);
    assertTrue(palette[0][3] == TOKEN_HORIZONTAL);
    assertTrue(palette[0][4] == TOKEN_HORIZONTAL);

    // Test the horizontal row array indexed at 5
    assertTrue(palette[5][0] == TOKEN_HORIZONTAL);
    assertTrue(palette[5][1] == TOKEN_HORIZONTAL);
    assertTrue(palette[5][2] == TOKEN_HORIZONTAL);
    assertTrue(palette[5][3] == TOKEN_HORIZONTAL);
    assertTrue(palette[5][4] == TOKEN_HORIZONTAL);

    // Test the vertical row array indexed at 0
    assertTrue(palette[0][0] == TOKEN_HORIZONTAL);
    assertTrue(palette[1][0] == TOKEN_VERTICAL);
    assertTrue(palette[2][0] == TOKEN_VERTICAL);
    assertTrue(palette[3][0] == TOKEN_VERTICAL);
    assertTrue(palette[4][0] == TOKEN_VERTICAL);
    assertTrue(palette[5][0] == TOKEN_HORIZONTAL);

    // Test the vertical row array indexed at 4
    assertTrue(palette[0][4] == TOKEN_HORIZONTAL);
    assertTrue(palette[1][4] == TOKEN_VERTICAL);
    assertTrue(palette[2][4] == TOKEN_VERTICAL);
    assertTrue(palette[3][4] == TOKEN_VERTICAL);
    assertTrue(palette[4][4] == TOKEN_VERTICAL);
    assertTrue(palette[5][4] == TOKEN_HORIZONTAL);

    // All other rows should have no line fill
    for (int i = 0; i < palette.length; i++) {
      for (int j = 0; j < palette[i].length; j++) {
        // Exclude the rows and columns already validated
        if (i != 0 && i != 5 && j != 0 && j != 4) {
          assertTrue(palette[i][j] == TOKEN_SPACER);
        }
      }
    }
  }

}
