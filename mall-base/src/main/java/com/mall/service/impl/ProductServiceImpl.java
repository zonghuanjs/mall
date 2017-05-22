package com.mall.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mall.bean.SystemConfig;

import com.mall.dao.ProductDao;
import com.mall.entity.CartItem;
import com.mall.entity.Goods;
import com.mall.entity.Lottery;
import com.mall.entity.Member;
import com.mall.entity.OrderItem;
import com.mall.entity.Product;
import com.mall.entity.ProductCategory;
import com.mall.entity.ProductImage;
import com.mall.entity.ProductParameterValue;
import com.mall.entity.SpecificationValue;
import com.mall.pager.ListFilter;
import com.mall.pager.ListPager;
import com.mall.pager.Pager;
import com.mall.service.AttributeService;
import com.mall.service.CartItemService;
import com.mall.service.GoodsService;
import com.mall.service.LotteryService;
import com.mall.service.MemberService;
import com.mall.service.OrderItemService;
import com.mall.service.ProductCategoryService;
import com.mall.service.ProductImageService;
import com.mall.service.ProductParameterValueService;
import com.mall.service.ProductService;
import com.mall.service.SpecificationValueService;
import com.mall.util.CommonUtil;
import com.mall.util.SystemConfigUtil;

/**
 * 
 * @author huan.zong
 * 
 */
@Repository
public class ProductServiceImpl extends BaseServiceImpl<Long, Product> implements ProductService {
	// 删除参数及参数组
	@Autowired
	private GoodsService goodsService;
	// 删除属性及属性可选项
	@Autowired
	private AttributeService attributeService;
	// 删除图片
	@Autowired
	private ProductImageService imageService;

	@Autowired
	private SpecificationValueService specValueService;

	// 删除相关商品参数
	@Autowired
	private ProductParameterValueService productParamService;

	@Autowired
	private MemberService memberService;// 会员服务

	@Autowired
	private OrderItemService orderItemService;// 订单项服务

	@Autowired
	private CartItemService cartItemService;// 购物车项服务

	@Autowired
	private ProductDao productDao;//

	@Autowired
	private ProductCategoryService productCategoryService;// 产品类别

	@Resource
	private LotteryService ltyService;// 投奖服务

	@Override
	public boolean add(Product model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());

