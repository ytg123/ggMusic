package com.tz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class TmDownLoadImageUtil {
    
    private static  List<HashMap<String, Object>> maps;
	public static void main(String[] args) {
		// System.out.println(getMineType("http://img1.gtimg.com/news/pics/hv1/53/7/1408/91557038.jpg"));
		// System.out.println(getNetFileSize("http://img1.gtimg.com/news/pics/hv1/53/7/1408/91557038.jpg"));
		// try {
		// System.out.println(getLength("http://img1.gtimg.com/news/pics/hv1/53/7/1408/91557038.jpg"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// };
		//
		// Map<String, Object> map = downImages("d://",
		// "http://img1.gtimg.com/news/pics/hv1/53/7/1408/91557038.jpg");
		// System.out.println(map);
		//
//		System.out.println(System.getProperty("line.separator"));
		for (int i = 1; i <=10; i++) {
			String text = "";
			if(i<10)text = "0"+i;
			downImg("d://mp3", "http://luoo.waasaa.com/low/luoo/radio704/"+text+".mp3",text+".mp3");
		}
	}

	// 根据一个图片的url，通过url的方式保存到本地。
	public static boolean downImg(String filePath, String imageUrl,
			String fileName) {
		try {
			File files = new File(filePath);
			if (!files.exists()) {
				files.mkdirs();
			}
			URL url = new URL(imageUrl);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			if (!filePath.endsWith("/")) {
				filePath = filePath + "/";
			}
			File file = new File(filePath + fileName);
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while ((i = is.read()) != -1) {
				out.write(i);
			}
			out.close();
			is.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取一个文件的后缀(带有点)
	 * 
	 * @param fileName
	 *            文件名
	 * @return 返回文件的后缀
	 */
	public static String getExt(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos == -1)
			return "";
		return fileName.substring(pos, fileName.length());
	}

	/**
	 * 获取一个文件的后缀(不带有点)
	 * 
	 * @param fileName
	 *            文件名
	 * @return 返回文件的后缀
	 */
	public static String getExtNoPoint(String fileName) {
		if (TmStringUtils.isNotEmpty(fileName)) {
			if (fileName.lastIndexOf(".") == -1)
				return "";
			int pos = fileName.lastIndexOf(".") + 1;
			return fileName.substring(pos, fileName.length());
		} else {
			return null;
		}
	}

	public static int getLength(String htmlUrl) throws IOException {
		URL url = new URL(htmlUrl);
		URLConnection con = url.openConnection();
		return con.getContentLength();
	}

	public static String downNetImg(String filePath, String remotePath,
			String htmlUrl, String fileName) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		URL url = null;
		OutputStream os = null;
		try {
			url = new URL(htmlUrl);
			URLConnection con = url.openConnection();
			int lenss = con.getContentLength();
			InputStream is = url.openStream();
			os = new FileOutputStream(filePath + fileName);
			int length = 0;
			byte[] buffer = new byte[5 * 1024];
			int len = 0;
			while ((length = is.read(buffer, 0, 5 * 1024)) != -1) {
				len = length + len;
				double percent = Math.round((len / (double) lenss) * 100D);
				os.write(buffer, 0, length);
			}
			return remotePath + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getNetFileSize(String urlFile) {
		URL url;
		try {
			url = new URL(urlFile);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			return connection.getContentType();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getMineType(String urlFile) {
		URL url;
		try {
			url = new URL(urlFile);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			return countFileSize(connection.getContentLength());
		} catch (Exception e) {
			return "0";
		}
	}

	public static void supportURLType(String host, String file) {
		String[] schemes = { "http", "https", "ftp", "mailto", "telent",
				"file", "ldap", "gopher", "jdbc", "rmi", "jar", "doc",
				"netdoc", "nfs", "verbatim", "finger", "daytime",
				"systemresouce" };
		for (int i = 0; i < schemes.length; i++) {
			try {
				@SuppressWarnings("unused")
				URL url = new URL(schemes[i], host, file);
				System.out.println(schemes[i] + "是Java所支持的URL类型\r\n");
			} catch (Exception e) {
				System.out.println(schemes[i] + "不是Java所支持的URL类型\r\n");
			}
		}
	}

	/**
	 * 根据File文件的长度统计文件的大小
	 * 
	 * @param size
	 *            File的长度 file.lenght()
	 * @return 返回文件大小
	 */
	public static String countFileSize(long fileSize) {
		String fileSizeString = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = fileSize;
			if (fileS == 0) {
				fileSizeString = "0KB";
			} else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01) + "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}

	public static void download(String strUrl, String path) {
		URL url = null;
		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
			return;
		}

		InputStream is = null;
		try {
			is = url.openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	
	
	public static int getNetURLLeneght(String htmlUrl){
		URL url = null;
		int  count = 0;
		try {
			url = new URL(htmlUrl);
			URLConnection con = url.openConnection();
			count = con.getContentLength();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

    /**
     * @return the maps
     */
    public static List<HashMap<String, Object>> getMaps() {
        return maps;
    }

    /**
     * @param maps the maps to set
     */
    public static void setMaps(List<HashMap<String, Object>> maps) {
        TmDownLoadImageUtil.maps = maps;
    }
   
}
