package rates.structuring.asciidraw;

import static rates.structuring.asciidraw.CreateLineCommand.LINE_FILL;

import org.junit.Assert;
import org.junit.Test;

import rates.structuring.asciidraw.CreateLineCommand;

/**
 * Tests for {@link CreateLineCommand}.
 */
public class CreateLineCommandTest {

  @Test
  public void testHorizontalLine() {
    int x1 = 1; 
    int y1 = 1;
    int x2 = 4;
    int y2 = 1;
    
    CreateLineCommand command = new CreateLineCommand(x1, y1, x2, y2);
    char[][] palette = new char[6][6];
    command.fillPalette(palette);

    // Test the horizontal row array indexed at 1
    Assert.assertTrue(palette[1][0] == Character.MIN_VALUE);
    Assert.assertTrue(palette[1][1] == LINE_FILL);
    Assert.assertTrue(palette[1][2] == LINE_FILL);
    Assert.assertTrue(palette[1][3] == LINE_FILL);
    Assert.assertTrue(palette[1][4] == LINE_FILL);
    Assert.assertTrue(palette[1][5] == Character.MIN_VALUE);

    // All other rows should have no line fill
    for (int i = 0; i < palette.length; i++) {
      for (int j = 0; j < palette[i].length; j++) {
        if (i != 1) {
          Assert.assertTrue(palette[i][j] == Character.MIN_VALUE); 
        }
      }
    }
  }
  
  @Test
  public void testVerticalLine() {
    int x1 = 3; 
    int y1 = 2;
    int x2 = 3;
    int y2 = 5;
    
    CreateLineCommand clc = new CreateLineCommand(x1, y1, x2, y2);
    char[][] palette = new char[6][6];
    clc.fillPalette(palette);

    // Test the vertical row array indexed at 1
    Assert.assertTrue(palette[0][3] == Character.MIN_VALUE);
    Assert.assertTrue(palette[1][3] == Character.MIN_VALUE);
    Assert.assertTrue(palette[2][3] == LINE_FILL);
    Assert.assertTrue(palette[3][3] == LINE_FILL);
    Assert.assertTrue(palette[4][3] == LINE_FILL);
    Assert.assertTrue(palette[5][3] == LINE_FILL);

    // All other columns should have no line fill
    for (int i = 0; i < palette.length; i++) {
      for (int j = 0; j < palette[i].length; j++) {
        if (j != 3) {
          Assert.assertTrue(palette[i][j] == Character.MIN_VALUE); 
        }
      }
    }
  }
}
