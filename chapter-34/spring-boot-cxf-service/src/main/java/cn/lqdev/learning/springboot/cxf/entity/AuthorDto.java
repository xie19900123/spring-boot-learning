package cn.lqdev.learning.springboot.cxf.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者信息实体
 * @author oKong
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

	String name;
	
	List<String> hobby; 
	
	String birthday;
	
	String description;
	
	Sex sex;
}
