package io.github.itliwei.vboot.vorm.query;

import java.io.Serializable;

import io.github.itliwei.vboot.vorm.orm.opt.QueryModel;
import io.swagger.annotations.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TestQueryModel", description = "TestQueryModel")
public class TestQueryModel  extends QueryModel implements Serializable {
	private Long idEQ;
	@ApiModelProperty(value ="姓名")
	private String usernameEQ;
	@ApiModelProperty(value ="姓名")
	private List<String> usernameIN;
	@ApiModelProperty(value ="jobId",example="0")
	private Long jobIdEQ;
	@ApiModelProperty(value ="jobId",example="0")
	private List<Long> jobIdIN;
	@ApiModelProperty(value ="年龄",example="0")
	private Integer ageEQ;
	@ApiModelProperty(value ="年龄",example="0")
	private List<Integer> ageIN;
	@ApiModelProperty(value ="地址")
	private String addressEQ;
	@ApiModelProperty(value ="地址")
	private List<String> addressIN;


}