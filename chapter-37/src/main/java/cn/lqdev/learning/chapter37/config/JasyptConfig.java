package cn.lqdev.learning.chapter37.config;

import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

/** 
*
* @ClassName   类名：JasyptConfig 
* @Description 功能说明：自定义配置文件加解密配置
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
@Configuration
//这里可加载多个 使用{}方式
@EncryptablePropertySource(name = "encryptedProperties", value = "classpath:encrypted.properties")
public class JasyptConfig {

}
