package rates.structuring.asciidraw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import rates.structuring.asciidraw.Command;
import rates.structuring.asciidraw.CommandExecutor;
import rates.structuring.asciidraw.CreateCanvasCommand;
import rates.structuring.asciidraw.CreateLineCommand;
import rates.structuring.asciidraw.CreateRectangleCommand;
import rates.structuring.asciidraw.Display;

/**
 * Tests for {@link CommandExecutor}.
 */
public class CommandExecutorTest {

  CommandExecutor executor;
  
  @Before
  public void init() {
    executor = new CommandExecutor();
  }
  
  @Test
  public void testExecuteCommands() {
    Display display = mock(Display.class);
    Deque<Command> mockCommands = new LinkedList<>(); 
    CreateCanvasCommand canvasCommand = mock(CreateCanvasCommand.class);
    when(canvasCommand.getWidth()).thenReturn(20);
    when(canvasCommand.getHeight()).thenReturn(10);
    
    mockCommands.add(canvasCommand);
    mockCommands.add(mock(CreateLineCommand.class));
    mockCommands.add(mock(CreateRectangleCommand.class));
    executor.setCommandHistory(mockCommands);
    
    executor.execute(display);
    
    verify(display).draw(any(char[][].class), System.out::println);
    for (Command command : mockCommands) {
      verify(command).fillPalette(any(char[][].class));
    }
  }

  @Test
  public void testCommandCleanup() {
    Display display = mock(Display.class);
    Deque<Command> mockCommands = new LinkedList<>(); 
    CreateCanvasCommand canvasCommand = mock(CreateCanvasCommand.class);
    when(canvasCommand.getWidth()).thenReturn(20);
    when(canvasCommand.getHeight()).thenReturn(10);

    //Two valid commands
    mockCommands.add(canvasCommand);
    mockCommands.add(mock(CreateLineCommand.class));

    CreateRectangleCommand createRectangleCommand = mock(CreateRectangleCommand.class);
    //Simulate and add an invalid command
    Mockito.doThrow(RuntimeException.class)
      .when(createRectangleCommand).fillPalette(any(char[][].class));
    executor.setCommandHistory(mockCommands);
    mockCommands.add(createRectangleCommand);
    
    // 3 commands, 2 valid and 1 invalid
    assertEquals(3, executor.getCommandHistory().size());

    try {
      executor.execute(display);
    } catch (RuntimeException e) {
      // After execution, and failure, the invalid command should be removed
      assertEquals(2, executor.getCommandHistory().size());
    }
  }

  @Test(expected = RuntimeException.class)
  public void testExecuteOrder() {
    Display display = mock(Display.class);
    Deque<Command> mockCommands = new LinkedList<>(); 
    CreateCanvasCommand canvasCommand = mock(CreateCanvasCommand.class);
    when(canvasCommand.getWidth()).thenReturn(20);
    when(canvasCommand.getHeight()).thenReturn(10);
    
    //Add commands out of order
    mockCommands.add(mock(CreateLineCommand.class));
    mockCommands.add(canvasCommand);
    mockCommands.add(mock(CreateRectangleCommand.class));
    
    executor.execute(display);
    
    verify(display).draw(any(char[][].class), System.out::println);
    for (Command command : mockCommands) {
      verify(command).fillPalette(any(char[][].class));
    }
  }

  @Test
  public void testAddCreateCommand() {
    executor.addCommand('C', new int[] {25, 20});
    Deque<Command> commands = executor.getCommandHistory();
    assertEquals(1, commands.size());
    assertTrue(CreateCanvasCommand.class.isInstance(commands.peek()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCreateCommandWithInvalidArgs() {
    executor.addCommand('C', new int[] {17});
  }

  @Test
  public void testAddLineCommand() {
    executor.addCommand('L', new int[] {25, 20, 40, 20});
    Deque<Command> commands = executor.getCommandHistory();
    assertEquals(1, commands.size());
    assertTrue(CreateLineCommand.class.isInstance(commands.peek()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLineCommandWithInvalidArgs() {
    executor.addCommand('L', new int[] {25, 20});
  }

  @Test
  public void testAddRectangleCommand() {
    executor.addCommand('R', new int[] {25, 20, 40, 20});
    Deque<Command> commands = executor.getCommandHistory();
    assertEquals(1, commands.size());
    assertTrue(CreateRectangleCommand.class.isInstance(commands.peek()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleCommandWithInvalidArgs() {
    executor.addCommand('R', new int[] {25, 20});
  }
  
  @Test
  public void testAddMultipleCommands() {
    executor.addCommand('C', new int[] {25, 20});
    executor.addCommand('L', new int[] {25, 20, 40, 20});
    executor.addCommand('R', new int[] {1, 2, 3, 4});
    
    Deque<Command> commands = executor.getCommandHistory();
    assertEquals(3, commands.size());
    assertTrue(CreateCanvasCommand.class.isInstance(commands.pop()));
    assertTrue(CreateLineCommand.class.isInstance(commands.pop()));
    assertTrue(CreateRectangleCommand.class.isInstance(commands.pop()));
  }

  @Test
  public void testRemoveCommands() {
    executor.addCommand('C', new int[] {25, 20});
    executor.addCommand('L', new int[] {25, 20, 40, 20});
    executor.addCommand('R', new int[] {1, 2, 3, 4});
    
    Deque<Command> commands = executor.getCommandHistory();
    assertEquals(3, commands.size());
    
    executor.removeLastCommand();
    assertEquals(2, commands.size());
    assertTrue(CreateCanvasCommand.class.isInstance(commands.pop()));
    assertTrue(CreateLineCommand.class.isInstance(commands.pop()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandsOutOfOrder() {
    executor.addCommand('L', new int[] {25, 20, 40, 20});
    executor.addCommand('R', new int[] {1, 2, 3, 4});
    executor.addCommand('C', new int[] {25, 20});
  }

  @Test(expected = RuntimeException.class)
  public void testInvalidCommands() {
    executor.addCommand('Z', new int[] {25, 20, 40, 20});
  }
}
