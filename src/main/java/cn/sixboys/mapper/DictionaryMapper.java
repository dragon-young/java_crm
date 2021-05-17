package cn.sixboys.mapper;


import cn.sixboys.domain.Dictionary;
import cn.sixboys.util.QueryObject;

import java.util.List;

/**
 * 数据字典mapper
 * @author FuqiangWu
 */
public interface DictionaryMapper {

    List<Dictionary>selectAll(Dictionary dictionary);
    /**
     * 通过id删除信息
     * @param id
     */
    void deleteByid(Long id);

    /**
     * 插入信息
     * @param dictionary
     */
    void insert (Dictionary dictionary);

    /**
     * 修改信息
     * @param dictionary
     */
    void update(Dictionary dictionary);

    /**
     *查询总条数
     * @param queryObject
     * @return
     */
    int queryForCount(QueryObject queryObject);

    /**
     *查询当前页显示条数信息
     * @param queryObject
     * @return
     */
    List<Dictionary> queryForList(QueryObject queryObject);

    List<Dictionary> queryForListById(QueryObject queryObject,Long id);
}
