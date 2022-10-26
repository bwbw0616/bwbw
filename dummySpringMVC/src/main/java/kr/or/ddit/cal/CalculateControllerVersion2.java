package kr.or.ddit.cal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *  /calculate_version2.do
 *	이 소스는 version1과 api 차이가 있음.
 */
@Controller
@RequestMapping(value="/calculate_version2.do")
public class CalculateControllerVersion2 {
	@RequestMapping						//(method = RequestMethod.GET)
	public String getHandler() {
		return "cal/calForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postHandler(
			@ModelAttribute("calVO") CalculateVO calVO		// command object
			, RedirectAttributes redirectAttributes
	){
//		CalculateVO calVO = new CalculateVO(operand1, operand2,operator);
//		model.addAttribute("calVO", calVO);
//		PRG pattern
		redirectAttributes.addAttribute("test", "324");			
		redirectAttributes.addFlashAttribute("calVO",calVO);
		return "redirect:/calculate_version2.do";
	}
}
