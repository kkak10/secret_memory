package org.ebj.mapper;

import java.util.List;
import org.ebj.vo.ReplyVO;

public interface ReplyMapper {
	
	public void insertReply (ReplyVO replyVO) throws Exception;
	
	public List<ReplyVO> selectReply (Integer boardNo) throws Exception;
	
	public void deleteReply (Integer replyNo) throws Exception;
}
