package com.czp.jsunspot.core;

/**
 *
 * 
 * @author dylan.chen 2010-5-29
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.util.Helper;

public class ClassDecompiler {
	
	private static final Log logger = LogFactory.getLog(ClassDecompiler.class);
	
	private final static ClassDecompiler INSTANCE=new ClassDecompiler();
	
	private Templator templator= Templator.getInstance();
	
	private CommandExecuter commandExecuter=CommandExecuter.getInstance();
	
	private Config config=Config.getConfig();
	
	private String jadFilePath=FilenameUtils.concat(config.getHome(), Constants.JAD_FILE_RELATIVE_PATH);
	
	private File outputDir=new File(System.getProperty("user.dir"));
	
	private File classFileOnTmp;
	
	private File javaFileOnTmp;
	
	private String javaFileName;
	
	private ClassDecompiler(){
	}
	
	public void init(File classFile,File outputDir){
		this.outputDir=outputDir;
        classFileOnTmp = new File(FilenameUtils.concat(config.getTmpDir(), FilenameUtils.getName(classFile.getPath())));
        try {
			FileUtils.copyFile(classFile, classFileOnTmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		javaFileName=FilenameUtils.getBaseName(classFile.getPath())+Constants.JAVA_FILE_EXTENSION;
		javaFileOnTmp = new File(FilenameUtils.concat(config.getTmpDir(),javaFileName));
	}

	private File decompileInternal(File classFile,File outputDir){
            logger.info("Decompiling "+classFile.getPath());
            init(classFile,outputDir);
            String command = templator.parse(Constants.JAD_COMMAND_TEMPLATE,jadFilePath,config.getTmpDir(),classFileOnTmp.getPath());
            commandExecuter.execute(command,true,new CommandExecuter.AfterCallBack() {
				public void executeAfter() {
		        	logger.info("Deleting class file["+classFileOnTmp.getPath()+"] on temp");
		        	FileUtils.deleteQuietly(classFileOnTmp);
				}
			});
            if(!javaFileOnTmp.exists()){
            	throw new RuntimeException("Decompile class file["+classFile.getPath()+"] failed");
            }
            File outputJavaFile=moveToOutputDir(javaFileOnTmp);
            logger.info("Output "+outputJavaFile.getPath());
            return outputJavaFile;
    }

	private File moveToOutputDir(File file){
        File outputJavaFile=new File(FilenameUtils.concat(outputDir.getPath(),javaFileName));
        try {
        	logger.info("Moving file["+file.getPath()+"] from temp dir to "+outputDir);
        	File javaFileOnOutputDir=new File(FilenameUtils.concat(outputDir.getPath(), javaFileName));
        	if(javaFileOnOutputDir.exists()){
        		javaFileOnOutputDir.renameTo(new File(FilenameUtils.concat(outputDir.getPath(), javaFileName+"."+Helper.getCurrentTimeStamp())));
        	}
			FileUtils.moveFileToDirectory(javaFileOnTmp,outputDir,true);
		} catch (IOException e) {
			throw new RuntimeException("Move file["+file.getPath()+"] from temp dir to "+outputDir+" failed",e);
		}
		return outputJavaFile;
	}
	
	private static ClassDecompiler getInstance(){
		return INSTANCE;
	}
	
	public static File decompile(File classFile,File outputDir){
		return getInstance().decompileInternal(classFile, outputDir);
	}
	
	@SuppressWarnings("unchecked")
	public static void batchDecompile(File dirIncludeClassFiles) throws NotFundClassFileException{
		Collection<File> classFiles = (Collection<File>) FileUtils.listFiles(dirIncludeClassFiles, new String[] { "class" }, Config.getConfig().isRecursiveSubdirectories());
		if(classFiles==null||classFiles.size()==0){
			throw new NotFundClassFileException();
		}
		for (File classFile : classFiles) {
			File outputDir=new File(FilenameUtils.getFullPath(classFile.getPath()));
			decompile(classFile, outputDir);
		}
	}

}
