package com.opskuba.debugger.domain.audit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 协议审计
 */
@Entity
@Table(name="im_protocol_audit")
public class IMProtocolAudit {

	private Integer bytes;// 多少字节
	private Byte channel;
	private Byte flag;// 方向：client（1），server（0）
	private Long id;

	private String origins;
	private Long sessionId;

	private Byte tag;
	private Timestamp ts;
	private Short type;

	private Integer uid;

	
	@Column(name = "bytes")
	public Integer getBytes() {
		return bytes;
	}

	@Column(name = "channel")
	public Byte getChannel() {
		return channel;
	}

	@Column(name = "flag")
	public Byte getFlag() {
		return flag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	public Long getId() {
		return id;
	}

	@Lob
	@Column(columnDefinition = "TEXT",name = "origins")
	public String getOrigins() {
		return origins;
	}

	@Column(name = "session_id")
	public Long getSessionId() {
		return sessionId;
	}

	@Column(name = "tag")
	public Byte getTag() {
		return tag;
	}

	@Column(name = "ts")
	public Timestamp getTs() {
		return ts;
	}

	@Column(name = "type")
	public Short getType() {
		return type;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return uid;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public void setFlag(Byte flag) {
		this.flag = flag;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrigins(String origins) {
		this.origins = origins;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public void setTag(Byte tag) {
		this.tag = tag;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "IMProtocolAudit [channel=" + channel + ", tag=" + tag + ", type=" + type + ", sessionId=" + sessionId
				+ ", uid=" + uid + ", bytes=" + bytes + ", ts=" + ts + ", flag=" + flag + "]";
	}

}
