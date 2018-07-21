package cn.lqdev.learning.springboot.chapter10.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * swagger2示例
 * @author oKong
 *
 */
@RestController
@Api(tags="Swagger2示例API")
public class DemoController {

	@GetMapping("/demo")
	@ApiOperation(value="demo示例")
	public DemoResp demo(@Valid DemoReq req){
		
		DemoResp resp = DemoResp.builder()
				.code(req.getCode())
				.name(req.getName())
				.remark(req.getRemark())
				.build();
		return resp;
	}
}
