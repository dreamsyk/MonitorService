package cn.dreamsyk.monitor.core;

/**
 * 系统资源监视器抽象接口
 * @author SYK
 * @time 2015-12-17 11:00:57
 */
public abstract interface IMonitor {

	/**
	 * 系统资源利用率的计算
	 */
	public abstract void compute();

	/**
	 * 资源利用率记录的存储
	 */
	public abstract void storage();

}
