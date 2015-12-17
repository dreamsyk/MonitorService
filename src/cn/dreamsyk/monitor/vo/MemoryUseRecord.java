package cn.dreamsyk.monitor.vo;


/**
 * 内存利用率记录存储对象
 * @author SYK
 * @time 2015-12-17 11:25:43
 */
public class MemoryUseRecord extends BaseUseRecord {
		
	private static final long serialVersionUID = -5233270400892225301L;

	public MemoryUseRecord(){
		super();
	}

	public MemoryUseRecord(long time, float use_prec) {
		super(time, use_prec);
	}
	
}
