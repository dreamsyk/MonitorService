package cn.dreamsyk.monitor.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import cn.dreamsyk.monitor.dao.MemoryRecordDao;
import cn.dreamsyk.monitor.dao.impl.MemoryRecordDaoImpl;
import cn.dreamsyk.monitor.vo.MemoryUseRecord;

/**
 * 内存监视器实体类
 * 
 * 基于/proc/meminfo内核文件
 * 每秒对内核文件参数读取进行利用率计算，并存储
 * 
 * 计算公式为：
 * 		利用率 usedPerc = (MemTotal - MemFree - Buffers - Cached) / MemTotal * 100
 * 
 * @author SYK
 * @time 2015-12-17 11:00:57
 */
public class MemoryMonitor extends BaseMonitor {
	
	private final String kenerl_FILE = "/proc/meminfo";

	private HashMap<String, String> map = null;
	private float used_perc;
	private MemoryRecordDao recordDao = null;
 
	public MemoryMonitor() {
		map = new HashMap<String, String>();
		recordDao = new MemoryRecordDaoImpl();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void compute() {
		
		map.clear();
		
		try {
			
			FileInputStream f = new FileInputStream(new File(kenerl_FILE));
			DataInputStream dis = new DataInputStream(f);
			
			String line = dis.readLine();
			while (line != null) {
				String arr[] = getKeyValue(line.split(" "));
				map.put(arr[0], arr[1]);
				line = dis.readLine();
			}
						
			long memTotal, memFree, buffers, cached;
			
			memTotal = Long.parseLong(map.get("MemTotal"));
			memFree = Long.parseLong(map.get("MemFree"));
			buffers = Long.parseLong(map.get("Buffers"));
			cached = Long.parseLong(map.get("Cached"));
			
			used_perc = (float) (memTotal - memFree - buffers - cached) / memTotal * 100;
			
			System.out.println(String.format("[Memory Used] -> %1$2.2f %%", used_perc));
			
			dis.close();
			f.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void storage() {
		
		if (used_perc != 0.0) {
			
			boolean result = recordDao.addRecord(
					new MemoryUseRecord(
							new Date().getTime(), 
							used_perc));

			if (!result)
				System.err.println("Memory record insert failed !!!");
		}

 	}

	/**
	 * 取得字符串数组中的"键"与"值"，结果将以字符串数组返回
	 * 
	 * @param temp
	 * @return 返回长度为2的字符串数组，arr[0]->键，arr[1]->值
	 */
	private String[] getKeyValue(String temp[]) {

		String[] arr = new String[2];

		int i = 0;
		boolean isEnd = false;

		for (String str : temp) {

			if (isEnd)
				break;

			if (str.trim().length() > 0) {

				switch (i) {
				case 0: {
					arr[0] = str.trim().split(":")[0];
				}
					break;
				case 1: {
					arr[1] = str;
				}
					break;
				}

				i++;

				if (i == 2)
					isEnd = true;
			}
		}

		return arr;
	}

}
