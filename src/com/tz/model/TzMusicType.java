package com.tz.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TzMusicType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tz_music_type")
public class TzMusicType implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String name;
	private Integer userId;
	private Integer sort;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	// Constructors

	/** default constructor */
	public TzMusicType() {
	}

	/** minimal constructor */
	public TzMusicType(Date createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public TzMusicType(String name, Integer userId, Integer sort,
			Integer status, Date createTime, Date updateTime) {
		this.name = name;
		this.userId = userId;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", nullable = false, length = 0)
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

}