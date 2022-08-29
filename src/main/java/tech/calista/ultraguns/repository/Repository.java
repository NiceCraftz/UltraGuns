package tech.calista.ultraguns.repository;

import java.util.Optional;

public interface Repository<K, V> {
    void put(K key, V value);

    Optional<V> get(K key);

    void remove(K key);

    boolean contains(K key);

    void clear();
}
