package com.beidouiot.alllink.community.common.migrate.misc;

public class SchemaMigrationException extends RuntimeException {

	private static final long serialVersionUID = -825288896004779724L;

	public SchemaMigrationException() {
		super();
	}

	public SchemaMigrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchemaMigrationException(String message) {
		super(message);
	}

	public SchemaMigrationException(Throwable cause) {
		super(cause);
	}

	
}
