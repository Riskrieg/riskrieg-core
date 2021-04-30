package com.riskrieg.core.unsorted.order;

import com.riskrieg.core.unsorted.constant.StandardPlayerColor;
import com.riskrieg.core.unsorted.player.Player;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nonnull;

public final class StandardOrder implements TurnOrder {

  @Nonnull
  @Override
  public Deque<Player> sort(@Nonnull final Collection<Player> players) {
    List<Player> playerList = new ArrayList<>(players);
    playerList.sort(new StandardComparator());
    return new ArrayDeque<>(playerList);
  }

  private static class StandardComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
      return StandardPlayerColor.valueOf(o1.color()).compareTo(StandardPlayerColor.valueOf(o2.color()));
    }

  }

}