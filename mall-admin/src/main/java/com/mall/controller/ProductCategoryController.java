package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.ProductCategory;
import com.mall.entity.Specification;
import com.mall.pager.Pager;
import com.mall.service.BrandService;
import com.mall.service.ProductCategoryService;
import com.mall.service.SpecificationService;
import com.mall.service.SpecificationValueService;


@Controller
@RequestMapping(value = "/product_category/")
public class ProductCategoryController extends BaseController
{

	private static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Resource
	private ProductCategoryService service;
	@Resource
	private BrandService brandService;
	@Resource
	private SpecificationService specificationService;
	@Resource
	private SpecificationValueService specificationValueService;
	
	/**
	 * 商品分类列表页
	 * @param pageNumber     当前页
	 * @param pageSize       页面大小
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, value = "pageNumber") Integer pageNumber,
			@RequestParam(required = false, value = "pageSize") Integer pageSize)
	{
		ModelAndView mv = new ModelAndView("product/category/list");		
		Pager<ProductCategory> pager = new Pager<>();
		pager.setOrderby("parent asc,orders asc");
		if(pageNumber!= null)
		{
			pager.setCurrentIdx(pageNumber);
		}
			
		if(pageSize != null)
		{
			pager.setPageSize(pageSize);
		}
		mv.addObject("categoryList", this.service.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}

	/**
	 * 添加商品分类
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView("product/category/add");
		mv.addObject("categoryList", this.service.getAll("orders asc"));
		mv.addObject("brands", this.brandService.getAll("orders asc"));
		
		List<Specification> listSpec = specificationService.getAll();
		mv.addObject("listSpec", listSpec);
		
		return mv;
	}

	/**
	 * 添加分类
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newCategory(@RequestParam(value="name") String name,
			@RequestParam(required=false, value="parentId") Long parentId,
			@RequestParam(required=false, value="seoTitle") String seoTitle,
			@RequestParam(required=false, value="seoKeywords") String seoKeywords,
			@RequestParam(required=false, value="seoDescription") String seoDescription,
			@RequestParam(required=false, value="order") Integer order,
			@RequestParam(required=false, value="parameter") String[] parameters,
			@RequestParam(required=false, value="brandIds") Long[] brandIds)
	{
		ProductCategory model = new ProductCategory();
		model.setName(name);
		if (parentId != null)
		{
			ProductCategory parent = this.service.get(parentId);
			model.setParent(parent);
			
			//处理分类路径treePath	
			if(parent != null)
			{
				model.setTreePath(parent.getTreePath() + parent.getId() + ",");
			}
			else
			{
				model.setTreePath(",");
			}
		}
		model.setSeoTitle(seoTitle);
		model.setSeoKeywords(seoKeywords);
		model.setSeoDescription(seoDescription);
		if(order != null){
			model.setOrders(order);
		}
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		
		/*if(brandIds != null && brandIds.length > 0)
		{
			Set<Brand> brands = new HashSet<>();
			brands.addAll(this.brandService.get(brandIds));
			model.setBrands(brands);
		}*/
		
		this.service.add(model);

		//保存筛选参数
		/*if(parameters != null && parameters.length > 0)
		{
			Collections.addAll(model.getFilterParameter(), parameters);
		}*/
		
		
		//保存相关规格参数
		/*Specification specification = null;
		SpecificationValue specificationValue = null;
		Long[] specIds = this.getLongParameters("specId");
		
		if(specIds != null && specIds.length>0){
			for(Long id : specIds){
				
				specification =  this.specificationService.get(id);
				model.getSpecs().add(specification);
				
				Long[] specValueIds = this.getLongParameters("specId_" + id);
				for(Long vid : specValueIds){
					
					specificationValue = this.specificationValueService.get(vid);
					model.getSpecValues().add(specificationValue);
				}
			}
			
			this.service.update(model);
		}*/
		
