package com.mall.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mall.entity.Model;
import com.mall.service.TestService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class BeanViewTest
{

	@Resource
	private TestService service;
	@Test  
    public void testGet(){  
        
		Model model = this.service.get(1L);
		System.out.println(model.getName());
         
    }
	
	@Test  
    public void testAdd(){  
        
        
		/*List<Article> list = this.service.getObject(1L);
        for(Article ov : list){  
            System.out.println(ov.getCreateDate());  
            System.out.println(ov.getTitle()); 
        }  */
		Model model = new Model();
		model.setName("呵呵");
		boolean b = this.service.add(model);
		System.out.println(b);
		System.out.println(model.getId());
		//System.out.println(this.service.get(model.getId()).getName());
         
    }  
		
}
