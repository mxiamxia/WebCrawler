////This sample is how to use websocket of Tomcat.
//package com.min.servlet;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.catalina.websocket.MessageInbound;
//import org.apache.catalina.websocket.StreamInbound;
//import org.apache.catalina.websocket.WebSocketServlet;
//import org.apache.catalina.websocket.WsOutbound;
//
//public class WsDictationServlet extends WebSocketServlet{
//	private static final long serialVersionUID = 1L;
//
//	public StreamInbound createWebSocketInbound(String protocol){
//		return new MyMessageInbound();
//	}
//
//	private class MyMessageInbound extends MessageInbound{
//		WsOutbound myoutbound;
//
//		@Override
//		public void onOpen(WsOutbound outbound){
//			try {
//				System.out.println("Open Client.");
//				this.myoutbound = outbound;
//				this.myoutbound.writeTextMessage(CharBuffer.wrap("Hello!"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Override
//		public void onClose(int status){
//			System.out.println("Close Client.");
//		}
//
//		@Override
//		public void onTextMessage(CharBuffer cb) throws IOException{
//			System.out.println("Accept Message : "+ cb);
//			CharBuffer buffer = CharBuffer.wrap(cb);
//			this.myoutbound.writeTextMessage(buffer);
//			//                this.myoutbound.flush();
//		}
//
//		@Override
//		public void onBinaryMessage(ByteBuffer buffer) throws IOException
//		{
//			ByteArrayInputStream  byteInputStream;
//			InputStream inputStream;
//			String result = "";
//			System.out.println("Accepet audio message");
//			byteInputStream = new ByteArrayInputStream(buffer.array());
////			        	inputStream = (InputStream)byteInputStream;
//
//			//test
//			inputStream = WsDictationServlet.class.getResourceAsStream("/co/voice/wav/job.wav");
//			inputStream.skip(44);
//
//			System.out.println(inputStream.available());
//			StreamRecognizer recognizer = (StreamRecognizer) AppContext.getBean("streamRecognizer");
//			result = recognizer.syncRecognize(inputStream, "grammar");
//			System.out.println("send back result " + result);
//			CharBuffer cb = CharBuffer.wrap(result);
//			this.myoutbound.writeTextMessage(cb);
//			//        		this.myoutbound.flush();
//		}
//	}
//	@Override
//	protected StreamInbound createWebSocketInbound(String arg0,
//			HttpServletRequest arg1) {
//		// TODO Auto-generated method stub
//		System.out.println("#########################################");  
//		return new MyMessageInbound(); 
//	}
//}
