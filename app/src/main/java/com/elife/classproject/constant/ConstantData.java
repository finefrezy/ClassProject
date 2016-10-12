package com.elife.classproject.constant;

/**
 * Created by tzhang on 2016/9/8.
 */
public class ConstantData {

    public static final String SHARED_FILE_NAME = "shared";
    public static final String SHARED_FILE_NAME1 = "shared_file";


    public static final String BORAD_SELF_ACTION = "com.elife.my.BROAD";

    public static final String BASE_URL = "http://10.50.8.79:8088";
    /**
     * 自定义广播的测试
     *
     * 广播是全局的，只要在应用中注册了相应的广播，当又该广播发送时，就会接收到
     * 可以通过其中的intent进行传值
     */
    public static final String TEST_BROAD_ACTION = "com.elife.classproject.SELF_BROADCAST";

}
