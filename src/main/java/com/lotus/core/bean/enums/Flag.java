package com.lotus.core.bean.enums;

/**
 * @author wyy
 */
public enum Flag {

    Y("是", 1),
    N("否", 0);

    Flag(String text, int value){
    	this.text = text;
    	this.value = value;
    }
    

    private String text;

    private int value;
    
    

    /**
	 * 返回成员属性 text
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 将传入参数text 赋给成员属性 text
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 返回成员属性 value
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 将传入参数value 赋给成员属性 value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
