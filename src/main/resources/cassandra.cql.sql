CREATE KEYSPACE statistic WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;

/*
 * 分区存储。
 */
CREATE TABLE statistic.protocols (
    uid int,
    channel int,
    bytes int,
    occured_on timestamp,
    client_type int,
    client_version text,
    ip_address inet,
    packet text,
    seq int,
    sid bigint,
    tag int,
    type int,
    PRIMARY KEY (uid, channel, bytes, occured_on)
) WITH CLUSTERING ORDER BY (channel ASC, bytes DESC, occured_on ASC);