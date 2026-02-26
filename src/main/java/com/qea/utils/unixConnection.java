package com.qea.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.google.common.io.Files;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class unixConnection {
	
	
	public static void connectUnix(String host, String user, String pemPath , String commands) {
	  // host="172.18.203.78";
	  // user="rsingh";
		//pemPath = "C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Ab_initio_key\\rsingh_rsa_keypair.pem";
	    //String command1="ls -ltr";
	    try{
	    	JSch jsch = new JSch();
	    	java.util.Properties config = new java.util.Properties(); 
	    	//Properties config = new Properties();
	    	config.put("StrictHostKeyChecking", "no");
	    	config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
	    	jsch.addIdentity(pemPath);
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setConfig(config);
	    	session.setPassword("password");
	    	System.out.println("Establishing connection");
	    	session.setTimeout(15000);
	    	session.connect(); // here I got Exception....
	    	System.out.println("Connected  ----");
	    	
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(commands);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            System.out.print(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
	        session.disconnect();
	        
	        System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	}
	
	
	public static void main(String[] args) {
		
		 // host="172.18.203.78";
		  // user="rsingh";
			//pemPath = "C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Ab_initio_key\\rsingh_rsa_keypair.pem";
		    //String command1="ls -ltr";
		
		connectUnix("172.18.203.78","rsingh","C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Ab_initio_key\\rsingh_rsa_keypair.pem","ls -ltr");
		
		
		
	}
	
	

}
