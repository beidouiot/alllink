package com.beidouiot.alllink.community.common.data.xxo.dto;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年5月25日
*/

public class TreeUtils {
    
    /**
     * 按照自然排序查询出pid最小值作为第一层父节点
     * @author Loren
     * @param <T>
     * @Datetime 2021年5月25日 下午6:08:50
     * @param nodes
     * @return
     */
    public static List<? extends TreeNode> build(List<? extends TreeNode> nodes) {
        return build(nodes, "");
    }
    
    /**
     * 按照自然排序查询出pid最小值作为第一层父节点
     * @author Loren
     * @Datetime 2021年5月25日 下午6:08:05
     * @param nodes
     * @param order TreeNodeDto#DESC / TreeNodeDto#ASC 
     * @return
     */
    public static List<? extends TreeNode> build(List<? extends TreeNode> nodes, String order) {
        String topValue = nodes.stream().map(TreeNode::pid).min(Comparator.naturalOrder()).get();
        return build(topValue, nodes, order);
    }
    
    /**
     * 基于topValue 作为第一层父节点
     * @author Loren
     * @Datetime 2021年5月25日 下午6:07:56
     * @param topValue
     * @param nodes
     * @return
     */
    public static List<? extends TreeNode> build(String topValue, List<? extends TreeNode> nodes) {
       return build(topValue, nodes, null);
    }
    
    /**
     * 基于topValue 作为第一层父节点
     * @author Loren
     * @Datetime 2021年5月25日 下午6:07:21
     * @param topValue
     * @param nodes
     * @param order TreeNodeDto#DESC / TreeNodeDto#ASC 
     * @return
     */
    public static List<? extends TreeNode> build(String topValue, List<? extends TreeNode> nodes, String order) {
        List<TreeNode> parents = new LinkedList<>();
        for(TreeNode node : nodes) {
            if(node.pid().equals(topValue)) {
                parents.add(node);
            }
        }
        if(CollectionUtils.isNotEmpty(parents)) {            
            Comparator<TreeNode> comparator = comparate(order);
            if(comparator != null) {
                parents.sort(comparator);
            }
        }
        
        buid(parents, nodes, order);
        
        return parents;
    }
    
    
    /**
     * 将children列表安装pid 与 id的关系形成树形结构 
     * @author Loren
     * @Datetime 2021年5月25日 下午5:57:03
     * @param parents
     * @param children
     */
    public static void build(List<? extends TreeNode> parents, List<? extends TreeNode> children) {
        buid(parents, children, null);
    }
    
    /**
     * 将children列表安装pid 与 id的关系形成树形结构 
     * order 传递则排序，否则不排序
     * @author Loren
     * @Datetime 2021年5月25日 下午5:56:20
     * @param parents
     * @param children
     * @param order
     */
    public static void buid(List<? extends TreeNode> parents, List<? extends TreeNode> children, String order) {
        for(TreeNode parent : parents) {
            build(parent, children);
        }
    }
    
    /**
     * 将children列表安装pid 与 id的关系形成树形结构 
     * @author Loren
     * @Datetime 2021年5月25日 下午5:55:58
     * @param parent
     * @param children
     */
    public static void build(TreeNode parent, List<? extends TreeNode> children) {
        build(parent, children, null);
    }
    
    /**
     * 将children列表安装pid 与 id的关系形成树形结构 
     * order 传递则排序，否则不排序
     * @author Loren
     * @Datetime 2021年5月25日 下午5:34:15
     * @param parent
     * @param children
     * @param order TreeNodeDto#DESC / TreeNodeDto#ASC 
     */
    public static void build(TreeNode parent, List<? extends TreeNode> children, String order) {
        if(parent != null) {
            List<TreeNode> newChildren = new LinkedList<>();
            for(TreeNode child : children) {
                if(child.pid().equals(parent.id())) {
                    newChildren.add(child);
                }
            }
            if(CollectionUtils.isNotEmpty(newChildren)) {
                Comparator<TreeNode> comparate = comparate(order);
                if(comparate != null) {
                    newChildren.sort(comparate);
                }
            }
            
            parent.fillChild(newChildren);
            
            for(TreeNode newChild : newChildren) {
                build(newChild, children, order);
            }
        }
    }
    
    private static Comparator<TreeNode> comparate(String order) {
        Comparator<TreeNode> comparator = null;
        if(StringUtils.isNotBlank(order)) {
            if(TreeNode.ASC.equals(order)) {
                comparator = Comparator.comparing(TreeNode::sortNo);
            }
            if(TreeNode.DESC.equals(order)) {
                comparator = Comparator.comparing(TreeNode::sortNo).reversed();
            }
        }
        return comparator;
    }
}
