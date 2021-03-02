package io.github.itliwei.vboot.vorm;


import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.VService;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.vboot.vorm.orm.opt.QueryModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author : liwei
 * @date : 2019/05/13 09:19
 */
public class BaseService<T> {
    @Autowired
    private VService vService;

    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public <T extends IdEntity> int save(T entity){
        return vService.save(entity);
    }

    public <T extends IdEntity> int  update(T entity){
        return vService.update(entity);
    }

    public <T extends IdEntity> int update(T entity,boolean useNull){
        return vService.update(entity,useNull);
    }

    public <T extends IdEntity> T findById(Long id){
        return vService.find((Class<T>)getTClass(),id);
    }

    public <T extends IdEntity> T findOne(QueryModel queryModel){
        return vService.find((Class<T>) this.getTClass(), queryModel);
    }


    public <T extends IdEntity> List<T> findList(QueryModel queryModel){
        return vService.findList((Class<T>) this.getTClass(), queryModel);
    }

    public <T extends IdEntity> Page<T> findPage(QueryModel queryModel){
        return vService.findPage((Class<T>) this.getTClass(), queryModel);
    }

    public <T extends IdEntity> int delete(Long id){
        return vService.delete((Class<T>)getTClass(),id);
    }

    public <T extends IdEntity> int deleteBatch(List<Long> ids){
        return vService.delete((Class<T>)getTClass(),ids);
    }

    public <T extends IdEntity> int delete(QueryModel queryModel){
        return vService.delete((Class<T>)getTClass(),queryModel);
    }

    public <T extends IdEntity> long count(QueryModel queryModel){
        return vService.count((Class<T>)getTClass(),queryModel);
    }
}