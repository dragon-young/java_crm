package cn.sixboys.service;


import cn.sixboys.domain.Dictionary;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

import java.util.List;

public interface IDictionaryService {
    List<Dictionary> selectAll(Dictionary dictionary);
    void delete (Long id);

    void input(Dictionary dictionary);

    void update(Dictionary dictionary);

    /**
     * 完成查询某一页信息的功能
     * @param queryObject
     * @return
     */
    PageResult<Dictionary> query(QueryObject queryObject);
}
