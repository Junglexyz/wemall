/*
package com.jungle.wemall.db;

import com.jungle.wemall.db.util.FastJsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

*/
/**
 * @description: fastJson测试
 * @author: jungle
 * @date: 2020-02-25 00:11
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FastJsonUtilTest {
    @Test
    public void getString(){
        String body = "{\"orderId\":1001,\"userId\":10001}";
        String key = FastJsonUtil.getString(body, "orderId");
        System.out.println(key);
    }

    @Test
    public void getList(){
        String body = "{\"orders\":{\"orderId\":1001,\"userId\":10001},\"list\":[{\"orderId\":1001,\"userId\":10001},{\"orderId\":1002,\"userId\":10002}]}";
        List list = FastJsonUtil.getList(body, "list");
        System.out.println(list.get(0));
    }

    @Test
    public void getMap(){
        String body = "{\"orders\":{\"orderId\":1001,\"userId\":10001},\"list\":[{\"orderId\":1001,\"userId\":10001},{\"orderId\":1002,\"userId\":10002}]}";
        Map map = FastJsonUtil.getMap(body, "orders");
        System.out.println(map.get("orderId"));
    }
}
*/
