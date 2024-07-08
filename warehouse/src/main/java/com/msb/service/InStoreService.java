package com.msb.service;

import com.msb.pojo.InStore;
import com.msb.pojo.Result;
import com.msb.page.Page;

import java.util.List;


public interface InStoreService {
    public Result saveInStore(InStore inStore, Integer buyId);
    public Page selectInStorePage(Page page, InStore inStore);
    public Result inStoreConfirm(InStore inStore);
    public List<InStore> inStoreExportTable(InStore inStore);
}
