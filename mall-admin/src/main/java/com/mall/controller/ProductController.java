package com.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Admin;
import com.mall.entity.Brand;
import com.mall.entity.Goods;
import com.mall.entity.GoodsResources;
import com.mall.entity.Parameter;
import com.mall.entity.ParameterGroup;
import com.mall.entity.Product;
import com.mall.entity.ProductCategory;
import com.mall.entity.ProductImage;
import com.mall.entity.ProductParameterValue;
import com.mall.entity.Specification;
import com.mall.entity.SpecificationValue;
import com.mall.entity.Tag;
import com.mall.pager.Pager;
import com.mall.service.AttributeService;
import com.mall.service.BrandService;
import com.mall.service.GoodsResourcesService;
import com.mall.service.GoodsService;
import com.mall.service.ParameterGroupService;
import com.mall.service.ParameterService;
import com.mall.service.ProductCategoryService;
import com.mall.service.ProductImageService;
import com.mall.service.ProductParameterValueService;
import com.mall.service.ProductService;
import com.mall.service.SpecificationService;
import com.mall.service.SpecificationValueService;
import com.mall.service.TagService;
import com.mall.util.ProductAdminUtils;

@Controller
@RequestMapping(value = "/product/")
public class ProductController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * 商品服务
	 */
	@Resource
	private ProductService productService;

	@Resource
	private ProductCategoryService categoryService;

	@Resource
	private BrandService brandService;

	@Resource
	private ParameterService paramService;

	@Resource
	private ParameterGroupService groupService;

	@Resource
	private AttributeService attributeService;

	@Resource
	private SpecificationService specifcationService;

	@Resource
	private SpecificationValueService specifcationValueService;

	@Resource
	private ProductImageService imageService;

	@Resource
	private ProductParameterValueService productParamService;

	@Resource
	private GoodsService goodsService;

	@Resource
	private TagService tagService;

	@Resource
	private GoodsResourcesService resourceService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(required = false, value = "pageNumber") Integer pageNumber,
			@RequestParam(required = false, value = "pageSize") Integer pageSize,
			@RequestParam(required = false, value = "searchValue") String searchValue,
			@RequestParam(required = false, value = "searchProperty") String searchProperty,
			@RequestParam(required = false, value = "orderProperty") String orderProperty,
			@RequestParam(required = false, value = "orderDirection") String orderDirection,
			@RequestParam(required = false, value = "marketable") String marketable,
			@RequestParam(required = false, value = "list") String list,
			@RequestParam(required = false, value = "top") String top,
			@RequestParam(required = false, value = "gift") String gift,
			@RequestParam(required = false, value = "stock") String stock) {
		
		ModelAndView mv = new ModelAndView("product/product/list");
		Pager<Product> pager = new Pager<>();
		pager.setOrderby("createDate desc");

		if (pageNumber != null) {
			pager.setCurrentIdx(pageNumber);
		}
		if (pageSize != null) {
			pager.setPageSize(pageSize);
		}

		/**
		 * 查询
		 */
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> likes = new HashMap<>();

		if (StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty)) {
			likes.put(this.getParameter("searchProperty"), searchValue.trim());
		}
		if (marketable != null && !marketable.equals("")) {
			map.put("marketable", marketable.equals("true"));
		}
		if (list != null && !list.equals("")) {
			map.put("list", list.equals("true"));
		}
		if (top != null && !top.equals("")) {
			map.put("top", top.equals("true"));
		}
		if (gift != null && !gift.equals("")) {
			map.put("gift", gift.equals("true"));
		}
		if (stock != null && !stock.equals("")) {
			map.put("stock", Integer.valueOf(stock));
		}
		pager.setFilter(map);
		pager.setLikes(likes);

		/**
		 * 排序
		 */
		if (StringUtils.isNotEmpty(orderProperty) && StringUtils.isNotEmpty(orderDirection)) {
			pager.setOrderby(orderProperty + " " + orderDirection);
		}
		mv.addObject("marketable", marketable);
		mv.addObject("list", list);
		mv.addObject("top", top);
		mv.addObject("gift", gift);
		mv.addObject("stock", stock);
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty", searchProperty);
		mv.addObject("productList", productService.findByPager(pager));
		mv.addObject("pager", pager);
		mv.addObject("orderProperty", orderProperty);
		mv.addObject("orderDirection", orderDirection);
		return mv;
	}

	/**
	 * 添加商品页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("product/product/add");
		mv.addObject("categoryList", this.categoryService.getAll("parent asc, orders asc"));
		mv.addObject("brandList", this.brandService.getAll());
		mv.addObject("specifications", this.specifcationService.getAll("orders asc"));
		Map<String, Object> filter = new HashMap<>();
		filter.put("type", 2);
		mv.addObject("tags", this.tagService.getList(filter, "orders asc"));
		// mv.addObject("authorities", this.getAdmin().getAuthorities());

		return mv;
	}

	/**
	 * 产品添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newProduct(@RequestParam("productCategoryId") Long categoryId,
			@RequestParam("brandId") Long brandId) {
		Product model = ProductAdminUtils.createNewProduct();
		ProductAdminUtils.fillProduct(model, this.getRequest());

		ProductCategory category = categoryService.get(categoryId);
		if (category != null) {
			model.setProductCategory(category);
		} else {
			logger.warn("添加商品({})时未选择商品分类", model.getName());
		}

		Brand brand = brandService.get(brandId);
		if (brand != null) {
			model.setBrand(brand);
		}

		// 保存基本参数
		// 添加产品
		Goods goods = new Goods();
		this.goodsService.add(goods);
		// 保存商品资源
		this.saveProductResources(goods);

		Long[] tagIds = this.getLongParameters("tagIds");
		model.setGoods(goods);
		if (this.productService.add(model)) {
			// 保存标签
			this.saveProductTags(model, new HashSet<Tag>(this.tagService.get(tagIds)));
			// 保存图片
			this.saveProductImages(model);
			// 保存参数
			this.saveProductParameters(model);
		}

		Long[] specificationIds = this.getLongParameters("specificationIds");
		Map<Long, Long[]> specValues = new HashMap<>();
		int count = 0;
		for (Long id : specificationIds) {
			Long[] valueIds = this.getLongParameters("specification_" + id);
			if (valueIds.length > 0) {
				count = valueIds.length;
				specValues.put(id, valueIds);
			}
		}
		// 保存规格
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				Map<Long, Long> specMap = new HashMap<>();
				for (Specification spec : model.getSpecifications()) {
					specMap.put(spec.getId(), specValues.get(spec.getId())[i]);
				}
				Product model2 = null;
				if (i == 0) {
					model2 = model;
				} else {
					model2 = ProductAdminUtils.clone(model);

					if (this.productService.add(model2)) {
						// 保存图片
						this.saveProductImages(model2);
						// 保存产品参数
						this.saveProductParameters(model2);
						// 保存产品规格值
						this.saveProductSpecification(model2, specMap);
						// 保存标签
						this.saveProductTags(model2, new HashSet<Tag>(model.getTags()));
					}
				}

				Map<Long, Long> valuesMap = new HashMap<>();
				for (Long id : specValues.keySet()) {
					valuesMap.put(id, specValues.get(id)[i]);
				}
				this.saveProductSpecification(model2, valuesMap);
			}
		}
		return "redirect:/product/list.do";
	}

	/**
	 * 保存产品数据
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveProduct(@RequestParam("id") Long productId, @RequestParam("productCategoryId") Long categoryId,
			@RequestParam("brandId") Long brandId) {
		Product model = this.productService.get(productId);
		Assert.notNull(model);

		ProductAdminUtils.fillProduct(model, this.getRequest());

		ProductCategory category = categoryService.get(categoryId);
		if (category != null) {
			model.setProductCategory(category);
		}

		Brand brand = brandService.get(brandId);
		if (brand != null) {
			model.setBrand(brand);
		}

		// 保存基本参数
		Long[] tagIds = this.getLongParameters("tagIds");
		this.saveProductTags(model, new HashSet<Tag>(this.tagService.get(tagIds)));
		this.saveProductImages(model);
		this.saveProductParameters(model);
		// 保存商品资源
		this.saveProductResources(model.getGoods());

		// 保存规格
		Set<Product> products = new HashSet<>(model.getGoods().getProducts());
		// 清除多余规格
		Long[] SpecificationIds = this.getLongParameters("specificationIds");

		// save product specifications
		for (Product product : products) {
			Map<Long, Long> specValues = new HashMap<>();
			String pid = this.getParameter("product_" + product.getId());
			if (pid == null) {
				this.productService.delete(product.getId());
			} else {
				for (Long specificationId : SpecificationIds) {
					Long value = this.getLongParameter("specification_" + product.getId() + "_" + specificationId);
					if (value != null) {
						specValues.put(specificationId, value);
					}
				}
				this.saveProductSpecification(product, specValues);
			}
		}

		// 新建产品
		Map<Long, Long[]> specValues = new HashMap<>();
		int rows = 0;
		// 存储参数
		for (Specification specification : model.getSpecifications()) {
			Long[] values = this.getLongParameters("specification_" + specification.getId());
			if (values != null) {
				specValues.put(specification.getId(), values);
				rows = values.length;
			}
		}
		// 添加产品
		for (int i = 0; i < rows; i++) {
			Map<Long, Long> specMap = new HashMap<>();
			for (Specification spec : model.getSpecifications()) {
				specMap.put(spec.getId(), specValues.get(spec.getId())[i]);
			}

			Product model2 = ProductAdminUtils.clone(model);

			if (this.productService.add(model2)) {
				// 保存图片
				this.saveProductImages(model2);
				// 保存产品参数
				this.saveProductParameters(model2);
				// 保存产品规格值
				this.saveProductSpecification(model2, specMap);
				// 保存标签
				this.saveProductTags(model2, new HashSet<Tag>(model.getTags()));
			}
		}

		return "redirect:/product/list.do";
	}

	/**
	 * 编辑产品
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editProduct(@RequestParam("id") Long productId) {
		ModelAndView mv = new ModelAndView("admin/product/product_edit");
		mv.addObject("authorities", this.getAdmin().getAuthorities());
		// 加载产品分类列表
		mv.addObject("categoryList", this.categoryService.getAll("parent asc, orders asc"));
		// 加载产品品牌列表
		mv.addObject("brands", this.brandService.getAll("orders asc"));
		// 加载规格列表
		mv.addObject("specifications", this.specifcationService.getAll("orders asc"));
		// 加载规格值列表
		mv.addObject("specificationValues", this.specifcationValueService.getAll("orders asc"));
		// 加载标签列表
		Map<String, Object> tagFilter = new HashMap<>();
		tagFilter.put("type", 2);
		mv.addObject("tags", this.tagService.getList(tagFilter, "orders asc"));
		// 加载属性列表
		mv.addObject("attributes", this.attributeService.getAll());

		Product product = this.productService.get(productId);
		if (product != null) {
			mv.addObject("product", product);
			// 加载产品参数
			mv.addObject("productParameters", this.getProductParamList(product));
			// 加载产品规格值列表
			mv.addObject("others", product.getSlibings());

			// Warehouse warehouse = null;
			try {
				// warehouse = Warehouse.forValue(product.getWarehouse());
			} catch (IllegalArgumentException ex) {
				// logger.error("非法ERP仓库值:{}", product.getWarehouse());
			}

			/*
			 * if(warehouse != null) { mv.addObject("cWarehouse", warehouse); }
			 */

		}
		return mv;
	}

	/**
	 * 获取产品参数列表
	 * 
	 * @param product
	 * @return
	 */
	private List<Map<String, Object>> getProductParamList(Product product) {
		Assert.notNull(product);
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		Assert.notNull(product.getProductCategory());
		List<ParameterGroup> groupList = this.groupService.getList("productCategory", product.getProductCategory(),
				"orders asc");
		for (ParameterGroup group : groupList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupName", group.getName());
			List<Parameter> paramList = this.paramService.getListFromProperty("parameterGroup", group.getId());
			List<Map<String, Object>> params = new LinkedList<Map<String, Object>>();
			for (Parameter param : paramList) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("id", param.getId());
				parameter.put("name", param.getName());
				ProductParameterValue paramValue = this.productParamService.get(product.getId(), param.getId());
				if (paramValue != null)
					parameter.put("value", paramValue.getValue());
				params.add(parameter);
			}
			map.put("params", params);
			list.add(map);
		}

		return list;
	}

	// 删除产品
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteProduct(@RequestParam("ids") Long[] ids, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		int errCode = this.productService.delete(ids) ? 0 : -1;
		try {
			rootNode.put("errCode", errCode);
			mapper.writeValue(response.getOutputStream(), rootNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存产品规格值
	 * 
	 * @param product
	 */
	private void saveProductSpecification(Product product, Map<Long, Long> specValues) {
		if (product.getId() != null && specValues != null) {
			product.getSpecifications().clear();
			product.getSpecificationValues().clear();
			Set<Long> keys = specValues.keySet();
			for (Long key : keys) {
				product.getSpecifications().add(this.specifcationService.get(key));
				product.getSpecificationValues().add(this.specifcationValueService.get(specValues.get(key)));
			}
			this.productService.update(product);
		}
	}

	/**
	 * 保存商品标签
	 * 
	 * @param product
	 * @param tags
	 */
	private void saveProductTags(Product product, Set<Tag> tags) {
		product.getTags().clear();
		if (tags != null) {
			product.getTags().addAll(tags);
		}
		this.productService.update(product);
	}

	/**
	 * 验证产品名称
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateName.do", method = RequestMethod.POST)
	public void validateName(HttpServletResponse response) throws IOException {
		response.reset();
		response.setContentType("text/html; charset=UTF-8");

		List<Product> list = this.productService.getAll();
		String name = this.getParameter("name");
		for (Product p : list) {
			if (name.equals(p.getName())) {
				response.getWriter().print(false);
				return;
			}
		}
		response.getWriter().print(true);
	}

	/**
	 * 产品参数查询接口
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/queryParamters.do")
	public void queryParameters(@RequestParam("id") Long categoryId, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		ObjectMapper mapper = new ObjectMapper();
		ProductCategory category = categoryService.get(categoryId);
		if (category != null) {
			// 本身对应的参数
			List<ParameterGroup> groups = this.groupService.getListFromProperty("productCategory", category);
			try {
				JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(response.getOutputStream());
				generator.writeStartArray();
				for (ParameterGroup group : groups) {
					generator.writeStartObject();
					generator.writeObjectField("groupName", group.getName());
					generator.writeArrayFieldStart("parameters");
					List<Parameter> parameters = this.paramService.getListFromProperty("parameterGroup", group.getId());
					for (Parameter param : parameters) {
						generator.writeObject(param);
					}
					generator.writeEndArray();
					generator.writeEndObject();
				}
				generator.writeEndArray();
				generator.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存产品参数
	 * 
	 * @param product
	 */
	private void saveProductParameters(Product product) {
		// 商品参数
		String[] params = this.getParameters("param");
		String[] paramIds = this.getParameters("paramId");
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				ProductParameterValue parameterValue = this.productParamService.get(product.getId(),
						Long.valueOf(paramIds[i]));
				if (parameterValue == null) {
					parameterValue = new ProductParameterValue();
					parameterValue.setProducts(product.getId());
					parameterValue.setParameters(Long.valueOf(paramIds[i]));
					this.productParamService.add(parameterValue);
				}
				parameterValue.setValue(params[i]);
				this.productParamService.update(parameterValue);
			}
		}
	}

	/**
	 * 保存产品图片
	 * 
	 * @param product
	 */
	private void saveProductImages(Product product) {
		Assert.notNull(product);
		// 更新已存在图片
		Long[] imageIds = this.getLongParameters("imageId");
		Set<ProductImage> images = product.getImages();
		for (Long imageId : imageIds) {
			ProductImage image = this.imageService.get(imageId);
			if (image != null && images != null) {
				images.remove(image);
				// 更新
				image.setImage(this.getParameter("image_" + image.getId()));
				image.setTitle(this.getParameter("imageTitle_" + image.getId()));
				if (StringUtils.isNotEmpty(this.getParameter("imageOrder_" + image.getId()))) {
					image.setOrders(this.getIntParameter("imageOrder_" + image.getId()));
				}
				imageService.update(image);
			}
		}
		// 删除已删除图片
		if (images != null) {
			Iterator<ProductImage> iter = images.iterator();
			while (iter.hasNext()) {
				ProductImage image = iter.next();
				iter.remove();
				imageService.delete(image.getId());
			}
		}
		// 增加新图片
		String[] newImages = this.getParameters("image");
		String[] titles = this.getParameters("imageTitle");
		Integer[] orders = this.getIntegerParameters("imageOrder");
		if (newImages != null && newImages.length > 0) {
			for (int i = 0; i < newImages.length; i++) {
				ProductImage image = new ProductImage();
				image.setImage(newImages[i]);
				image.setTitle(titles[i]);
				image.setOrders(orders[i]);
				image.setProducts(product.getId());
				this.imageService.add(image);
			}
		}
	}

	/**
	 * 保存商品资源
	 * 
	 * @param resources
	 * @param goods
	 */
	private void saveProductResources(Goods goods) {
		Assert.notNull(goods);
		List<GoodsResources> oldeResources = new LinkedList<>(goods.getResources());
		// 更新已有资源
		Long[] resourceIds = this.getLongParameters("resourceId");
		if (resourceIds != null && resourceIds.length > 0) {
			List<GoodsResources> list = this.resourceService.get(resourceIds);
			for (GoodsResources res : list) {
				res.setUrl(this.getParameter("resourceUrl_" + res.getId()));
				res.setTitle(this.getParameter("resourceTitle_" + res.getId()));
				res.setOrders(this.getIntParameter("resourceOrder_" + res.getId()));
				this.resourceService.update(res);
				oldeResources.remove(res);
			}
		}
		// 删除资源
		Iterator<GoodsResources> iter = oldeResources.iterator();
		while (iter.hasNext()) {
			GoodsResources res = iter.next();
			this.resourceService.delete(res.getId());
			// 清除物理文件
			String realFile = this.getServletContext().getRealPath(res.getUrl());
			try {
				File file = new File(realFile);
				if (file.exists())
					file.delete();
			} catch (Exception ex) {

			}
		}
		// 保存新商品资源商品资源
		String[] resourceUrls = this.getParameters("resource");
		String[] resourceTitles = this.getParameters("resourceTitle");
		Integer[] resourceOrders = this.getIntegerParameters("resourceOrder");
		if (resourceUrls != null && resourceUrls.length > 0) {
			for (int i = 0; i < resourceUrls.length; i++) {
				GoodsResources resource = new GoodsResources();
				resource.setUrl(resourceUrls[i]);
				resource.setTitle(resourceTitles[i]);
				resource.setName(resourceTitles[i]);
				resource.setOrders(resourceOrders[i]);
				resource.setGoods(goods);
				this.resourceService.add(resource);
			}
		}
	}


	/**
	 * 产品规格查询接口 请求参数: id 返回数据: {id: xx, name: xx, values:[{id: xx, name: xx},
	 * ...]}
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/querySpecificationValue.do")
	public void querySpecification(@RequestParam("id") Long id, HttpServletResponse response) {
		Assert.notNull(id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		ObjectMapper mapper = new ObjectMapper();
		Specification specification = this.specifcationService.get(Long.valueOf(id));
		Assert.notNull(specification);
		try {
			JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(response.getOutputStream());
			generator.writeStartObject();
			generator.writeObjectField("id", specification.getId());
			generator.writeObjectField("name", specification.getName());
			generator.writeArrayFieldStart("values");
			for (SpecificationValue specificationValue : specification.getValues()) {
				generator.writeStartObject();
				generator.writeObjectField("id", specificationValue.getId());
				generator.writeObjectField("name", specificationValue.getName());
				generator.writeEndObject();
			}
			generator.writeEndArray();
			generator.writeEndObject();
			generator.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addFile.do", method = RequestMethod.POST)
	public void uplodFile(@RequestParam("file") CommonsMultipartFile file, HttpServletResponse response) {
		int errCode = 0;
		ObjectMapper mapper = new ObjectMapper();
		JsonGenerator generator = null;

		String ip = this.getRemoteIp();
		Admin admin = this.getAdmin();
		Map<String, String> map = null;
		if (admin == null) {
			errCode = 1;
			logger.warn("用户({})未登录上传文件", ip);
		}

		try {
			if (errCode == 0) {
				FileInputStream is = (FileInputStream) file.getInputStream();
				Workbook workbook = WorkbookFactory.create(is);
				// 第一个sheet
				Sheet sheet = workbook.getSheetAt(0);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				map = new HashMap<String, String>();
				for (int r = 0; r < rowCount; r++) {
					Row row = sheet.getRow(r);
					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
					// 遍历每一列
					String key = "";
					String value = "";
					if (cellCount > 1) {
						for (int c = 0; c <= 1; c++) {
							Cell cell = row.getCell(c);
							int cellType = 5;
							if (cell != null) {
								cellType = cell.getCellType();
							}

							String cellValue = null;
							switch (cellType) {
							case Cell.CELL_TYPE_STRING: // 文本
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								cellValue = String.valueOf(cell.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_BOOLEAN: // 布尔型
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_BLANK: // 空白
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_ERROR: // 错误
								cellValue = "错误";
								break;
							case Cell.CELL_TYPE_FORMULA: // 公式
								cellValue = "错误";
								break;
							default:
								cellValue = "错误";
							}

							if (c == 0) {
								key = cellValue;
							} else {
								value = cellValue;
							}
						}
					}

					if (key != null && key.trim().length() > 0) {
						map.put(key, value);
					}

				}
			}
		} catch (NullPointerException ex) {
			errCode = 1110;
		} catch (IllegalArgumentException ex) {
			errCode = 1130;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				generator = mapper.getJsonFactory().createJsonGenerator(response.getOutputStream());
				generator.writeStartObject();
				generator.writeObjectField("errCode", errCode);
				if (errCode == 0 && map != null && map.size() > 0) {
					generator.writeArrayFieldStart("maps");
					for (String key : map.keySet()) {
						generator.writeStartObject();
						generator.writeObjectField("key", key);
						generator.writeObjectField("value", map.get(key));
						generator.writeEndObject();
					}
					generator.writeEndArray();

				}
				generator.writeEndObject();
				generator.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
