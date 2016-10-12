package com.elife.classproject.logistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.elife.classproject.R;

import java.util.ArrayList;

public class LogisticsActivity extends AppCompatActivity {


    private ListView mListViewAddress;
    private AddressAdapter mAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        mListViewAddress = (ListView) findViewById(R.id.list_address);

        mAddressAdapter = new AddressAdapter(LogisticsActivity.this, init());
        mListViewAddress.setAdapter(mAddressAdapter);
    }


    public LogisticsModel init() {
        LogisticsModel logisticsModel = new LogisticsModel();
        logisticsModel.resultcode = "200";
        logisticsModel.reason = "查询成功!";

        LogisticsDetail logisticsDetail = new LogisticsDetail();
        logisticsDetail.com = "sf";
        logisticsDetail.no = "575677355677";
        logisticsDetail.company = "顺丰";
        logisticsDetail.status = "1";
        logisticsDetail.list = new ArrayList<PointDetail>();


        PointDetail pointDetail = new PointDetail();
        pointDetail.datetime = "2013-06-25 10:44:05";
        pointDetail.remark = "已收件";
        pointDetail.zone = "台州市";
        logisticsDetail.list.add(pointDetail);

        PointDetail pointDetail1 = new PointDetail();
        pointDetail1.datetime = "2013-06-25 11:05:21";
        pointDetail1.remark = "快件在 台州 ,准备送往下一站 台州集散中心";
        pointDetail1.zone = "台州市";
        logisticsDetail.list.add(pointDetail1);

        PointDetail pointDetail2 = new PointDetail();
        pointDetail2.datetime = "2013-06-25 20:36:02";
        pointDetail2.remark = "快件在 台州集散中心 ,准备送往下一站 台州集散中心";
        pointDetail2.zone = "台州市";
        logisticsDetail.list.add(pointDetail2);

        PointDetail pointDetail3 = new PointDetail();
        pointDetail3.datetime = "2013-06-25 21:17:36";
        pointDetail3.remark = "快件在 台州集散中心 ,准备送往下一站 杭州集散中心";
        pointDetail3.zone = "台州市";
        logisticsDetail.list.add(pointDetail3);


        PointDetail pointDetail4 = new PointDetail();
        pointDetail4.datetime = "2013-06-26 12:20:00";
        pointDetail4.remark = "快件在 杭州集散中心 ,准备送往下一站 西安集散中心";
        pointDetail4.zone = "杭州市";
        logisticsDetail.list.add(pointDetail4);

        PointDetail pointDetail5 = new PointDetail();
        pointDetail5.datetime = "2013-06-26 12:20:00";
        pointDetail5.remark = "快件在 杭州集散中心 ,准备送往下一站 西安集散中心";
        pointDetail5.zone = "杭州市";
        logisticsDetail.list.add(pointDetail5);


        PointDetail pointDetail6 = new PointDetail();
        pointDetail6.datetime = "2013-06-27 05:48:42";
        pointDetail6.remark = "正在派件..";
        pointDetail6.zone = "西安市/咸阳市";
        logisticsDetail.list.add(pointDetail6);

        PointDetail pointDetail7 = new PointDetail();
        pointDetail7.datetime = "2013-06-27 08:51:33";
        pointDetail7.remark = "快件在 杭州集散中心 ,准备送往下一站 西安集散中心";
        pointDetail7.zone = "派件已签收";
        logisticsDetail.list.add(pointDetail7);

        PointDetail pointDetail8 = new PointDetail();
        pointDetail8.datetime = "2013-06-27 08:51";
        pointDetail8.remark = "签收人是：已签收";
        pointDetail8.zone = "西安市/咸阳市";
        logisticsDetail.list.add(pointDetail8);


        logisticsModel.result = logisticsDetail;

        return logisticsModel;

    }
}
