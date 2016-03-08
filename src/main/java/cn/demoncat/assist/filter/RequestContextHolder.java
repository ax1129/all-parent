package cn.demoncat.assist.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取Request和Response的过滤器
 * 请求到来时，先传递Requst和Response并持有，然后在Controller中直接调用，而不需要声明Request形参。
 */
public class RequestContextHolder implements Filter{
	public static final ThreadLocal<HttpServletRequest> THREAD_LOCAL1=new ThreadLocal<HttpServletRequest>();
	public static final ThreadLocal<HttpServletResponse> THREAD_LOCAL2=new ThreadLocal<HttpServletResponse>();
	/**
	 * 项目绝对路径
	 */
	public static String ABSOLUTEPATH;	
	/**
	 * 项目访问路径
	 */
	public static String BASEURL;	
	/**
	 * 获取Request
	 */
	public static HttpServletRequest getRequest() {		 
		return THREAD_LOCAL1.get();
	}
	/**
	 * 获取Response
	 */
	public static HttpServletResponse getResponse() {	  
		return THREAD_LOCAL2.get();
	}
	
	// 请求到来时过滤(进行赋值)
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		THREAD_LOCAL1.set(req);
		THREAD_LOCAL2.set((HttpServletResponse)response);
		ABSOLUTEPATH=req.getSession().getServletContext().getRealPath("/");
		BASEURL=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/";	
		filterChain.doFilter(request, response);
		THREAD_LOCAL1.remove();
		THREAD_LOCAL2.remove();
		ABSOLUTEPATH="";
		BASEURL="";
	}

	// web容器初始化时
	public void init(FilterConfig arg0) throws ServletException {}
	
	// web容器销毁时结束时
	public void destroy() {}
}