package kr.or.ddit.memo.async;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.memo.MemoController;
import kr.or.ddit.memo.MemoVO;
import kr.or.ddit.memo.service.MemoService;

@RestController
@RequestMapping(value="/memo", produces=MediaType.APPLICATION_JSON_VALUE)
public class MemoRESTController {
	private MemoService service;
	@Inject
	public void setService(MemoService service) {
		this.service = service;
	}
	
	@GetMapping
	public Collection<MemoVO> list() {
		return memoTable.values();
	}
	
	@PostMapping
	public MemoVO insert(@RequestBody MemoVO memo) {
		int lastCode = memoTable.keySet().stream()
									.mapToInt(key->key.intValue())
									.max()
									.orElse(0);
		memo.setCode(lastCode+1);
		memoTable.put(memo.getCode(), memo);
		return memo;
	}
	
	@PutMapping("{memoCode}")
	public MemoVO update(@RequestBody MemoVO memo, @PathVariable("memoCode") int code) {
		memo.setCode(code);
		memoTable.put(code, memo);
		return memo;
	}
	@DeleteMapping("{code}")
	public MemoVO delete(@PathVariable int code) {
//		memo.setCode(0);
//		memoTable.put(null, memo);
		return memoTable.remove(code);
	}
}
