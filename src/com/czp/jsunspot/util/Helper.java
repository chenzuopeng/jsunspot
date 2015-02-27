
package com.czp.jsunspot.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.czp.jsunspot.core.Constants;


public class Helper{

    private Helper(){
    }
    
    public static File createFile(File file){
    	String dirPath=FilenameUtils.getFullPath(file.getPath());
		if (isNotBlank(dirPath)) {
			File dir = new File(dirPath);
			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					throw new RuntimeException("Create directory["+ dir.getPath() + "] failed");
				}
			}
		}
    	if(file.exists()){
    		FileUtils.deleteQuietly(file);
    	}
    	try {
			file.createNewFile();
		} catch (IOException e) {
		}
		return file;
    }
    
	public static boolean isNotBlank(String input){
		return input!=null&&!input.trim().equals("");
	}

    public static String getClassFilePathFromArgs(String[] args){
        StringBuilder tmp = new StringBuilder();
        for (String arg : args) {
            tmp.append(arg);
            tmp.append(Constants.BLANK_SPACE);
		}
        return tmp.toString().trim();
    }

    public static String formatFilePath(String path){
        return path.replaceAll("\\\\", "\\\\\\\\");
    }
    
    public static void closeStream(Object stream){
    	if(stream==null){
    		return;
    	}
    	try {
			Method closeMethod=stream.getClass().getMethod("close");
			closeMethod.invoke(stream);
		} catch (Exception e) {
		}
    }
    
    public static String getCurrentTimeStamp(){
    	return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
    }
    
    public static URI getURI(String path){
    	try {
			System.out.println("--->"+Helper.class.getClassLoader().getResource(path).toURI());
			return Helper.class.getClassLoader().getResource(path).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
    }
    
    public static String readFileToString(String path){
    	try {
			return IOUtils.toString(Helper.class.getClassLoader().getResourceAsStream(path),"UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

}
