package com.min.pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.springframework.beans.factory.annotation.Autowired;

public class ThreadPool {
	@Autowired
	private ThreadPoolTaskExecutor executor;
	public void setExecutor(ThreadPoolTaskExecutor executor) {
		this.executor = executor;
	}

	public ThreadPoolTaskExecutor getExecutor() {
		return executor;
	}
}
