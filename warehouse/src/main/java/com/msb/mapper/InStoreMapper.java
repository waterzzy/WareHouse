package com.msb.mapper;

import com.msb.entity.InStore;
import com.msb.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InStoreMapper {
    //  添加入库单的方法
    public int addInStore(InStore inStore);
    //  查询入库单总行数
    public int selectInStoreCount(InStore inStore);
    //  分页查询入库单
    public List<InStore> selectInStorePage(@Param("page") Page page,@Param("inStore") InStore inStore);
}
