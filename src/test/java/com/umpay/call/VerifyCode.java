package com.umpay.call;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.umpay.demo.step0_准备工作.EnvConfig;
import com.umpay.util.db.OnTestUcainfoaDBConnect;
import com.umpay.util.db.OnTestUmssfrnaDBConnect;

public class VerifyCode extends BaseAPI{

	
	
	@SuppressWarnings("unchecked")
	@Test
	public void getVerifyCode() throws Exception{
		VerifyCode doheandel=new VerifyCode();
		doheandel.查询合同编号();
		doheandel.查询验证码();
		
	}
	
	public static void main(String[] args) throws Exception {
//		EnvConfig.context.put("merId", "M2019090300000456");
//		API_6_8获取签约验证码 doheandel=new API_6_8获取签约验证码();
//		doheandel.查询合同编号();
//		
		EnvConfig.context.put("contid", "190903KUAHXAJ3E1");
		VerifyCode doheandel2=new VerifyCode();
		doheandel2.查询验证码();
		
	}
	
	public void 查询合同编号(){
		String merid= (String) EnvConfig.context.get("merId");
		String sql = "SELECT CONTID FROM UTS.T_PROVIDER_CONT_INF  WHERE MERID = '"+merid+"'";		
		System.out.println(sql);
		Connection conn = OnTestUmssfrnaDBConnect.getConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			System.out.println("查询合同编号成功，结果集为："+rs.getString(1));
			String contid = rs.getString(1);
			EnvConfig.context.put("contid", contid);
			System.out.println("查询合同编号成功，contid为："+contid);
		} catch (Exception e) {
			System.out.println("入表操作失败，SQL语句为：" + sql);
			e.printStackTrace();
		} finally {
			OnTestUmssfrnaDBConnect.release(null, pstmt, rs);
		}
	}
	
	
	public void 查询验证码(){
		String contid= (String) EnvConfig.context.get("contid");
		String sql = "select TRADENO,VERIFYCODE from umpay.T_UMPAY_CA_INFO where TRADENO='"+contid+"'";		
		System.out.println(sql);
		Connection conn = OnTestUcainfoaDBConnect.getConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
 			rs = pstmt.executeQuery();
// 			rs.absolute(1);
			rs.next();
// 			System.out.println(rs.getRow());
			System.out.println("查询验证码成功，结果集为："+rs.getString(1));
			System.out.println("查询验证码成功，结果集为："+rs.getString(2));
			String VERIFYCODE = rs.getString(2);
			EnvConfig.context.put("VERIFYCODE", VERIFYCODE);
			System.out.println("查询验证码成功，VERIFYCODE为："+VERIFYCODE);
		} catch (Exception e) {
			System.out.println("查询验证码操作失败，SQL语句为：" + sql);
			e.printStackTrace();
		} finally {
			OnTestUcainfoaDBConnect.release(null, pstmt, rs);
		}
	}
	
}
