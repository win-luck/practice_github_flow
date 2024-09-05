package com.example.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 현재 트랜잭션이 읽기 전용인 경우 Reader DB, 그렇지 않으면 Writer DB로 라우팅
        return isCurrentTransactionReadOnly() ? "reader" : "writer";
    }
}
