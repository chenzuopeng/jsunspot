package com.czp.jsunspot.setup;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.core.CommandExecuter;
import com.czp.jsunspot.core.Config;
import com.czp.jsunspot.core.Constants;
import com.czp.jsunspot.core.Templator;
import com.czp.jsunspot.util.Helper;

/**
 *
 *
 * @author dylan.chen 2010-6-1
 * 
 */
public class SetupService {
	
	private final static Log logger=LogFactory.getLog(SetupService.class);
	
	private static Templator templator=Templator.getInstance();
	
	private static Config config=Config.getConfig();
	
	private static CommandExecuter commandExecuter=CommandExecuter.getInstance();
	
	private final static String INSTALL_TEMPLATE_FILE_PATH="com/czp/jsunspot/setup/template/install.reg.template";
	
	private final static String INSTALL_TEMP_FILE_OF_NAME="install.reg";
	
	private final static String UNINSTALL_TEMPLATE_FILE_PATH="com/czp/jsunspot/setup/template/uninstall.reg.template";
	
	private final static String UNINSTALL_TEMP_FILE_OF_NAME="uninstall.reg";

	public void install() throws Exception{
			String template=Helper.readFileToString(INSTALL_TEMPLATE_FILE_PATH);
			String data=templator.parse(template,Helper.formatFilePath(config.getHome()));
			File tempFile=new File(FilenameUtils.concat(config.getTmpDir(),INSTALL_TEMP_FILE_OF_NAME));
			Helper.createFile(tempFile);
			FileUtils.writeStringToFile(tempFile, data);
			executeCommand(tempFile);
	}
	
	public void uninstall() throws Exception{
		    String template=Helper.readFileToString(UNINSTALL_TEMPLATE_FILE_PATH);
			File tempFile=new File(FilenameUtils.concat(config.getTmpDir(),UNINSTALL_TEMP_FILE_OF_NAME));
			Helper.createFile(tempFile);
			FileUtils.writeStringToFile(tempFile, template);
			executeCommand(tempFile);
	}
	
	private void executeCommand(final File file){
		String command=templator.parse(Constants.REG_COMMAND_TEMPLATE, file.getPath());
		commandExecuter.execute(command,true,new CommandExecuter.AfterCallBack() {
			public void executeAfter() {
				file.delete();
				logger.info("Delete temp file:"+file.getPath());
			}
		});
	}
	
}
