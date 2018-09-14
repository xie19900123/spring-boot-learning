package cn.lqdev.learning.chapter30.admin.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义通知
 * @author oKong
 *
 */
@Component
@ConfigurationProperties("okong.custom.notify")
@Setter
@Getter
@Slf4j
public class CustomNotifier extends AbstractStatusChangeNotifier{

	String name;
	
	@Override
	protected void doNotify(ClientApplicationEvent event) throws Exception {
		//这里只是为了示例 ，直接输出到控制台了。
		log.info("{}-自定义通知：应用-{}", name,event.getApplication().getName());
	}

}
