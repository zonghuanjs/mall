package com.mall.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mall.entity.Product;
import com.mall.pager.Pager;



public interface ProductService extends BaseService<Long, Product>
{
	public List<Product> getListAccordingSales(Map<String, Object> filter, String orderby,int topNumber);
	//根据商品名检索
	public List<Product> getListPropertyNameLike(Map<String, Object> filter);
	/**
	 * 多条件过滤
	 */
	public long getCount(Map<String, Object> filter);
	/**
	 * 获取总数
	 * @param eqFilter
	 * @param likeFilter
	 * @return
	 */
	public long getCount(Map<String, Object> eqFilter,Map<String, Object> likeFilter);
	/**
	 * 可以按照属性值like进行查询  并带分页功能
	 * @param eqFilter
	 * @param likeFilter
	 * @param inSet
	 * @param orderby
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Product> getList(Map<String, Object> eqFilter,Map<String, Object> likeFilter, Map<String, Set<Object> > inSet, String orderby, int first, int count);

	/**
	 * 可以按照集合范围取值
	 * 
	 * @param eqFilter
	 * @param likeFilter
	 * @param inSet
	 * @param filterBetween
	 * @param orderby
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Product> getList(Map<String, Object> eqFilter,Map<String, Object> likeFilter, Map<String, Set<Object> > inSet, Map<String, List<Double>> filterBetween, String orderby, int first, int count);
	
	/**
	 * 商品列表页分类, 按规格搜索接口
	 * @param pager
	 * @param categorys
	 * @param valueIds
	 * @param prices
	 * @return
	 */
	public List<Product> findByPager(Pager<Product> pager, Long[] valueIds, Double[] prices);
	
	/**
	 * 更新商品评分
	 * @param product	商品对象
	 */
	public void updateScore(Product product);
	
	/**
	 *  精确查找  查询 product
	 * @param key 搜索关键字 
	 * @param brandIds 品牌ids
	 * @param categoryIds 商品分类ids
	 * @param specValueIds 商品参数值ids
	 * @param orderkey 属性排序
	 * @param isdesc  true:降序 / false:升序
	 * @param pageNumber 第几页
	 * @param pageSize 分页大小
	 * @param low 价格区间 最低价 
	 * @param high 价格区间 最高价
	 * @param total 输出满足条件的总数 
	 * @return 根据设置的条件 查询的结果
	 */
	public List<Product> exactSearch(String key, String brandIds, String categoryIds, String specValueIds, 
			String orderkey, boolean isdesc, int pageNumber, int pageSize, double low, double high, List<Integer> total);
	
	
	public Map<String,Object> getSomeInfo();
	
	
}
