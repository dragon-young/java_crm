package cn.sixboys.service.impl;

import cn.sixboys.domain.Customer;
import cn.sixboys.domain.TraceRecord;
import cn.sixboys.mapper.TraceRecordMapper;
import cn.sixboys.service.ITraceRecordService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/15 21:04
 */
@Service
public class TraceRecordServiceImpl implements ITraceRecordService {
    @Resource
    private TraceRecordMapper traceRecordMapper;
    @Override
    public PageResult<TraceRecord> selectTraceRecord(QueryObject queryObject) {
        List<TraceRecord> traceRecords = traceRecordMapper.queryForList(queryObject);
        int i = traceRecordMapper.queryForCount(queryObject);
        PageResult<TraceRecord> pageResult = new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(), i, traceRecords);
        return pageResult;
    }

    @Override
    public void insert(TraceRecord traceRecord) {
        traceRecordMapper.insert(traceRecord);
    }
}
