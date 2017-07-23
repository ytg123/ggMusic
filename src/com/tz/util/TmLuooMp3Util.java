package com.tz.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * 
 * 抓取网页mp3信息
 * TmLuooMp3Util
 * 创建人:keke
 * 时间：2015年3月21日-下午9:56:27 
 * @version 1.0.0
 *
 */
public class TmLuooMp3Util {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		File file = new File("D:\\mp3\\707\\01.mp3");
		long total = 0;
		try {
		    AudioFileFormat aff = AudioSystem.getAudioFileFormat(file);
		    Map props = aff.properties();
		    if (props.containsKey("duration"))
		        total = (long) Math.round((((Long) props.get("duration"))
		                .longValue())/1000);
		    System.out.println(total);
		} catch (UnsupportedAudioFileException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		
//		for (int i = 707; i > 705; i--) {
//				java.io.File file = new java.io.File("d://mp3/"+i);
//				if(!file.exists())file.mkdirs();
//				Document document = Jsoup.parse(new URL("http://www.luoo.net/music/"+i),3000);
//				String fengmian = document.getElementById("volCoverWrapper").child(0).attr("src");
//				String label = document.getElementsByClass("vol-tag-item").get(0).text();
//				//TmDownLoadImageUtil.download(fengmian, file.getAbsolutePath()+"/"+i+".jpg");
//				String description  = document.getElementsByClass("vol-desc").get(0).html();
//				System.out.println("封面是:"+fengmian);
//				System.out.println("标签是:"+label);
//				System.out.println("描述是:"+description);
//				Elements elements = document.getElementsByClass("track-item");
//				int count = 1; 
//				for (Element element : elements) {
//					String name = element.getElementsByClass("trackname").get(0).text();//歌名
//					String artist = element.getElementsByClass("artist").get(0).text();//专辑
//					String img =  element.getElementsByClass("btn-action-share").get(0).attr("data-img");
//					System.out.println(name);
//					System.out.println(artist);
//					System.out.println(img);
//					//TmDownLoadImageUtil.download(img, file.getAbsolutePath()+"/"+count+".jpg");
//					count++;
//				}
//				
//				System.out.println("===============================================================");
////				for (int j = 1; j <= count; j++) {
////					try{
////						String text = i+"";
////						if(j<10)text = "0"+j;
////						TmDownLoadImageUtil.download("http://luoo.waasaa.com/low/luoo/radio"+i+"/"+text+".mp3", "d://mp3/"+i+"/"+text+".mp3");
////					}catch(Exception ex){
////						continue;
////					}
////				}
//		}
	}
}
