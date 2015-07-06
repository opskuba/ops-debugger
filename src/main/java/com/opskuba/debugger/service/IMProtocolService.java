package com.opskuba.debugger.service;

import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.opskuba.debugger.domain.model.IMProtocolStatisticModel;
import com.opskuba.debugger.model.IMAuditModel;
import com.opskuba.debugger.repository.CassandraOperator;
import com.opskuba.debugger.utils.JsonUtil;
import com.uplus.model.im.IMPacket;
import com.uplus.model.im.IMPacket.Header;

@Service("imProtocolService")
public class IMProtocolService {

	private static final Logger LOG = LoggerFactory.getLogger(IMProtocolService.class);

	private static final String KEYSPACE_NAME = "statistic";
	
	private static final String ORIGIN_TABLE_NAME = "protocols";
	private static final String ORIGIN_TABLE_NAME_ORDER_BY_BYTES = "protocols_order_by_bytes";

	private static StringBuffer ORIGIN_CQL_BUFF = new StringBuffer()
			.append("INSERT INTO ")
			.append(KEYSPACE_NAME)
			.append(".")
			.append(ORIGIN_TABLE_NAME)
			.append("(uid,sid,ip_address,occured_on,occured_at,seq,channel,tag,type,packet,bytes,client_version,client_type) ")
			.append("VALUES").append("(?,?,?,?,?,?,?,?,?,?,?,?,?)").append(";");

	private static StringBuffer ORIGIN_CQL_BUFF_ORDER_BY_BYTES = new StringBuffer()
			.append("INSERT INTO ")
			.append(KEYSPACE_NAME)
			.append(".")
			.append(ORIGIN_TABLE_NAME_ORDER_BY_BYTES)
			.append("(uid,sid,ip_address,occured_on,occured_at,seq,channel,tag,type,bytes,client_version,client_type) ")
			.append("VALUES").append("(?,?,?,?,?,?,?,?,?,?,?,?)").append(";");

	private static final PreparedStatement ORIGIN_STATEMENT = CassandraOperator.instance().getSession()
			.prepare(ORIGIN_CQL_BUFF.toString());
	
	private static final PreparedStatement ORIGIN_STATEMENT_ORDER_BY_BYTES = CassandraOperator.instance().getSession()
			.prepare(ORIGIN_CQL_BUFF_ORDER_BY_BYTES.toString());

	public void audit(String protocolStr, Byte flag) {

		IMAuditModel auditModel = (IMAuditModel) JsonUtil.fromJson(protocolStr, IMAuditModel.class);

		IMPacket imPacket = null;

		try {
			imPacket = (IMPacket) JsonUtil.fromJson(auditModel.getPacket(), IMPacket.class);
		} catch (Exception e) {
			LOG.error(auditModel.getPacket(), e);
		}

		if (imPacket != null) {

			IMProtocolStatisticModel imProtocolStatisticModel = new IMProtocolStatisticModel();

			imProtocolStatisticModel.setUid(auditModel.getUid());
			imProtocolStatisticModel.setSid(auditModel.getSessionId());
			imProtocolStatisticModel.setIpAddress(auditModel.getHostIp());
			imProtocolStatisticModel.setOccuredOn(new DateTime(auditModel.getTs()).toString("yyyy-MM-dd"));
			imProtocolStatisticModel.setOccuredAt(new Date(auditModel.getTs()));

			Long seq = imPacket.getSeq();
			imProtocolStatisticModel.setSeq(null != seq ? seq.intValue() : 0);
			imProtocolStatisticModel.setChannel(imPacket.getChannel().intValue());

			Header header = imPacket.getHeader();
			if (null != header) {
				imProtocolStatisticModel.setTag(header.getTag().intValue());
				Short type = header.getType();
				imProtocolStatisticModel.setType(null != type ? type.intValue() : 1000);
			}

			imProtocolStatisticModel.setPacket(auditModel.getPacket());
			imProtocolStatisticModel.setBytes(auditModel.getPacket().getBytes().length);

			imProtocolStatisticModel.setClientVersion(auditModel.getCliver());
			imProtocolStatisticModel.setClientType(Integer.valueOf(auditModel.getClitype()));

			Session session = CassandraOperator.instance().getSession();

			// 存储数据到主表
			{
				BoundStatement boundStatement = new BoundStatement(ORIGIN_STATEMENT);
				boundStatement.bind(imProtocolStatisticModel.getUid(), imProtocolStatisticModel.getSid(),
						imProtocolStatisticModel.getIpAddress(), imProtocolStatisticModel.getOccuredOn(),
						imProtocolStatisticModel.getOccuredAt(), imProtocolStatisticModel.getSeq(),
						imProtocolStatisticModel.getChannel(), imProtocolStatisticModel.getTag(),
						imProtocolStatisticModel.getType(), imProtocolStatisticModel.getPacket(),
						imProtocolStatisticModel.getBytes(), imProtocolStatisticModel.getClientVersion(),
						imProtocolStatisticModel.getClientType());

				session.execute(boundStatement);
			}

			// 数据按大小存储
			{
				BoundStatement boundStatement = new BoundStatement(ORIGIN_STATEMENT_ORDER_BY_BYTES);
				boundStatement.bind(imProtocolStatisticModel.getUid(), imProtocolStatisticModel.getSid(),
						imProtocolStatisticModel.getIpAddress(), imProtocolStatisticModel.getOccuredOn(),
						imProtocolStatisticModel.getOccuredAt(), imProtocolStatisticModel.getSeq(),
						imProtocolStatisticModel.getChannel(), imProtocolStatisticModel.getTag(),
						imProtocolStatisticModel.getType(), imProtocolStatisticModel.getBytes(),
						imProtocolStatisticModel.getClientVersion(), imProtocolStatisticModel.getClientType());
				session.execute(boundStatement);
			}
		}

	}
}