		return "redirect:/product_category/list.do";
	}

	/**
	 * 保存分类
	 * @param id		商品分类ID
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveCategory(@RequestParam("id") Long categoryId,
			@RequestParam(value="name") String name,
			@RequestParam(required=false, value="parentId") Long parentId,
			@RequestParam(required=false, value="seoTitle") String seoTitle,
			@RequestParam(required=false, value="seoKeywords") String seoKeywords,
			@RequestParam(required=false, value="seoDescription") String seoDescription,
			@RequestParam(required=false, value="order") Integer order,
			@RequestParam(required=false, value="parameter") String[] parameters,
			@RequestParam(required=false, value="brandIds") Long[] brandIds)
	{
		ProductCategory model = this.service.get(categoryId);
		if (model != null)
		{
			model.setName(name);			
			model.setSeoTitle(seoTitle);
			model.setSeoKeywords(seoKeywords);
			model.setSeoDescription(seoDescription);
            if (order != null)
			{
				model.setOrders(order);
			}
			model.setModifyDate(new Date());

			//model.setBrands(new HashSet<Brand>());
			//model.getBrands().addAll(this.brandService.get(brandIds));
			
			//model.setSpecs(new HashSet<Specification>());
			//model.setSpecValues(new HashSet<SpecificationValue>());
			
			/**
			 * treePath以及父类
			 */
			ProductCategory parent = this.service.get(parentId);

			if(parent != null)
			{
				Set<ProductCategory> set = this.service.getChildren(model);
				set.add(model);
				if(set.contains(parent))
				{
					logger.error("商品{}是{}其子类", parent.getId(),model.getId());
					model.setParent(null);
					model.setTreePath(",");
				}
				else
				{
					model.setParent(parent);				
					model.setTreePath(parent.getTreePath() + parent.getId() + ",");
				}
				
			}
			else
			{
				model.setParent(null);
				model.setTreePath(",");
			}
			
			//保存筛选参数
			/*model.getFilterParameter().clear();
			if(parameters != null && parameters.length > 0)
			{
				Collections.addAll(model.getFilterParameter(), parameters);
			}*/
			
			/**
			 * 更新子类treePath
			 */
			if(this.service.update(model))
			{
				Queue<ProductCategory> queue = new LinkedList<>();
				if(model != null)
				{
					queue.addAll(model.getChildren());
				}
				
				while(!queue.isEmpty())
				{
					ProductCategory category = queue.poll();
					
					if(category != null && !category.getChildren().isEmpty())
					{
						queue.addAll(category.getChildren());
					}
					
					ProductCategory parent1 = category.getParent();
					if(parent1 != null)
					{
						category.setTreePath(parent1.getTreePath() + parent1.getId() + ",");
					}
					else
					{
						category.setTreePath(",");
					}
					if(!this.service.update(category))
					{
						logger.error("商品分类:{}数据库更新失败", category.getId());
					}
					
				}
			}
			
			
			//保存相关规格参数
			/*Specification specification = null;
			SpecificationValue specificationValue = null;
			Long[] specIds = this.getLongParameters("specId");
			if(specIds != null && specIds.length>0){
				
				for(Long id : specIds){
					
					specification =  this.specificationService.get(id);
					model.getSpecs().add(specification);
					
					Long[] specValueIds = this.getLongParameters("specId_" + id);
					for(Long vid : specValueIds){
						
						specificationValue = this.specificationValueService.get(vid);
						model.getSpecValues().add(specificationValue);
					}
				}
				
				this.service.update(model);
			}*/
			
		}
		return "redirect:list.do";
	}

	/**
	 * 编辑分类
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editCategory(@RequestParam("id") Long categoryId)
	{
		ModelAndView mv = new ModelAndView("product/category/add");
		ProductCategory category = this.service.get(categoryId);
		if (category != null)
		{
			mv.addObject("category", category);
			List<ProductCategory> list = this.service.getAll("parent asc,orders asc");
			Set<ProductCategory> set =this.service.getChildren(category);
			set.add(category);
			list.removeAll(set);
			mv.addObject("categoryList", list);
		}
		else
		{
			logger.error("分类({})不存在", categoryId);
		}
		
		mv.addObject("brands", this.brandService.getAll("orders asc"));
		
		List<Specification> listSpec = specificationService.getAll();
		mv.addObject("listSpec", listSpec);
		
		return mv;
	}

	// 删除分类
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteCategory(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for (int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		int errCode = this.service.delete(deleteIds) ? 0 : -1;
		rootNode.put("errCode", errCode);
		try
		{
			mapper.writeValue(response.getOutputStream(), rootNode);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 验证名称是否存在
	@RequestMapping(value = "/validate.do", method = RequestMethod.POST)
	public void validateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.reset();
		response.setContentType("text/html; charset=UTF-8");

		List<ProductCategory> list = this.service.getAll();
		String name = this.getParameter("name");
		for (ProductCategory pc : list)
		{
			if (name.equals(pc.getName()))
			{
				response.getWriter().print(false);
				return;

			}
		}
		response.getWriter().print(true);
	}
}
