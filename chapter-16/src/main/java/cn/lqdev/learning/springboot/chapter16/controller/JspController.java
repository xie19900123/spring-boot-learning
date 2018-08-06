package cn.lqdev.learning.springboot.chapter16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jsp")
public class JspController {
	
	//正常和springmvc设置返回参数是意义的用法了
		@GetMapping("/map")
		public String index(String name,ModelMap map) {
			map.addAttribute("name", name);
			map.addAttribute("from", "lqdev.cn");
			//模版名称，实际的目录为：src/main/webapp/jsp/index.html
			return "index";
		}
		
		@GetMapping("/mv")
		public ModelAndView index(String name) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("name", name);
			mv.addObject("from", "lqdev.cn");
			//模版名称，实际的目录为：src/main/webapp/jsp/index.html
			mv.setViewName("index");
			return mv;
		}
}
