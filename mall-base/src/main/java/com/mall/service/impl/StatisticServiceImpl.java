package com.mall.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.OrderDao;
import com.mall.dao.StatisticDao;
import com.mall.entity.Order;
import com.mall.pager.Pager;
import com.mall.service.StatisticService;
import com.mall.util.StatisticUtils;
import com.mall.util.TimeUtils;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticDao statisticDao;

	@Autowired
	private OrderDao orderDao;

	@Override
	public long mobileBindCount() {

		return statisticDao.mobileBindCount();
	}

	@Override
	public long mailValidateCount() {
		return statisticDao.mailValidateCount();
	}

	@Override
	public long loginInDaysCount(int days) {
		return statisticDao.loginInDaysCount(days);
	}

	@Override
	public long orderCount(String propertyName, Object value) {
		return orderDao.getCount(propertyName, value);
	}

	@Override
	public double orderSalseCount(String propertyName, Object value) {
		return statisticDao.orderSalseCount(propertyName, value);
	}

	@Override
	public double refundSalseCount(String propertyName, Object value) {
		return statisticDao.refundSalseCount(propertyName, value);
	}

	/**
	 * 打印订单
	 */
	@Override
	public HSSFWorkbook exportOrder(List<Order> orders) {
		String[] excelHeader = { "订单编号", "订单金额", "会员", "收货人", "订单状态", "地址", "邮编", "电话", "是否开发票", "发票类型", "发票抬头",
				"创建时间" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("订单");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}

		for (int i = 0; i < orders.size(); i++) {
			row = sheet.createRow(i + 1);
			Order order = orders.get(i);
			row.createCell(0).setCellValue(order.getSn());
			row.createCell(1).setCellValue(order.getAmountPaid());
			row.createCell(2).setCellValue(order.getMember().getUsername());
			row.createCell(3).setCellValue(order.getConsignee());
			row.createCell(4).setCellValue(orderStatus(order.getOrderStatus()));
			row.createCell(5).setCellValue(order.getAreaName() + order.getAddress());
			row.createCell(6).setCellValue(order.getZipCode());
			row.createCell(7).setCellValue(order.getPhone());
			row.createCell(8).setCellValue(order.getIsInvoice());
			row.createCell(9).setCellValue((order.getInvoice() == null ? -1 : order.getInvoice().getType()));
			row.createCell(10).setCellValue((order.getInvoice() == null ? "" : order.getInvoice().getTitle()));
			row.createCell(11).setCellValue(order.getCreateDate().toString());
		}
		return wb;
	}

	/**
	 * 订单状态
	 * 
	 * @param status
	 * @return
	 */
	public String orderStatus(int status) {
		switch (status) {
		case 1:
			return "未支付";
		case 2:
			return "已支付";
		case 3:
			return "待发货";
		case 4:
			return "已发货";
		case 5:
			return "待处理";
		case 6:
			return "退货处理";
		case 0:
			return "交易完成";
		case -1:
			return "交易关闭";
		default:
			return "处理中";
		}

	}

	@Override
	public Map<String, Object> orderTimeSalsesCount(Date date, int minDay, int maxDay) {
		return statisticDao.orderTimeSalseCount(date, minDay, maxDay);
	}

	/**
	 * 注册会员统计
	 */
	@Override
	public Map<String, Object> registerMemberCount(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		if (startDate != null) {
			start.setTime(startDate);
			if (endDate != null) {
				end.setTime(endDate);
			} else {
				end.setTime(startDate);
				end.add(Calendar.DATE, 10);
			}
		} else {
			if (endDate != null) {

				end.setTime(endDate);
				start.setTime(endDate);
				start.add(Calendar.DATE, -10);
			} else {
				start.setTime(new Date());
				end.setTime(new Date());
				start.add(Calendar.DATE, -5);
				end.add(Calendar.DATE, 5);
			}
		}

		Long totalCount = 0L;
		Long maxCount = 0L;
		List<Map<String, Object>> list = new ArrayList<>();
		totalCount = (Long) statisticDao.registerMemberCount(start.getTime(), end.getTime()).get("count");
		while (start.compareTo(end) <= 0) {
			Map<String, Object> map = new HashMap<>();
			Date lo = start.getTime();
			start.add(Calendar.DATE, 1);
			Date hi = start.getTime();
			map = statisticDao.registerMemberCount(lo, hi);
			list.add(map);
			if ((long) map.get("count") > maxCount) {
				maxCount = (long) map.get("count");
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("maxCount", StatisticUtils.maxCount(maxCount));
		result.put("list", list);
		result.put("totalCount", totalCount);
		return result;
	}

	@Override
	public long getTradeRecodesCount(long productId) {
		return this.statisticDao.getTradeRecodesCount(productId);
	}

	@Override
	public Long[] getTradeRecodesInfo(long productId, int pageNumber, int pageSize, String order) {

		if (productId <= 0 || pageNumber < 1 || pageSize < 1 || order == null) {
			return null;
		}

		return this.statisticDao.getTradeRecodesInfo(productId, pageNumber, pageSize, order);
	}

	@Override
	public Map<String, Object> getCommentsInfo(long productId) {

		if (productId <= 0) {
			return null;
		}

		return this.statisticDao.getCommentsInfo(productId);
	}

	@Override
	public Long[] getCommentsInfo(long productId, int pageNumber, int pageSize, String order) {
		if (productId <= 0 || pageNumber < 1 || pageSize < 1 || order == null) {
			return null;
		}

		return this.statisticDao.getCommentsInfo(productId, pageNumber, pageSize, order);
	}

	@Override
	public long getSalesCount(long productId) {

		if (productId <= 0) {
			return 0;
		}

		return this.statisticDao.getSalesCount(productId);
	}

	@Override
	public long getTodayLoginCount() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return statisticDao.getLoginCountAfter(date);
	}

	@Override
	public List<Long> getCommentsInfoById(long productid, List<Integer> info) {
		return statisticDao.getCommentsInfoById(productid, info);
	}

	@Override
	public List<Long> getCommentsInfoByPage(long productid, String type, int pageNumber, int pageSize) {
		return statisticDao.getCommentsInfoByPage(productid, type, pageNumber, pageSize);
	}

	@Override
	public List<Map<String, Object>> salesRanking(Pager<T> pager, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statisticDao.salesRanking(pager, startDate, endDate);
	}

	@Override
	public long memberLogoutDays(int days) {
		Date nowDate = new Date();
		Date daysBefore = TimeUtils.delayTime(nowDate, -60 * 24 * days);
		return statisticDao.memberCountLoginBeforeTime(daysBefore);
	}

}
