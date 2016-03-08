package cn.demoncat.assist.exception;

/**
 * 自定义异常：项目中通常针对业务可能出现的问题，自定义异常类来封装异常提示信息，然后进行相应的业务性处理。
 * 用途：在dao、service、controller中抛出的异常转换为自定义异常并抛出，最后在统一异常处理器中处理，而未知/未处理异常则需排除。
 */
public class CustomException extends Exception {
 
	private static final long serialVersionUID = 2485716221904131790L;
	//异常信息
	private String message;
	
	public CustomException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
