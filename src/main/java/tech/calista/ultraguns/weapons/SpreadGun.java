package tech.calista.ultraguns.weapons;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SpreadGun extends Gun {
    private final double spread;
    private final int quantity;

    @Override
    public void shoot() {
        // Re-Implementation of shoot following spread and quantity of bullets.
    }
}
