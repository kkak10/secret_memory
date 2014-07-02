package org.ebj.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ebj.service.MapMarkerService;
import org.ebj.vo.MapMarkerVO;
import org.ebj.vo.MapMarkerVOAdd;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class MapMarkerTest {
	
	protected static Logger logger = Logger.getLogger(MapMarkerTest.class);
	
	@Inject
	MapMarkerService mapMarkerService;
	
	
//	@Test
	public void cereateMarkerTest(){
		
		MapMarkerVO mapMarkerVO = new MapMarkerVO(6,1, 37.5157, 126.5774,"ㅎ");
		
		logger.info("======================");
			try {
				mapMarkerService.createMarker(mapMarkerVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		logger.info("======================");
		
	}
	
//	@Test 
	public void selectMarkerTest(){
		
		try {
			logger.info("==========================");
			mapMarkerService.selectMarker(10);
			logger.info("==========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void selectCategoryNo(){
		MapMarkerVO mapMarkerVO = new MapMarkerVO(10,1, 37.5157, 126.5774,"ㅎ");
		logger.info("selectCategoryNo Start");
		try {
			mapMarkerService.selectCategoryMarker(mapMarkerVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("selectCategoryNo End");
	}
	
	@Test
	public void selectMarkerCheck(){
		
		List<MapMarkerVOAdd> markerList = new ArrayList<MapMarkerVOAdd>();
		List<MapMarkerVO> alarmMarkerList = new ArrayList<MapMarkerVO>();
		
		try {
			markerList = mapMarkerService.selectMarkerCheck("ssb");
			
			double d2r = Math.PI / 180;
			double nowMarkerX = 37.5705076;
			double nowMarkerY = 126.98963059999998;
			double dbMarkerX = 0;
			double dbMarkerY = 0;
			double x = 0;
			double y = 0;
			double a = 0;
			double c = 0;
			double distance = 0;
			
			for (int i = 0; i < markerList.size(); i++) {
				
				dbMarkerX = markerList.get(i).getMarkerX();
				dbMarkerY = markerList.get(i).getMarkerY();
				
				x = (dbMarkerX - nowMarkerX) * d2r;
				y = (dbMarkerY - nowMarkerY) * d2r;
				
				a = Math.pow(Math.sin(y / 2.0), 2)
						+ Math.cos(nowMarkerY * d2r) * Math.cos(dbMarkerY * d2r)
						* Math.pow(Math.sin(x / 2.0), 2);
				
				c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * 2;
				
				distance = c * 6378 * 1000;
				
				logger.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				logger.info(distance);
				logger.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			
				if (distance <= 100) {
					alarmMarkerList.add(markerList.get(i));
				}
			}
			
			logger.info("***********" + alarmMarkerList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
