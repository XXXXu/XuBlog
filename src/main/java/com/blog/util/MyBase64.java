package com.blog.util;



import sun.misc.BASE64Decoder;




public class MyBase64 {
	
	//public static String decode(String str) {
		/*//进行解码,str是获得的加密后的参数,下面进行解密  
		if (str != null) {  
            String os = System.getProperty("os.name");  
            if (os.toLowerCase().startsWith("windows")) {  
                str = str.replaceAll("\\r\\n", "<br/>");  
            } else if (os.toLowerCase().startsWith("linux")) {  
                str = str.replaceAll("\\n", "<br/>");  
            }  
            //返回解密后的参数  
            try {
				return new String(Base64.decodeBase64(str.getBytes("GBK")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
        }  
        return null;  */
		
	
	public static String decode4Base64(String s) {
		if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"GB2312");
        } catch (Exception e) {
            return null;
        }
	}
}
