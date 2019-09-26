package com.example.service;

import com.example.AbstractTest;
import com.example.thrift.api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by henry on 2018/7/23.
 */
@Slf4j
public class HelloServiceTest extends AbstractTest {
    @Autowired
    private HelloService.Iface helloService;

    @Test
    public void testLocal() {
        try {
            log.info("本地调用服务...{}", helloService.greet("Local"));
        } catch (TException e) {
            log.error("本地调用异常.", e);
        }
    }

    @Test
    public void testRemote() {
        try (TTransport transport = new TSocket("localhost", 9898, 30000)) {
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloService.Client helloService = new HelloService.Client(protocol);
            transport.open();
            log.info("远程调用服务...{}", helloService.greet("Remote"));
        } catch (TException e) {
            log.error("远程调用异常.", e);
        }
    }
}
