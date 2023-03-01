package com.beidouiot.alllink.community.common.migrate.migration;

import org.springframework.scheduling.config.Task;

import com.beidouiot.alllink.community.common.migrate.Configure;
import com.beidouiot.alllink.community.common.migrate.Engine;

public class AntTask extends Task {

	public AntTask(Runnable runnable) {
		super(runnable);
	}

	private int version = Integer.MAX_VALUE;
	private String url = null; 
	private String driver = null; 
	private String username = null; 
	private String password = null; 
	//private String connectionArguments = null; 
	private String packageName = null;
	private String configfile = null;
	//private String classprefix = null;
	//private String separator = null;
	//private Integer startIndex = null;
	//private String versionTable = null;
	//private Path path = null;
		
	public void setVersion(int version) {
		this.version = version;
		
	}	
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public void setConnectionArguments(String connectionArguments) {
		this.connectionArguments = connectionArguments;
	}*/

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public void setConfigfile(String configfile) {
		this.configfile = configfile;
	}

	/*public void setClassprefix(String classprefix) {
		this.classprefix = classprefix;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public void setVersionTable(String versionTable) {
		this.versionTable = versionTable;
	}*/
	
	public void execute() throws Exception {
			
//		if (false) {
//			throw new BuildException("Update occurred");
//		}
		
		try {
			if (url != null && driver != null) {
				Configure.configure(url, driver, username, password, packageName);
			} else if (configfile != null){
				Configure.configure(configfile);
			} else {
				Configure.configure();
			}
			
			Engine.migrate(version);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}

	}
	
}
