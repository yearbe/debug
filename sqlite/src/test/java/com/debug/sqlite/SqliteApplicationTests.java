package com.debug.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.debug.sqlite.database.DatabaseContextHolder;
import com.debug.sqlite.database.DatabaseKey;
import com.debug.sqlite.mapper.DictMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqliteApplicationTests {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<Connection> connections = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private DictMapper dictMapper;

    @Test
    public void testDatabase() {
        Set<String> keys = DatabaseKey.getKeys();
        for (String key : keys) {
            System.out.println(key);
            DatabaseContextHolder.setDatabaseType(key);
            System.out.println(dictMapper.selectAll());
        }
    }

    @Test
    public void test() {
        long startTime = System.currentTimeMillis();

        int count = 3;
        int dataCount = 5;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            createTable(i + 1, dataCount, latch);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            queryTable(i, latch);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        logger.info("操作总耗时：{}分", (endTime - startTime) / 1000 / 60);
    }

    private void queryTable(int i, CountDownLatch latch) {
        executorService.execute(() -> {
            long start = System.currentTimeMillis();
            try {
                Connection connection = connections.get(i);
                Statement stmt = connection.createStatement();
                String sql = "select * from dict";
                ResultSet rs = stmt.executeQuery(sql);
                int index = 0;
                while (rs.next()) {
                    index++;
                    logger.info("===============" + index + "===============");
                    int id = rs.getInt("id");
                    String key = rs.getString("key");
                    String value = rs.getString("value");
                    logger.info("ID = {}，KEY = {}， VALUE = {}", id, key, value);
                    logger.info("===============" + index + "===============");
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();
            logger.info("查询表数据{}耗时：{}毫秒", i + 1, end - start);
            latch.countDown();
        });
    }

    private void createTable(int index, int dataCount, CountDownLatch latch) {
        executorService.execute(() -> {
            long start = System.currentTimeMillis();
            Connection c = null;
            try {
                Statement stmt = null;
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:data/test/test" + index + ".db");
//            logger.info("{}: Opened database successfully", index);

                stmt = c.createStatement();
                String sql = "create table dict " +
                        "(id integer primary key  autoincrement  not null," +
                        " key varchar(64)   not null, " +
                        " value text)";
                stmt.executeUpdate(sql);
//            logger.info("{}: Create table successfully", index);

                for (int j = 0; j < dataCount; j++) {
                    sql = "insert into dict (key, value) values ('key_" + index + "_" + j + "', 'value_" + index + "_" + j + "')";
                    stmt.executeUpdate(sql);
                    logger.info("{}: 数据{}插入成功", index, j);
                }
//            logger.info("{}: Insert data successfully", index);

                stmt.close();
                // c.close();
                connections.add(c);
            } catch (Exception e) {
                logger.error("创建表失败", e);
            }
            long end = System.currentTimeMillis();
            logger.info("{}: 创建表插入数据耗时：{}秒", index, (end - start) / 1000);
            latch.countDown();
        });
    }
}
