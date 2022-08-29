package tech.calista.ultraguns.weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.Sound;

@AllArgsConstructor
@Getter
public class GunEffects {
    private Sound reloadSound;
    private Sound shootSound;
    private Sound hitSound;
    private Particle shootParticle;
    private Particle hitParticle;
}
