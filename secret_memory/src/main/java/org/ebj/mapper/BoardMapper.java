package org.ebj.mapper;

import java.util.List;

import org.ebj.vo.BoardVO;
import org.ebj.vo.BoardVOAdd;
import org.ebj.vo.MapMarkerVO;

public interface BoardMapper {
	
	public void insertMarkerBoard(BoardVO boardVO) throws Exception;
	
	public void insertNoMarkerBoard(BoardVO boardVO) throws Exception;
	
	public List<BoardVOAdd> selectBoardList(BoardVOAdd boardVOAdd) throws Exception;
	
	public BoardVOAdd selectBoard(MapMarkerVO mapMarkerVO) throws Exception;
	
	public List<BoardVO> selectBoardImage(Integer groupListNo) throws Exception;
	
	public Integer selectBoardMakerNo (Integer boardNo) throws Exception;
	
	public void deleteMarkerBoard(MapMarkerVO mapMarkerVO) throws Exception;
	
	public void deleteBoard (Integer boardNo) throws Exception;
}
