package cn.lqdev.learning.springboot.chapter25.config;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * 自定义log4j2输出源，简单的输出到控制台
 * @author oKong
 *
 */
//这里的 MyLog4j2 对应就是 xml中，
/**
 * 
 *  <appenders>
 *     <MyLog4j2 name="customAppender" printString="一枚趔趄的猿">
 *     </MyLog4j2>
 *  </appenders>
 *
 */
@Plugin(name = "MyLog4j2", category = "Core", elementType = "appender", printObject = true)
public class MyLog4j2Appender extends AbstractAppender {

	String printString;
   /**  
	 *构造函数 可自定义参数 这里直接传入一个常量并输出
	 * 
	*/ 
	protected MyLog4j2Appender(String name, Filter filter, Layout<? extends Serializable> layout,String printString) {
		super(name, filter, layout);
		this.printString = printString;
	}

	@Override
	public void append(LogEvent event) {
		 if (event != null && event.getMessage() != null) {
	         // 此处自定义实现输出			 
			 // 获取输出值：event.getMessage().toString()
			 // System.out.print(event.getMessage().toString());
			 // 格式化输出
			 System.out.print(printString + "：" + getLayout().toSerializable(event));
	      }
		
	}
	
	/**  接收配置文件中的参数 
	 * 
	 * @PluginAttribute 字面意思都知道，是xml节点的attribute值，如<oKong name="oKong"></oKong> 这里的name 就是 attribute
	 * @PluginElement：表示xml子节点的元素，
	 * 如
	 *     <oKong name="oKong">
	 *         <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
	 *     </oKong>
	 *   其中，PatternLayout就是 的 Layout，其实就是{@link Layout}的实现类。
	 */ 
	@PluginFactory
	public static MyLog4j2Appender createAppender(
			@PluginAttribute("name") String name,
            @PluginElement("Filter") final Filter filter, 
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute("printString") String printString) {
		if (name == null) {
            LOGGER.error("no name defined in conf."); 
            return null; 
        } 
		//默认使用 PatternLayout
        if (layout == null) { 
            layout = PatternLayout.createDefaultLayout(); 
        } 
        
        return new MyLog4j2Appender(name, filter, layout, printString);
	}
	
	@Override
	public void start() {
		System.out.println("log4j2-start方法被调用");
		super.start();
	}
	
	@Override
	public void stop() {
		System.out.println("log4j2-stop方法被调用");
		super.stop();
	}
}
