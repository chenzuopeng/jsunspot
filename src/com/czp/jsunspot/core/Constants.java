package com.czp.jsunspot.core;

/**
 *
 * 公共常量定义.
 * 
 * @author dylan.chen 2010-5-28
 * 
 */
public final class Constants {

	/**
	 * 工程名
	 */
	public final static String PROJECT_NAME="JSunspot";
	
	/**
	 * 空格
	 * */
	public final static String BLANK_SPACE=" ";
	
	/**
	 * JAD程序的在安装目录下的相对路径 
	 */
	public final static String JAD_FILE_RELATIVE_PATH="lib\\jad.exe";
	
	/**
	 * java文件扩展名
	 */
	public final static String JAVA_FILE_EXTENSION=".java";
	
	/**
	 * JAD命令的模板
	 */
	public final static String JAD_COMMAND_TEMPLATE="%s -o -sjava -d%s \"%s\"";
	
	/**
	 * 查看JAVA文件的命令模板
	 */
	public final static String SHOW_JAVA_FILE_COMMAND_TEMPLATE="%s %s";
	
	/**
	 * 打开一个目录的命令模板
	 */
	public final static String OPEN_DIR_COMMAND_TEMPLATE="explorer %s";
	
	/**
	 * 展示帮助文档的命令模板
	 */
	public final static String SHOW_HELP_TEMPLATE="hh Help.chm";
	
	
	//安装和卸载相关常量
	
	/**
	 * 导入REG文件的命令表达式
	 */
	public final static String REG_COMMAND_TEMPLATE="cmd.exe /c regedit /s %s";
	
	/**
	 * 添加注册表信息的注册文件的文件名
	 */
	public final static String INSTALL_REG_FILE_NAME="install.reg";
	
	/**
	 * 删除注册表信息的注册文件的文件名
	 */
	public final static String UNINSTALL_REG_FILE_NAME="uninstall.reg";
	
	/**
	 * 运行程序的批处理文件的文件名
	 * */
	public final static String EXECUTE_FILE_NAME=(PROJECT_NAME+".bat").toLowerCase();
	
	
	
	//配置文件相关常量
		
    /**
     * 配置文件的文件名
     * */	
	public final static String CONFIG_FILE_NAME="config.xml";
	
	/**
	 * 默认的程序安装目录:用户目录(程序运行目录)
	 */
	public final static String DEFAULT_HOME=System.getProperty("user.dir");
	
	/**
	 * 默认的缓存目录
	 *    注：由于JAD无法很好处理带空格的绝对路径,所用缓存目录最好是一个相对与运行目录的不带空格的目录
	 */
	public final static String DEFAULT_TMP_DIR="temp";
	
	/**
	 * 默认的用于打开JAVA文件的程序:系统默认的TXT文件编译器
	 */
	public final static String DEFAULT_JAVA_FILE_EDITOR="C:\\WINDOWS\\NOTEPAD.EXE";
		
}
