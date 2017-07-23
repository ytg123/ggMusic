package com.tz.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.BitstreamException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.lyrics3.AbstractLyrics3;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;

/**
 * MP3工具
 * 
 * supports:
 * ID3 v1 & v1.1
 * Lyrics3 v1 & v2
 * ID3 v2.2 & v2.3 & v2.4 tags
 * MP3 Frame Headers
 * 
 * @author gary
 *
 */
public class MP3Util {
	/**
	 *  jaudiotagger-2.0.3.jar
	 *	jid3lib-0.5.4.jar
	 *	jl1.0.1.jar
	 *	tritonus_mp3-0.3.6.jar
	*/
	public static MP3Info getMP3Info(String path) throws IOException, TagException, UnsupportedAudioFileException{
		MP3File file = new MP3File(path);
		AbstractID3v2 id3v2 = file.getID3v2Tag();
		ID3v1 id3v1 = file.getID3v1Tag();
		AbstractLyrics3 lyrics = file.getLyrics3Tag();
		HashMap<String, String> map = timeLine(path);
		if(id3v2 != null){
			return new MP3Info(id3v2.getLeadArtist(), id3v2.getSongTitle(), 
					id3v2.getAlbumTitle(), id3v2.getTrackNumberOnAlbum(), 
					id3v2.getYearReleased(), lyrics == null ? null : lyrics.getSongLyric(),map.get("time"),map.get("mstime"),file.getMp3file().length()+"");
		}else if(id3v1 != null){
			return new MP3Info(id3v1.getLeadArtist(), id3v1.getSongTitle(), 
					id3v1.getAlbumTitle(), id3v1.getTrackNumberOnAlbum(), 
					id3v1.getYearReleased(), lyrics == null ? null : lyrics.getSongLyric(),map.get("time"),map.get("mstime"),file.getMp3file().length()+"");
		}else{
			return null;
		}
	}

	
	public static HashMap<String, String> timeLine(String path){
		File file = new File(path);
		try {
			AudioFile f = AudioFileIO.read(file);
			MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
			int timelength = audioHeader.getTrackLength();
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("time", timelength+"");
			int minute = timelength / 60;
			int second = timelength % 60;
			map.put("mstime", (minute<10?"0"+minute:minute+"")+":"+(second<10?"0"+second:second+""));
			return map;	
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException, TagException, UnsupportedAudioFileException, BitstreamException {
//		String path = "E:/JavaProjects02/tzmusic2/WebRoot/resource/mp3/01.mp3";
		String path = "F:/kuwo/kuwomusic/KWMUSIC/周杰伦-稻香.mp3";
		MP3Info mp3Info = getMP3Info(path);
		mp3Info.printMP3Info();
//		System.out.println(mp3Info.getAlbumTitle());//专辑标题
//		System.out.println(mp3Info.getArtist());//演唱者
//		System.out.println(mp3Info.getSongLyric());//歌词
//		System.out.println(mp3Info.getSongTitle());//歌名
//		System.out.println(mp3Info.getTrackNumberOnAlbum());
//		System.out.println(mp3Info.getYearReleased());//专辑年份
//		System.out.println(mp3Info.getTimeLine());//总时长
//		System.out.println(mp3Info.getTimeSecond());//专辑年份
//		System.out.println(mp3Info.getTimeSize());//专辑年份
		
		
		
		
		
		
		//File file = new File("E:/JavaProjects02/tzmusic2/WebRoot/resource/mp3/01.mp3");
		//URL urlfile = file.toURI().toURL();
//		URL urlfile = new URL("http://luoo.waasaa.com/low/luoo/radio707/01.mp3");
//		URLConnection con = urlfile.openConnection();
//		int ms = con.getContentLength();
//		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
//		Bitstream bt = new Bitstream(bis);
//		Header hh = bt.readFrame();
//		int time = (int) hh.total_ms(ms);
//		System.out.println(time / 1000);
	}
}
