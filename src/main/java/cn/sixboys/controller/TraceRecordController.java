package cn.sixboys.controller;

import cn.sixboys.domain.JsonResult;
import cn.sixboys.domain.TraceRecord;
import cn.sixboys.service.ITraceRecordService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mingjuntang
 * @Data 2021/5/15 21:09
 */
@Controller
@RequestMapping("/traceRecord")
public class TraceRecordController {
    @Resource
    private ITraceRecordService traceRecordService;

    @RequestMapping("/queryForList")
    @ResponseBody
    public JsonResult queryForList(Integer currentPage, Integer pageSize, String keyword, Date startTime,Date endTime,Integer type){
        if(currentPage == null) {
            currentPage = 1;
        }
        if(pageSize ==null ) {
            pageSize = 4;
        }

        QueryObject queryObject = new QueryObject();
        queryObject.setCurrentPage(currentPage);
        queryObject.setPageSize(pageSize);
        if (keyword !=null){
            queryObject.setKeyword(keyword);
        }
        if (startTime != null && endTime != null){
            queryObject.setStartTime(startTime);
            queryObject.setEndTime(endTime);
        }
        if (type != null){
            queryObject.setType(type);
        }
        PageResult<TraceRecord> traceRecordPageResult = traceRecordService.selectTraceRecord(queryObject);
        return new JsonResult(true,"查询成功",traceRecordPageResult);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public JsonResult insert(TraceRecord traceRecord){
        traceRecordService.insert(traceRecord);
        return new JsonResult(true,"插入成功");
    }
}
