//package com.min.schedule;
//
//import java.util.Collection;
//import java.util.Properties;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//
//import com.min.log.Log;
//
//public class ScanScheduler {
//	
//	private Properties p = null;
//	private SchedulerFactory sf = new StdSchedulerFactory();
//	
//	public ScanScheduler(Properties p)
//	{
//		this.p = p;
//	}
//	
//	public void run(){
//		
//		try
//		{
//			Scheduler sched = sf.getScheduler();
//			JobDetail job = JobBuilder.newJob(CrawlerJob.class).withIdentity("job1", "group1").build();
//			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
//					.withSchedule(CronScheduleBuilder.cronSchedule(getCronExpr()))
//					.build();
//			sched.scheduleJob(job, trigger);
//			System.out.println("start " + sched.getSchedulerName());
//			sched.start();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//	
//	public void stop() {
//		try {
//			Collection<Scheduler> list  = sf.getAllSchedulers();
//			for(Scheduler s : list) {
//				System.out.println("Stop " + s.getSchedulerName());
//				s.shutdown(true);
//			}
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public String getCronExpr()
//	{
//		String expr = String.format("0 */%s * * * ?", this.p.getProperty("checkRate","5"));
//		Log.info(expr);
//		return expr;
//	}
//
//}
