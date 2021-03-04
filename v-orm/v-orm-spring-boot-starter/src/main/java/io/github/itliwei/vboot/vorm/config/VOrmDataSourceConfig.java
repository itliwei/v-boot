package io.github.itliwei.vboot.vorm.config;


import io.github.itliwei.vboot.vorm.orm.VOrmConfig;
import io.github.itliwei.vboot.vorm.orm.VService;
import io.github.itliwei.vboot.vorm.orm.mapper.VMapper;
import io.github.itliwei.vboot.vorm.orm.plugins.SkipInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by liw411 on 2019/2/25 0025.
 */

@Configuration
public class VOrmDataSourceConfig {
    @Autowired
    private MybatisProperties properties;

    @Bean
    public SqlSessionFactory vormSqlSessionFactory(DataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(druidDataSource);
        factoryBean.setPlugins(new Interceptor[]{new SkipInterceptor()});
        factoryBean.setConfiguration(properties.getConfiguration());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:VMapper.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public VMapper vMapper(SqlSessionFactory vormSqlSessionFactory) throws Exception {
        MapperFactoryBean<VMapper> mfb = new MapperFactoryBean<VMapper>();
        mfb.setMapperInterface(VMapper.class);
        mfb.setSqlSessionFactory(vormSqlSessionFactory);
        return mfb.getObject();
    }

    @Bean
    public VOrmConfig vOrmConfig(VMapper vMapper) throws Exception {
        VOrmConfig cormConfig = new VOrmConfig();
        cormConfig.addDefaultMasterMapper(vMapper);
        cormConfig.addDefaultSlaveMapper(vMapper);
        return cormConfig;
    }

    @Bean(name = "vService")
    public VService cService(VMapper vMapper) throws Exception {
        return new VService(vMapper);
    }

}
