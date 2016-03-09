package com.min.schedule;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.executor.HttpExecutor;
import com.min.log.Log;
import com.min.model.Item;
import com.min.pool.PoolManager;
import com.min.pool.ThreadPool;
import com.min.queue.QueueUtils;

public class CrawlerTask {

	/** 
	 * @author Min
	 * @date Aug 14, 2015
	 * @return void 
	 * @throws 
	 */
	@Autowired
	private	QueueUtils queue;
	public void setQueue(QueueUtils queue) {
		this.queue = queue;
	}

	@Autowired
	private PoolManager poolManager;
	public void setPoolManager(PoolManager poolManager) {
		this.poolManager = poolManager;
	}
	
	@Autowired
	private ThreadPool reqPool;
	public void setReqPool(ThreadPool reqPool) {
		this.reqPool = reqPool;
	}
	
	public void execute() throws JobExecutionException {
		// TODO Auto-generated method stub
//		@SuppressWarnings("unchecked")
//		Iterator<Item> it = queue.getRequestQueue()..iterator();
		System.out.println(queue + " reqThreadPool");
		System.out.println(queue.getRequestQueue() + " reqThreadPool");
		System.out.println(queue.getRequestQueue().size() + " reqThreadPool");
		Log.info("start Job at " + new Date());
		ArrayBlockingQueue<Item> tmpQ = new ArrayBlockingQueue<Item>(1024);
		System.out.println(queue.getRequestQueue().size() + " reqThreadPool before take");
		tmpQ.addAll(queue.getRequestQueue());
		System.out.println(queue.getRequestQueue().size() + " reqThreadPool before take11");
		while(tmpQ.size() > 0)
		{
			try {
				Item it = (Item)tmpQ.take();
				if(it.isStatus()) {
					this.execute(it);
				}
				System.out.println(queue.getRequestQueue().size() + " reqThreadPool after take");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//send email
	}
	
	public void execute(final Item item)
	{
		reqPool.getExecutor().execute(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				//enhancement -- can setup a executor pool to reuse executor
				HttpExecutor exe = null;
				try
				{
					exe = (HttpExecutor)poolManager.borrowObject();
					Log.info("borrow  HttpExecutor to pool with Number==" + poolManager.getObjectPool().getNumActive() + "--" + poolManager.getObjectPool().getNumIdle());
					exe.run(item, queue.getResponseQueue());
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				finally
				{
					try {
						if(null!=exe)
						{
							poolManager.returnObject(exe);
							Log.info("Return HttpExecutor to pool with Number==" + poolManager.getObjectPool().getNumActive() + "--" + poolManager.getObjectPool().getNumIdle());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				new HttpExecutor().run(page);
			}
			
		});
	}

}
