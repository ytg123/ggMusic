package com.tz.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TzMusicComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tz_music_comment")
public class TzMusicComment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Integer status;
	private Integer isDelete;
	private String ip;
	// private String showTime;

	// 如果你进行ajax查询就不单向
	private TzMusicPeriod period;
	// 用户和评论关系:1:N
	private TzMusicUser user;

	// Constructors

	/** default constructor */
	public TzMusicComment() {
	}

	public TzMusicComment(Integer id) {
		this.id = id;
	}

	/** minimal constructor */
	public TzMusicComment(Date createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public TzMusicComment(String content, Date createTime, Date updateTime,
			Integer userId, Integer status, Integer isDelete, Integer periodId) {
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
		this.isDelete = isDelete;
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

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public TzMusicUser getUser() {
		return user;
	}

	public void setUser(TzMusicUser user) {
		this.user = user;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "period_id", referencedColumnName = "id")
	public TzMusicPeriod getPeriod() {
		return period;
	}

	public void setPeriod(TzMusicPeriod period) {
		this.period = period;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	// @Transient
	// public String getShowTime() {
	// if(createTime!=null){
	// showTime = TmDateUtil.getTimeFormat(createTime);
	// }
	// return showTime;
	// }
	//
	// public void setShowTime(String showTime) {
	// this.showTime = showTime;
	// }
}