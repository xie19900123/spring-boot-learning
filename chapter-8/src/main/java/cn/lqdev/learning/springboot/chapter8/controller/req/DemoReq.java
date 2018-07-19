package cn.lqdev.learning.springboot.chapter8.controller.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import cn.lqdev.learning.springboot.chapter8.valid.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoReq {
	
	@NotBlank(message="code不能为空")
	String code;
	
	@Length(max=10,message="name长度不能超过10")
	String name;
	
	@Constant(message = "verson只能为1.0",value="1.0")
	String version;
	
}
