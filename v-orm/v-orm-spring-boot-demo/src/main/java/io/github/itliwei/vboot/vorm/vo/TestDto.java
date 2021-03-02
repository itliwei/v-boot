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
@ApiModel(value = "TestDto", description = "TestDto")
public class TestDto implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "姓名")
	private String username;
	/* 扩展 */

	/* 转换 */
	public TestEntity convert2TestEntity(){
		TestDtoConvert convert = new TestDtoConvert();
		TestEntity target = convert.convert(this);
		return target;
	}


	private static class TestDtoConvert extends Converter<TestDto, TestEntity> {
		@Override
		protected TestEntity doForward(TestDto source) {
			TestEntity target = new TestEntity();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected TestDto doBackward(TestEntity source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}