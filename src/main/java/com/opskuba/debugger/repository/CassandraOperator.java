package com.opskuba.debugger.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * 临时测试使用。
 */
public class CassandraOperator {

	private Cluster cluster;
	private Session session;

	private CassandraOperator() {
		connect("10.10.10.119");
	}

	public static CassandraOperator instance() {
		return CassandraOperatorHolder.CASSANDRA_OPERATOR;
	}

	private static class CassandraOperatorHolder {
		public static final CassandraOperator CASSANDRA_OPERATOR = new CassandraOperator();
	}

	public Session getSession() {
		return this.session;
	}

	public void connect(String node) {
		cluster = Cluster.builder().addContactPoint(node).build();
		session = cluster.connect();
	}

	public void close() {
		session.close();
		cluster.close();
	}
}
