package com.javhl.course;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrompLogger {

    private PrompLogger(){

    }

    private String loggerHeader;

    private static final String INFO = "[INFO]";

    private static final String ERROR = "[ERROR]";

    public void setLoggerHeader(String loggerHeader){

        this.loggerHeader = loggerHeader;
    }

    public static PrompLogger getLogger(Class<?> clazz){

        PrompLogger logger = new PrompLogger();

        logger.setLoggerHeader(clazz.getName());

        return logger;
    }

    /**
     * 打印提示日志
     * @param logInfo   日志信息支持格式化，如：{}
     * @param objects   日志格式化参数，用于替换{}
     */
    public void info(String logInfo,Object ... objects){

       log(INFO,logInfo,objects);

    }

    /**
     * 打印错误日志
     * @param logInfo   日志信息支持格式化，如：{}
     * @param objects   日志格式化参数，用于替换{}
     */
    public void error(String logInfo,Object ... objects){

        log(ERROR,logInfo,objects);

    }

    /**
     * 打印日志
     * @param level     日志级别
     * @param logInfo   日志信息
     * @param objects   日志内容
     */
    private void log(String level,String logInfo,Object ... objects){

        if(null == logInfo || logInfo.isEmpty()) return;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss:SSS");

        String format = logInfo.replaceAll("\\{\\}","%s");

        String log = String.format(format,objects);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateFormat.format(new Date()))
                .append(" ")
                .append(loggerHeader)
                .append(level)
                .append(" ")
                .append(log);

        System.out.println(stringBuilder.toString());

    }
}
