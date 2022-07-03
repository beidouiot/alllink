package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu;

import java.util.List;

import com.beidouiot.alllink.community.common.data.xxo.dto.TreeNode;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年5月25日
*/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class MenuTreeRro implements TreeNode {
	
	
	private String id;
	
	private String name;
	
	private String code;

	private String addr;
	
	private String icon;
	
	private String type;
	
	private Integer sortNo;
	
	private Integer level;
	
	private Boolean leafFlag;
	
	private Boolean hasButton;
	
	private String pid;

	/**
     * 子节点
     */
    private List<? extends TreeNode> children;
    
	@Override
	public String id() {
		return id;
	}

	@Override
	public String pid() {
		return pid;
	}

	@Override
	public int sortNo() {
		return sortNo;
	}

	@Override
	public void fillChild(List<? extends TreeNode> children) {
		this.children = children;

	}

}
