package Test.AddMerInfo;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GbkEncode {


    /**
     * @param args��java�е���sun��˾�ṩ��3DES���ܽ����㷨ʱ
     *            ����Ҫʹ
     *            �õ�$JAVA_HOME/jre/lib/Ŀ¼�����µ�4��jar����
     *            jce.jar
     *            security/US_export_policy.jar
     *            security/local_policy.jar
     *            ext/sunjce_provider.jar
     */

    private static final String Algorithm = "DESede"; // ��������㷨,����
    // DES,DESede,Blowfish

    // keybyteΪ������Կ������Ϊ24�ֽ�

    // srcΪ�����ܵ����ݻ�������Դ��

    public static byte[] encryptMode(byte[] keybyte, byte[] src) {

        try {

            // ������Կ

            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // ����

            Cipher c1 = Cipher.getInstance(Algorithm);

            c1.init(Cipher.ENCRYPT_MODE, deskey);

            return c1.doFinal(src);// �ڵ�һ����ļ��ܻ����

        } catch (java.security.NoSuchAlgorithmException e1) {

            // TODO: handle exception

            e1.printStackTrace();

        } catch (javax.crypto.NoSuchPaddingException e2) {

            e2.printStackTrace();

        } catch (java.lang.Exception e3) {

            e3.printStackTrace();

        }

        return null;

    }

    // keybyteΪ������Կ������Ϊ24�ֽ�

    // srcΪ���ܺ�Ļ�����

    public static byte[] decryptMode(byte[] keybyte, byte[] src) {

        try {

            // ������Կ

            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // ����

            Cipher c1 = Cipher.getInstance(Algorithm);

            c1.init(Cipher.DECRYPT_MODE, deskey);

            return c1.doFinal(src);

        } catch (java.security.NoSuchAlgorithmException e1) {

            // TODO: handle exception

            e1.printStackTrace();

        } catch (javax.crypto.NoSuchPaddingException e2) {

            e2.printStackTrace();

        } catch (java.lang.Exception e3) {

            e3.printStackTrace();

        }

        return null;

    }

    /*
     * �����ַ���������Կ�ֽ�����
     * @param keyStr ��Կ�ַ���
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
        byte[] key = new byte[24];    //����һ��24λ���ֽ����飬Ĭ�����涼��0
        byte[] temp = keyStr.getBytes("UTF-8");    //���ַ���ת���ֽ�����

        /*
         * ִ�����鿽��
         * System.arraycopy(Դ���飬��Դ�������￪ʼ������Ŀ�����飬��������λ)
         */
        if(key.length > temp.length){
            //���temp����24λ���򿽱�temp�����������ȵ����ݵ�key������
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            //���temp����24λ���򿽱�temp����24�����ȵ����ݵ�key������
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    public static byte[] build3DesKey(byte[] temp) throws UnsupportedEncodingException{
        byte[] key = new byte[24];    //����һ��24λ���ֽ����飬Ĭ�����涼��0
//        byte[] temp = keyStr.getBytes("UTF-8");    //���ַ���ת���ֽ�����
        /*
         * ִ�����鿽��
         * System.arraycopy(Դ���飬��Դ�������￪ʼ������Ŀ�����飬��������λ)
         */
        if(temp.length < key.length){
            System.arraycopy(temp,0,key,0,16);
            System.arraycopy(temp,0,key,16,8);
        }else{
            System.arraycopy(temp,0,key,0,24);
        }
        return key;
    }

    /**
     * ʮ������ת��Ϊ������
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static byte[] hex2b(String s) throws UnsupportedEncodingException {
        return hex2b(s.getBytes("GBK"), 0, s.length() >> 1);
    }
    public static byte[] hex2b(byte b[], int offset, int len) {
        byte d[] = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }

        return d;
    }
    /**
     * desc:����
     * <p>�����ˣ�������, 2016��11��5�� ����9:41:39</p>
     * @param str
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getStr(String str) throws UnsupportedEncodingException{
        byte[] keyBytes=null;
        try {
            keyBytes = GbkEncode.build3DesKey(GbkEncode.hex2b("8E5036DFD29F2383D04DB1DA35F9D6B5"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] reqStr= GbkEncode.hex2b(str);
        byte[] srcBytes = decryptMode(keyBytes, reqStr);
        String result=new String(srcBytes);
        return  result;
    }

    public static String byte2hex(byte b) {
        StringBuilder sb = new StringBuilder();
        int h = (b & 240) >> 4;
        int l = b & 15;
        sb.append(new Character((char) (h <= 9 ? 48 + h : (97 + h) - 10)));
        sb.append(new Character((char) (l <= 9 ? 48 + l : (97 + l) - 10)));
        return sb.toString();
    }

    public static String byte2hex(byte buf[]) {
        StringBuffer sb = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++)
            sb.append(byte2hex(buf[i]));

        return sb.toString();
    }

    /**
     * desc:����
     * <p>�����ˣ�������, 2016��11��5�� ����9:41:48</p>
     * @param str
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String encode(String str) throws UnsupportedEncodingException{
        byte[] keyBytes=null;
        try {
            keyBytes = GbkEncode.build3DesKey(GbkEncode.hex2b("8E5036DFD29F2383D04DB1DA35F9D6B5"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] encoded = encryptMode(keyBytes, str.getBytes("UTF-8"));
        String encodeStr=GbkEncode.byte2hex(encoded);
        return encodeStr;
    }
    public static void main(String[] args) {
        String aa="���";
        try {
			System.out.println("���ܺ���ַ���:" + encode(aa));
			System.out.println("���ܺ�:" + getStr("8abfc575ac9e0888"));
		} catch (Exception e) {
			// TODO: handle exception
		}
    }


}
