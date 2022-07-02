package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年6月26日
*/
@Data
public class StandardPropertyModelListRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7391153282358289829L;

	private Long productTypeId;
	
	private List<StandardPropertyModelAddRpo> standardPropertyModelList;

}
