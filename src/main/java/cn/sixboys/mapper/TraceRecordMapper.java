package cn.sixboys.mapper;

import cn.sixboys.domain.TraceRecord;
import cn.sixboys.util.QueryObject;

import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/15 20:28
 */
public interface TraceRecordMapper {
    List<TraceRecord> queryForList(QueryObject queryObject);
    void insert(TraceRecord traceRecord);
    int queryForCount(QueryObject queryObject);
}
