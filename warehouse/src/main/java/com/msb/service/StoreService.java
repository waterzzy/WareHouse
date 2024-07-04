package com.msb.service;

import com.msb.entity.Store;
import com.msb.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoreService {

    public List<Store> getAllStore();
}
