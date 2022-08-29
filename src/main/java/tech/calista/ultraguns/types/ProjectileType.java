package tech.calista.ultraguns.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;

@RequiredArgsConstructor
@Getter
public enum ProjectileType {
    ARROW(Arrow.class),
    SNOWBALL(Snowball.class);

    private final Class<? extends Entity> entity;
}
