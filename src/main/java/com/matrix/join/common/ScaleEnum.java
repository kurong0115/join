package com.matrix.join.common;

/**
 * @ClassName ScaleEnum
 * @Description 人数区间枚举
 * @Author Administrator
 * @Date 2019/11/29 14:21
 * @Version 1.0
 */
public enum ScaleEnum {
	/**
	 * 第一阶层，0-20人
	 */
	SCALE_A(1, "0-20人"),
	/**
	 * 第二阶层，20-99人
	 */
	SCALE_B(2, "20-99人"),
	/**
	 * 第三阶层，100-499人
	 */
	SCALE_C(3, "100-499人"),
	/**
	 * 第四阶层，500-999人
	 */
	SCALE_D(4, "500-999人"),
	/**
	 * 第五阶层，1000-9999人
	 */
	SCALE_E(5, "1000-9999人"),
	/**
	 *  第六阶层，10000人以上
	 */
	SCALE_F(6, "10000人以上");

	/**
	 * 层级编码
	 */
	private Integer code;

	/**
	 * 层级描述信息
	 */
	private String scale;

	/**
	 * 私有构造方法
	 * @param code
	 * @param scale
	 */
	private ScaleEnum(Integer code, String scale){
		this.code = code;
		this.scale = scale;
	}

	/**
	 * 根据编码获取到对应的描述信息
	 * @param code
	 * @return
	 */
	public static String getScale(Integer code){
		ScaleEnum[] enums = ScaleEnum.values();
		String scale = null;
		for (ScaleEnum scaleEnum: enums){
			if (code.equals(scaleEnum.code)){
				scale =  scaleEnum.scale;
				break;
			}
		}
		return scale;
	}

	/**
	 * 根据信息获取编码
	 * @param scale
	 * @return
	 */
	public static Integer getCode(String scale){
		Integer code = null;
		ScaleEnum[] enums = ScaleEnum.values();
		for (ScaleEnum scaleEnum: enums){
			if (scale.equals(scaleEnum.scale)){
				code =  scaleEnum.code;
				break;
			}
		}
		return code;
	}
}
