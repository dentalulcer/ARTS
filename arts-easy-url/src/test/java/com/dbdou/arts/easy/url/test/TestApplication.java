package com.dbdou.arts.easy.url.test;

import com.dbdou.arts.easy.url.core.SeqGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by dentalulcer
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestApplication {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SeqGenerator seqGenerator;

    @Test
    public void testGeneratorId() {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + "-" + seqGenerator.generate("A")), "t1" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + "-" + seqGenerator.generate("A")), "t2" + i).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testGenerator() throws Exception {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                MvcResult mvcResult = null;
                try {
                    mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.post("/gen/")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{\"url\":\"http://www.baidu.com\"}"))
                            .andReturn();
                    System.out.println("code=" + mvcResult.getResponse().getContentAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(10000);

    }


}
