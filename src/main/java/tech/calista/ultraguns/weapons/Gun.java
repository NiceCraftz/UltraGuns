package tech.calista.ultraguns.weapons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import tech.calista.ultraguns.types.ProjectileType;

@RequiredArgsConstructor
@Getter
public class Gun {
    private final String name;
    private final String permission;

    private final ItemStack gunItem;

    private final double damage;
    private final double reloadTime;
    private final double shootDelay;

    private final int maxAmmo;

    private final GunEffects effects;
    private final ProjectileType projectileType;


    public void shoot() {

    }
}
