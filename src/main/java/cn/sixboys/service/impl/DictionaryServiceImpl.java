package cn.sixboys.service.impl;

import cn.sixboys.domain.Department;
import cn.sixboys.domain.Dictionary;
import cn.sixboys.mapper.DictionaryMapper;
import cn.sixboys.service.IDictionaryService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public void delete(Long id) {
        dictionaryMapper.deleteByid(id);

    }

    @Override
    public void input(Dictionary dictionary) {
        dictionaryMapper.insert(dictionary);

    }

    @Override
    public void update(Dictionary dictionary) {
        dictionaryMapper.update(dictionary);

    }

    /**
     *分页查询信息
     * @param queryObject
     * @return
     */
    @Override
    public PageResult<Dictionary> query(QueryObject queryObject) {

        int totalCount = dictionaryMapper.queryForCount(queryObject);
        if (totalCount==0){
            return new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(),totalCount, Collections.emptyList());
        }
        List<Dictionary> dictionaries = dictionaryMapper.queryForList(queryObject);
        return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,dictionaries);
    }
}
