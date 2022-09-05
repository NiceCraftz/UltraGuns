package tech.calista.ultraguns.repository.impl;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;
import de.tr7zw.nbtapi.NBTItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tech.calista.ultraguns.UltraGuns;
import tech.calista.ultraguns.repository.Repository;
import tech.calista.ultraguns.utils.chat.ChatUtils;
import tech.calista.ultraguns.weapons.Gun;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class GunRepository implements Repository<String, Gun> {
    private final UltraGuns ultraGuns;
    private final Map<String, Gun> gunCache = Maps.newHashMap();

    @Override
    public void put(String key, Gun value) {
        gunCache.put(key.toLowerCase(), value);
    }

    @Override
    public Optional<Gun> get(String key) {
        return Optional.ofNullable(gunCache.get(key.toLowerCase()));
    }

    @Override
    public void remove(String key) {
        gunCache.remove(key.toLowerCase());
    }

    @Override
    public boolean contains(String key) {
        return gunCache.containsKey(key.toLowerCase());
    }

    @Override
    public void clear() {
        gunCache.clear();
    }

    @Override
    public Collection<Gun> getAll() {
        return gunCache.values();
    }

    @Override
    public void load() {
        FileConfiguration config = ultraGuns.getConfig();
        ConfigurationSection gunSection = config.getConfigurationSection("guns");

        if (gunSection == null || !config.isSet("guns")) {
            return;
        }

        for (String gunKey : gunSection.getKeys(false)) {
            ConfigurationSection gunConfig = gunSection.getConfigurationSection(gunKey);

            String permission = gunConfig.getString("permission");

            double damage = gunConfig.getDouble("damage");
            int maxAmmo = gunConfig.getInt("max-ammo");

            if (maxAmmo < 1) {
                maxAmmo = 1;
                ultraGuns.getLogger().warning("Max ammo for " + gunKey + " is less than 1, defaulting to 1");
            }

            EntityType entityType = Enums.getIfPresent(EntityType.class, gunConfig.getString("entity-type")).or(EntityType.ARROW);
            Class<? extends Entity> entityClass = entityType.getEntityClass();

            if (!Projectile.class.isAssignableFrom(entityClass)) {
                ultraGuns.getLogger().warning("Entity type " + entityType.name() + " is not a projectile, skipping...");
                continue;
            }

            Class<Projectile> projectileClass = (Class<Projectile>) entityClass;

            double reloadTime = gunConfig.getDouble("reload-time");
            double shootDelay = gunConfig.getDouble("shoot-delay");

            if (reloadTime < 0 || shootDelay < 0) {
                ultraGuns.getLogger().warning("Reload time or shoot delay cannot be negative, skipping...");
                continue;
            }

            ConfigurationSection gunItemConfig = gunConfig.getConfigurationSection("item");
            ItemStack itemStack = getItemFromConfig(gunItemConfig);

            NBTItem nbtItem = new NBTItem(itemStack, true);
            nbtItem.setString("gun", gunKey);

            Gun gun = new Gun(gunKey, permission, itemStack, damage, maxAmmo, projectileClass, reloadTime, shootDelay);

            ConfigurationSection gunEffectsConfig = gunConfig.getConfigurationSection("effects");
            applyEffectsToGun(gunEffectsConfig, gun);

            gunCache.put(gunKey.toLowerCase(), gun);
        }

    }

    private ItemStack getItemFromConfig(ConfigurationSection configurationSection) {
        Material material = Enums.getIfPresent(Material.class, configurationSection.getString("material")).or(Material.STONE);
        String name = configurationSection.getString("name");
        List<String> lore = configurationSection.getStringList("lore");

        ItemStack itemStack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);

        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(ChatUtils.color(lore));
        }

        if (name != null && !name.isEmpty()) {
            itemMeta.setDisplayName(ChatUtils.color(name));
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private void applyEffectsToGun(ConfigurationSection configurationSection, Gun gun) {
        Particle hitParticle = Enums.getIfPresent(Particle.class, configurationSection.getString("hit-particle")).orNull();
        Particle shootParticle = Enums.getIfPresent(Particle.class, configurationSection.getString("shoot-particle")).orNull();
        Sound shootSound = Enums.getIfPresent(Sound.class, configurationSection.getString("shoot-sound")).orNull();
        Sound hitSound = Enums.getIfPresent(Sound.class, configurationSection.getString("hit-sound")).orNull();

        gun.applyEffects(shootSound, hitSound, shootParticle, hitParticle);
    }
}
