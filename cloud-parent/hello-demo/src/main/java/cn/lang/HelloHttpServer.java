package cn.lang;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.tomcat.util.net.IPv6Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import sun.net.util.IPAddressUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EnableAutoConfiguration
@RestController
public class HelloHttpServer {
    @Autowired
    private MeterRegistry meterRegistry;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/date")
    public String printDate() throws UnknownHostException {
        String port = System.getProperty("server.port");
        logger.info("hello");
        meterRegistry.counter("ts_pv").increment();
//        try {
//            if (true){
//                throw new RuntimeException(new RuntimeException());
//            }
//        }catch (Exception e){
//            logger.error("", e);
//        }
        return port+"  :  "+LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @RequestMapping(value="metrics",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String metrics() throws UnknownHostException {
//        return  ((PrometheusMeterRegistry) meterRegistry).scrape();
        return "# HELP ts_pv_total  \n" +
                "# TYPE ts_pv_total counter\n" +
                "ts_pv_total "+i+".0";
    }

    public static int i = 0;

    @RequestMapping(value="doSet",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String doSet(@RequestParam("int") int x) throws UnknownHostException {
        i = x;
        return i+"";
    }


    public static final Pattern pattern = Pattern.compile("ts_pv_total [0-9]+.0");



    public static void main(String[] args) {
        SpringApplication.run(HelloHttpServer.class, args);
    }
}