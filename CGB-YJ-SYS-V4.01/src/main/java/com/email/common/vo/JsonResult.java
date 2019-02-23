package com.email.common.vo;

public class JsonResult {

	/**我们自己设置的服务端向客户端输出的状态码*/
	private int state=1;
	private String message="ok";
	/**业务层返回给控制层的具体数据*/
	private Object data;
	
	public JsonResult() {
		
	}
	
	public JsonResult(String message) {
		this.message = message;
	}
	
	public JsonResult(Object data) {
		this.data=data;
	}
	
	public JsonResult(Throwable e) {
		this.state =0;
		this.message =e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
