package com.cssl.util;

import java.io.FileWriter;
import java.io.IOException;



public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100200645216";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "x60xtkVVD11Py8Jg81Q5htQldTMCgYEAruBpj6rReqvP6d0Gaw+PvSJNm9NhmHRTvtpYvAwJOQtVbrqtufvp8rPNM3UXkkuhaj/Sftm20DMSgfEnJkNmddmzawlCe6cBqC1s+IykKO7X7Lr27sPFdZaCDjvNdsLYn8j//E58/2GdBwP+3vOobe4Goye33LgrSatlQgfKDa0CgYAmRMPKuAIT0XGp6NgoJBRCZEZ0/XdUabl9cRZC1aQ8MDnrsL/n3SIqxLrWF5SRGLPAHRlXfs2TN2xco8DsCUccaJfghnYpSxf2GKrr/egFnlabLG6TWPpe+gDCs3vATaV2HDLqocnCUCjUWNI5Lz/2oqLa26RUJen+pVKXMYVDxQKBgQCmJU4FkhxN+RijLzwe0X0WC5ZCmxUZXEQIirhNiP3mNY9okXHmxqFh1lP6NKA9fR+Rq6tYm45lxnAqBqzUXKrnAvvEkNqaUnRvjSEAD9cTkMCTCJr9BAEBlApibYzq7XoUbUpQM13s4JfpB3HJ7yMxtjiWIHE+AbgW2QFuN6pPTQKBgQCcEHbE1rWy9vY8fU/PjrjThMwES6dhw0KNZo7p2JAZzn38xPG";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnn52mtAA5oDoi4DgBKlZZoxZqogXWdRdmz9T+u+2GonD7r7YwZ3lt5qNdl1B4mSK8cMDImKrqb5diZnszmnwTZxAaqFqNMXfCiB8gc5f069ddUyDeTX3+VmQRlQzyq72c4Z4S/50+fsWqSY+7jBAukt0F09x5EA48yUbNW0qLpO2vNAwnMzUBnWAl6RJjMvHwLppuhpGCc8tHEakroS5dt1O5v3Tw9C5h3EtgBzghbb6pLQTlZKT4vVHJJSzXYrvFAVXSZ/5upd2WWbx8J72WKLHaBQmHOGeCm8iSWJXu1ahUymE4yRQ9F30lHv6GBRDSVNLvkJKtvUIHZi9+VaxFQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

