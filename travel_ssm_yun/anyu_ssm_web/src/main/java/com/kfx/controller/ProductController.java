package com.kfx.controller;

import com.kfx.pojo.Product;
import com.kfx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询全部产品
     * @return
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(){
        List<Product> list = productService.finAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",list);
        modelAndView.setViewName("product-list");
        return modelAndView;

    }

    /**
     * 添加产品
     * @param product
     * @return
     */
    @RequestMapping("/saveProduct")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
