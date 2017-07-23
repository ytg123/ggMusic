package com.tz.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TzMusicUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tz_music_user")
public class TzMusicUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// 注解
	private String username;// 用户名
	private String account;// 账号
	private String password;// 密码
	private String headerPic;// 头像
	private String telephone;// 手机号码
	private Integer age;// 年龄
	private String address;// 地址
	private Integer male;// 性别0女1男2保密
	private Date birthday;// 生日
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private Integer isDelete;// 删除状态0未删除1删除

	// Constructors

	/** default constructor */
	public TzMusicUser() {
	}
	public TzMusicUser(Integer id) {
		this.id = id;
	}
	/** minimal constructor */
	public TzMusicUser(String username, String account, String password,
			Date createTime) {
		this.username = username;
		this.account = account;
		this.password = password;
		this.createTime = createTime;
	}

	/** full constructor */
	public TzMusicUser(String username, String account, String password,
			String headerPic, String telephone, Integer age, String address,
			Integer male, Date birthday, Date createTime, Date updateTime,
			Integer isDelete) {
		this.username = username;
		this.account = account;
		this.password = password;
		this.headerPic = headerPic;
		this.telephone = telephone;
		this.age = age;
		this.address = address;
		this.male = male;
		this.birthday = birthday;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	@Column(name = "username", length = 80)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "account", nullable = false, length = 100)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "header_pic", length = 100)
	public String getHeaderPic() {
		return this.headerPic;
	}

	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}

	@Column(name = "telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "male")
	public Integer getMale() {
		return this.male;
	}

	public void setMale(Integer male) {
		this.male = male;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 0)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

}