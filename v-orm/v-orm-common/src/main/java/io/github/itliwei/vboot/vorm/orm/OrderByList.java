package io.github.itliwei.vboot.vorm.orm;

import io.github.itliwei.vboot.vorm.orm.opt.OrderBy;

import java.util.Arrays;
import java.util.List;

/**
 * @author : liwei
 * @date : 2019/12/02 22:08
 * @description :
 */
public class OrderByList {

    public static List<OrderBy> add(OrderBy ... orderBy){
        return Arrays.asList(orderBy);
    }
}
