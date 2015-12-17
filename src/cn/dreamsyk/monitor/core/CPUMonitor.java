package cn.dreamsyk.monitor.core;

import java.util.Date;

import cn.dreamsyk.monitor.dao.CPURecordDao;
import cn.dreamsyk.monitor.dao.impl.CPURecordDaoImpl;
import cn.dreamsyk.monitor.vo.CPUTime;
import cn.dreamsyk.monitor.vo.CPUUseRecord;

/**
 * CPU监视器实体类
 * 
 * 基于/proc/stat内核文件
 * 每秒对内核文件参数读取进行利用率计算，并存储
 * 
 * 计算公式为：
 * 		usage=[(user_2 +sys_2+nice_2) - (user_1 + sys_1+nice_1)]/(total_2 - total_1)*100
 * 
 * @author SYK
 * @time 2015-12-17 14:20:29
 */
public class CPUMonitor extends BaseMonitor {
	
	private float useage;
	private CPURecordDao recordDao;
	
	public CPUMonitor() {
		this.recordDao=new CPURecordDaoImpl(); 
	}

	@Override
	public void compute() {
		
		CPUTime time1=new CPUTime();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		CPUTime time2=new CPUTime();
				
		 useage = (float) ((time2.user + time2.system + time2.nice) 
				 		- (time1.user + time1.system + time1.nice))
				 		/ (time2.getTotalTime() - time1.getTotalTime()) * 100;
		
		
		
	}

	@Override
	public void storage() {
		
		if (useage != 0.0) {
			
			System.out.println(String.format("[CPU Used] -> %1$2.2f %%", useage));
			
			boolean result = recordDao.addRecord(
					new CPUUseRecord(new Date().getTime(), useage));

			if (!result)
				System.err.println("Memory record insert failed !!!");
		}
	}
	
	
	public static void main(String args[]) {
		new CPUMonitor().compute();
	}

}
