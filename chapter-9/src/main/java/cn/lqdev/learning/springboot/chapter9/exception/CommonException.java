package cn.lqdev.learning.springboot.chapter9.exception;

/**
 *  自定义异常类
 * @author oKong
 *
 */

public class CommonException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030344392591938285L;

	String code;
	
	String msg;
	
	public CommonException(String code,String msg) {
		super(code + msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
