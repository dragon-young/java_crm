package cn.sixboys.controller;

import cn.sixboys.domain.Dictionary;
import cn.sixboys.domain.Dictionarydetails;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.IDictionarydetailsService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 字典明细接口
 * @author FuqiangWu
 */
@Controller
@RequestMapping("/dictionarydetails")
public class DictionarydetailsController {
    @Autowired
    private IDictionarydetailsService dictionarydetailsService;
    @RequestMapping("/selectAll")
    @ResponseBody
    public JsonResult selectAll(Dictionarydetails dictionarydetails){
        List<Dictionarydetails> dictionarydetailsList = dictionarydetailsService.selectAll(dictionarydetails);
        return new JsonResult(true,"查询成功",dictionarydetailsList);

    }

    @RequestMapping("/selectById")
    @ResponseBody
    public JsonResult selectById(Long dictionaryId){
        List<Dictionarydetails> dictionarydetails = dictionarydetailsService.selectById(dictionaryId);
        return new JsonResult(true,"查询成功",dictionarydetails);
    }

    @RequestMapping("/selectQueryById")
    @ResponseBody
    public JsonResult selectQueryById(Integer pageSize, Integer currentPage,Long dictionaryId){
        if (pageSize==null){
            pageSize=4;
        }
        if (currentPage==null){
            currentPage=1;
        }
        QueryObject queryObject = new QueryObject();
        queryObject.setPageSize(pageSize);
        queryObject.setCurrentPage(currentPage);
        queryObject.setDictionaryId(dictionaryId);
        PageResult<Dictionarydetails> queryForListById = dictionarydetailsService.queryForListById(queryObject);
        return new JsonResult(true,"查询成功",queryForListById);
    }

    /**
     * 删除
     * @param id
     * @return
     */

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
       dictionarydetailsService.delete(id);
        return new JsonResult(true,"删除成功");
    }

    /**
     * 插入
     * @param
     * @return
     */

    @RequestMapping("/input")
    @ResponseBody
    public JsonResult input(Dictionarydetails dictionarydetails){
        dictionarydetailsService.insert(dictionarydetails);
        return new JsonResult(true,"插入成功");

    }


    /**
     * 修改
     * @param dictionarydetails
     * @return
     */

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Dictionarydetails dictionarydetails){
        dictionarydetailsService.update(dictionarydetails);
        return new JsonResult(true,"修改成功");
    }

    /**
     * 分页查询
     * @param pageSize
     * @param currentPage
     * @return
     */

    @RequestMapping("/query")
    @ResponseBody
    public JsonResult query(Integer pageSize, Integer currentPage,String keyword){
        if (pageSize==null){
            pageSize=4;
        }
        if (currentPage==null){
            currentPage=1;
        }
        QueryObject queryObject = new QueryObject();
        queryObject.setPageSize(pageSize);
        queryObject.setCurrentPage(currentPage);
        if(keyword !=null){
            queryObject.setKeyword(keyword);
        }
        PageResult<Dictionarydetails> query = dictionarydetailsService.query(queryObject);
        return new JsonResult(true,"查询成功",query);
    }


}
