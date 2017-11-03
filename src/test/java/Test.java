import com.alibaba.fastjson.JSONObject;
import com.ccfsoft.utils.PropertyConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muma on 2017.3.1.
 */
public class Test {
    private static Map<String,String> hdfsMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
////        String eventBody = "2015-12-31 00:39:02|0|4600318154981212|18154981212|13627551145|2015-12-31|00:39:01|2015-12-31|00:42:27|204|||||||||||||460001583|31.892155661086584|118.57032141470845| ";
////        String[] bodys = eventBody.split("\\|");
////        System.out.print(bodys.length);
//        Iterator<String> it= PropertyConstants.getProperties().stringPropertyNames().iterator();
//        while(it.hasNext()){
//            String key=it.next();
//            hdfsMap.put(key,PropertyConstants.getPropertiesKey(key));
//        }
//        String jsonStr = "{ \"data\":\"数据内容\" , \"datatype\":\"test\" }";
//
//
//        Map eventBody = JSONObject.parseObject(jsonStr);
//        Map<String,String> head = new HashMap<>();
//        String dataType = eventBody.get("datatype").toString();
//        head.put("dataType",dataType);
//        head.put("hdfsDir",hdfsMap.get(dataType));
//        System.out.println(hdfsMap.get(dataType));
//
//
////        for(java.util.Map.Entry<String,Object> entry: JSONObject.parseObject(jsonStr).entrySet()){
////            System.out.print(entry.getKey()+"-"+entry.getValue()+"\t");
////        }
    }
}
