//package com.min.email;
//
//import java.text.NumberFormat;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;
//
//import com.min.log.Log;
//import com.min.model.Result;
//
//public class SendEmail {
//
//	public static void sendAttachmentMail(Result rst, Properties p) {
//		
//		String hostName;
//		String user;
//		String password;
//		String from;
//		String subject;
//		String to;
//
//		Log.info("Begin Send attacmment email");
//		try
//		{
//			Email email = new SimpleEmail();
//			email.setHostName(p.getProperty("hostName"));
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator(p.getProperty("user"), p.getProperty("password")));
//			email.setSSLOnConnect(true);
//			email.setFrom(p.getProperty("from"));
//			email.setSubject(p.getProperty("subject"));
//			String content = formatContent(rst);
//			email.setMsg(content);
//			email.addTo(p.getProperty("to"));
//			email.send();
//		}
//		catch(EmailException e)
//		{
//			Log.info(e.getMessage());
//		}
//	}
//	
//	private static String formatContent(Result rst)
//	{
//		int i = 0;
//		StringBuffer sb = new StringBuffer();
//		sb.append("\n");
//		sb.append("-------------Inventory Price List-------------\n");
//		String item = rst.getPage().getItem();
//		sb.append("     Item# " + item);
//		sb.append("\n");
//		List origLst = rst.getOrig_price();
//		List saleLst = rst.getSale_price();
//		Iterator sit = saleLst.iterator();
//		try
//		{
//			while(sit.hasNext())
//			{
//				if(!origLst.isEmpty())
//				{
//					sb.append("       Original Price: " + origLst.get(i) + "--------" + "Sale Price: " + saleLst.get(i));
//				}
//				else
//				{
//					sb.append("       Sale Price: " + saleLst.get(i));
//				}
//				sb.append("\n");
//				i++;
//			}
//		}
//		catch(Exception e)
//		{
//		}
//		sb.append("\n");
//		
////		while(it.hasNext())
////		{
////			Result rst = (Result)it.next();
////			String item = rst.getPage().getItem();
////			sb.append("     Item# " + item);
////			sb.append("\n");
////			List origLst = rst.getOrig_price();
////			List saleLst = rst.getSale_price();
////			Iterator sit = saleLst.iterator();
////			while(sit.hasNext())
////			{
////				sb.append("       Original Price: " + origLst.get(0) + "--------" + "Sale Price: " + sit.next().toString());
////				sb.append("\n");
////			}
////			sb.append("\n");
////		}
//		
//		
//		return sb.toString();
//	}
//	
//	public static void main(String[] args)
//	{
//		SendEmail.sendAttachmentMail(null, null);
//	}
//}
