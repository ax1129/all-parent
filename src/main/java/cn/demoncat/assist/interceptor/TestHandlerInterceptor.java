package cn.demoncat.assist.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.demoncat.assist.util.StringUtil;

/**
 * 拦截器：拦截指定的Controller，进行相应的额外操作；只要有一个拦截器不放行或转发，Controller就不会执行完成。
 * 1、需要在springmvc.xml中进行配置拦截器和拦截的URL。
 * 2、可以通过实现HandlerInterceptor接口来创建，或者通过继承HandlerInterceptorAdapter适配器来创建。
 * 3、由于缓存问题，发送相同的Ajax不会多次拦截，所以Ajax请求若想被拦劫，URL地址应拼接new Date()。 
 */
public class TestHandlerInterceptor implements HandlerInterceptor {
	/**
	 * 前置拦截器：在执行Controller之前执行。
	 * 用途：身份认证、权限校验、编码转换、参数过滤等。
	 * 参数：Request、Response、HandlerMethod(可以通过反射获取Controller的信息)。
	 * 返回：true表示放行，继续执行Controller；false表示不再执行Controller，因此需要在方法中进行请求转发。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取当前请求的URL
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		// 判断是否登录
		String username = (String)(request.getSession().getAttribute("username"));
		boolean logined = StringUtil.isNotNull(username);
		// 获取执行的Handler方法(可能通过反射操纵该方法)
		HandlerMethod handlerMethod=(HandlerMethod)handler; 
		Method method = handlerMethod.getMethod();
		return false;
	}
	/**
	 * 后置拦截器：在执行Controller之后，返回视图之前执行。
	 * 用途：在生成视图前修改数据，例如需要向页面提供一些公用的数据或配置信息。
	 * 参数：Request、Response、HandlerMethod、ModelAndView(封装了结果数据和视图)。
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }
	/**
	 * 后期拦截器：在执行Controller并返回视图之后执行。
	 * 用途：释放资源、处理异常、日志管理、方法执行性能监控(preHandle到afterCompletion之间计算时间差)。
	 * 参数：Request、Response、HandlerMethod、异常(可以在这里统一处理Controller抛出的异常)。
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

}
