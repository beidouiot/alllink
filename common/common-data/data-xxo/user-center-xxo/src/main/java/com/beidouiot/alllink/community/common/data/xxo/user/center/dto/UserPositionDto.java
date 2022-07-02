package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 用户职位
* @author longww
* @date 2021年5月9日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPositionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4522124885312237095L;

	private Long positionId;
	
	private String strPositionId;
	
	public String getStrPositionId() {
		this.strPositionId = positionId == null ? "" : String.valueOf(positionId);
		return strPositionId;
	}
	
	private List<Long> userIds;
	
	private List<String> strUserIds;
	
	public List<String> getStrUserIds() {
		strUserIds = userIds == null ? null : userIds.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
		return strUserIds;
	}
	
}
