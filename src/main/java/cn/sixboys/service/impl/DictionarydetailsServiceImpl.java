package cn.sixboys.service.impl;

import cn.sixboys.domain.Dictionary;
import cn.sixboys.domain.Dictionarydetails;
import cn.sixboys.mapper.DictionaryMapper;
import cn.sixboys.mapper.DictionarydetailsMapper;
import cn.sixboys.service.IDictionarydetailsService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class DictionarydetailsServiceImpl implements IDictionarydetailsService {
    @Autowired
    private DictionarydetailsMapper dictionarydetailsMapper;
    @Override
    public List<Dictionarydetails> selectAll(Dictionarydetails dictionarydetails) {
        List<Dictionarydetails> dictionarydetailsList = dictionarydetailsMapper.selectAll(dictionarydetails);
        return dictionarydetailsList;
    }

    @Override
    public List<Dictionarydetails> selectById(Long dictionaryId) {
        List<Dictionarydetails> dictionarydetailsList = dictionarydetailsMapper.selectById(dictionaryId);
        return dictionarydetailsList;
    }

    @Override
    public void insert(Dictionarydetails dictionarydetails) {
        dictionarydetailsMapper.insert(dictionarydetails);
    }

    @Override
    public void delete(Long id) {
        dictionarydetailsMapper.delete(id);

    }

    @Override
    public void update(Dictionarydetails dictionarydetails) {
        dictionarydetailsMapper.update(dictionarydetails);
    }

    /**
     *分页查询信息
     * @param queryObject
     * @return
     */
    @Override
    public PageResult<Dictionarydetails> query(QueryObject queryObject) {

        int totalCount = dictionarydetailsMapper.queryForCount(queryObject);
        if (totalCount==0){
            return new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(),totalCount, Collections.emptyList());
        }
        List<Dictionarydetails> dictionarydetails = dictionarydetailsMapper.queryForList(queryObject);
        return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,dictionarydetails);
    }

    @Override
    public PageResult<Dictionarydetails> queryForListById(QueryObject queryObject) {
        int totalCount = dictionarydetailsMapper.queryForCount(queryObject);
        if (totalCount==0){
            return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,Collections.emptyList());
        }
        List<Dictionarydetails>dictionarydetails = dictionarydetailsMapper.queryForListById(queryObject);
        return new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),totalCount,dictionarydetails);
    }
}
