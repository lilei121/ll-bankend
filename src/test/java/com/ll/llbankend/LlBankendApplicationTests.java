package com.ll.llbankend;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;


@SpringBootTest
class LlBankendApplicationTests {

    @Test
    void contextLoads() {
        String md5Digest = DigestUtils.md5DigestAsHex("wesdfds".getBytes(StandardCharsets.UTF_8));
        System.out.println(md5Digest);
    }

}
