package cn.lqdev.learning.springboot.chapter38.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
*
* @ClassName   类名：HttpEntity 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年7月17日
* @author      创建人：xds
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年7月17日   xds   创建该类功能。
*
***********************************************************************
*</p>
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpEntity {
	
	String url;
	
	Map<String,Object> params;
	
	String method;
 
}
