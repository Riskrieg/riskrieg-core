package com.riskrieg.api.gamemode;

import com.riskrieg.api.gamemap.GameMap;
import com.riskrieg.api.gamemap.territory.Territory;
import com.riskrieg.api.gamemode.order.TurnOrder;
import com.riskrieg.api.player.Player;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Conquest implements Gamemode {

  private final UUID id;
  private final Instant creationTime;
  private Instant lastUpdated;
  private GameMap map;

  public Conquest() {
    this.id = UUID.randomUUID();
    this.creationTime = Instant.now();
    this.lastUpdated = Instant.now();
  }

  @Override
  public UUID id() {
    return id;
  }

  @Override
  public Instant creationTime() {
    return creationTime;
  }

  @Override
  public Instant lastUpdated() {
    return lastUpdated;
  }

  @Override
  public void join(Player player) {
    Objects.requireNonNull(player);
    // TODO: Implement this method
  }

  @Override
  public void leave(Player player) {
    Objects.requireNonNull(player);
    // TODO: Implement this method
  }

  @Override
  public void selectMap(GameMap map) {
    this.map = Objects.requireNonNull(map);
    // TODO: Implement this method
  }

  @Override
  public void grant(Player player, Territory territory) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(territory);
    // TODO: Implement this method
  }

  @Override
  public void revoke(Player player, Territory territory) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(territory);
    // TODO: Implement this method
  }

  @Override
  public void start(TurnOrder order) {

  }

}