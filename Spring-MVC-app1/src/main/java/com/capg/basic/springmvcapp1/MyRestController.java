package com.capg.basic.springmvcapp1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/api")
@Api(value="Online Product Store", description="Implementations of operations in online App")
public class MyRestController {
	
	@Autowired
	ProductDAOImpl dao;
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "Hello";
	}
    
	//            localhost:9090:\product\10000\40000
	//  endpoint :- www.abc.com\product\10000\40000
	@ApiOperation(value = "Method to fetch products between two ranges",response = List.class)
	@GetMapping("/product/{r1}/{r2}")
	public List<Product> getProductsbyrange(@PathVariable int r1 ,@PathVariable int r2)
	{
		return dao.getProductsByRange(r1, r2);
	}
	
	// ..../product/101
	@GetMapping("/product/{searchid}")
	@ApiOperation(value = "Method to fetch product based on ID",response = Product.class)
	public Product getProductByid(@PathVariable int searchid)
	{
		return dao.getProductById(searchid);
	}
	
	@GetMapping("/delete/{id}")
	public Product doDelete(@PathVariable int id)
	{
		System.out.println("--->> inside REST delete "+id);
		Product p = dao.doDeleteById(id);
		System.out.println("---->> REST p "+p);
		if(p != null) return p;
		else return new Product(0, "Invalid Product Id", 0);
	}
	
}
