package com.tz.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;

/**
 * TzMusic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tz_music")
public class TzMusic implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String title;//歌名
	private String article;//专辑
	private String singer;//歌手
	private Date pushTime;//发表日期
	private String timer;//总时长
	private String path;//路径
	private Integer timelength;//视频大小
	private String pattern;//格式
	private String labelPath;//歌词目录
	private String img;//封面
	private Integer sort;//排序
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private Integer isDelete;//是否删除  0未删除  1删除
	// 期刊id
	private TzMusicPeriod period;//期刊
	private Integer status;//发表状态  0未发布  1发布
	private Integer hit;//点击数

	// Constructors

	/** default constructor */
	public TzMusic() {
	}
	public TzMusic(Integer id) {
		this.id = id;
	}
	/** minimal constructor */
	public TzMusic(String title, Date createTime) {
		this.title = title;
		this.createTime = createTime;
	}

	/** full constructor */
	public TzMusic(String title, String article, Date yearMonth, String timer,
			String path, Integer timelength, String pattern, String labelPath,
			String img, Integer sort, Date createTime, Date updateTime,
			Integer isDelete, Integer periodId, Integer status, Integer hit) {
		this.title = title;
		this.article = article;
		this.pushTime = pushTime;
		this.timer = timer;
		this.path = path;
		this.timelength = timelength;
		this.pattern = pattern;
		this.labelPath = labelPath;
		this.img = img;
		this.sort = sort;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDelete = isDelete;
		this.status = status;
		this.hit = hit;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "article", length = 80)
	public String getArticle() {
		return this.article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pushTime", length = 0)
	public Date getYearMonth() {
		return this.pushTime;
	}

	public void setYearMonth(Date pushTime) {
		this.pushTime = pushTime;
	}

	@Column(name = "timer", length = 10)
	public String getTimer() {
		return this.timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	@Column(name = "path", length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "timelength")
	public Integer getTimelength() {
		return this.timelength;
	}

	public void setTimelength(Integer timelength) {
		this.timelength = timelength;
	}

	@Column(name = "pattern", length = 10)
	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Column(name = "label_path", length = 100)
	public String getLabelPath() {
		return this.labelPath;
	}

	public void setLabelPath(String labelPath) {
		this.labelPath = labelPath;
	}

	@Column(name = "img", length = 100)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "create_time", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToOne
	@JoinColumn(name = "period_id")
	@JSON(serialize=false)
	public TzMusicPeriod getPeriod() {
		return period;
	}

	public void setPeriod(TzMusicPeriod period) {
		this.period = period;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "hit")
	public Integer getHit() {
		return this.hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}
	@Column(name = "singer",length=100)
	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}
}