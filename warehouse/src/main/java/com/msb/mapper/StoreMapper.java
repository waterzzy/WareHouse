package com.msb.mapper;

import com.msb.entity.Store;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StoreMapper {
    public List<Store> quarryAllStore();
}
