package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.constant.LogEnumLevel;
import com.example.demo.constant.SnbLogFormat;


public class LogUtils {

    /**
     * 获取Logger
     *
     * @param clazz
     * @return
     */
    public static Logger get(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     *debug
     * @param clazz
     * @param msg
     */
    public static void debug(Class<?> clazz,String msg){
        LogUtils.get(clazz).debug(msg);
    }

    /**
     * info
     * @param clazz
     * @param msg
     */
    public static void info(Class<?> clazz,String msg){
        LogUtils.get(clazz).info(msg);
    }

    /**
     * error
     * @param clazz
     * @param msg
     */
    public static void error(Class<?> clazz,String msg,Object ... args){
        LogUtils.get(clazz).error(msg,args);
    }

    /**
     * unifLog(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     *
     * @param cls
     * @param level
     * @param methodName  方法名
     * @param serilNo     流水号
     * @param logCategory 日志类型
     * @param logMsg      日志内容
     *                    void
     * @throws
     * @since 1.0.0
     */
    public static void unifLog(Class<?> cls, int level, String methodName, String serilNo, String logCategory, String logMsg) {
        String log = SnbLogFormat.logFormat(methodName, serilNo, logCategory, logMsg);
        if (level == LogEnumLevel.DEBUG.getValue()) {
            debug(cls, log);
        } else if (level == LogEnumLevel.INFO.getValue()) {
            info(cls, log);
        } else if (level == LogEnumLevel.ERROR.getValue()) {
            error(cls, log);
        }
    }

    public static void main(String[] args) {
        LogUtils.get(LogUtils.class).info("info");
        LogUtils.get(LogUtils.class).error("error");
    }

}
