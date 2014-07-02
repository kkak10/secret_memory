package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.BoardMapper;
import org.ebj.vo.BoardVO;
import org.ebj.vo.BoardVOAdd;
import org.ebj.vo.MapMarkerVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardMapper boardMapper;
	
	@Override
	@Transactional
	public void insertMarkerBoard(BoardVO boardVo) throws Exception {
		boardMapper.insertMarkerBoard(boardVo);
	}

	@Override
	@Transactional
	public void insertNoMarkerBoard(BoardVO boardVo) throws Exception {
		boardMapper.insertNoMarkerBoard(boardVo);
	}

	@Override
	public List<BoardVOAdd> selectBoardList(BoardVOAdd boardVOAdd) throws Exception {
		
		return boardMapper.selectBoardList(boardVOAdd);
	}

	@Override
	public BoardVOAdd selectBoard(MapMarkerVO mapMarkerVO) throws Exception {
		return boardMapper.selectBoard(mapMarkerVO);
	}

	@Override
	public List<BoardVO> selectBoardImage(Integer groupListNo) throws Exception {
		return boardMapper.selectBoardImage(groupListNo);
	}

	@Override
	@Transactional
	public void deleteMarkerBoard(MapMarkerVO mapMarkerVO) throws Exception {
		boardMapper.deleteMarkerBoard(mapMarkerVO);
		
	}
	
	@Override
	@Transactional
	public void deleteBoard(Integer boardNo) throws Exception {
		boardMapper.deleteBoard(boardNo);
	}

	@Override
	public Integer selectBoardMakerNo(Integer boardNo) throws Exception {
		
		return boardMapper.selectBoardMakerNo(boardNo);
	}

}
