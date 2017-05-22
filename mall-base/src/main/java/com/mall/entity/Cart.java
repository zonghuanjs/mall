package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 购物车实体
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_cart")
public class Cart
{
	public static final String cart_Key = "cart_key";//购物车Cookie
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//购物车ID
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="cart_key")
	private String cartKey;//购物车KEY
	
	@OneToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
	@JoinColumn(name="member")
	private Member member;//所属会员
	
	@OneToMany(targetEntity=CartItem.class, mappedBy="cart", fetch=FetchType.LAZY)
	@OrderBy("createDate")
	private Set<CartItem> items;//购物车物品
	
	@Column(name="total")
	private double total; //购物车总价
	
	public Cart()
	{
		this.items = new HashSet<>();
	}
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public Date getModifyDate()
	{
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}
	public String getCartKey()
	{
		return cartKey;
	}
	public void setCartKey(String cartKey)
	{
		this.cartKey = cartKey;
	}
	public Member getMember()
	{
		return member;
	}
	public void setMember(Member member)
	{
		this.member = member;
	}

	public Set<CartItem> getItems()
	{
		return items;
	}

	public void setItems(Set<CartItem> items)
	{
		this.items = items;
	}
	
	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}
	
	/**
	 * 获取购物车商品总量
	 * @return
	 */
	public double getWeight()
	{
		double weight = 0;
		for(CartItem item : items)
		{
			weight += item.getProduct().getWeight();
		}
		return weight;
	}

	/**
	 * 检测购物车里是否存在商品
	 * @param product    待检测商品
	 * @return  null,不存在; CartItem对象, 存在
	 */
	public CartItem checkProduct(Product product)
	{
		CartItem ci = null;
		for(CartItem item : items)
		{
			if(item.getProduct().getId() == product.getId())
			{
				ci = item;
				break;
			}
		}
		return ci;
	}
	
	/**
	 * 更新购物车状态
	 */
	public void update()
	{
		this.total = 0;
		for(CartItem item : items)
		{
			double amount = item.getProduct().getPrice() * item.getQuantity(); 
			item.setAmount(amount);
			this.total += amount;
		}
	}
	
	/**
	 * 合并购物车
	 * @param other 其他购物车
	 */
	public void mergeCart(Cart other)
	{
		for(CartItem item : other.getItems())
		{
			CartItem ci = this.checkProduct(item.getProduct());
			if(ci == null)
			{
				this.items.add(item);
			}
		}
		this.update();
	}
	
	/**
	 * 购物车Key
	 */
	public static final class Keys
	{
		public static final String itemSelect = "item_select";//选择商品
	}
}
