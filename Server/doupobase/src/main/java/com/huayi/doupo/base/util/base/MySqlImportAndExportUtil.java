package com.huayi.doupo.base.util.base;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * 在进行导出的时候，需要注意命令语句的运行环境，如果已经将mysql安装路径下的bin加入到
 * 系统的path变量中，那么在导出的时候可以直接使用命令语句，否则，就需要在执行命令语句的
 * 时候加上命令所在位置的路径，即mysql安装路径想的bin下的mysqldump命令
 * @author andy
 *
 */
public class MySqlImportAndExportUtil {

	public static void main(String args[]) throws IOException {
		InputStream is = MySqlImportAndExportUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
//		MySqlImportAndExport.export(properties);//这里简单点异常我就直接往上抛
		MySqlImportAndExportUtil.importSql(properties);
	}
	
	/**
	 * 根据属性文件的配置导出指定位置的指定数据库到指定位置
	 * @param properties
	 * @throws IOException
	 */
	public static void export(Properties properties) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		String command = getExportCommand(properties);
		runtime.exec(command);//这里简单一点异常我就直接往上抛
	}
	
	/**
	 * 根据属性文件的配置把指定位置的指定文件内容导入到指定的数据库中
	 * 在命令窗口进行mysql的数据库导入一般分三步走：
	 * 第一步是登到到mysql； mysql -uusername -ppassword -hhost -Pport -DdatabaseName;如果在登录的时候指定了数据库名则会
	 * 直接转向该数据库，这样就可以跳过第二步，直接第三步； 
	 * 第二步是切换到导入的目标数据库；use importDatabaseName；
	 * 第三步是开始从目标文件导入数据到目标数据库；source importPath；
	 * @param properties
	 * @throws IOException 
	 */
	public static void importSql(Properties properties) throws IOException {
//		Runtime runtime = Runtime.getRuntime();
//		//因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
		String cmdarray[] = getImportCommand(properties);//根据属性文件的配置获取数据库导入所需的命令，组成一个数组
//		//runtime.exec(cmdarray);//这里也是简单的直接抛出异常
//		Process process = runtime.exec(cmdarray[0]);
//		process.destroy();
		
		
		
		
		
		 Runtime runtime = Runtime.getRuntime();  
	     String command = cmdarray[0];  
	     System.out.println("command" + command);
	     runtime.exec("cmd  /c " + command);//这里简单一点异常我就直接往上抛  
		System.out.println("---- exec finish ----");
		
		
//		OutputStream os = process.getOutputStream();
//		OutputStreamWriter writer = new OutputStreamWriter(os);
		/*Process process = runtime.exec(cmdarray[0]);
		//执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
		//进程执行后面的代码
		OutputStream os = process.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(os);
		//命令1和命令2要放在一起执行
		writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
		writer.flush();
		writer.close();
		os.close();*/
	}
	
	/**
	 * 利用属性文件提供的配置来拼装命令语句
	 * 在拼装命令语句的时候有一点是需要注意的：一般我们在命令窗口直接使用命令来
	 * 进行导出的时候可以简单使用“>”来表示导出到什么地方，即mysqldump -uusername -ppassword databaseName > exportPath，
	 * 但在Java中这样写是不行的，它需要你用-r明确的指出导出到什么地方，如：
	 * mysqldump -uusername -ppassword databaseName -r exportPath。
	 * @param properties
	 * @return
	 */
	public static String getExportCommand(Properties properties) {
		StringBuffer command = new StringBuffer();
		String username = properties.getProperty("jdbc.user");//用户名
		String password = properties.getProperty("jdbc.password");//密码
		
		String dbUrl =  properties.getProperty("jdbc.jdbcUrl");//Mysql 连接串
		String dbStr = dbUrl.substring(dbUrl.indexOf("//") + 2, dbUrl.indexOf("?"));
		String host = dbStr.substring(0, dbStr.indexOf(":"));
		String port = dbStr.substring(dbStr.indexOf(":") + 1, dbStr.indexOf("/"));
		String exportDatabaseName = dbStr.substring(dbStr.indexOf("/") + 1, dbStr.length());
		String exportPath = System.getProperty("user.dir") + "/config/export.sql";//导入的目标文件所在的位置
		
		//注意哪些地方要空格，哪些不要空格
		command.append("mysqldump -u").append(username).append(" -p").append(password)//密码是用的小p，而端口是用的大P。
		.append(" -h").append(host).append(" -P").append(port).append(" ").append(exportDatabaseName).append(" -r ").append(exportPath);
		System.out.println("cccc" + command.toString());
		return command.toString();
	}
	
	/**
	 * 根据属性文件的配置，分三步走获取从目标文件导入数据到目标数据库所需的命令
	 * 如果在登录的时候指定了数据库名则会
	 * 直接转向该数据库，这样就可以跳过第二步，直接第三步； 
	 * @param properties
	 * @return
	 */
	private static String[] getImportCommand(Properties properties) {
		String username = properties.getProperty("jdbc.user");//用户名
		String password = properties.getProperty("jdbc.password");//密码
		
		String dbUrl =  properties.getProperty("jdbc.jdbcUrl");//Mysql 连接串
		String dbStr = dbUrl.substring(dbUrl.indexOf("//") + 2, dbUrl.indexOf("?"));
		String host = dbStr.substring(0, dbStr.indexOf(":"));
		String port = dbStr.substring(dbStr.indexOf(":") + 1, dbStr.indexOf("/"));
		String importDatabaseName = dbStr.substring(dbStr.indexOf("/") + 1, dbStr.length());
		String importPath = System.getProperty("user.dir") + "/config/inst.sql";//导入的目标文件所在的位置
		
		//第一步，获取登录命令语句
		String loginCommand = new StringBuffer().append("mysql -u").append(username).append(" -p").append(password).append(" -h").append(host).append(" -P").append(port).toString();
		
//		System.out.println("vvvv " + loginCommand);
		
		//第二步，创建数据库
//		String createDb = "create database " + importDatabaseName + ";";
		
		//第三步，获取切换数据库到目标数据库的命令语句
//		String switchCommand = new StringBuffer("use ").append(importDatabaseName).toString();
		
		//第四步，获取导入的命令语句
		String importCommand = new StringBuffer("source ").append(importPath).toString();
		
		String importSql = "mysql -u"+username+" -h"+host+" -p"+password+" -P3306 "+importDatabaseName+" < "+importPath+"";
		
//		System.out.println("import-sql = " + importSql);
		
		//需要返回的命令语句数组
//		String[] commands = new String[] {loginCommand, switchCommand, importCommand};
		String[] commands = new String[] {importSql};
		
		return commands;
	}
	
}