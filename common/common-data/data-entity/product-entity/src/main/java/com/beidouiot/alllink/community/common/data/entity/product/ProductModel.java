package com.beidouiot.alllink.community.common.data.entity.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品物模型
* @author longww
* @date 2021年12月22日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7474749928442005441L;

	private Long productId;
	
	private List properties;
	
	private List events;
	
	private List commands;
	
}
