package com.beidouiot.alllink.community.common.migrate.core;

import com.beidouiot.alllink.community.common.migrate.schema.Column;

public class GeneralColumn {
	
	public GeneralColumn(Column column, String comment){
		this.column = column;
		this.comment = comment;
	}
	private Column column;
	private String comment;
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
