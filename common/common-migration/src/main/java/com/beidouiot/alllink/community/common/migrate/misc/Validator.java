package com.beidouiot.alllink.community.common.migrate.misc;

import org.apache.commons.logging.Log;


public class Validator {

	public static void notNull(Object object, String message) {
		notNull(object, message, null);
	}
	
	public static void notNull(Object object, String message, Log logger) {
		if (object == null) {
			throwException(message, logger);
		}
	}

	public static void isTrue(boolean trueExpression, String message) {
		isTrue(trueExpression, message, null);
	}
	public static void isTrue(boolean trueExpression, String message, Log logger) {
		
		if (!trueExpression) {
			throwException(message, logger);
		}
	}
	
	private static void throwException(String message, Log logger) {
		SchemaMigrationException exception = new SchemaMigrationException(message);
		
		if (logger != null) {
			logger.error("Required object was null", exception);
		}
		
		throw exception;
	}
	
}
