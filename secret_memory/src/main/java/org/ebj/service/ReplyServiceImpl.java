package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.ReplyMapper;
import org.ebj.vo.ReplyVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyMapper replyMapper;

	@Override
	public List<ReplyVO> selectReply(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.selectReply(boardNo);
	}
	
	@Transactional
	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
		replyMapper.insertReply(replyVO);
	}
	
	@Transactional
	@Override
	public void deleteReply(Integer replyNo) throws Exception {
		replyMapper.deleteReply(replyNo);
	}

}
