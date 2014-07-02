package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.MapMarkerMapper;
import org.ebj.vo.MapMarkerVO;
import org.ebj.vo.MapMarkerVOAdd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("MapMarkerService")
public class MapMarkerServiceImpl implements MapMarkerService{
	
	@Inject
	private MapMarkerMapper mapMarkerMapper;

	@Override
	public List<MapMarkerVO> selectMarker(Integer groupListNo) throws Exception {
		return mapMarkerMapper.selectMarker(groupListNo);
	}

	@Transactional
	@Override
	public void createMarker(MapMarkerVO mapMarkerVO) throws Exception {
		mapMarkerMapper.createMarker(mapMarkerVO);
	}


	@Override
	public Integer selectLastMarkerNo() throws Exception {
		return mapMarkerMapper.selectLastMarkerNo();
	}
		
	@Override
	public List<MapMarkerVO> selectCategoryMarker(MapMarkerVO mapMarkerVO) throws Exception{
		return mapMarkerMapper.selectCategoryMarker(mapMarkerVO);
	}

	@Override
	public List<MapMarkerVOAdd> selectMarkerCheck(String markerUser) throws Exception {
		return mapMarkerMapper.selectMarkerCheck(markerUser);
	}

	@Override
	public void deleteMarker(MapMarkerVO mapMarkerVo) throws Exception {
		mapMarkerMapper.deleteMarker(mapMarkerVo);
	}

	@Override
	@Transactional
	public void deleteMarkerNo(Integer markerNo) throws Exception {
		mapMarkerMapper.deleteMarkerNo(markerNo);	
	}
}
