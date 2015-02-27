package com.czp.jsunspot.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 2010-6-1
 * @version 1.00.00
 * @history:
 * 
 */
public class DialogUtils {
	
	private DialogUtils(){
	}

    public static void showErrorOnDialog(Throwable throwable){
    	ByteArrayOutputStream out=null;
    	try {
    		out=new ByteArrayOutputStream();
    		throwable.printStackTrace(new PrintStream(out));
    		JOptionPane.showMessageDialog(null, out.toString(),"错误", JOptionPane.ERROR_MESSAGE);	
		}catch (Exception e) {
		}finally{
			Helper.closeStream(out);
		}
    }
    
    public static void showWarningMessageOnDialog(Object message){
    	JOptionPane.showMessageDialog(null, message.toString(),"提示", JOptionPane.WARNING_MESSAGE);	
    }
    
    public static void showMessageOnDialog(Object message){
    	JOptionPane.showMessageDialog(null, message.toString(),null, JOptionPane.INFORMATION_MESSAGE);	
    }
}
