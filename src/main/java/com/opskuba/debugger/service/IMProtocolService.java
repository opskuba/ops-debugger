package com.opskuba.debugger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opskuba.debugger.model.IMAuditModel;
import com.opskuba.debugger.repository.IMProtocolRepository;
import com.opskuba.debugger.utils.JsonUtil;

@Service("imProtocolService")
public class IMProtocolService {

	@Autowired
	private IMProtocolRepository protocolRepository;

	public void audit(String protocolStr, Byte flag) {
		
		IMAuditModel auditModel = (IMAuditModel) JsonUtil.fromJson(protocolStr,IMAuditModel.class);
		
		System.out.println(auditModel.getTs()+"-"+auditModel.getHostIp()+":"+auditModel.getUid()+"-["+auditModel.getClitype()+"]->"+auditModel.getCliver()+"->"+auditModel.getSessionId());
		
//		String[] splitProtocols = protocolStr.split("`\\$-\\$`");
//		List<String> protocols = new ArrayList<String>(Arrays.asList(splitProtocols));
//
//		IMProtocolAudit imProtocolAudit = new IMProtocolAudit();
//		imProtocolAudit.setFlag(flag);
//		imProtocolAudit.setUid(Integer.valueOf(protocols.get(1)));
//		imProtocolAudit.setSessionId(Long.valueOf(protocols.get(0)));
//		String originProtocol = protocols.get(2);
//
//		imProtocolAudit.setBytes(originProtocol.getBytes().length);
//		imProtocolAudit.setOrigins(originProtocol);
//
//		//(IMPacket) JsonUtil.fromJson(text, IMPacket.class);
//		IMPacket imPacket = (IMPacket) JsonUtil.fromJson(originProtocol, IMPacket.class);
//		imProtocolAudit.setChannel(imPacket.getChannel());
//		imProtocolAudit.setTag(imPacket.getHeader().getTag());
//		imProtocolAudit.setType(imPacket.getHeader().getType());
//		imProtocolAudit.setTs(new Timestamp(System.currentTimeMillis()));
//
//		protocolRepository.save(imProtocolAudit);
	}

	public static void main(String[] args) {

		String message = "601796"
				+ "`$-$`78958242`$-$`"
				+ "{\"header\":{\"tag\":2,\"type\":7},\"seq\":322,\"meta\":{\"extra_params\":{\"callback\":\"{\"type\":0,\"msgid\":2702846,\"tag\":0,\"uucid\":\"02B4F4424E010000040000000124ABEB0300000000FE3D2900000000000100000000000000\"}\"}},\"channel\":4}";

		String[] split = message.split("`\\$-\\$`");

		for (String string : split) {
			System.out.println(string);
		}

	}
}
