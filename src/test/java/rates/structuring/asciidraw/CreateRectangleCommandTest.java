package rates.structuring.asciidraw;

import static org.junit.Assert.assertTrue;
import static rates.structuring.asciidraw.CreateRectangleCommand.RECT_FILL;

import org.junit.Test;

import rates.structuring.asciidraw.CreateRectangleCommand;

/**
 * Tests for {@link CreateRectangleCommand}.
 */
public class CreateRectangleCommandTest {

  @Test
  public void testCreateRectangle() {
    int x1 = 1;
    int y1 = 1;
    int x2 = 4;
    int y2 = 3;

    CreateRectangleCommand command = new CreateRectangleCommand(x1, y1, x2, y2);
    char[][] palette = new char[6][6];
    command.fillPalette(palette);

    // Test the horizontal row array indexed at 1
    assertTrue(palette[1][0] == Character.MIN_VALUE);
    assertTrue(palette[1][1] == RECT_FILL);
    assertTrue(palette[1][2] == RECT_FILL);
    assertTrue(palette[1][3] == RECT_FILL);
    assertTrue(palette[1][4] == RECT_FILL);
    assertTrue(palette[1][5] == Character.MIN_VALUE);

    // Test the horizontal row array indexed at 3
    assertTrue(palette[3][0] == Character.MIN_VALUE);
    assertTrue(palette[3][1] == RECT_FILL);
    assertTrue(palette[3][2] == RECT_FILL);
    assertTrue(palette[3][3] == RECT_FILL);
    assertTrue(palette[3][4] == RECT_FILL);
    assertTrue(palette[3][5] == Character.MIN_VALUE);

    // Test the vertical row array indexed at 1
    assertTrue(palette[0][1] == Character.MIN_VALUE);
    assertTrue(palette[1][1] == RECT_FILL);
    assertTrue(palette[2][1] == RECT_FILL);
    assertTrue(palette[3][1] == RECT_FILL);
    assertTrue(palette[4][1] == Character.MIN_VALUE);
    assertTrue(palette[5][1] == Character.MIN_VALUE);

    // Test the vertical row array indexed at 4
    assertTrue(palette[0][4] == Character.MIN_VALUE);
    assertTrue(palette[1][4] == RECT_FILL);
    assertTrue(palette[2][4] == RECT_FILL);
    assertTrue(palette[3][4] == RECT_FILL);
    assertTrue(palette[4][4] == Character.MIN_VALUE);
    assertTrue(palette[5][4] == Character.MIN_VALUE);

    // All other rows should have no line fill
    for (int i = 0; i < palette.length; i++) {
      for (int j = 0; j < palette[i].length; j++) {
        // Exclude the rows and columns already validated
        if (i != 1 && i != 3 && j != 1 && j != 4) {
          assertTrue(palette[i][j] == Character.MIN_VALUE);
        }
      }
    }
  }

}
