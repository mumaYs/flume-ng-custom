import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * Created by muma on 2017.3.1.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String eventBody = "2015-12-31 00:39:02|0|4600318154981212|18154981212|13627551145|2015-12-31|00:39:01|2015-12-31|00:42:27|204|||||||||||||460001583|31.892155661086584|118.57032141470845| ";
        String[] bodys = eventBody.split("\\|");
        System.out.print(bodys.length);
    }
}
