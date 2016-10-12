package com.elife.classproject.aidl;

import java.util.UUID;

/**
 * Created by tzhang on 2016/10/11.
 * 服务端
 */
public class BankBinder extends IBankAidl.Stub{

    @Override
    public String openAccount(String name, String pwd) {
        return name + " open success, account " + UUID.randomUUID().toString() ;
    }

    @Override
    public String saveMoney(int money, String account) {
        return "账户 " + account + " 存入" + money + " 单位：人民币" ;
    }

    @Override
    public String takeMoney(int money, String account, String pwd) {
        return "账户 " + account + " 支取" + money + " 单位：人民币" ;
    }

    @Override
    public String closeAccount(String account, String pwd) {
        return account + "销户成功";
    }
}
