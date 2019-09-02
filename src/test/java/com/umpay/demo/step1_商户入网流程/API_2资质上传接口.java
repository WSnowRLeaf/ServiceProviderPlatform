package com.umpay.demo.step1_商户入网流程;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class API_2资质上传接口 extends API_2资质上传接口parent {
	
	
	public static void main(String[] args) throws Exception {
		API_2资质上传接口 group = new API_2资质上传接口();
		//自然人
		group.test_natPerson();
		//个休
		//group.test_unit();
	}
	/**
	 * @throws Exception 
	 * @Description: 自然人 流程
	 * @date 2019年8月16日 下午3:20:58
	 */
	@Test
	public void test_natPerson() throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("acqSpId", "Y471790403");
		paramMap.put("merId", "M2019082700000348");
//		paramMap.put("merId", "M2019080800000067");
		
		//1、上传身份证正面
		test_idCardFront(paramMap);
		//2、上传身份证反面
		test_idCardBack(paramMap);
		//3、签约授权书
		test_signAuthLetterPhoto(paramMap);
		//4、上传银行卡正面
		test_bankCardPhotoFront(paramMap);
		//5、上传银行卡反面
		test_bankCardPhotoBack(paramMap);
		//6、签约授权书
		test_signAuthLetterPhoto(paramMap);
		//门店门头照
		test_storeHeadPhoto(paramMap);
		//门店外景照
		test_storeShopPhoto(paramMap);
		//门店内景照
		test_storeHallPhoto(paramMap);
		//门店收银台照
		test_storeCashierPhoto(paramMap);
	}
	
	/**
	 * @throws Exception 
	 * @Description: 个体+企业商户 流程
	 * @date 2019年8月16日 下午3:20:58
	 */
	@Test
	public void test_unit() throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("acqSpId", "Y471790403");
		paramMap.put("merId", "M2019082700000348");
		
		//1、上传身份证正面
		test_idCardFront(paramMap);
		//2、上传身份证反面
		test_idCardBack(paramMap);
		//3、签约授权书
		test_signAuthLetterPhoto(paramMap);
		//4、上传银行卡正面
		test_bankCardPhotoFront(paramMap);
		//5、上传银行卡反面
		test_bankCardPhotoBack(paramMap);
		//6、营业执照照片
		test_businessLicensePhoto(paramMap);
	}
}
