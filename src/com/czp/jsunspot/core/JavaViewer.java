package com.czp.jsunspot.core;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 *
 * @author dylan.chen 2010-5-28
 * 
 */
public class JavaViewer {
	
	private final static Log logger=LogFactory.getLog(JavaViewer.class);
	
	private final static JavaViewer INSTANCE=new JavaViewer();
	
	private Config config=Config.getConfig();

	private Templator templator=Templator.getInstance();
	
	private CommandExecuter commandExecuter=CommandExecuter.getInstance();
	
	private JavaViewer(){
	}
	
	public static JavaViewer getInstance(){
		return INSTANCE;
	}
	
    public void show(File javaFile){
    	logger.info("Showing java file["+javaFile.getPath()+"]");
    	String command=templator.parse(Constants.SHOW_JAVA_FILE_COMMAND_TEMPLATE, config.getJavaFileEditor(),javaFile.getPath());
    	commandExecuter.execute(command);
	}

}
