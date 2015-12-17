package cn.dreamsyk.monitor.core;

/**
 * 系统资源监视器抽象类
 * 
 * 定义为Runnable线程资源，并集成Imonitor接口
 * 默认每1秒计算，存储一次
 * @author SYK
 * @time 2015-12-17 11:00:30 
 */
public abstract class BaseMonitor implements IMonitor, Runnable {

	private long interval = 1000;

	@Override
	public void run() {

		while (true) {

			this.compute();
			this.storage();
			
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 获取监视器采样频率
	 * @return 监视器采样频率 （秒/次）
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * 设置监视器采样频率 
	 * @param interval 监视器采样频率 （秒/次）
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}
}
