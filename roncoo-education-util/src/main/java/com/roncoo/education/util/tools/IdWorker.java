/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 高效GUID产生算法(sequence),基于Snowflake实现64位自增ID算法。 <br>
 * 优化开源项目 http://git.oschina.net/yu120/sequence
 * </p>
 *
 * @author hubin
 * @Date 2016-08-01
 */
public class IdWorker {

	/**
	 * 主机和进程的机器码
	 */
	private static final Sequence worker = new Sequence();

	public static long getId() {
		return worker.nextId();
	}

	/**
	 * <p>
	 * 获取去掉"-" UUID
	 * </p>
	 */
	public static synchronized String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}

class Sequence {
	private static final Logger logger = LoggerFactory.getLogger(Sequence.class);

	/* 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动） */
	private final long twepoch = 1288834974657L;
	private final long workerIdBits = 5L;/* 机器标识位数 */
	private final long datacenterIdBits = 5L;
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final long sequenceBits = 12L;/* 毫秒内自增位 */
	private final long workerIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	/* 时间戳左移动位 */
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;

	/* 数据标识id部分 */
	private long datacenterId;
	private long sequence = 0L;/* 0，并发控制 */
	private long lastTimestamp = -1L;/* 上次生产id时间戳 */

	public Sequence() {
		this.datacenterId = getDatacenterId(maxDatacenterId);
		this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
	}

	/**
	 * @param workerId
	 *            工作机器ID
	 * @param datacenterId
	 *            序列号
	 */
	public Sequence(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new RuntimeException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new RuntimeException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	/**
	 * <p>
	 * 获取 maxWorkerId
	 * </p>
	 */
	protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
		StringBuilder mpid = new StringBuilder();
		mpid.append(datacenterId);
		String name = ManagementFactory.getRuntimeMXBean().getName();
		if (StringUtils.hasText(name)) {
			/*
			 * GET jvmPid
			 */
			mpid.append(name.split("@")[0]);
		}
		/*
		 * MAC + PID 的 hashcode 获取16个低位
		 */
		return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
	}

	/**
	 * <p>
	 * 数据标识id部分
	 * </p>
	 */
	protected static long getDatacenterId(long maxDatacenterId) {
		long id = 0L;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			if (network == null) {
				id = 1L;
			} else {
				byte[] mac = network.getHardwareAddress();
				if (null != mac) {
					id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
					id = id % (maxDatacenterId + 1);
				}
			}
		} catch (Exception e) {
			logger.warn(" getDatacenterId: " + e.getMessage());
		}
		return id;
	}

	/**
	 * 获取下一个ID
	 *
	 * @return
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {// 闰秒
			long offset = lastTimestamp - timestamp;
			if (offset <= 5) {
				try {
					wait(offset << 1);
					timestamp = timeGen();
					if (timestamp < lastTimestamp) {
						throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else {
				throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
			}
		}

		if (lastTimestamp == timestamp) {
			// 相同毫秒内，序列号自增
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				// 同一毫秒的序列数已经达到最大
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			// 不同毫秒内，序列号置为 1 - 3 随机数
			sequence = ThreadLocalRandom.current().nextLong(1, 3);
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) // 时间戳部分
				| (datacenterId << datacenterIdShift) // 数据中心部分
				| (workerId << workerIdShift) // 机器标识部分
				| sequence; // 序列号部分
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return SystemClock.now();
	}

}

class SystemClock {

	private final long period;
	private final AtomicLong now;

	private SystemClock(long period) {
		this.period = period;
		this.now = new AtomicLong(System.currentTimeMillis());
		scheduleClockUpdating();
	}

	private static SystemClock instance() {
		return InstanceHolder.INSTANCE;
	}

	public static long now() {
		return instance().currentTimeMillis();
	}

	public static String nowDate() {
		return new Timestamp(instance().currentTimeMillis()).toString();
	}

	private void scheduleClockUpdating() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable runnable) {
				Thread thread = new Thread(runnable, "System Clock");
				thread.setDaemon(true);
				return thread;
			}
		});
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				now.set(System.currentTimeMillis());
			}
		}, period, period, TimeUnit.MILLISECONDS);
	}

	private long currentTimeMillis() {
		return now.get();
	}

	private static class InstanceHolder {
		public static final SystemClock INSTANCE = new SystemClock(1);
	}

}
