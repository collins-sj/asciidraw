package rates.structuring.asciidraw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.internal.matchers.GreaterThan;

public class DrawRectSteps {

  Command command;
  char[][] palette;
  
  public DrawRectSteps() {
  }

  @Given("an input of type rectangle")
  public void rectCommand() {
    command = new CreateRectangleCommand(1, 5, 8, 7);
  }
  
  @When("the output is drawn")
  public void drawOuput() {
    command.fillPalette(palette);
  }
  
  @Then("a rectangle is drawn") 
  public void outIsDisplayed() {
    assertThat("Palette is empty", palette.length > 0);
    assertThat(palette.length, is(new GreaterThan<Integer>(0)));
  }
}
