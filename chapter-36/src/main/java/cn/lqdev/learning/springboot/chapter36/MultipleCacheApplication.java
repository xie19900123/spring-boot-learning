package cn.lqdev.learning.springboot.chapter36;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：MultipleCacheApplication
 * @Description 功能说明：多缓存启动类
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年3月7日
 * @author 创建人：oKong
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2019年3月7日 oKong 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@SpringBootApplication
@Slf4j
public class MultipleCacheApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MultipleCacheApplication.class, args);
		log.info("spring-boot-multiple-chapter36启动!");
	}

}
