package com.opskuba.debugger.domain.audit;

import java.sql.Timestamp;

/**
 * 协议审计
 */
public class IMProtocolAudit {

	private Byte channel;
	private Byte tag;
	private Short type;

	private Long sessionId;
	private Integer uid;

	private Integer bytes;// 多少字节
	private String origins;
	private Timestamp ts;

	private Byte flag;// 方向：client（1），server（0）

	public Byte getChannel() {
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public Byte getTag() {
		return tag;
	}

	public void setTag(Byte tag) {
		this.tag = tag;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public String getOrigins() {
		return origins;
	}

	public void setOrigins(String origins) {
		this.origins = origins;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public Byte getFlag() {
		return flag;
	}

	public void setFlag(Byte flag) {
		this.flag = flag;
	}

}
