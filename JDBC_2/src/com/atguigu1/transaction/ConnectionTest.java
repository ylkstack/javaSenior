package com.atguigu1.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * 数据库连接类
 *
 * @Author ylkstack
 * @Date 2021/10/31 10:43
 */
public class ConnectionTest {
    @Test
    public void testGetConnertion() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }


}
