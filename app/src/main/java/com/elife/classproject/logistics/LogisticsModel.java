package com.elife.classproject.logistics;

import java.util.List;

/**
 * Created by tzhang on 2015/12/2.
 *
 */
public class LogisticsModel {
    public String resultcode;
    public String reason;
    public LogisticsDetail result;
}

class LogisticsDetail {
    public String company;
    public String com;
    public String status;
    public String no;
    public List<PointDetail> list;
}

class PointDetail{
    public String datetime;
    public String remark;
    public String zone;
}
