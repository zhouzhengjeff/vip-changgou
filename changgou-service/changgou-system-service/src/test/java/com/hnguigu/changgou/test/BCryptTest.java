package com.hnguigu.changgou.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void test() {
        System.out.println(BCrypt.gensalt());

        // 生成密码
        String password = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(password);

        // 检测
        boolean flag = BCrypt.checkpw("123456", password);
        System.out.println(flag);
    }
}
