package kr.or.ddit.memo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.InsertGroup;

/**
 * RESTful URI 구조
 *  /memo/write.do (작성)
 *  /memo/list.do (전체 메모 조회)
 */

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	
	@ModelAttribute("memo")			// 한 번 만들어진 객체를 재활용할 수 있음
	public MemoVO memo() {
		return new MemoVO();
	}
	
	// 메모 작성 기능, 작성된 메모 조회
	@GetMapping("write.do")
	public String memoForm() {
//		model.addAttribute("memo", new MemoVO());
		return "memo/memoForm";
	}
	
	@PostMapping("write.do")
	public String memoWrite(
			@Validated(InsertGroup.class) @ModelAttribute("memo")MemoVO memo
			, Errors errors
		) {
			if(errors.hasErrors()) {
				return "memo/memoForm";
			}else {
				int length = memoTable.size();
				memo.setCode(length + 1);
				memoTable.put(memo.getCode(), memo);
				return "redirect:/memo/list.do";
			}
	}
	@GetMapping("list.do")
	public ModelAndView memoList() {
		Collection<MemoVO> memoList = memoTable.values();
		ModelAndView mav = new ModelAndView();
		mav.addObject("memoList", memoList);	// model.addAttribute
		mav.setViewName("memo/memoList");
		return mav;
	}
	
}

//	
//	@RequestMapping(value="memo.do", method=RequestMethod.GET)
//	public String getHandler() {
//		return "";		
//	}
//	
//	@RequestMapping(value="", method=RequestMethod.POST)
//	public String postHandler(
//			@Validated(InsertGroup.class) @ModelAttribute("memo") MemoVO memoVO  
//			, Errors errors
//			, RedirectAttributes redirectAttributes
//			, Model model
//	) throws IOException{
//		
//		boolean valid = !errors.hasErrors();
//		
//		String message = null;
//		String view = null;
//		if(valid) {
//			
//			File saveFolder = new File("d:/contents");
//			memoVO.saveTo(saveFolder);
//			
//			message = "등록 완료";
//			redirectAttributes.addAttribute("student", memoVO);
//			redirectAttributes.addAttribute("message", message);
//			view = "redirect:/student/resultView.do";
//		}else {
//			message = "등록 실패. 검증 실패";
//			model.addAttribute("message",message);
//			view = "student/registForm";
//		}
//		return view;
//	}
//	
//	
//	// 메모 작성 기능, 작성된 메모 조회
//	@ModelAttribute("writeBoard")
//	private static 
//		Map<Integer, MemoVO> memoTable = new LinkedHashMap<Integer, MemoVO>();
//		return writeBoard;
//	}
//
//	@ModelAttribute("checkBoard")
//	private static 
//		Map<Integer, MemoVO> memoTable = new LinkedHashMap<Integer, MemoVO>();
//		return checkBoard;
//	}