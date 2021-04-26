import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.riskrieg.core.api.Conquest;
import com.riskrieg.core.api.Riskrieg;
import com.riskrieg.core.gamemode.GameState;
import com.riskrieg.core.map.MapOptions;
import com.riskrieg.core.map.options.Availability;
import com.riskrieg.core.map.options.InterfaceAlignment;
import com.riskrieg.core.map.options.alignment.HorizontalAlignment;
import com.riskrieg.core.map.options.alignment.VerticalAlignment;
import com.riskrieg.core.order.RandomOrder;
import com.riskrieg.core.player.Identity;
import com.riskrieg.map.RkmMap;
import com.riskrieg.map.vertex.Territory;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiTests {

  @Test
  public void testRiskrieg() {
    Riskrieg api = new Riskrieg();
    api.create(Path.of("res/saves/test/test.json"), Conquest.class).complete(Assertions::assertNotNull);
  }

  @Test
  public void testJoin() {
    Conquest game = new Conquest();
    game.join("Name", new Color(1, 1, 1));
    assertEquals(0, game.players().size());
    game.join("Name", new Color(1, 1, 1)).submit();
    assertEquals(1, game.players().size());
    game.join("Name2", new Color(1, 1, 1)).submit();
    assertEquals(1, game.players().size());
    game.join(Identity.of("identity2"), "Name", new Color(1, 1, 1)).submit();
    assertEquals(1, game.players().size());
    game.join(Identity.of("identity2"), "Name", new Color(2, 2, 2)).submit();
    assertEquals(2, game.players().size());
    game.join(Identity.of("identity2"), "Name", new Color(3, 3, 3)).submit();
    assertEquals(2, game.players().size());
    game.join(Identity.of("identity3"), "Name", new Color(3, 3, 3)).submit();
    assertEquals(3, game.players().size());
  }

  @Test
  public void testConquest() {
    Conquest game = new Conquest();

    game.join(Identity.of("1234"), "Test", new Color(0, 0, 0));
    assertEquals(0, game.players().size());

    game.join(Identity.of("1234"), "Test", new Color(0, 0, 0)).submit();
    assertEquals(1, game.players().size());

    game.join(Identity.of("1234"), "Test", new Color(0, 0, 0)).submit();
    assertEquals(1, game.players().size());

    game.start(new RandomOrder()).submit();
    assertEquals(game.gameState(), GameState.SETUP);

    assertFalse(game.map().isSet());

    try {
      game.selectMap(
          RkmMap.load(new URL("https://github.com/Riskrieg/maps/raw/main/antarctica.rkm")).orElseThrow(),
          new MapOptions(Availability.AVAILABLE, new InterfaceAlignment(VerticalAlignment.BOTTOM, HorizontalAlignment.LEFT)))
          .submit();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue(game.map().isSet());

    game.join("Janice", new Color(1, 1, 1)).submit(player -> {
      game.formNation(player, game.map().getGraph().vertexSet().toArray(new Territory[0])[0].id()).submit();
    });
    assertEquals(2, game.players().size());
    assertEquals(1, game.nations().size());

    game.start(new RandomOrder()).submit();
    assertEquals(game.gameState(), GameState.SETUP);

    game.formNation(Identity.of("1234"), game.map().getGraph().vertexSet().toArray(new Territory[0])[1].id()).submit();
    assertEquals(2, game.nations().size());

    game.start(new RandomOrder()).submit();
    assertEquals(game.gameState(), GameState.RUNNING);

  }

}
