package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.pojo.Picture;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hfy
 * @date 2021/5/27 22:34
 */
@RestController
@RequestMapping(Const.hg + "/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping
    public ResponseDo getAllPicature(){
        List<Picture> lists =  pictureService.getAll();
        return new ResponseDo(lists);
    }
    @GetMapping("/list")
    public ResponseDo getAllList(){
        List<Picture> lists =  pictureService.getList();
        return new ResponseDo(lists);
    }

    @PostMapping
    public ResponseDo updatePicature(@RequestBody Picture picture){
        pictureService.insertOrUpdate(picture);

        return new ResponseDo("更新成功");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseDo deletePicture(@PathVariable(value = "id") Integer id){
        pictureService.deleteById(id);
        return new ResponseDo("删除成功");
    }
}
