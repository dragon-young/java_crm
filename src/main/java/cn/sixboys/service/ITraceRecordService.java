package cn.sixboys.service;

import cn.sixboys.domain.TraceRecord;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

/**
 * @author mingjuntang
 * @Data 2021/5/15 21:03
 */
public interface ITraceRecordService {
    PageResult<TraceRecord> selectTraceRecord(QueryObject queryObject);
    void insert(TraceRecord traceRecord);
}
