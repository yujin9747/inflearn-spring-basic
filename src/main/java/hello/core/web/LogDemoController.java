package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    //    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    private final MyLogger myLogger;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL =
                request.getRequestURL().toString(); // intercepter나 filter에서 처리하는 것이 더 좋다. 스스로 공부 해보기.
//        MyLogger myLogger = myLoggerObjectProvider.getObject();
        System.out.println("myLogger.getClass() = " + myLogger.getClass()); // spring이 myLogger를 덮어씌워서 만든 가짜 프록시 객체(CGLIB)
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
