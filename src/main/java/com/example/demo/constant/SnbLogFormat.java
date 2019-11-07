package com.example.demo.constant;


public class SnbLogFormat {


    /**
     * buffer
     * logFormat(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     * @param methodName
     * @param serilNo
     * @param logCategory
     * @param logMsg
     * @return
     *String
     * @exception
     * @since  1.0.0
     */
    public static String logFormat(String methodName,String serilNo,String logCategory,String logMsg){
        StringBuffer buffer=new StringBuffer();
        buffer.append("[")
                .append(methodName)
                .append("][")
                .append(serilNo)
                .append("][")
                .append(logCategory)
                .append("][")
                .append(logMsg)
                .append("]");
        return buffer.toString();
    }


}
