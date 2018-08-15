package cn.lqdev.learning.springboot.exception;

/**
 * 自定义超时异常类
 * @author oKong
 *
 */
public class CustomAsyncRequestTimeoutException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8754629185999484614L;

	public CustomAsyncRequestTimeoutException(String uri){
		super(uri);
	}
}
