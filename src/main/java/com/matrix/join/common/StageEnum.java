package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName StageEnum
 * @Description 融资情况枚举
 * @Author Administrator
 * @Date 2019/11/29 14:33
 * @Version 1.0
 */
public enum StageEnum {

	/**
	 *
	 */
	STAGE(0, "不限"),

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
	private StageEnum(Integer code, String stage) {
		this.code = code;
		this.stage = stage;
	}

	/**
	 * 根据编码获取到公司的融资情况
	 * @param code
	 * @return
	 */
	public static String stage(Integer code) {
		if (!Objects.isNull(code)) {
			StageEnum[] enums = StageEnum.values();
			String stage = null;
			List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.stage).collect(Collectors.toList());
			return stage = list.isEmpty() ? null : list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 获取编码
	 * @param stage
	 * @return
	 */
	public static Integer getCode(String stage) {
		if (!Objects.isNull(stage)) {
			StageEnum[] enums = StageEnum.values();
			Integer code = null;
			List<Integer> list = Arrays.stream(enums).filter(x -> stage.equals(x.stage)).map(x -> x.code).collect(Collectors.toList());
			return code = list.isEmpty() ? null : list.get(0);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		Arrays.stream(StageEnum.values()).forEach(x -> System.out.print(x.code + "为" + x.stage + "，"));
	}
}