			SystemConfig config = SystemConfigUtil.getSystemConfig();
			// 设置产品编号
			model.setSn(CommonUtil.getProductSn());
			if (StringUtils.isEmpty(model.getThumbnail())) {
				model.setThumbnail(config.getDefaultThumbnailProductImage());
			}
			if (StringUtils.isEmpty(model.getMediumImage())) {
				model.setMediumImage(config.getDefaultMediumProductImage());
			}
			if (StringUtils.isEmpty(model.getImage())) {
				model.setImage(config.getDefaultlargeProductImage());
			}
		}
		return super.add(model);
	}

	@Override
	public boolean update(Product model) {
		if (model != null) {
			model.setModifyDate(new Date());

			SystemConfig config = SystemConfigUtil.getSystemConfig();
			if (StringUtils.isEmpty(model.getThumbnail())) {
				model.setThumbnail(config.getDefaultThumbnailProductImage());
			}
			if (StringUtils.isEmpty(model.getMediumImage())) {
				model.setMediumImage(config.getDefaultMediumProductImage());
			}
			if (StringUtils.isEmpty(model.getImage())) {
				model.setImage(config.getDefaultlargeProductImage());
			}
		}
		return super.update(model);
	}

	@Override
	public boolean delete(Long id) {
		Product model = this.get(id);
		if (model != null) {
			preRemove(id);
		}

		boolean delgoods = false;
		Goods goods = model.getGoods();
		// 清理Goods
		if (goods != null && model.getSlibings().size() == 0) {
			delgoods = true;
		}
		boolean ret = super.delete(id);

		if (ret && delgoods) {
			goodsService.delete(goods.getId());
		}

		return ret;
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids) {
			preRemove(id);
		}
		return super.delete(ids);
	}

	@Override
	public List<Product> findByPager(Pager<Product> pager) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public List<Product> getListAccordingSales(Map<String, Object> filter, String orderby, int topNumber) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getListAccordingSales(filter, orderby, topNumber);
	}

	/**
	 * 商品预删除
	 * 
	 * @param id
	 *            商品ID
	 */
	private void preRemove(Long id) {
		Product model = this.get(id);
		if (model != null) {
			// 清理商品图片
			for (ProductImage image : model.getImages()) {
				this.imageService.delete(image.getId());
			}

			// 删除关联参数值
			List<ProductParameterValue> list = this.productParamService.getListFromProperty("products", model.getId());
			for (ProductParameterValue pv : list)
				this.productParamService.delete(pv.getId());

			// 删除用户收藏
			if (!model.getMembers().isEmpty()) {
				for (Member member : model.getMembers()) {
					member.getFavoriteProducts().remove(model);
					this.memberService.update(member);
				}
			}
			// 清理购物车
			if (!model.getCartItems().isEmpty()) {
				for (CartItem item : model.getCartItems()) {
					this.cartItemService.delete(item.getId());
				}
			}

			// 处理订单项
			if (!model.getOrderItems().isEmpty()) {
				for (OrderItem item : model.getOrderItems()) {
					item.setProduct(null);
					this.orderItemService.update(item);
				}
			}

			// 处理投奖活动
			List<Lottery> lotterys = model.getLotterys();
			if (!lotterys.isEmpty()) {
				for (Lottery lty : lotterys) {
					lty.setProduct(null);
					ltyService.update(lty);
				}
			}
		}
	}

	/**
	 * 根据商品名检索
	 */
	@Override
	public List<Product> getListPropertyNameLike(Map<String, Object> filter) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getListPropertyNameLike(filter);
	}

	/**
	 * 多条件过滤
	 */
	@Override
	public long getCount(Map<String, Object> filter) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getCount(filter);

	}

	/**
	 * 可以按照属性值like进行查询 并带分页功能
	 * 
	 * @param eqFilter
	 * @param likeFilter
	 * @param inSet
	 * @param orderby
	 * @param first
	 * @param count
	 * @return
	 */
	@Override
	public List<Product> getList(Map<String, Object> eqFilter, Map<String, Object> likeFilter,
			Map<String, Set<Object>> inSet, String orderby, int first, int count) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getList(eqFilter, likeFilter, inSet, orderby, first, count);
	}

	/**
	 * 获取总数
	 * 
	 * @param eqFilter
	 * @param likeFilter
	 * @return
	 */
	@Override
	public long getCount(Map<String, Object> eqFilter, Map<String, Object> likeFilter) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getCount(eqFilter, likeFilter);
	}

	@Override
	public List<Product> getList(Map<String, Object> eqFilter, Map<String, Object> likeFilter,
			Map<String, Set<Object>> inSet, Map<String, List<Double>> filterBetween, String orderby, int first,
			int count) {
		ProductDao dao = (ProductDao) this.getDao();
		return dao.getList(eqFilter, likeFilter, inSet, filterBetween, orderby, first, count);
	}

	@Override
	public List<Product> findByPager(Pager<Product> pager, Long[] valueIds, Double[] prices) {
		ProductDao dao = (ProductDao) this.getDao();

		List<SpecificationValue> specValues = specValueService.get(valueIds);

		Map<String, List<Double>> betweenFilter = new HashMap<>();
		List<Double> priceList = new ArrayList<>();
		if (prices != null) {
			priceList.addAll(Arrays.asList(prices));
		}
		betweenFilter.put("price", priceList);

		// 获取原始数据
		List<Product> list = dao.getList(pager.getFilter(), null, pager.getInset(), betweenFilter, pager.getOrderby(),
				0, 0);
		ListPager<Product> listPager = new ListPager<>(list);
		listPager.filter(new SpecificationFilter(specValues));

		pager.setTotalCount(listPager.getCount());
		return listPager.getPage(pager.getCurrentIdx(), pager.getPageSize());
	}

	/**
	 * 商品规格过滤器
	 * 
	 * @author ChenMingcai
	 *
	 */
	class SpecificationFilter implements ListFilter {
		public List<SpecificationValue> specValues;

		public SpecificationFilter(List<SpecificationValue> list) {
			this.specValues = list;
		}

		@Override
		public boolean filter(Object object) {
			Product product = (Product) object;
			Iterator<SpecificationValue> iter = specValues.iterator();
			while (iter.hasNext()) {
				if (!product.getSpecificationValues().contains(iter.next())) {
					return true;
				}
			}
			return false;
		}

	}

	@Override
	public List<Product> exactSearch(String key, String brandIds, String categoryIds, String specValueIds,
			String orderkey, boolean isdesc, int pageNumber, int pageSize, double low, double high,
			List<Integer> total) {

		if (key != null && key.trim().length() > 0) {
			// 剔除TEKISM 和 特科芯 关键字
			Pattern pattern = Pattern.compile("TEKISM", Pattern.CASE_INSENSITIVE);
			Matcher m = pattern.matcher(key);
			key = m.replaceAll("");
			key = key.replaceAll("特科芯", "");
			key = key.replaceAll("\\s+", " ");
		}

		// 品牌用sql in（） 处理
		if (brandIds != null && brandIds.trim().length() > 0) {

			if (brandIds.endsWith(",")) {

				brandIds = brandIds.substring(0, brandIds.length() - 1);
			}

			brandIds = " (" + brandIds + ") ";
		}

		// 类别处理 加载子类
		if (categoryIds != null && categoryIds.trim().length() > 0) {

			ProductCategory category = this.productCategoryService.get(Long.valueOf(categoryIds));

			Set<ProductCategory> categorys = category.getChildren();
			if (categorys == null) {
				categorys = new HashSet<ProductCategory>();
				categorys.add(category);
			} else {
				categorys.add(category);
			}

			categoryIds = " (";
			for (ProductCategory ca : categorys) {
				categoryIds += ca.getId() + ",";
			}

			categoryIds = categoryIds.substring(0, categoryIds.length() - 1) + ") ";
		}

		return productDao.exactSearch(key, brandIds, categoryIds, specValueIds, orderkey, isdesc, pageNumber, pageSize,
				low, high, total);
	}

	@Override
	public Map<String, Object> getSomeInfo() {

		return productDao.getSomeInfo();
	}

	@Override
	public void updateScore(Product product) {
		if (product == null) {
			return;
		}

		ProductDao dao = (ProductDao) super.getDao();
		dao.updateScore(product);
	}
}
