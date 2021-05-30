package com.xihua.hotpot.service;

import com.xihua.hotpot.mapper.TableMapper;
import com.xihua.hotpot.pojo.Table;
import javafx.scene.control.Tab;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hfy
 * @date 2021/5/27 23:17
 */
@Service
public class TableService {
    @Resource
    private TableMapper tableMapper;

    public List<Table> getAll() {
        List<Table> tables = tableMapper.selectAll();
        return tables;
    }

    public void insertOrUpdate(Table table){
        if(table.getId()!=null){
            tableMapper.updateByPrimaryKeySelective(table);
        }else{
            tableMapper.insert(table);
        }
    }
    public void deleteById(Integer id){
        tableMapper.deleteByPrimaryKey(id);
    }
}
