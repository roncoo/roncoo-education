package com.roncoo.education.util.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存基本支撑类
 * 
 * @author wujing
 * @param <K>
 * @param <V>
 */

public abstract class BaseCached<K, V> {
	private ConcurrentMap<K, V> cached = new ConcurrentHashMap<K, V>();

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(10);

	/** 读写锁， 读的时候不互斥，写的时候互斥，保证在更新数据的时候，都不能读 */
	protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 
	 * @param initialDelay
	 *            开始更新的时间，单位秒
	 * @param period
	 *            更新一次的时间段，单位秒
	 */
	public void init(Long initialDelay, Long period) {
		initialDelay = initialDelay + new Random().nextInt(60); // 防止缓存同一时间更新。
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				reload();
			}
		}, initialDelay, period, TimeUnit.SECONDS);
		reload();
	}

	public void reload() {
		Long timeStart = System.currentTimeMillis();
		ConcurrentMap<K, V> newCached = createNewCahcedObject();
		ConcurrentMap<K, V> tempCached = cached;

		// reload from db
		reloadFromDb(newCached);

		if (newCached.isEmpty()) {
			logger.error(this.getClass().getName() + " reload  elements is empty");
			return;
		}
		try {
			lock.writeLock().lock();
			resetCachedObject(newCached);
			tempCached.clear();
			tempCached = null;
		} finally {
			lock.writeLock().unlock();
		}
		logger.warn("缓存更新所使用时间：" + (System.currentTimeMillis() - timeStart) + "ms");
	}

	public abstract void reloadFromDb(ConcurrentMap<K, V> cached);

	public V get(K k) {
		V result;
		try {
			lock.readLock().lock();
			result = cached.get(k);
		} finally {
			lock.readLock().unlock();
		}
		return result;
	}

	public Map<K, V> getMap() {
		Map<K, V> map = new HashMap<K, V>();
		try {
			lock.readLock().lock();
			for (Map.Entry<K, V> entry : cached.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
			return map;
		} finally {
			lock.readLock().unlock();
		}
	}

	public List<V> getList() {
		List<V> list = new ArrayList<V>();
		try {
			lock.readLock().lock();
			for (Map.Entry<K, V> entry : cached.entrySet()) {
				list.add(entry.getValue());
			}
			return list;
		} finally {
			lock.readLock().unlock();
		}
	}

	private ConcurrentMap<K, V> createNewCahcedObject() {
		return new ConcurrentHashMap<K, V>();
	}

	private void resetCachedObject(ConcurrentMap<K, V> newCached) {
		cached = newCached;
	}
}
