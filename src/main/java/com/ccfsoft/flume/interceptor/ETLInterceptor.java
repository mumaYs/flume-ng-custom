package com.ccfsoft.flume.interceptor;
import com.ccfsoft.flume.analysis.parse.BillParse;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 数据ETL拦截器:(json、分隔符分割数据)
 */
public class ETLInterceptor implements
        Interceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(ETLInterceptor.class);

    static final String EXTRACTOR_HEADER_KEY = "extractorHeaderKey";
    private final String extractorHeaderKey;

    private ETLInterceptor(String extractorHeaderKey) {
        this.extractorHeaderKey = extractorHeaderKey;
    }

    public void initialize() {
    }

    public void close() {
        // NO-OP...
    }

    public Event intercept(Event event) {
        String tmpHeadStr;
        String body = null;
//        //获取头信息,做数据分类
//        Map<String, String> headers = event.getHeaders();
//        tmpHeadStr = headers.get(extractorHeaderKey);
//
//        if ("H3C".equals(tmpHeadStr)) {
//            body = getH3CReport(event.getBody());
//        } else if ("SKSPRUCE".equals(tmpHeadStr)) {
//            body = getSkspruceReport(event.getBody());
//        }

        try {
            //直接通过数据格式分类
            body = BillParse.billParse(new String(event.getBody(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (body == null) {
            return null;
        } else {
//            logger.info(String.format(
//                    "结果:%s", body));
            event.setBody(body.getBytes());
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
            return new ETLInterceptor(extractorHeaderKey);
        }
    }
}
