package org.ebj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController{
	
	protected static final Logger logger = Logger.getLogger(FileController.class);
	
	@RequestMapping(value ="/imageUpload", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> imageUpload(@RequestParam("myPageImageUpload") MultipartFile multiPartFile,
					HttpServletRequest request, String folderName){
		
		logger.info("============================");
		logger.info("imageUpload POST");
		logger.info("============================");
		
		Map<String, String> urlMap = new HashMap<String, String>();
		
		FileOutputStream fos = null;
		byte[] fileData = null;
		
		String path = null;	//디렉토리까지의 경로 (파일명빼고)
		String fullPath = null;	//파일명포함 full경로
		String dbInsertPath = null;	//DB에 담기는 경로
		String fileInsertPath = null;	//DB에서 꺼내는 경로
		
		//이클립스 톰캣의 루트 경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		File file = new File(contextRoot + "resources/file/image/" + folderName);

		logger.info("============================");
		logger.info(file.getPath());
		logger.info("============================");
		
		try {
			fileData = multiPartFile.getBytes();
			
			path = file.getPath();
			
			fullPath = path + "/" + multiPartFile.getOriginalFilename();
			dbInsertPath = "file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();			
			fileInsertPath = "resources/file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();
			
			logger.info(dbInsertPath);
			logger.info("-----------------------" + fullPath);
			
			//디렉터리가 없을 경우
			file.mkdir();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);
			
			urlMap.put("dbInsertPath", dbInsertPath);
			urlMap.put("fileInsertPath", fileInsertPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("파일생성 완료");
		return urlMap;
	}
	
	
	@RequestMapping(value ="/boardImageUpload", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> boardImageUpload(@RequestParam("boardImageUpload") MultipartFile multiPartFile,
					HttpServletRequest request, String folderName){
		
		logger.info("============================");
		logger.info("boardImageUpload POST");
		logger.info("============================");
		
		Map<String, String> urlMap = new HashMap<String, String>();
		
		FileOutputStream fos = null;
		byte[] fileData = null;
		
		String path = null;	//디렉토리까지의 경로 (파일명빼고)
		String fullPath = null;	//파일명포함 full경로
		String dbInsertPath = null;	//DB에 담기는 경로
		String fileInsertPath = null;	//DB에서 꺼내는 경로
		
		//이클립스 톰캣의 루트 경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		File file = new File(contextRoot + "resources/file/image/" + folderName);

		logger.info("============================");
		logger.info(file.getPath());
		logger.info("============================");
		
		try {
			fileData = multiPartFile.getBytes();
			
			path = file.getPath();
			
			fullPath = path + "/" + multiPartFile.getOriginalFilename();
			dbInsertPath = "file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();			
			fileInsertPath = "resources/file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();
			
			logger.info(dbInsertPath);
			logger.info("-----------------------" + fullPath);
			
			//디렉터리가 없을 경우
			file.mkdir();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);
			
			urlMap.put("dbInsertPath", dbInsertPath);
			urlMap.put("fileInsertPath", fileInsertPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("파일생성 완료");
		return urlMap;
	}
	
	@RequestMapping(value ="/mapBoardImageUpload", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> mapBoardImageUpload(@RequestParam("mapBoardImageUpload") MultipartFile multiPartFile,
					HttpServletRequest request, String folderName){
		
		logger.info("============================");
		logger.info("mapBoardImageUpload POST");
		logger.info("============================");
		
		Map<String, String> urlMap = new HashMap<String, String>();
		
		FileOutputStream fos = null;
		byte[] fileData = null;
		
		String path = null;	//디렉토리까지의 경로 (파일명빼고)
		String fullPath = null;	//파일명포함 full경로
		String dbInsertPath = null;	//DB에 담기는 경로
		String fileInsertPath = null;	//DB에서 꺼내는 경로
		
		//이클립스 톰캣의 루트 경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		File file = new File(contextRoot + "resources/file/image/" + folderName);

		logger.info("============================");
		logger.info(file.getPath());
		logger.info("============================");
		
		try {
			fileData = multiPartFile.getBytes();
			
			path = file.getPath();
			
			fullPath = path + "/" + multiPartFile.getOriginalFilename();
			dbInsertPath = "file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();			
			fileInsertPath = "resources/file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();
			
			logger.info(dbInsertPath);
			logger.info("-----------------------" + fullPath);
			
			//디렉터리가 없을 경우
			file.mkdir();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);
			
			urlMap.put("dbInsertPath", dbInsertPath);
			urlMap.put("fileInsertPath", fileInsertPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("파일생성 완료");
		return urlMap;
	}
	
	@RequestMapping(value ="/groupImageUpload", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> groupImageUpload(@RequestParam("createGroupImageFile") MultipartFile multiPartFile,
					HttpServletRequest request, String folderName){
		
		logger.info("============================");
		logger.info("boardImageUpload POST");
		logger.info("============================");
		
		Map<String, String> urlMap = new HashMap<String, String>();
		
		FileOutputStream fos = null;
		byte[] fileData = null;
		
		String path = null;	//디렉토리까지의 경로 (파일명빼고)
		String fullPath = null;	//파일명포함 full경로
		String dbInsertPath = null;	//DB에 담기는 경로
		String fileInsertPath = null;	//DB에서 꺼내는 경로
		
		//이클립스 톰캣의 루트 경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		File file = new File(contextRoot + "resources/file/image/" + folderName);

		logger.info("============================");
		logger.info(file.getPath());
		logger.info("============================");
		
		try {
			fileData = multiPartFile.getBytes();
			
			path = file.getPath();
			
			fullPath = path + "/" + multiPartFile.getOriginalFilename();
			dbInsertPath = "file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();			
			fileInsertPath = "resources/file/image/" + folderName + "/" + multiPartFile.getOriginalFilename();
			
			logger.info(dbInsertPath);
			logger.info("-----------------------" + fullPath);
			
			//디렉터리가 없을 경우
			file.mkdir();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);
			
			urlMap.put("dbInsertPath", dbInsertPath);
			urlMap.put("fileInsertPath", fileInsertPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("파일생성 완료");
		return urlMap;
	}

}
