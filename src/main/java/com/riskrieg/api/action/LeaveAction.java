package com.riskrieg.api.action;

import com.riskrieg.nation.Nation;
import com.riskrieg.player.Player;
import java.util.Collection;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public final class LeaveAction implements GameAction<Player> {

  private final Player player;
  private final Collection<Player> players;
  private final Collection<Nation> nations;

  public LeaveAction(Player player, Collection<Player> players, Collection<Nation> nations) {
    this.player = player;
    this.players = players;
    this.nations = nations;
  }

  @Override
  public void submit(@Nullable Consumer<? super Player> success, @Nullable Consumer<? super Throwable> failure) {
    try {
      if (player == null || !players.contains(player)) {
        throw new IllegalStateException("Player is not present");
      }
      Nation toRemove = null;
      for (Nation nation : nations) {
        if (nation.getLeaderIdentity().equals(player.identity())) {
          toRemove = nation;
        }
      }
      if (toRemove != null) { // TODO: Handle alliances
        nations.remove(toRemove);
      }
      players.remove(player);
      if (success != null) {
        success.accept(player);
      }
    } catch (Exception e) {
      if (failure != null) {
        failure.accept(e);
      }
    }
  }

}
