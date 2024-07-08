package com.msb.service.impl;

import com.msb.mapper.StoreMapper;
import com.msb.page.Page;
import com.msb.pojo.Result;
import com.msb.pojo.Store;
import com.msb.service.StoreService;
import com.msb.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //查询所有的仓库
    @Override
    public List<Store> queryAllStores() {
        List<Store> storeList = storeMapper.queryAllStores();
        return storeList;
    }


    @Override
    public Page queryStorePage(Page page, Store store) {

        //查询仓库总行数
        int storeCount = storeMapper.selectStoreCount(store);

        //分页查询仓库
        List<Store> storeList = storeMapper.selectStorePage(page, store);

        //将查到的总行数和当前页数据封装到Page对象
        page.setTotalNum(storeCount);
        page.setResultList(storeList);

        return page;
    }

    /**
     * 添加仓库
     * @param store
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result saveStore(Store store) {
        Result result = new Result();
        /**
         * 参数的校验
         */
        //判断名称是否重复
        //通过store 中的 名称查询数据库得到一个对象 ,如果对象为空,表示没有重复,如果对象部位空 ,则表明 仓库得名称不能用

        Store storeByName= storeMapper.selectStoreByName(store.getStoreName());
        if (storeByName!=null){
            result.setCode(Result.CODE_ERR_UNLOGINED);
            result.setMessage("仓库名称已经存在请重新输入");
            return result;
        }
        //判断编号是否重复
        Store storeByNum=  storeMapper.selectStoreByNum(store.getStoreNum());
        if (storeByNum!=null){
            result.setCode(Result.CODE_ERR_UNLOGINED);
            result.setMessage("仓库编号已经存在请重新输入");
            return result;
        }
        //手机号码格式正确
        if (!PhoneUtil.isMobile(store.getPhone())){
            result.setCode(Result.CODE_ERR_UNLOGINED);
            result.setMessage("手机号码不对");
            return result;
        }

        Integer row= storeMapper.insertStore(store);
        if (row== 1){
            result.setCode(Result.CODE_OK);
            return result;
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
        }
    }

    /**
     * 根据仓库id 进行删除
     * @param storeId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteStoreById(Integer storeId) {
        Integer row=storeMapper.deleteStoreById(storeId);
        return row;
    }
    /**
     * 更新仓库
     * @param store
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateStore(Store store) {
        Result result= new Result();
        //被更行放库的名字是否有重复的
        Store nameStore =storeMapper.selectStoreByNoName(store.getStoreName(),store.getStoreId());
        if (nameStore==null){
            if (!PhoneUtil.isMobile(store.getPhone())){
                result.setCode(Result.CODE_ERR_BUSINESS);
                result.setMessage("仓库联系人的电话格式不对");
                return result;
            }
            //执行更新方法
            Integer integer = storeMapper.updateStore(store);
            if (integer==1){
                result.setCode(Result.CODE_OK);
                result.setMessage("更新成功");
                return result;
            }else {
                result.setCode(Result.CODE_ERR_BUSINESS);
                result.setMessage("更新失败");
                return result;
            }
        }else {
            result.setCode(Result.CODE_ERR_BUSINESS);
            result.setMessage("仓库的名字已经存在请重新输入");
            return result;
        }
    }

    @Override
    public List<Store> queryAllStore() {
        return storeMapper.queryAllStore();
    }
}
