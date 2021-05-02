package com.riskrieg.core.api.gamemode.classic;

import com.riskrieg.core.api.Save;
import com.riskrieg.core.api.gamemode.GameID;
import com.riskrieg.core.api.gamemode.GameMode;
import com.riskrieg.core.api.nation.Nation;
import com.riskrieg.core.api.order.TurnOrder;
import com.riskrieg.core.api.player.Identity;
import com.riskrieg.core.api.player.Player;
import com.riskrieg.core.internal.action.Action;
import com.riskrieg.core.unsorted.gamemode.GameState;
import com.riskrieg.core.unsorted.map.GameMap;
import com.riskrieg.core.unsorted.map.MapOptions;
import com.riskrieg.map.RkmMap;
import com.riskrieg.map.territory.TerritoryId;
import java.awt.Color;
import java.time.Instant;
import java.util.Collection;
import javax.annotation.Nonnull;

public final class ClassicMode implements GameMode { // TODO: No capitals, no alliances

  public ClassicMode() {

  }

  public ClassicMode(Save save) {

  }

  @Nonnull
  @Override
  public GameID getId() {
    return null;
  }

  @Nonnull
  @Override
  public Instant creationTime() {
    return null;
  }

  @Nonnull
  @Override
  public Instant lastUpdated() {
    return null;
  }

  @Nonnull
  @Override
  public GameState gameState() {
    return null;
  }

  @Override
  public void setGameState(@Nonnull GameState gameState) {

  }

  @Override
  public boolean isEnded() {
    return false;
  }

  @Nonnull
  @Override
  public Collection<Player> players() {
    return null;
  }

  @Nonnull
  @Override
  public Collection<Nation> nations() {
    return null;
  }

  @Nonnull
  @Override
  public GameMap map() {
    return null;
  }

  @Nonnull
  @Override
  public Action<Player> join(@Nonnull Identity id, @Nonnull String name, @Nonnull Color color) {
    return null;
  }

  @Nonnull
  @Override
  public Action<Player> leave(@Nonnull Identity id) {
    return null;
  }

  @Nonnull
  @Override
  public Action<GameMap> selectMap(@Nonnull RkmMap rkmMap, @Nonnull MapOptions options) {
    return null;
  }

  @Nonnull
  @Override
  public Action<Nation> formNation(@Nonnull Identity identity, @Nonnull TerritoryId id) {
    return null;
  }

  @Nonnull
  @Override
  public Action<GameState> start(@Nonnull TurnOrder order) {
    return null;
  }

}
