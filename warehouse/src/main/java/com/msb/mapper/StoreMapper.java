package com.msb.mapper;

import com.msb.page.Page;
import com.msb.pojo.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dh
 * @date 2024/7/3
 */
public interface StoreMapper {

    //查询所有的仓库
    List<Store> queryAllStores();


    //查询仓库的总行数
    int selectStoreCount(@Param("store") Store store);
    /**
     * 多条件分页查询仓库
     * @param store
     * @return
     */
    List<Store> selectStorePage(@Param("page") Page page, @Param("store") Store store);
    //添加仓库
    Integer insertStore(@Param("store") Store store);

    //根据仓库id 进行删除
    Integer deleteStoreById(@Param("storeId")Integer storeId);

    //更新仓库
    Integer updateStore(@Param("store")Store store);

    //通过仓库名称查询仓库对象
    Store selectStoreByName(String storeName);

    //通过仓库编号查询仓库对象
    Store selectStoreByNum(String storeNum);

    //查询名字是否重复
    Store selectStoreByNoName(String storeName,Integer storeId);

    //查询所有的store
    List<Store> queryAllStore();
}
