package cn.dreamsyk.monitor;

import java.util.ArrayList;
import java.util.List;

import cn.dreamsyk.monitor.core.BaseMonitor;
import cn.dreamsyk.monitor.core.CPUMonitor;
import cn.dreamsyk.monitor.core.MemoryMonitor;
import cn.dreamsyk.monitor.core.NetWorksMonitor;
import cn.dreamsyk.monitor.util.DBRecordsUtil;

/**
 * 系统资源监视计算存储服务 主入口
 * 
 * @author SYK
 * @time 2015-12-17 10:59:51 
 */
public class Main {
	
	public static void main(String[] args) {
		
		switch (args.length) {
		
		case 0: {

			List<BaseMonitor> monitors = new ArrayList<BaseMonitor>();

			monitors.add(new CPUMonitor());
			monitors.add(new MemoryMonitor());
			monitors.add(new NetWorksMonitor());

			for (BaseMonitor monitor : monitors) {
				new Thread(monitor).start();
			}
		}
			break;

		case 1: {
			
			String str=args[0].toLowerCase();
			
			if (str.equals("-c") || str.equals("-clear") || str.equals("-clean")) {
				DBRecordsUtil.clearAllRecords();
			}
			
		}
			break;
		}
		
	}

}
