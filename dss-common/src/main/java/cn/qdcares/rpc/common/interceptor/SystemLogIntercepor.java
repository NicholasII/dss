/**
 * Copyright 2002-2013 Qingdao Civil Aviation Cares Co., LTD
 * All Rights Reserved.
 * 2013年11月20日 下午7:05:35 Administrator
 */
package cn.qdcares.rpc.common.interceptor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * @author bxy 监听系统日志
 */
public class SystemLogIntercepor {
	Logger logger = LogManager.getLogger(getClass());
	ThreadLocal<Long> timeSpend = new ThreadLocal<>();
	
	public void doAfterThrow(JoinPoint joinPoint, Throwable ex)
			throws Throwable {
		logger.error(getMessage(joinPoint, "fail", timeSpend.get(), System.currentTimeMillis()), ex);
		throw ex;
	}

	public void doBefore(JoinPoint joinPoint) {
		timeSpend.set(System.currentTimeMillis());
	}

	public void doAfterReturn(JoinPoint joinPoint) {
		logger.info(getMessage(joinPoint, "success", timeSpend.get(), System.currentTimeMillis()));
	}

	private String getMessage(JoinPoint joinPoint, String status, long beginTime, long endTime) {

		Object target = joinPoint.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		String methodName = joinPoint.getSignature().getName();
		// 拦截的方法参数
		String args = "";

		Object[] argsObj = joinPoint.getArgs();
		if (argsObj == null || argsObj.length == 0) {
			args = "()";
		} else {
			for (int i = 0; i < argsObj.length; i++) {
				Object object = argsObj[i];
				if (i == 0) {
					args += "(";
				}
				args += (object == null ? "null" : object.toString());
				args += "|";

				if (i == argsObj.length - 1) {
					args = args.substring(0, args.length() - 1);
					args += ")";
				}
			}
		}

		if (joinPoint.getSignature().getName().equals("enter")) {
			args = "(***)";
		}
		String message = target.getClass().getSimpleName() + " " + methodName + " " + args + " "
				+ (endTime - beginTime) + " " + status;
		return message;
	}
}
