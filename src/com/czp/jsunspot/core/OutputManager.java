package com.czp.jsunspot.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 *
 * @author dylan.chen 2010-5-31
 * 
 */
public class OutputManager {

	public interface Outputor{
		public void out(String output);
	}
	
	private static class DefaultOutputor implements Outputor{
		
        private final static Log logger=LogFactory.getLog(DefaultOutputor.class);
        
		public void out(String output) {
			logger.info(output);
		}
		
	}
	
	private OutputManager(){
		
	}
	
	private static List<Outputor> outputors=new ArrayList<Outputor>();
	
	static{
		registerOuput(new DefaultOutputor());
	}
	
	public static void out(String output){
		for (Outputor outputor : outputors) {
			outputor.out(output);
		}
	}
	
	public static void registerOuput(Outputor output){
		outputors.add(output);
	}
}
