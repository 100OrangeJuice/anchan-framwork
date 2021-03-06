//package org.sakura.anchan.common;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by Anchan on 2018/5/20.
// */
//@Slf4j
//@Component
//public class ExceptionResolver implements HandlerExceptionResolver {
////    Logger log = LoggerFactory.getLogger(this.getClass());
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        log.error("{} Exception" ,request.getRequestURI(),ex);
//        ModelAndView modelAndView = new ModelAndView(new MappingJacksonJsonView());
//
//
//        modelAndView.addObject("status",ResponseCode.ERROR.getCode());
//        modelAndView.addObject("msg", "接口异常");
//        modelAndView.addObject("data", ex.toString());
//        return modelAndView;
//    }
//}
