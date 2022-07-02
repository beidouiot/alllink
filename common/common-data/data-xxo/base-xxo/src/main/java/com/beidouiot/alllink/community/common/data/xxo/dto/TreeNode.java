package com.beidouiot.alllink.community.common.data.xxo.dto;

import java.util.List;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年5月25日
*/

public interface TreeNode {

    /**
     * 降序排序
     */
    String DESC = "DESC";
    
    /**
     * 升序排序
     */
    String ASC = "ASC";
    
    String id();
    
    String pid();
    
    int sortNo();
    
    void fillChild(List<? extends TreeNode> children);
    
}
