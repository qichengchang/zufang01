
package com.sunrui.zufang.util;

/**
 * @author Administrator
 */

public class ResponseMessage<T> {


	/* 错误码 */
	private int code;

	/* 提示信息 */
	private String msg;

	/* 具体内容 */
	private T data;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	public ResponseMessage(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResponseMessage(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
