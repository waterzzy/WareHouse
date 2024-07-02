package com.msb.warehouse.controller;

import com.msb.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dh
 * @date 2024/7/2
 */

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    ProductService productService;



}
