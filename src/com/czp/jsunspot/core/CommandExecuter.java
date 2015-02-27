package com.czp.jsunspot.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.util.Helper;

/**
 *
 *
 * @author dylan.chen 2010-5-30
 * 
 */
public class CommandExecuter {
	
	private final static Log logger=LogFactory.getLog(CommandExecuter.class);
	
	private final static CommandExecuter INSTANCE=new CommandExecuter();
	
	public interface AfterCallBack{
		public void executeAfter();
	}
	
	private CommandExecuter(){
	}
	
	public static CommandExecuter getInstance(){
		return INSTANCE;
	}
	
	public void execute(String command){
		execute(command,false,null);
	}
	
	public void execute(String command,boolean isWait,AfterCallBack afterCallBack){
		try {
			logger.info("Executing "+command);
            Process process = Runtime.getRuntime().exec(command);
            doOutputOfProcess(process);
			if (isWait) {
				process.waitFor();
			}
		}catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}finally{
			if(afterCallBack!=null){
			    afterCallBack.executeAfter();
			}
		}
	}
	
	private void doOutputOfProcess(Process process){
		BufferedReader stdInput=null;
		BufferedReader errorInput=null;
		try {
			String output="";
	        stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        while ((output=stdInput.readLine()) != null){
	        	OutputManager.out(output);
	        }
	        stdInput.close();
	        errorInput = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	        while ((output=errorInput.readLine()) != null){
	        	OutputManager.out(output);
	        }
	        errorInput.close();
		} catch (Exception e) {
			logger.error(e);
		}finally{
			Helper.closeStream(stdInput);
			Helper.closeStream(errorInput);
		}
	}

}
