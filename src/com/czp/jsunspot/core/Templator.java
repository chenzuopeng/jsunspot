package com.czp.jsunspot.core;

/**
 *
 * 
 * @author dylan.chen 2010-5-29
 * 
 */
public class Templator {
	
	private final static Templator INSTANCE=new Templator();

	private Templator(){
	}
	
	public static Templator getInstance(){
		return INSTANCE;
	}
	
	public String parse(String template,Object... args){
		return String.format(template, args);
	}
}
