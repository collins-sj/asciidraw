package rates.structuring.asciidraw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import rates.structuring.asciidraw.App;
import rates.structuring.asciidraw.CommandExecutor;
import rates.structuring.asciidraw.Display;

/**
 * Unit test for Ascii Canvas Application.
 */
public class AppTest
{
    private CommandExecutor executor;
  
    @Before
    public void init() {
      executor = mock(CommandExecutor.class);
    }
    
    @Test
    public void testExtractCommandParameters()
    {
      int[] params = App.extractCommandParams("C 50 20");
      params[0] = 50;
      params[1] = 20;
      
      params = App.extractCommandParams("L 14 7");
      params[0] = 14;
      params[1] = 7;

      params = App.extractCommandParams("R 8 3 15 12");
      params[0] = 8;
      params[1] = 3;
      params[2] = 15;
      params[3] = 12;
    }

    @Test
    public void testExitCommand()
    {
      assertFalse(App.onInput("Q", executor));
      verify(executor, never()).removeLastCommand();      
      verify(executor, never()).addCommand(anyChar(), any(int[].class));
      verify(executor, never()).execute(any(Display.class));
    }

    @Test
    public void testCreateCommand()
    {
      assertTrue(App.onInput("C 1 1", executor));
      verify(executor, never()).removeLastCommand();      
      verify(executor).addCommand(anyChar(), any(int[].class));
      verify(executor).execute(any(Display.class));
    }

    @Test
    public void testLineCommand()
    {
      assertTrue(App.onInput("L 1 1", executor));
      verify(executor, never()).removeLastCommand();      
      verify(executor).addCommand(anyChar(), any(int[].class));
      verify(executor).execute(any(Display.class));
    }

    @Test
    public void testRectangleCommand()
    {
      assertTrue(App.onInput("R 1 1 1 1", executor));
      verify(executor, never()).removeLastCommand();      
      verify(executor).addCommand(anyChar(), any(int[].class));
      verify(executor).execute(any(Display.class));
    }

    @Test
    public void testRemoveLastCommand()
    {
      assertTrue(App.onInput("D", executor));
      verify(executor).removeLastCommand();      
      verify(executor, never()).addCommand(anyChar(), any(int[].class));
      verify(executor).execute(any(Display.class));
    }
}
