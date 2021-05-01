package com.riskrieg.core.api.gamemode.conquest;

import com.riskrieg.core.api.Save;
import com.riskrieg.core.api.gamemode.GameID;
import com.riskrieg.core.api.gamemode.GameMode;
import com.riskrieg.core.api.nation.Nation;
import com.riskrieg.core.api.order.TurnOrder;
import com.riskrieg.core.api.player.Identity;
import com.riskrieg.core.api.player.Player;
import com.riskrieg.core.internal.action.Action;
import com.riskrieg.core.internal.action.running.ClaimAction;
import com.riskrieg.core.internal.action.setup.FormNationAction;
import com.riskrieg.core.internal.action.setup.JoinAction;
import com.riskrieg.core.internal.action.setup.LeaveAction;
import com.riskrieg.core.internal.action.setup.SelectMapAction;
import com.riskrieg.core.internal.action.setup.StartAction;
import com.riskrieg.core.unsorted.gamemode.GameState;
import com.riskrieg.core.unsorted.map.GameMap;
import com.riskrieg.core.unsorted.map.MapOptions;
import com.riskrieg.map.RkmMap;
import com.riskrieg.map.territory.TerritoryId;
import java.awt.Color;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public final class ConquestMode implements GameMode {

  private final GameID id;
  private final Instant creationTime;
  private Instant lastUpdated;

  private GameState gameState;
  private GameMap gameMap;

  private Deque<Player> players;
  private Set<Nation> nations;

  public ConquestMode() {
    this.id = GameID.random();
    this.creationTime = Instant.now();
    this.lastUpdated = Instant.now();
    this.gameState = GameState.SETUP;
    this.gameMap = new GameMap();

    this.players = new ArrayDeque<>();
    this.nations = new HashSet<>();
  }

  public ConquestMode(Save save) {
    this.id = save.id();
    this.creationTime = save.creationTime().asInstant();
    this.lastUpdated = save.lastUpdated().asInstant();
    this.gameState = save.gameState();
    // TODO: Load map
    this.players = new ArrayDeque<>(save.players());
    this.nations = new HashSet<>(save.nations());
  }

  @Nonnull
  @Override
  public GameID getId() {
    return id;
  }

  @Nonnull
  @Override
  public Instant creationTime() {
    return creationTime;
  }

  @Nonnull
  @Override
  public Instant lastUpdated() {
    return lastUpdated;
  }

  @Nonnull
  @Override
  public GameState gameState() {
    return gameState;
  }

  @Override
  public void setGameState(@Nonnull GameState gameState) {
    this.gameState = gameState;
  }

  @Override
  public boolean isEnded() {
    return gameState.equals(GameState.ENDED);
  }

  @Nonnull
  @Override
  public Collection<Player> players() {
    return Collections.unmodifiableCollection(players);
  }

  @Nonnull
  @Override
  public Collection<Nation> nations() {
    return Collections.unmodifiableCollection(nations);
  }

  @Nonnull
  @Override
  public GameMap map() {
    return gameMap;
  } // TODO: Return unmodifiable version of GameMap

  /* Setup */

  @Nonnull
  @Override
  public Action<Player> join(@Nonnull Identity id, @Nonnull String name, @Nonnull Color color) {
    setLastUpdated();
    return new JoinAction(id, name, color, gameState, players);
  }

  @Nonnull
  @Override
  public Action<Player> leave(@Nonnull Player player) {
    setLastUpdated();
    return new LeaveAction(player, players, nations);
  }

  @Nonnull
  @Override
  public Action<GameMap> selectMap(@Nonnull RkmMap rkmMap, @Nonnull MapOptions options) {
    setLastUpdated();
    return new SelectMapAction(rkmMap, options, gameState, gameMap, nations);
  }

  @Nonnull
  @Override
  public Action<Nation> formNation(@Nonnull Identity identity, @Nonnull TerritoryId id) {
    setLastUpdated();
    return new FormNationAction(identity, id, gameState, gameMap, players, nations);
  }

  @Nonnull
  @Override
  public Action<GameState> start(@Nonnull TurnOrder order) {
    players = order.sort(players);
    return new StartAction(this, gameMap, players, nations);
  }

  /* Running */

  @Nonnull
  @CheckReturnValue
  public ClaimAction claim(Identity identity, TerritoryId... ids) {
    setLastUpdated();
    return new ClaimAction(identity, Set.of(ids), players.getFirst().identity(), gameState, gameMap, nations);
  }

  @Nonnull
  @CheckReturnValue
  public ClaimAction claim(Player player, TerritoryId... ids) {
    return this.claim(player.identity(), ids);
  }

  /* Private Methods */

  private void setLastUpdated() {
    this.lastUpdated = Instant.now();
  }

}