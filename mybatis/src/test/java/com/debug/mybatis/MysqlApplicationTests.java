package com.debug.mybatis;

import com.debug.mybatis.mapper.OperateDatabaseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlApplicationTests {

    @Autowired
    private OperateDatabaseMapper mapper;

    @Test
    public void test() {
        drop();
    }

    private void create() {
        mapper.createDatabase("my_db");
    }

    private void drop() {
        mapper.dropDatabase("my_db");
    }

}
