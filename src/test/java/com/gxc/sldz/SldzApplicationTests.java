package com.gxc.sldz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SldzApplicationTests {

    @Test
    void contextLoads() {

        int rest= 0;
        int days=30;
        for (int i=1;i<days;i++){
            double l = 1.2*i-1;
            System.out.println("每天"+l);
            rest += l;
            System.out.println(rest);
        }
    }

}
