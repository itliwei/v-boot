package io.github.itliwei.vboot.vorm;

import io.github.itliwei.vboot.vorm.entity.TestEntity;
import io.github.itliwei.vboot.vorm.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
class VOrmSpringBootDemoApplicationTests {
    @Autowired
    private TestMapper testMapper;

    @Test
    void testMapper() {
        List<TestEntity> testEntities = testMapper.selectAll();
        Assert.isTrue(!CollectionUtils.isEmpty(testEntities),"true");
    }

}
