package com.ccfsoft.flume.interceptor;
import com.alibaba.fastjson.JSONObject;
import com.ccfsoft.utils.PropertyConstants;
import com.google.common.collect.Lists;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 映射数据类型和Hdfs路径的拦截器
 */
public class HdfsInterceptor implements
        Interceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(HdfsInterceptor.class);

    static final String EXTRACTOR_HEADER_KEY = "extractorHeaderKey";
    private final String extractorHeaderKey;

    /** hdfs目录映射*/
    private static Map<String,String> hdfsMap = new HashMap<>();

    private HdfsInterceptor(String extractorHeaderKey) {
        this.extractorHeaderKey = extractorHeaderKey;
    }


    /**
     * 初始化加载hdfs映射
     */
    public void initialize() {
        Iterator<String> it= PropertyConstants.getProperties().stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            hdfsMap.put(key,PropertyConstants.getPropertiesKey(key));
        }
    }

    public void close() { }

    public Event intercept(Event event) {
        String body = null;
        try {
            //解析数据内容和数据种类: 事件{ "data":"数据内容" , "datatype":"数据类型" }
            body = new String(event.getBody(),"UTF-8");
            logger.info(String.format(
                    "结果:%s", body));
            Map eventBody = JSONObject.parseObject(body);
            Map<String,String> head = new HashMap<>();
            String dataType = eventBody.get("datatype").toString();
            head.put("dataType",dataType);
            head.put("hdfsDir",hdfsMap.get(dataType));
            event.setHeaders(head);
            event.setBody(eventBody.get("data").toString().getBytes());
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
        private String extractorHeaderKey;

        public void configure(Context context) {
            extractorHeaderKey = context.getString(EXTRACTOR_HEADER_KEY);
//            Preconditions.checkArgument(
//                    !StringUtils.isEmpty(extractorHeaderKey),
//                    "必须指定要抽取内容的header key");
        }

        public Interceptor build() {
            logger.info(String.format(
                    "Creating StaticInterceptor: extractorHeaderKey=%s",
                    extractorHeaderKey));
            return new HdfsInterceptor(extractorHeaderKey);
        }
    }
}
