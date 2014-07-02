package org.ebj.service;

import java.util.List;

import org.ebj.vo.MapMarkerVO;
import org.ebj.vo.MapMarkerVOAdd;

public interface MapMarkerService {

	public void createMarker(MapMarkerVO mapMarkerVO) throws Exception;
	
	public Integer selectLastMarkerNo() throws Exception;
	
	public List<MapMarkerVO> selectMarker(Integer groupListNo) throws Exception;
	
	public List<MapMarkerVO> selectCategoryMarker(MapMarkerVO mapMarkerVO) throws Exception;

	public List<MapMarkerVOAdd> selectMarkerCheck(String markerUser) throws Exception;
	
	public void deleteMarker(MapMarkerVO mapMarkerVo)throws Exception;
	
	public void deleteMarkerNo(Integer markerNo)throws Exception;
}
