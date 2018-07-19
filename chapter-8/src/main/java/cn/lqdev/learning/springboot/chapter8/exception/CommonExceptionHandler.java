package cn.lqdev.learning.springboot.chapter8.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 *  统一异常处理类
 * @author oKong
 *
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

	/**
	 * 拦截 CommonException 的异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public Map<String,Object> exceptionHandler(CommonException ex){
		log.info("CommonException：{}({})",ex.getMsg(), ex.getCode());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", ex.getCode());
		result.put("respMsg", ex.getMsg());
		return result; 
	}
	
	/**
	 *  拦截Exception类的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String,Object> exceptionHandler(Exception e){
		log.info("Exception：{}({})",e);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", "9999");
		result.put("respMsg", e.getMessage());
		//正常开发中，可创建一个统一响应实体，如CommonResp
		return result; 
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Map<String,Object> handleBindException(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", "01002");
		result.put("respMsg", fieldError.getDefaultMessage());
		return result;
	}


	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Map<String,Object> handleBindException(BindException ex) {
		//校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", "01002");
		result.put("respMsg", fieldError.getDefaultMessage());
		return result;
	}

}
