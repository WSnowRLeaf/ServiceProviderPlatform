package com.umpay.demo.step1_商户入网流程;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.call.BaseAPI;
import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.AddSign;
import com.umpay.util.HttpUtilClient;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class API_6_7电子签约 extends BaseAPI {

	String url = EnvConfig.url;

	/**
	 * 
	 * @Description: 获取电子合约挑战码
	 * @throws Exception
	 * @return void 返回类型
	 * @author yangqr
	 * @date 2019年8月19日 上午11:41:42
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verifyCode_获取电子签约挑战码() throws Exception{
		
		TreeMap<String, Object> reqSign = new TreeMap<String, Object>();

		reqSign.put("acqSpId", EnvConfig.acqSpId);//服务商编号	10	M	服务商编号
		reqSign.put("merId", merId);//报备编号	16	M	报备编号
		
		//对请求报文做加签处理
		String reqMerinfo = AddSign.addSign(reqSign);
		Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);

		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(url+"sign/getVerifyCode", new JSONObject(), reqMap);
//			System.out.println("输出请求结果:"+ result.toString());

			//将响应报文转成map
			Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				String merId = (String) resMap.get("merId");
//				EnvConfig.context.put("merId", merId);
				EnvConfig.context.put("transCaId", (String) resMap.get("transCaId"));
				Assert.assertTrue("获取电子合约挑战码成功", true);
				
			}else{
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("获取电子合约挑战码失败：" + respMsg, false);
			}
		}catch (Exception e) {
			Assert.assertTrue("获取电子合约挑战码异常", false);
		}
	}
	/**
	 * 
	 * @Description: 电子合约验签
	 * @throws Exception
	 * @return void 返回类型
	 * @author yangqr
	 * @date 2019年8月19日 上午11:42:18
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verifyCode_电子签约确认() throws Exception{
		
		TreeMap<String, Object> reqSign = new TreeMap<String, Object>();

		reqSign.put("acqSpId", EnvConfig.acqSpId);//服务商编号	10	M	服务商编号
		reqSign.put("merId", merId);//报备编号	16	M	报备编号
		reqSign.put("transCaId", (String) EnvConfig.context.get("transCaId"));//缓存事务ID
		reqSign.put("verifyCode", (String) EnvConfig.context.get("VERIFYCODE"));//验证码
		
		//对请求报文做加签处理
		String reqMerinfo = AddSign.addSign(reqSign);
		Map<String, Object> reqMap = JSONObject.parseObject(reqMerinfo);
		try{
			//发送post请求
			String result = HttpUtilClient.doPostJson(url+"sign/doVerifyCodeSign", new JSONObject(), reqMap);
//			System.out.println("输出请求结果:"+ result.toString());

			//将响应报文转成map
			Map<String, Object> resMap = JSON.parseObject(result, TreeMap.class);
			String respCode = (String) resMap.get("respCode");
			if ("00".equals(respCode)) {
				String merId = (String) resMap.get("merId");
//				EnvConfig.context.put("merId", merId);
				//开户成功
				Assert.assertTrue("电子签约确认成功", true);
			}else{
				//开户失败
				String respMsg = (String) resMap.get("respMsg");
				Assert.assertTrue("电子签约确认失败：" + respMsg, false);
			}
		}catch (Exception e) {
			Assert.assertTrue("电子签约确认异常", false);
		}


	}
	
	public static void main(String args[]){
		try {
			new API_6_7电子签约().verifyCode_获取电子签约挑战码();
//			new API_6_7电子签约().verifyCode_电子签约确认();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
