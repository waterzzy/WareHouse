package com.msb.controller;

import com.msb.pojo.Result;
import com.msb.pojo.Store;
import com.msb.page.Page;
import com.msb.service.StoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/store")
    @RestController
    public class StoreController {
        @Resource
        private  StoreService storeService;
        @RequestMapping("/store-page-list")
        public Result storePageList(Page page, Store store){
            //执行业务
            page = storeService.queryStorePage(page, store);
            //响应
            return Result.ok(page);
        }

        /**
         * 校验仓库的编号是否唯一
         * @param storeNum
         * @return
         */
        @RequestMapping("/store-num-check")
        public Result checkNum(String storeNum){

            return Result.ok("成功");
        }
        /**
         * 添加仓库
         * @param store
         * @return
         */
        @RequestMapping("/store-add")
        public Result addStore(@RequestBody Store store){
            System.out.println(store);
            Result result= storeService.saveStore(store);
            if (result.getCode() !=Result.CODE_OK){
                return Result.err(result.getCode(),result.getMessage());
            }
            return Result.ok("添加成功");
        }

        /**
         * 删除仓库的接口
         * @param storeId
         * @return
         * @PathVariable 注解的作用占位符,可以将请求中的参数设置到参数中
         */
        @RequestMapping("/store-delete/{storeId}")
        public Result deleteStoreById(@PathVariable Integer storeId){
            Integer row= storeService.deleteStoreById(storeId);
            if (row!=1){
                return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
            }else {
                return Result.ok("删除成功");
            }
        }

        /**
         * 修改仓库的信息
         * @param store
         * @return
         */
        @RequestMapping("/store-update")
        public Result updateStore(@RequestBody Store store){
            Result result = storeService.updateStore(store);
            return result;
        }

    /**
     * 将数据导出
     * @return
     */
        @RequestMapping("/exportTable")
        public Result exportTable(){
            //查询所有仓库
           List<Store> resultList=  storeService.queryAllStore();
            return Result.ok(resultList);
        }
    }
