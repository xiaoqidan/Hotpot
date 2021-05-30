package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.pojo.Table;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.PictureService;
import com.xihua.hotpot.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hfy
 * @date 2021/5/27 23:16
 */
@RestController
@RequestMapping(Const.hg + "/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping
    public ResponseDo getAllPTable(){
        List<Table> lists =  tableService.getAll();
        return new ResponseDo(lists);
    }

    @PostMapping
    public ResponseDo updatePicature(@RequestBody Table table){
        tableService.insertOrUpdate(table);

        return new ResponseDo("更新成功");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseDo deletePicture(@PathVariable(value = "id") Integer id){
        tableService.deleteById(id);
        return new ResponseDo("删除成功");
    }
}
