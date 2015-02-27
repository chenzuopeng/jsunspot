package com.czp.jsunspot.core;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.util.Helper;


/**
 *
 *
 * @author dylan.chen 2010-5-28
 * 
 */
public class Config {
	
	private final static Log logger=LogFactory.getLog(Config.class);
		
	private final static File CONFIG_FILE=new File(Constants.CONFIG_FILE_NAME);
	
	private static Config config=new Config();
	
	private String home=Constants.DEFAULT_HOME;

	private String tmpDir=Constants.DEFAULT_TMP_DIR;
		
	private String javaFileEditor=Constants.DEFAULT_JAVA_FILE_EDITOR;
	
	private boolean recursiveSubdirectories=false;
		
	public static void load(){
		try {
			XMLDecoder xmlDecoder=new XMLDecoder(new FileInputStream(CONFIG_FILE));
	        config=(Config)xmlDecoder.readObject();
	        xmlDecoder.close();
	        logger.info("Loading "+config.toString()+" from external config file["+CONFIG_FILE.getPath()+"]");
		} catch (Exception e) {
			logger.warn("Unable to load external config file["+CONFIG_FILE.getPath()+"],so use the default "+config.toString());
		}
	}
	
	public static Config getConfig(){
		return config;
	}
		
	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		//由于JAD无法处理带空格的目录,所以缓存目录不能修改
//		if(Helper.isNotBlank(tmpDir)){
//		   this.tmpDir = tmpDir;
//		}
	}
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		//不应许修改程序安装路径
//		if (Helper.isNotBlank(home)) {
//			this.home = home;
//		}
	}

	public String getJavaFileEditor() {
		return javaFileEditor;
	}

	public void setJavaFileEditor(String javaFileEditor) {
		if (Helper.isNotBlank(javaFileEditor)) {
			this.javaFileEditor = javaFileEditor;
		}
	}
	
	public boolean isRecursiveSubdirectories() {
		return recursiveSubdirectories;
	}

	public void setRecursiveSubdirectories(boolean recursiveSubdirectories) {
		this.recursiveSubdirectories = recursiveSubdirectories;
	}

	public static void store(){
		try {
			Helper.createFile(CONFIG_FILE);
			XMLEncoder xmlEncoder=new XMLEncoder(new FileOutputStream(CONFIG_FILE));
			xmlEncoder.writeObject(config);
			xmlEncoder.close();
		} catch (Exception e) {
			logger.warn("Store configuration to config file failed");
		}
		logger.info("Store "+config.toString()+" to config file that the path is "+CONFIG_FILE.getPath());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [home=");
		builder.append(home);
		builder.append(", javaFileEditor=");
		builder.append(javaFileEditor);
		builder.append(", recursiveSubdirectories=");
		builder.append(recursiveSubdirectories);
		builder.append(", tmpDir=");
		builder.append(tmpDir);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Config.store();
	}
}
