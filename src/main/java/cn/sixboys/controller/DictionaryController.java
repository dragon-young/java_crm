package cn.sixboys.controller;


import cn.sixboys.domain.Dictionary;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.IDictionaryService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import cn.sixboys.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典接口
 * @author FuqiangWu
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private IDictionaryService dictionaryService;

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiredPermission(nane = "字典删除",expression = "dictionary:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        dictionaryService.delete(id);
        return new JsonResult(true,"删除成功");
    }

    /**
     * 插入
     * @param dictionary
     * @return
     */
    @RequiredPermission(nane = "字典新增",expression = "dictionary:input")
    @RequestMapping("/input")
    @ResponseBody
    public JsonResult input(Dictionary dictionary){
        dictionaryService.input(dictionary);
        return new JsonResult(true,"插入成功");

    }

    /**
     * 修改
     * @param dictionary
     * @return
     */
    @RequiredPermission(nane = "字典修改",expression = "dictionary:update")
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Dictionary dictionary){
        dictionaryService.update(dictionary);
        return new JsonResult(true,"修改成功");
    }

    /**
     * 分页查询
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequiredPermission(nane = "字典页面",expression = "dictionary:query")
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
        PageResult<Dictionary> query = dictionaryService.query(queryObject);
        return new JsonResult(true,"查询成功",query);
    }
}
