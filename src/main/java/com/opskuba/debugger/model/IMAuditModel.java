package com.opskuba.debugger.model;

import com.opskuba.debugger.utils.JsonUtil;

public class IMAuditModel {

	private Integer uid;
	private Long sessionId;
	private String hostIp;
	private Long ts;
	private String packet;
	private String cliver;
	private String clitype;

	public IMAuditModel() {
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public String getPacket() {
		return packet;
	}

	public void setPacket(String packet) {
		this.packet = packet;
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

	public String getCliver() {
		return cliver;
	}

	public void setCliver(String cliver) {
		this.cliver = cliver;
	}

	public String getClitype() {
		return clitype;
	}

	public void setClitype(String clitype) {
		this.clitype = clitype;
	}

}
