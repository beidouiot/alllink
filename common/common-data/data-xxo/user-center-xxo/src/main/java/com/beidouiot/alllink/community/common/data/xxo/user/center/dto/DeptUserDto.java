package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2021年4月14日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptUserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5227957808344235428L;

	@NotBlank(message = "组织i/部门Id不能为空！")
	private Long deptId;
	
	private List<Long> userIds;
	
	private String strDeptId;
	
	private List<String> strUserIds;
	
	public String getStrDeptId() {
		strDeptId = deptId == null ? "" : String.valueOf(deptId);
		return strDeptId;
	}
	
	public List<String> getStrUserIds() {
		strUserIds = userIds == null ? null : userIds.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
		return strUserIds;
	}
}
