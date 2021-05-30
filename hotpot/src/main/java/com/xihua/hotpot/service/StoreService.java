package com.xihua.hotpot.service;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.mapper.StoreMapper;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * 火锅店铺
 * @author wufang
 */
@Service
public class StoreService {
    @Resource
    private StoreMapper storeMapper;

    @GetMapping
    public ResponseDo getStore() {
        return new ResponseDo(storeMapper.selectByPrimaryKey(Const.StoreId));
    }
}
