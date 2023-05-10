package com.beidouiot.alllink.community.common.dao.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.DataSearchUtils;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchFilter;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;


/**
 * 
 *
 * @Description Service基类
 * @author longww
 * @date 2021年4月11日
 * @param <D>
 * @param <ID>
 */
public interface BaseService<D,U, E, ID> {
	/**
	 * 持久化对象实体
	 * @param t 待持久化的实体对象
	 */
	void saveEntity(D t) throws ServiceException;
	
	/**
	 * 根据对象id删除对象(真实)
	 * @param id
	 */
	void delete(ID id) throws ServiceException;
	
	/**
	 * 根据对象id删除对象(假删除，实际是将更新删除标志置为1)
	 * @param id 主键id
	 */
	void logicalDelete(ID id) throws ServiceException;
	
	/**
	 * 更新实体对象
	 * @param u 待更新的实体对象
	 */
	void updateEntity(U u) throws ServiceException;
	  
	/**
	 * 根据对象ID查询对象
	 * @param id 实体id
	 * @return 实体对象
	 */
	D findById(ID id) throws ServiceException;
	
	/**
	 * 查询所有对象，不分页
	 * @return 所有实体对象
	 */
	List<D> findAll() throws ServiceException;
	
	/**
	 * 根据searchParams参数集动态查询分页查询，并按sortTypes排序
	 * @param searchParams  查询参数Map集，并做and连接，KEY为查询操作方式及条件字段，组合方式为：操作方式_条件字段，如：EQ_name表示“查询等于name值的数据”,
	 * LIKE_name表示“查询包含name值得数据，即模糊查询”,GT_number表示为“查询大于number值的数据”，LT_number表示“查询小于number值得数据”，
	 * GTE_number表示“查询大于等于number值的数据”，LTE_number表示“查询小于等于number值的数据”
	 * @param pageNumber 需要查询的页数
	 * @param pageSize 总页数
	 * @param sortTypes 排序类型方式集，按sortTypes集合中先后顺序确认多字段排序优先级。
	 * @return 返回分页实体对象
	 */
	Page<D> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes) throws ServiceException;
	
	default Page<D> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes, JpaSpecificationExecutor<E> jse, BaseMapping<E, D> bm) {
		PageRequest pageRequest = DataSearchUtils.getInstance().buildPageRequest(pageNumber, pageSize, sortTypes);
		searchParams.put("EQ_deleteFlag", Constants.FALSE);
//		Map<String, Integer> hash = new HashMap<>();
//		Specification<E> spec = DataSearchUtils.getInstance().buildSpecification(searchParams,hash);
		
		Specification<E> spec = DataSearchUtils.getInstance().buildSpecification(searchParams);
		Page<E> page = jse.findAll(spec, pageRequest);
		List<D> list = new ArrayList<D>();
		for (E e : page) {
			D t = bm.sourceToTarget(e);
			list.add(t);
		}
		Page<D> pageDto = new PageImpl<D>(list, page.getPageable(), page.getTotalElements());
		return pageDto;
	}
	
	default Page<D> findPage(List<SearchFilter> searchFilterList, int pageNumber, int pageSize, List<SortRpo> sortTypes, JpaSpecificationExecutor<E> jse, BaseMapping<E, D> bm) {
		PageRequest pageRequest = DataSearchUtils.getInstance().buildPageRequest(pageNumber, pageSize, sortTypes);
		searchFilterList.add(new SearchFilter("deleteFlag",SearchFilter.Operator.EQ,Constants.FALSE));
		
		Specification<E> spec = DataSearchUtils.getInstance().buildSpecification(searchFilterList);
		Page<E> page = jse.findAll(spec, pageRequest);
		List<D> list = new ArrayList<D>();
		for (E e : page) {
			D t = bm.sourceToTarget(e);
			list.add(t);
		}
		Page<D> pageDto = new PageImpl<D>(list, page.getPageable(), page.getTotalElements());
		return pageDto;
	}
	
	default JSONObject getHeaderUser() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(Constants.USER);
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
		return userJsonObject;
	}
}
