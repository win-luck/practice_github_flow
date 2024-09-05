package com.example.demo.config;

import com.example.demo.config.aop.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 컨텍스트에 데이터 소스 키가 설정되어 있으면 그 값을 사용
        String dataSourceKey = DataSourceContextHolder.getDataSourceKey();
        if (dataSourceKey != null) {
            return dataSourceKey;
        }

        // 그렇지 않으면 트랜잭션 읽기 전용 여부에 따라 기본적으로 라우팅
        return isCurrentTransactionReadOnly() ? "reader" : "writer";
    }
}
