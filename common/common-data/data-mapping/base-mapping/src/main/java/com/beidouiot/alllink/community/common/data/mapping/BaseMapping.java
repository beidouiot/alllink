package com.beidouiot.alllink.community.common.data.mapping;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * 
 *
 * @Description mapstruct基类
 * @author longww
 * @date 2021年4月11日
 * @param <SOURCE>
 * @param <TARGET>
 */
@MapperConfig(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BaseMapping<SOURCE, TARGET> {

	/**
	 * 映射同名属性
     */
//    @Mapping(target = "createDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TARGET sourceToTarget(SOURCE var1);
    
    /**
     * =映射同名属性（修改目标属性）
     * @param var1
     * @param var2
     * @return
     */
    TARGET sourceToTargetForUpdate(SOURCE var1, @MappingTarget TARGET var2);
    
    /**
             * 反向，映射同名属性
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSource(TARGET var1);
    
    /**
     * =反向，映射同名属性(修改源属性）
     * @param var1
     * @param var2
     * @return
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSourceForUpdate(TARGET var1, @MappingTarget SOURCE var2);
    
       
    /**
     	* 映射同名属性，集合形式
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> var1);
    
    /**
     	* 反向，映射同名属性，集合形式
     */
    @InheritConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> var1);
    
    /**
     	* 映射同名属性，集合流形式
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> stream);

    /**
     	* 反向，映射同名属性，集合流形式
     */
    List<SOURCE> targetToSource(Stream<TARGET> stream);
}
