package com.riskrieg.core.api.game.event;

import com.riskrieg.core.api.game.EndReason;
import com.riskrieg.core.api.game.entity.player.Player;
import java.util.Objects;
import java.util.Set;

public record TurnAdvanceEvent(Player currentPlayer, Player previousPlayer, Set<Player> defeatedPlayers, EndReason endReason) {

  public TurnAdvanceEvent {
    Objects.requireNonNull(currentPlayer);
    Objects.requireNonNull(previousPlayer);
    Objects.requireNonNull(defeatedPlayers);
    Objects.requireNonNull(endReason);
    defeatedPlayers = Set.copyOf(defeatedPlayers);
  }

}
