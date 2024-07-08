package com.msb.mapper;

import com.msb.pojo.OutStore;
import com.msb.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutStoreMapper {
    //  添加出库单
    public int addOutStore(OutStore outStore);

    //  查询出库单总行数
    public int queryOutStoreCount(OutStore outStore);

    //  分页查询入库单
    public List<OutStore> queryOutStorePage(@Param("page") Page page, @Param("outStore") OutStore outStore);
    //  根据出库单ID更新出库单状态- 改为1已出库
    public int updateIsInById(Integer outsId);
    public List<OutStore> exportTable(@Param("outStore") OutStore outStore);
}
