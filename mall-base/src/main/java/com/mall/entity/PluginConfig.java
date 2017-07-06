package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_plugin_config")
public class PluginConfig {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/**
	 * 插件类型：1, 支付插件; 2, 登录插件; 3, 短信插件; 4, 移动支付插件
	 */
	@Column(name = "type")
	private int type;

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	@Column(name = "orders")
	private int orders;

	@Column(name = "enabled")
	private boolean enabled;// 是否可用

	@Column(name = "version")
	private String version;// 插件版本

	@Column(name = "author")
	private String author;// 插件作者

	@Column(name = "setting_url")
	private String settingUrl;// 配置地址

	@Column(name = "plugin_name")
	private String pluginName;

	/**
	 * 插件配置信息
	 */
	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_plugin_config_attribute", joinColumns = @JoinColumn(name = "plugin_configs"))
	private Map<String, String> attributes;

	public PluginConfig() {
		this.attributes = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public String getSettingUrl() {
		return settingUrl;
	}

	public void setSettingUrl(String settingUrl) {
		this.settingUrl = settingUrl;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 获取属性值
	 * 
	 * @param name
	 * @return
	 */
	public String getAttribute(String name) {
		String value = this.attributes.get(name);
		return value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 插件类型
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static final class Type {
		public static final int pluginPay = 1;// 支付插件
		public static final int pluginLogin = 2;// 登录插件
		public static final int pluginSms = 3;// 短信插件
		public static final int pluginMobilePay = 4;// 移动端支付插件
	}
}
