package com.github.hazork.sinkspigot.data.storage;

import java.util.concurrent.ConcurrentMap;

import com.github.hazork.sinkspigot.data.Database;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.gson.JsonObject;

public abstract class CachedStorage<V> extends Storage<V> {

    private transient LoadingCache<String, V> cache;

    public CachedStorage(Database<JsonObject> database, LoadingCache<String, V> cache) {
	super(database);
	this.cache = cache;
    }

    public CachedStorage(Database<JsonObject> database) {
	super(database);
	cache = CacheBuilder.newBuilder().removalListener(new DataListener()).build(new DataLoader());
    }

    @Override
    public V get(String key) {
	return cache.getUnchecked(key);
    }

    public void refresh(String key) {
	cache.refresh(key);
    }

    public void cleanUp() {
	cache.cleanUp();
    }

    public ConcurrentMap<String, V> asMap() {
	return cache.asMap();
    }

    public CacheStats stats() {
	return cache.stats();
    }

    public long size() {
	return cache.size();
    }

    public final class DataListener implements RemovalListener<String, V> {

	@Override
	public void onRemoval(RemovalNotification<String, V> notification) {
	    save(notification.getKey(), notification.getValue());
	}
    }

    public class DataLoader extends CacheLoader<String, V> {

	@Override
	public V load(String key) {
	    return CachedStorage.super.get(key);
	}
    }
}
