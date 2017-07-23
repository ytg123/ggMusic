package com.tz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

/**
 * 
 * 
 * MusicPeriod 创建人:keke 时间：2015年3月17日-下午10:29:23
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_music_period")
public class TzMusicPeriod implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 标题
	private String title;
	// 封面图片
	private String img;
	// 描述
	private String description;
	// 状态 0 未发布 1发布
	private Integer status;
	// 状态 0 未删除 1删除
	private Integer isDelete;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 点击数
	private Integer hit;
	// 标签
	private String label;
	// 期数
	private Integer period;
	// 用户ID
	private TzMusicUser musicUser;
	// 音乐类型
	private TzMusicType musicType;
	/*一个期刊有多个音乐*/
	private List<TzMusic> musics = new ArrayList<TzMusic>(0);

	public TzMusicPeriod() {

	}

	public TzMusicPeriod(Integer id) {
		this.id = id;
	}
	//多个评论
	private List<TzMusicComment> commentList = new ArrayList<TzMusicComment>(0);
	// Constructors
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="period")
	public List<TzMusicComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TzMusicComment> commentList) {
		this.commentList = commentList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "img", length = 100)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Lob
	@Column(name = "description")
	@Basic(fetch = FetchType.LAZY)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status", length = 1)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "is_delete", length = 1)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", columnDefinition = "datetime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "hit")
	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	@Column(name = "label", length = 80)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "period")
	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public TzMusicUser getMusicUser() {
		return musicUser;
	}

	public void setMusicUser(TzMusicUser musicUser) {
		this.musicUser = musicUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public TzMusicType getMusicType() {
		return musicType;
	}

	public void setMusicType(TzMusicType musicType) {
		this.musicType = musicType;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="period")
	@Where(clause="is_delete = 0")
	public List<TzMusic> getMusics() {
		return musics;
	}

	public void setMusics(List<TzMusic> musics) {
		this.musics = musics;
	}
}
