package cn.dreamsyk.monitor.vo;

import java.io.Serializable;

/**
 * 记录存储抽象对象
 * @author SYK
 * @time 2015-12-17 11:23:43
 */
public abstract class BaseUseRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 利用率
	 */
	private float use_prec;
	
	/**
	 * 时间戳
	 */
	private long time;
	
	public BaseUseRecord(){
		init(0, 0);
	}

	public BaseUseRecord(long time, float use_prec) {
		init(time, use_prec);
	}
	
	private void init(long time, float use_prec){
		this.time = time;
		this.use_prec = use_prec;
	}

	public float getUse_prec() {
		return use_prec;
	}

	public void setUse_prec(float use_prec) {
		this.use_prec = use_prec;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
