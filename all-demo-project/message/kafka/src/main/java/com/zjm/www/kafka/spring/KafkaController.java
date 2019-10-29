package com.zjm.www.kafka.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * http://localhost/kafka/send?value=hello
     * @param value
     * @return
     */
    @RequestMapping(value = "/send" ,  method = RequestMethod.GET)
    public String send(@RequestParam("value") String value) throws Exception{
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("heijue-topic", "key","1222");
        return JSON.toJSONString(result.get());
    }
}
