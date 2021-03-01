import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class _test_runnerTestSuite {

  @Test
  public void pruebaGetPositionLetter() {
    char letter="A".charAt(0);
    int result = Main.getPositionLetter(letter);
    assertEquals(0, result,0);
    
  }

  @Test
  public void pruebaPierdeBarco() {
    int barcos = 5;
    int result = Main.pierdeBarco(barcos);
    assertEquals(4, result,0);
  }

}