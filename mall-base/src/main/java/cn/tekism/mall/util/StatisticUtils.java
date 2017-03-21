package cn.tekism.mall.util;

public class StatisticUtils
{

	/**
	 * 统计图y轴计算
	 * @param maxCount
	 * @return
	 */
	public static double maxCount(double maxCount)
	{
		if (maxCount < 10)
		{
			return 10;

		}
		else if (maxCount < 100)
		{

			return Math.ceil(maxCount / 10) * 10;
		}
		else if (maxCount < 1000)
		{

			return Math.ceil(maxCount / 100) * 100;
		}
		else if (maxCount < 10000)
		{

			return Math.ceil(maxCount / 1000) * 1000;
		}
		else if(maxCount < 100000)
		{
			return Math.ceil(maxCount / 10000) * 10000;
		}
		else if(maxCount < 1000000)
		{
			return Math.ceil(maxCount / 100000) * 100000;
		}
		else if(maxCount < 10000000)
		{
			return Math.ceil(maxCount / 1000000) * 1000000;
		}
		else if(maxCount < 100000000)
		{
			return Math.ceil(maxCount / 10000000) * 10000000;
		}
		else 
		{
			return Math.ceil(maxCount / 100000000) * 100000000;
		}

	}

}
