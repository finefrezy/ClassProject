// IBankAidl.aidl
package com.elife.classproject.aidl;

// Declare any non-default types here with import statements

interface IBankAidl {
    /**
        * 开户
        * @param name
        * @param pwd
        * @return 开户信息
        */
       String openAccount(String name, String pwd);

       /**
        * 存钱
        * @param money
        * @param account
        * @return 存钱信息
        */
       String saveMoney(int money, String account);

       /**
        * 取钱
        * @param money
        * @param account
        * @param pwd
        * @return 取钱消息
        */
       String takeMoney(int money, String account, String pwd);

       /**
        * 销户
        * @param account
        * @param pwd
        * @return
        */
       String closeAccount(String account, String pwd);
}
