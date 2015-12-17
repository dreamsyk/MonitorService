package cn.dreamsyk.monitor.dao;

import java.util.List;

import cn.dreamsyk.monitor.vo.MemoryUseRecord;

/**
 * 内存利用率记录数据库操作接口
 * @author SYK
 * @time 2015年12月16日11:03:27
 */
public abstract interface MemoryRecordDao {
	
	/**
	 * 添加内存利用率记录
	 * @param record 需要添加的内存记录对象
	 * @return 记录是否添加成功
	 */
	public abstract boolean addRecord(MemoryUseRecord record);

	/**
	 * 删除指定内存利用率对象的记录 
	 * @param record 需要删除的内存记录对象
	 * @return 记录是否删除成功
	 */
	public abstract boolean deleteRecord(MemoryUseRecord record);
	
	/**
	 * 删除指定时间的利用率记录
	 * @param time 指定的时间
	 * @return 返回删除记录的条数
	 */
	public abstract int deleteRecordAt(long time);

	/**
	 * 获取指定时间的内存利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间的内存记录对象
	 */
	public abstract MemoryUseRecord getRecordAt(long time);

	/**
	 * 获取指定时间之前的内存利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间之前的内存记录集合
	 */
	public abstract List<MemoryUseRecord> getRecordsBefore(long time);

	/**
	 * 获取指定时间之后的内存利用率记录
	 * @param time 指定的时间	
	 * @return 指定时间之后的内存记录集合
	 */
	public abstract List<MemoryUseRecord> getRecordsAfter(long time);

	/**
	 * 获取指定时间范围内的内存使用记录
	 * @param start_time 时间范围开始时间
	 * @param end_time 时间范围结束时间
	 * @return 指定时间范围的内存记录集合
	 */
	public abstract List<MemoryUseRecord> getRecordsBetween(long start_time , long end_time);

	/**
	 * 获取所有内存利用率的记录
	 * @return 所有内存记录的集合
	 */
	public abstract List<MemoryUseRecord> getAllRecords();

	/**
	 * 清空所有内存利用率记录
	 * @return 是否成功清空所有记录
	 */
	public abstract boolean clearAllRecords();
	
}