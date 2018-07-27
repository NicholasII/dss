package cn.qdcares.rpc.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dongqun
 * @version 1.0
 * @Description: 服务调用信息拦截器
 * @date 2018/5/31 11:20
 */
@Component
public class ServiceInvokeAop {

    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public void doAfterThrow(JoinPoint jp, Throwable ex) throws Throwable {
        logger.error(getLogMessage(), ex);
        if (ex instanceof NullPointerException) {
            throw new RuntimeException("空指针异常", ex);
        }

        Throwable nextCause = ex.getCause();
        Throwable rootCause = null;
        while (nextCause != null) {
            if (nextCause != null) {
                rootCause = nextCause;
            }
            nextCause = nextCause.getCause();
        }
        if (rootCause == null) {
            rootCause = ex;
        }

        if (rootCause != null) {

        }
    }

    public void doAfterReturn(JoinPoint jp) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    public void doBeforeInvoke(JoinPoint jp) {
        System.out.println(jp.getKind());
//		logger.info(getLogMessage());
    }

    private String getLogMessage() {
        String message = "";
        return message;
    }
}
