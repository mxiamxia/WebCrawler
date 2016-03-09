package com.min.pool;

import org.apache.commons.pool.impl.GenericObjectPool;

public class ExecutorPool extends GenericObjectPool{
	private ExecutorPoolFactory factory;

	public ExecutorPool()
	{
		this.factory = new ExecutorPoolFactory();
		this.setFactory(this.factory);
		this.setMaxWait(1000);//unit ms.
		this.setMaxActive(50);
		this.setMaxIdle(10);
	}

}
