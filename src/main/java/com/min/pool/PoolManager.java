package com.min.pool;


import java.util.NoSuchElementException;

import org.apache.commons.pool.ObjectPool;

public class PoolManager {
	
	private  ObjectPool objectPool;

	public ObjectPool getObjectPool() {
		return objectPool;
	}

	public void setObjectPool(ObjectPool objectPool) {
		this.objectPool = objectPool;
	}
	
	public Object borrowObject() throws NoSuchElementException, IllegalStateException, Exception
	{
		return this.objectPool.borrowObject();
	}
	
	public void returnObject(Object obj) throws Exception
	{
		objectPool.returnObject(obj);
	}
	

}
