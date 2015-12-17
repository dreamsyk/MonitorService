package cn.dreamsyk.monitor.util;

import cn.dreamsyk.monitor.dao.impl.CPURecordDaoImpl;
import cn.dreamsyk.monitor.dao.impl.MemoryRecordDaoImpl;

/**
 * 数据库记录工具类
 * @author SYK
 * @date 2015-12-17 15:19:41
 */
public class DBRecordsUtil {

	/**
	 * 清空所有记录
	 */
	public static void  clearAllRecords(){
		
		MemoryRecordDaoImpl memory = new MemoryRecordDaoImpl();
		CPURecordDaoImpl cpu = new CPURecordDaoImpl();
		
		cpu.clearAllRecords();
		memory.clearAllRecords();
		
		cpu.close();
		memory.close();
		
	}
	
}
