package io.github.itliwei.vboot.vorm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import io.github.itliwei.vboot.vorm.response.Resp;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.util.PageBuilder;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.vboot.vorm.entity.TestEntity;
import io.github.itliwei.vboot.vorm.service.TestService;
import io.github.itliwei.vboot.vorm.query.TestQueryModel;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import javax.validation.Valid;



import io.github.itliwei.vboot.vorm.vo.TestVo;
import io.github.itliwei.vboot.vorm.vo.TestDto;

@Slf4j
@RestController
@RequestMapping("api/user")
@Api(tags = "用户接口",description = "用户接口")
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "根据ID查找",httpMethod = "GET")
    public Resp<TestVo> getById(@PathVariable long id) {
        TestEntity result = testService.findById(id);
        if (result != null){
            TestVo testEntityVo =  TestVo.convert2TestVo(result);
            return Resp.success(testEntityVo);
        }
        return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
    }


    @PostMapping("/page/query")
    @ApiOperation(value = "分页查找内容",httpMethod = "POST")
    public Resp<Page<TestVo>> pageQuery(@RequestBody TestQueryModel queryModel) {
        Page<TestEntity> result = testService.findPage(queryModel);
        Page<TestVo> voPage = PageBuilder.copyAndConvert(result, TestVo::convert2TestVo);
        return Resp.success(voPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",httpMethod = "POST")
    public Resp<TestVo> save(@Valid @RequestBody TestDto testEntityDto) {
        TestEntity entity = testEntityDto.convert2TestEntity();
        int result = testService.save(entity);
        if (result > 0){
            TestVo testEntityVo =  TestVo.convert2TestVo(entity);
            return Resp.success(testEntityVo);
        }
        return Resp.error(ErrorCode.SERVER,"保存数据失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改",httpMethod = "POST")
    public Resp update(@Valid @RequestBody TestDto testEntityDto) {
        TestEntity entity = testEntityDto.convert2TestEntity();
        int result = testService.update(entity);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"修改数据失败");
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除",httpMethod = "GET")
    public Resp delete(@PathVariable long id) {
        int result = testService.delete(id);
        if (result > 0) {
            return Resp.success();
        }
        return Resp.error(ErrorCode.SERVER,"删除数据失败");
    }


}