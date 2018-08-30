package rates.structuring.asciidraw;

import java.util.Optional;
import java.util.function.Consumer;

public class Display {
  
  public void draw(char[][] palette, Consumer<Optional<Character>> consumer) {
    for (int i = 0; i < palette.length; i++) {
      for (int j = 0; j < palette[i].length; j++) {
        consumer.accept(Optional.ofNullable(palette[i][j]));
      }
      consumer.accept(Optional.empty());
    }
    consumer.accept(Optional.empty());
  }
}
