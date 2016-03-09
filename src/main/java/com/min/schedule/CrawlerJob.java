package com.min.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CrawlerJob extends QuartzJobBean {
	private CrawlerTask crawlerTask;

	public void setCrawlerTask(CrawlerTask crawlerTask) {
		this.crawlerTask = crawlerTask;
	}

	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
		crawlerTask.execute();
	}
}
