package com.min.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;


import com.min.executor.HttpExecutor;
import com.min.log.Log;


public class ExecutorPoolFactory extends BasePoolableObjectFactory{

	@Override
	public Object makeObject() throws Exception {
		// TODO Auto-generated method stub
		HttpExecutor executor = new HttpExecutor();
		Log.info("Make a new  HttpExecutor. ");
		return executor;
	}
	
	public void passivateObject(Object obj) throws java.lang.Exception {
	}

}
