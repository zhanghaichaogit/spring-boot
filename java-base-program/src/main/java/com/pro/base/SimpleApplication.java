package com.pro.base;

/**
 * 测试打包使用
 */
public class SimpleApplication {
    public String ddd() {
        return "qqqqqq";
    }

    public static void main(String[] args){
        test(null);

        Object obj=null;
        test(obj);
    }

    static void test(Object ...params){
        System.out.println(params);
    }

}
