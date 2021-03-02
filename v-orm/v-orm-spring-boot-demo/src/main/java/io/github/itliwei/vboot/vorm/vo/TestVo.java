package io.github.itliwei.vboot.vorm.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.vboot.vorm.entity.TestEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TestVo", description = "TestVo")
public class TestVo implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "姓名")
	private String username;
	@ApiModelProperty(value = "jobId")
	private Long jobId;
	@ApiModelProperty(value = "年龄")
	private Integer age;
	@ApiModelProperty(value = "地址")
	private String address;
	/* 扩展 */

	/* 转换 */

	public static TestVo convert2TestVo(TestEntity source){
		TestVoConvert convert = new TestVoConvert();
		return  convert.reverse().convert(source);
	}


	private static class TestVoConvert extends Converter<TestVo, TestEntity> {
		@Override
		protected TestEntity doForward(TestVo source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected TestVo doBackward(TestEntity source) {
		TestVo target = new TestVo();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}