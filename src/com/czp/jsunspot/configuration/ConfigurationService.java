package com.czp.jsunspot.configuration;


import com.czp.jsunspot.core.CommandExecuter;
import com.czp.jsunspot.core.Config;
import com.czp.jsunspot.core.Constants;
import com.czp.jsunspot.util.Helper;

/**
 *
 *
 * @author dylan.chen 2010-6-1
 * 
 */
public class ConfigurationService {
	
	private final static String ABOUT_FILE_PATH="com/czp/jsunspot/configuration/about.txt";
	
	public ConfigurationService(){
		Config.load();
	}
	
	public String getCurrentJavaFileEditor() throws Exception{
		return Config.getConfig().getJavaFileEditor();
	}
	
	public boolean isRecursiveSubdirectories()throws Exception{
		return Config.getConfig().isRecursiveSubdirectories();
	}
	
	public void modifyConfig(String javaFileEditor,boolean recursiveSubdirectories) throws Exception{
		 Config.getConfig().setJavaFileEditor(javaFileEditor);
		 Config.getConfig().setRecursiveSubdirectories(recursiveSubdirectories);
		 Config.store();
	}
	
	public void showHelp() throws Exception{
		CommandExecuter.getInstance().execute(Constants.SHOW_HELP_TEMPLATE);
	}
	
	public String getAboutInfo() throws Exception{
		return Helper.readFileToString(ABOUT_FILE_PATH);
	}
}
