package cn.sixboys.service;

import cn.sixboys.domain.Dictionary;
import cn.sixboys.domain.Dictionarydetails;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

import java.util.List;

public interface IDictionarydetailsService {
    List<Dictionarydetails> selectAll(Dictionarydetails dictionarydetails);

    List<Dictionarydetails> selectById(Long dictionaryId);
    void insert(Dictionarydetails dictionarydetails);

    void delete(Long id);

    void  update(Dictionarydetails dictionarydetails);
    /**
     * 完成查询某一页信息的功能
     * @param queryObject
     * @return
     */
    PageResult<Dictionarydetails> query(QueryObject queryObject);

    PageResult<Dictionarydetails> queryForListById(QueryObject queryObject);
}
