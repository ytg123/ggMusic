package com.tz.util.ip;


import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Level;

/**
 * 工具类，提供一些方便的方法
 */
public class Util {
	
	private static StringBuilder sb = new StringBuilder();
	/**
     * 从ip的字符串形式得到字节数组形式
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
          LogFactory.log("从ip的字符串形式得到字节数组形式报错", Level.ERROR, e);
        }
        return ret;
    }
    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
    	sb.delete(0, sb.length());
    	sb.append(ip[0] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[1] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[2] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[3] & 0xFF);
    	return sb.toString();
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }
    
    /**
	 * 判断当前操作是否Windows.
	 * 
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static List<String> getLocalIP() {
		List<String> localIps=new ArrayList<String>(); 
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (false) {//经过测试一样可以使用isWindowsOS();所以禁用window方式
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						//System.out.println(ip.getHostAddress()+"-----"+ip.isSiteLocalAddress()+"-----"+ip.isLoopbackAddress()+"----"+ip.getHostName());
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 127.开头的都是lookback地址
							localIps.add(ip.getHostAddress());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localIps;
	}
	
	public static void main(String[] args) {
		System.out.println(Util.getLocalIP().toString());
	}
}