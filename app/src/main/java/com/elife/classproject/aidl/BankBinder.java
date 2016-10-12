package com.elife.classproject.aidl;

import android.os.Binder;

import java.util.UUID;

/**
 * Created by tzhang on 2016/10/11.
 */
public class BankBinder extends Binder implements IBank{

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
