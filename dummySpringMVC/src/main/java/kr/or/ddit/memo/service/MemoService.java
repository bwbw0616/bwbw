package kr.or.ddit.memo.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.memo.dao.MemoDAO;

@Service
public class MemoService {
	private MemoDAO memoDAO;
	@Inject
	public MemoService(MemoDAO memoDAO) {
		super();
		this.memoDAO = memoDAO;
	}
	
//	retrieveMemoList
//	createMemo
//	modifyMemo
//	removeMemo
}
