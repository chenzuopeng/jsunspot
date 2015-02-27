package com.czp.jsunspot;

/**
 *
 * 
 * @author dylan.chen 2010-5-29
 * 
 */

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.core.ClassDecompiler;
import com.czp.jsunspot.core.CommandExecuter;
import com.czp.jsunspot.core.Config;
import com.czp.jsunspot.core.Constants;
import com.czp.jsunspot.core.JavaViewer;
import com.czp.jsunspot.core.NotFundClassFileException;
import com.czp.jsunspot.util.DialogUtils;
import com.czp.jsunspot.util.Helper;

public class JSunspot {

	private static final Log logger = LogFactory.getLog(JSunspot.class);
	
	public JSunspot(){
		Config.load();
	}
	
	public void doFile(File classFile){
		File javaFile = ClassDecompiler.decompile(classFile,new File(FilenameUtils.getFullPath(classFile.getPath())));
		JavaViewer.getInstance().show(javaFile);
	}
	
	public void doDirectory(File dir) {
		try {
			ClassDecompiler.batchDecompile(dir);
			CommandExecuter.getInstance().execute(String.format(Constants.OPEN_DIR_COMMAND_TEMPLATE, dir.getPath()));
		} catch (NotFundClassFileException e) {
			DialogUtils.showWarningMessageOnDialog("在目录[" + dir.getPath()+ "]下未找到Class文件");
		}
	}
	

	public static void main(String args[]){
		if (args == null || args.length < 1) {
			DialogUtils.showWarningMessageOnDialog("双击打开Class文件或者在一个包含Class文件的目录右键点击菜单[JSunspot]");	
			return;
		}
		String inputFilePath = Helper.getClassFilePathFromArgs(args);
		File inputFile=new File(inputFilePath);
		if(!inputFile.exists()){
			throw new RuntimeException("File["+inputFilePath+"] is't valid file or directory");
		}
		try {
			JSunspot sunspot=new JSunspot();
			if(inputFile.isDirectory()){
				sunspot.doDirectory(inputFile);
			}else{
				sunspot.doFile(inputFile);
			}
		} catch (Throwable e) {
			logger.error(e);
			DialogUtils.showErrorOnDialog(e);
		}
	}
}
