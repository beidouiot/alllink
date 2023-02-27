package com.beidouiot.alllink.community.common.migrate.migration;

import com.beidouiot.alllink.community.common.migrate.Configure;
import com.beidouiot.alllink.community.common.migrate.Engine;

public class CommandLine {

	
	public static void main(String[] args) {
		//Call with 2, 6 or 11
		
		if (args.length < 2) {
			System.out.println(getHelp());
		}
		
		int version = getVersion(args);
		
		if (version < 0) {
			return;
		}
		
		boolean configured = false;
		
		if (args.length == 2) {
			Configure.configure(args[1]);
			configured = true;
		} else if (args.length == 6) {
			Configure.configure(args[1], args[2], args[3], args[4], args[5]);
			configured = true;
		} else if (args.length == 11) {
			System.out.println("This is legal, but not implemented yet");
		}
		
		if (!configured) {
			System.out.println(getHelp());
		} else {
			Engine.migrate(version);
		}
	}
	
	private static int getVersion(String[] args) {
		
		try {
			int version = new Integer(args[0]).intValue();
			
			if (version > 0) {
				return version;
			}
			
			System.out.println("Version must be a positive integer! \n\n" + getHelp());
			
		} catch (NumberFormatException e) {
			System.out.println("Version must be an integer! \n\n" + getHelp());
		}
		
		return -1;
	}
	
	private static String getHelp() {
		StringBuffer help = new StringBuffer();
		
		help.append("CommandLine usage:  \n")
			.append("   1)  CommandLine <version>, <propertyfilename> \n")
			.append("   2)  CommandLine <version>, <url>, <driver>, <username>, <password>, <packagename>");
		
		return help.toString();
	}
}
