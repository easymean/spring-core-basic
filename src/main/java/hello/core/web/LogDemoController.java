package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest req) throws InterruptedException {
        String requestURL = req.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject(); // 이 시점에 MyLogger 가 생성된다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(2000);
        logDemoService.logic("testId");
        return "OK";
    }
}
