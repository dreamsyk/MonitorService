package cn.dreamsyk.monitor.dao;

import java.util.List;

import cn.dreamsyk.monitor.vo.CPUUseRecord;

/**
 * CPU利用率记录数据库操作接口
 * @author SYK
 * @time 2015年12月17日14:29:34
 */
public interface CPURecordDao {
	
	/**
	 * 添加CPU利用率记录
	 * @param record 需要添加的CPU记录对象
	 * @return 记录是否添加成功
	 */
	public abstract boolean addRecord(CPUUseRecord record);

	/**
	 * 删除指定CPU利用率对象的记录 
	 * @param record 需要删除的CPU记录对象
	 * @return 记录是否删除成功
	 */
	public abstract boolean deleteRecord(CPUUseRecord record);
	
	/**
	 * 删除指定时间的利用率记录
	 * @param time 指定的时间
	 * @return 返回删除记录的条数
	 */
	public abstract int deleteRecordAt(long time);

	/**
	 * 获取指定时间的CPU利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间的记录对象
	 */
	public abstract CPUUseRecord getRecordAt(long time);

	/**
	 * 获取指定时间之前的CPU利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间之前的CPU记录集合
	 */
	public abstract List<CPUUseRecord> getRecordsBefore(long time);

	/**
	 * 获取指定时间之后的CPU利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间之后的CPU记录集合
	 */
	public abstract List<CPUUseRecord> getRecordsAfter(long time);

	/**
	 * 获取指定时间范围内的CPU使用记录
	 * @param start_time 时间范围开始时间
	 * @param end_time 时间范围结束时间
	 * @return 指定时间范围的CPU记录集合
	 */
	public abstract List<CPUUseRecord> getRecordsBetween(long start_time , long end_time);

	/**
	 * 获取所有CPU利用率的记录
	 * @return 所有CPU记录的集合
	 */
	public abstract List<CPUUseRecord> getAllRecords();

	/**
	 * 清空所有CPU利用率记录
	 * @return 是否成功清空所有记录
	 */
	public abstract boolean clearAllRecords();
	

}
