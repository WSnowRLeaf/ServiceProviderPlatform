package com.umpay.demo.step2_入网成功配置参数;

import com.alibaba.fastjson.JSONObject;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * @author: weijieming
 * @date:2019年8月21日 上午10:37:51
 * @类说明:查询电子协议接口
 * @产品号:
 */
public class QueryElectronicAgreementAPI {

    public String queryUrl ="http://106.120.215.230:8011/entry-provider-plat-client/merchants/queryElectronicAgreement";

    @Test
    public void queryOrder() throws Exception{
        TreeMap<String, Object> reqPay = new TreeMap<String, Object>();
        reqPay.put("acqSpId", "Y471790403");//代理商编号	10	M	代理商编号(联动平台分配)
        reqPay.put("merId", "M2019082000000271");//报备编号	17	M
        reqPay.put("signature", "");

        //对请求报文做加签处理
        String reqpay = AddSign.addSign(reqPay);
        Map<String, Object> reqMap = JSONObject.parseObject(reqpay);

        //发送post请求
        String result = HttpUtilClient.doPostJson(queryUrl, new JSONObject(), reqMap);
        System.out.println("输出请求结果:"+result);

    }



}
