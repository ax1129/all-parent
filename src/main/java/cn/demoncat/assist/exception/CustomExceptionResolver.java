package cn.demoncat.assist.exception;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理器：当Controller抛出异常类时，会进入异常处理器进行统一处理。
 * 1、实现HandlerExceptionResolver接口，然后在springmvc.xml中进行配置。
 * 2、可以判断异常信息，统一进行区别处理，这样在service、controller中就可以直接抛出相关异常而无须处理。
 * 3、可以做异常日志，描述谁在什么时间、什么原因、什么请求、什么地方出现异常。
 */
public class CustomExceptionResolver implements HandlerExceptionResolver  {

	/**
	 * 前端控制器DispatcherServlet在执行Handler过程中，如果遇到异常就会执行此方法。
	 * 参数：Request、Response、HandlerMethod(执行的Handler方法)、Exception(抛出的异常)。
	 * 返回：异常处理后跳转的视图
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// 输出异常
		ex.printStackTrace();
		// 获取执行的Handler方法
		HandlerMethod handlerMethod=(HandlerMethod)handler; 
		@SuppressWarnings("unused")
		Method method = handlerMethod.getMethod();
		// 统一异常处理：
		// 要统一处理的异常都转换为自定义的CustomException异常并设置不同的message，然后通过message区别化处理。
		String message = null;
		CustomException customException = null;
		// 如果是系统 自定义的异常，直接取出异常信息
		if(ex instanceof CustomException){
			customException = (CustomException)ex;
		}else{
			// 对于非CustomException异常，转换为CustomException，异常信息设为"未知错误"
			customException = new CustomException("未知错误");
		}
		// 错误信息
		message = customException.getMessage();
		// 判断不同的错误信息，进行相应的处理，然后跳转到不同的Model
		ModelAndView mv=new ModelAndView("error");	
		mv.addObject("message", message);	
		return mv;
	} 

}
