package tech.calista.ultraguns.repository.impl;

import com.google.common.collect.Maps;
import tech.calista.ultraguns.repository.Repository;
import tech.calista.ultraguns.weapons.Gun;

import java.util.Map;
import java.util.Optional;

public class GunRepository implements Repository<String, Gun> {
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
}
