package tech.calista.ultraguns;

import lombok.experimental.SuperBuilder;
import tech.calista.ultraguns.weapons.Gun;

@SuperBuilder
public class SpreadGun extends Gun {
    private final double spread;
    private final int quantity;
}
