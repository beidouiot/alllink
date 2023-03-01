package com.beidouiot.alllink.community.common.migrate;


/**
 * Represents changes to a database schema.
 * Migrations are applied in the order of their
 * name, which must follow a "Migration_X" pattern
 * where X indicates the order.
 * 
 */
public interface Migration {

	/**
	 * Work to perform when upgrading the database
	 * schema
	 */
	public void up();
	
	
	/**
	 * Work to perform when reverting the database
	 * schema to a previous version
	 */
	public void down();
	
}
