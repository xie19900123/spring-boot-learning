package cn.lqdev.learning.chapter37.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
*
* @ClassName   类名：DemoController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年5月8日
* @author      创建人：oKong
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年5月8日   oKong   创建该类功能。
*
***********************************************************************
*</p>
*/
@RestController
public class DemoController {
	//正常使用即可
	@Value("${okong.name}")
	String name;
	
	@Value("${okong.code}")
	String code;
	
	@GetMapping("/")
	public String index() {
		return "name值为--->:" + name + ",code值为--->:" + code;
	}

}
