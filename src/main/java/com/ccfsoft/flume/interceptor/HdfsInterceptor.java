package com.ccfsoft.flume.interceptor;
import com.alibaba.fastjson.JSONObject;
import com.ccfsoft.utils.PropertyConstants;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 映射数据类型和Hdfs路径的拦截器
 */
public class HdfsInterceptor implements
        Interceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(HdfsInterceptor.class);

    /** 配置文件的全路径*/
    static final String PROPERTY_FILE_PATH = "propertyFilePath";
    private final String propertyFilePath;

    /** hdfs目录映射*/
    private static Map<String,String> hdfsMap = new HashMap<>();

    private HdfsInterceptor(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
    }


    /**
     * 通过配置文件初始化加载hdfs映射
     */
    public void initialize() {
        Iterator<String> it= PropertyConstants.getFullPathProperties(propertyFilePath).stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            hdfsMap.put(key,PropertyConstants.getPropertiesKey(key));
        }
    }

    public void close() { }

    public Event intercept(Event event) {
        String body;
        try {
            //Base64解码事件内容
            String originalStr = new String(event.getBody(),"UTF-8").trim();
            body = new String(Base64.getDecoder().decode(originalStr)).trim();

            //格式是否为:{ "data":"数据内容" , "datatype":"数据类型" }
            if(body.startsWith("{") && body.endsWith("}") && body.contains("data") && body.contains("datatype")){
                Map eventBody = JSONObject.parseObject(body);
                Map<String,String> head = new HashMap<>();
                String dataType = eventBody.get("datatype").toString();

                //hdfs路径不能为空
                String hdfsDir = hdfsMap.get(dataType);
                if(hdfsDir != null)
                {
                    head.put("dataType",dataType);
                    head.put("hdfsDir",hdfsDir);
                    event.setHeaders(head);
                    event.setBody(eventBody.get("data").toString().getBytes());
                }else{
                    event.setBody(null);
                    logger.warn(String.format("此数据类型:%s,没有响应的HDFS目录需要在data-hdfs.properties添加配置",dataType));
                }
            }else{
                event.setBody(null);
                logger.warn(String.format("此数据内容:%s不是指定的json格式",body));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        List<Event> intercepted = Lists.newArrayListWithCapacity(events.size());
        for (Event event : events) {
            Event interceptedEvent = intercept(event);
            if (interceptedEvent != null) {
                intercepted.add(interceptedEvent);
            }
        }
        return intercepted;
    }

    public static class Builder implements Interceptor.Builder {
        private String propertyFilePath;

        public void configure(Context context) {
            propertyFilePath = context.getString(PROPERTY_FILE_PATH);
            Preconditions.checkArgument(
                    !StringUtils.isEmpty(propertyFilePath),
                    "必须指定配置文件的全路径");
        }

        public Interceptor build() {
            logger.info(String.format(
                    "Creating HdfsInterceptor: propertyFilePath=%s",
                    propertyFilePath));
            return new HdfsInterceptor(propertyFilePath);
        }
    }
}
