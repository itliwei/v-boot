package io.github.itliwei.vboot.vtool.trend;

import io.github.itliwei.vboot.vtool.trend.vo.Result;

import java.util.List;

/**
 * @author : liwei
 * @date : 2021/03/02 20:48
 * @description : 分析工具
 */
public interface TrendTool {
    /**
     * 分析数据
     */
    List<Result> analyseData(double[] data);


    /**
     * 预估数据
     * @return
     */
    List<Result> predictData(double[] data);

}
