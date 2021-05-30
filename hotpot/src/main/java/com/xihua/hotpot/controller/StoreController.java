package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 火锅店铺
 * 这个未使用
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    public ResponseDo getStore(){
        return storeService.getStore();
    }

}
