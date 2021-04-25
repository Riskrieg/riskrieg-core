package com.riskrieg.core.map;

import com.aaronjyoder.util.json.gson.GsonUtil;
import com.riskrieg.core.map.options.Availability;
import com.riskrieg.core.map.options.InterfaceAlignment;
import com.riskrieg.core.map.options.alignment.HorizontalAlignment;
import com.riskrieg.core.map.options.alignment.VerticalAlignment;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class MapOptions {

  private Availability availability;
  private InterfaceAlignment alignment;

  @Nullable
  public static MapOptions load(@Nonnull Path optionsPath, boolean createIfUnavailable) throws IOException {
    MapOptions result = GsonUtil.read(optionsPath, MapOptions.class);
    if (result == null && createIfUnavailable) {
      result = new MapOptions();
      GsonUtil.write(optionsPath, MapOptions.class, result);
    }
    return result;
  }

  public MapOptions() {
    this.availability = Availability.UNAVAILABLE;
    this.alignment = new InterfaceAlignment(VerticalAlignment.BOTTOM, HorizontalAlignment.LEFT);
  }

  public MapOptions(Availability availability, InterfaceAlignment alignment) {
    Objects.requireNonNull(availability);
    Objects.requireNonNull(alignment);
    this.availability = availability;
    this.alignment = alignment;
  }

  public InterfaceAlignment alignment() {
    return alignment;
  }

  public Availability availability() {
    return availability;
  }

  public void setAlignment(InterfaceAlignment alignment) {
    this.alignment = alignment;
  }

  public void setAvailability(Availability availability) {
    this.availability = availability;
  }

}
