package com.msb.service;

import com.msb.entity.InStore;
import com.msb.entity.Result;
import com.msb.page.Page;

import java.awt.geom.RectangularShape;
import java.util.List;

public interface InStoreService {
    public Result saveInStore(InStore inStore, Integer buyId);
    public Page selectInStorePage(Page page,InStore inStore);
}
