package com.example.service;

import com.example.thrift.api.HelloService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * Created by henry on 2018/7/23.
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String greet(String para) throws TException {
        return String.format("Hello %s!", para);
    }
}
