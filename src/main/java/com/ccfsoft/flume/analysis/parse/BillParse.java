package com.ccfsoft.flume.analysis.parse;

import com.alibaba.fastjson.JSON;
import com.ccfsoft.flume.analysis.entity.Bill;
import org.apache.log4j.Logger;

/**
 * Created by muma123 on 2017/6/21.
 */
public class BillParse {
    /** log4j */
    private static Logger logger = Logger.getLogger(BillParse.class);

    /**
     *
     * @参数 消息体
     * @返回值 话单json串
     * @描述：判断数据是json还是分割符分割，分隔符分割数据转换成json
     */
    public static String billParse(String eventBody)
    {
        if(!eventBody.startsWith("[{"))
        {
            String[] bodys = eventBody.split("\\|");
            Bill bill = new Bill();
            bill.setProcessTime(bodys[0]);
            bill.setCallType(Integer.valueOf(bodys[1]));
            bill.setIMSI(bodys[2]);
            bill.setPhone(bodys[3]);
            bill.setOtherPhone(bodys[4]);
            bill.setBeginDate(bodys[5]);
            bill.setBeginTime(bodys[6]);
            bill.setEndDate(bodys[7]);
            bill.setEndTime(bodys[8]);
            bill.setTalkTime(Integer.valueOf(bodys[9]));
            bill.setCallBill(bodys[10]);
            bill.setAwt(bodys[11]);
            bill.setSWIN(bodys[12]);
            bill.setLAC(bodys[13]);
            bill.setCELLID(bodys[14]);
            bill.setOLAC(bodys[15]);
            bill.setOCELLID(bodys[16]);
            bill.setRelay(bodys[17]);
            bill.setInterval(bodys[18]);
            bill.setTOS(bodys[19]);
            bill.setTR(bodys[20]);
            bill.setDN(bodys[21]);
            bill.setStationId(bodys[22]);
            bill.setStationLat(Double.valueOf(bodys[23]));
            bill.setStationLon(Double.valueOf(bodys[24]));
            eventBody = JSON.toJSONString(bill);
        }
        return eventBody;
    }
}
