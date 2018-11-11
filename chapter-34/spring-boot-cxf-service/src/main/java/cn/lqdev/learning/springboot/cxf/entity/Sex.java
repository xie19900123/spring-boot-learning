package cn.lqdev.learning.springboot.cxf.entity;

/**
 * 性别枚举类
 * @author oKong
 *
 */
public enum Sex {

	MALE("male"),
	FEMALE("female");
	
	String value;
	
	Sex(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

	public static Sex fromValue(String v) {
		for (Sex c : Sex.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}	
}
