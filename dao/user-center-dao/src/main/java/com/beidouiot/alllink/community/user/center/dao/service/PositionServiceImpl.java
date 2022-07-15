package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Position;
import com.beidouiot.alllink.community.common.data.entity.user.center.UserPosition;
import com.beidouiot.alllink.community.common.data.mapping.user.center.position.PositionDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.position.PositionUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.PositionRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.UserPositionRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.PositionService;

/**
*
* @Description 职位业务逻辑处理
* @author longww
* @date 2021年5月9日
*/
@Service
public class PositionServiceImpl implements PositionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private UserPositionRepository userPositionRepository;

	@Autowired
	private PositionDtoMapping positionDtoMapping;
	
	@Autowired
	private PositionUpdateDtoMapping positionUpdateDtoMapping;
	
	@Override
	public void saveEntity(PositionDto positionDto) throws ServiceException {
		LOGGER.debug("positionDto = [ {} ]", positionDto);
		Position position = positionDtoMapping.targetToSource(positionDto);
		Map<String, Object> map = getHeaderUser();
		String strTenantId = map.get("tenantId").toString();
		Long tenantId = strTenantId == null || strTenantId.equals("") ? null : Long.valueOf(strTenantId);
		position.setTenantId(tenantId);
		positionRepository.save(position);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		Optional<Position> optional = positionRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		List<UserPosition> userPosition = userPositionRepository.findUserPositionByPositionId(id);
		if (null != userPosition && userPosition.size() > 0) {
			throw new CanNotDeleteDataException("该职位在用，不能删除");
		}
		positionRepository.deleteById(id);
	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		Optional<Position> optional = positionRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<UserPosition> userPosition = userPositionRepository.findUserPositionByPositionId(id);
		if (null != userPosition && userPosition.size() > 0) {
			throw new CanNotDeleteDataException("该职位在用，不能删除");
		}

		Position position = optional.get();
		Map<String, Object> map = getHeaderUser();
		position.setUpdatedBy(map.get("username").toString());
		position.setDeleteFlag(Constants.TRUE);
		positionRepository.save(position);

	}

	@Override
	public void updateEntity(PositionUpdateDto positionUpdateDto) throws ServiceException {
		LOGGER.debug("positionUpdateDto = [ {} ]", positionUpdateDto);
		Optional<Position> optional = positionRepository.findById(positionUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Position position = optional.get();
		position = positionUpdateDtoMapping.targetToSourceForUpdate(positionUpdateDto,position);
		positionRepository.save(position);
	}

	@Override
	public PositionDto findById(Long id) throws ServiceException {
		return positionDtoMapping.sourceToTarget(positionRepository.findById(id).get());
	}

	@Override
	public List<PositionDto> findAll() throws ServiceException {
		List<Position> list = positionRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0 ) {
			return null;
		}
		return positionDtoMapping.sourceToTarget(list);
	}

	@Override
	public Page<PositionDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, positionRepository, positionDtoMapping);
	}

}
