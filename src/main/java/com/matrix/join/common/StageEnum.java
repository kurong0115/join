package com.matrix.join.common;

/**
 * @ClassName StageEnum
 * @Description 融资情况枚举
 * @Author Administrator
 * @Date 2019/11/29 14:33
 * @Version 1.0
 */
public enum StageEnum {

	/**
	 * 未融资
	 */
	STAGE_A(1, "未融资"),

	/**
	 * 天使轮
	 */
	STAGE_B(2, "天使轮"),

	/**
	 * A轮
	 */
	STAGE_C(3, "A轮"),

	/**
	 * B轮
	 */
	STAGE_D(4, "B轮"),

	/**
	 * C轮
	 */
	STAGE_E(5, "C轮"),

	/**
	 * D轮
	 */
	STAGE_F(6, "D轮"),

	/**
	 * 已上市
	 */
	STAGE_G(7, "已上市"),

	/**
	 * 不需要融资
	 */
	STAGE_H(8, "不需要融资");

	/**
	 * 编码
	 */
	private Integer code;

	/**
	 * 具体融资情况
	 */
	private String stage;

	/**
	 * 私有构造方法，不允许其他类来实例化一个对象
	 * @param code
	 * @param stage
	 */
	private StageEnum(Integer code, String stage){
		this.code = code;
		this.stage = stage;
	}

	/**
	 * 根据编码获取到公司的融资情况
	 * @param code
	 * @return
	 */
	public static String stage(Integer code){
		String stage = null;
		StageEnum[] enums = StageEnum.values();
		for (StageEnum stageEnum: enums){
			if (code.equals(stageEnum.code)){
				stage = stageEnum.stage;
				break;
			}
		}
		return stage;
	}
}
