package org.ebj.test;

import javax.inject.Inject;

import org.ebj.mapper.BoardMapper;
import org.ebj.service.BoardService;
import org.ebj.service.MapMarkerService;
import org.ebj.vo.BoardVO;
import org.ebj.vo.BoardVOAdd;
import org.ebj.vo.MapMarkerVO;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class BoardServiceTest {

	static Logger logger = Logger.getLogger(BoardServiceTest.class);
	
	@Inject
	BoardService boardService;
	
	@Inject
	BoardMapper boardMapper;
	
	@Inject 
	MapMarkerService mapMarkerSerivce;
	
//	@Test
	public void selectLastMarkerNoTest() throws Exception{
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info(mapMarkerSerivce.selectLastMarkerNo());
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	/*@Test*/
	public void insertBoard(){
		MapMarkerVO mapMarkerVO = new MapMarkerVO(3, 3, 126.9896408, 37.5707273, "asd");
		BoardVO boardVO = new BoardVO("lhu", 3, 2, "", "l");
		int markerNo;
		try {
			mapMarkerSerivce.createMarker(mapMarkerVO);
			markerNo = mapMarkerSerivce.selectLastMarkerNo();
			boardVO.setMarkerNo(markerNo);
			boardService.insertMarkerBoard(boardVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void selectBoardList(){
		BoardVOAdd boardVOAdd = new BoardVOAdd("lhu",73,"게시판테스트","lhu");
		try {
			boardService.selectBoardList(boardVOAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void selectBoardMakerNo(){
		try {
			boardMapper.selectBoardMakerNo(46);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteMarkerBoard(){
		try {
			boardMapper.deleteBoard(48);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void insertNoMarkerBoard(){
		/*String boardUser, Integer groupListNo, 
		String boardText, String boardNickname*/
		BoardVO boardVO = new BoardVO("lhu",73,"게시판테스트","lhu");
		try {
			boardService.insertNoMarkerBoard(boardVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
