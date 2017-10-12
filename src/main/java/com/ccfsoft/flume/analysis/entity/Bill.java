package com.ccfsoft.flume.analysis.entity;

/**
 * 话单实体
 */
public class Bill {
    /** 处理时间 */
    public String processTime;
    /** 呼叫类型 */
    public int callType;
    /** IMSI号 */
    public String IMSI;
    /** 手机号码 */
    public String phone;
    /** 对方号码 */
    public String otherPhone;
    /** 通话开始日期 */
    public String beginDate;
    /** 通话开始时间 */
    public String beginTime;
    /** 通话结束日期 */
    public String endDate;
    /** 通话结束时间 */
    public String endTime;
    /** 通话时长（秒） */
    public int talkTime;
    /** 通话计费 */
    public String callBill;
    /** 动态漫游 */
    public String awt;
    /** 交换机号 */
    public String SWIN;
    /** LAC */
    public String LAC;
    /** CELLID */
    public String CELLID;
    /** 对端LAC */
    public String OLAC;
    /** 对端CELLID */
    public String OCELLID;
    /** 中继 */
    public String relay;
    /** 时隔 */
    public String interval;
    /** 服务类型 */
    public String TOS;
    /** 终止原因 */
    public String TR;
    /** 文件号 */
    public String DN;
    /** 基站号 */
    public String stationId;
    /** 基站LAT */
    public Double stationLat;
    /** 基站LON */
    public Double stationLon;
    /** 基站地址 */
    public String stationSite;

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public String getCallBill() {
        return callBill;
    }

    public void setCallBill(String callBill) {
        this.callBill = callBill;
    }

    public String getAwt() {
        return awt;
    }

    public void setAwt(String awt) {
        this.awt = awt;
    }

    public String getSWIN() {
        return SWIN;
    }

    public void setSWIN(String SWIN) {
        this.SWIN = SWIN;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getCELLID() {
        return CELLID;
    }

    public void setCELLID(String CELLID) {
        this.CELLID = CELLID;
    }

    public String getOLAC() {
        return OLAC;
    }

    public void setOLAC(String OLAC) {
        this.OLAC = OLAC;
    }

    public String getOCELLID() {
        return OCELLID;
    }

    public void setOCELLID(String OCELLID) {
        this.OCELLID = OCELLID;
    }

    public String getRelay() {
        return relay;
    }

    public void setRelay(String relay) {
        this.relay = relay;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getTOS() {
        return TOS;
    }

    public void setTOS(String TOS) {
        this.TOS = TOS;
    }

    public String getTR() {
        return TR;
    }

    public void setTR(String TR) {
        this.TR = TR;
    }

    public String getDN() {
        return DN;
    }

    public void setDN(String DN) {
        this.DN = DN;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Double getStationLat() {
        return stationLat;
    }

    public void setStationLat(Double stationLat) {
        this.stationLat = stationLat;
    }

    public Double getStationLon() {
        return stationLon;
    }

    public void setStationLon(Double stationLon) {
        this.stationLon = stationLon;
    }

    public String getStationSite() {
        return stationSite;
    }

    public void setStationSite(String stationSite) {
        this.stationSite = stationSite;
    }
}
