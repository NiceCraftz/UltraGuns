package tech.calista.ultraguns.weapons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
@Getter
public class Gun {
    private final String name;
    private final String permission;

    private final ItemStack gunItem;

    private final double damage;
    private final int maxAmmo;
    private final Class<Projectile> projectileClass;

    private final double reloadTime;
    private final double shootDelay;

    private Sound shootSound = null;
    private Sound hitSound = null;
    private Particle shootParticle = null;
    private Particle hitParticle = null;

    public void applyEffects(Sound shootSound, Sound hitSound, Particle shootParticle, Particle hitParticle) {
        this.shootSound = shootSound;
        this.hitSound = hitSound;
        this.shootParticle = shootParticle;
        this.hitParticle = hitParticle;
    }
}
