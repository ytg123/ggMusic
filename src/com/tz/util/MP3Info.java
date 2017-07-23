package com.tz.util;

/**
 * MP3信息
 * 
 * @author gary
 * 
 */
public class MP3Info {

	private String artist;
	private String songTitle;
	private String albumTitle;
	private String trackNumberOnAlbum;
	private String yearReleased;
	private String songLyric;
	//总时长
	private String timeLine;
	//时分
	private String timeSecond;
	/*文件大小*/
	private String timeSize;
	
	
	public MP3Info(String artist, String songTitle, String albumTitle,
			String trackNumberOnAlbum, String yearReleased, String songLyric,
			String timeLine, String timeSecond, String timeSize) {
		super();
		this.artist = artist;
		this.songTitle = songTitle;
		this.albumTitle = albumTitle;
		this.trackNumberOnAlbum = trackNumberOnAlbum;
		this.yearReleased = yearReleased;
		this.songLyric = songLyric;
		this.timeLine = timeLine;
		this.timeSecond = timeSecond;
		this.timeSize = timeSize;
	}

	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getTrackNumberOnAlbum() {
		return trackNumberOnAlbum;
	}

	public void setTrackNumberOnAlbum(String trackNumberOnAlbum) {
		this.trackNumberOnAlbum = trackNumberOnAlbum;
	}

	public String getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getSongLyric() {
		return songLyric;
	}

	public void setSongLyric(String songLyric) {
		this.songLyric = songLyric;
	}
	

	public String getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(String timeLine) {
		this.timeLine = timeLine;
	}
	

	public String getTimeSecond() {
		return timeSecond;
	}

	public void setTimeSecond(String timeSecond) {
		this.timeSecond = timeSecond;
	}

	public String getTimeSize() {
		return timeSize;
	}

	public void setTimeSize(String timeSize) {
		this.timeSize = timeSize;
	}

	public void printMP3Info() {
		System.out.println("歌手:" + artist);
		System.out.println("歌曲名:" + songTitle);
		System.out.println("专辑名:" + albumTitle);
		System.out.println("音轨:" + trackNumberOnAlbum);
		System.out.println("发行年份:" + yearReleased);
		System.out.println("歌词:" + songLyric);
		System.out.println("总时长:" + timeLine);
		System.out.println("时分:" + timeSecond);
		System.out.println("大小:" + timeSize);
	}
}