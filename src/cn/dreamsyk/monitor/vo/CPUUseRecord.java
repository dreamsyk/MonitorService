package cn.dreamsyk.monitor.vo;

public class CPUUseRecord extends BaseUseRecord {

	private static final long serialVersionUID = -7722170454272249949L;
	
	public CPUUseRecord(){
		super();
	}

	public CPUUseRecord(long time, float use_prec) {
		super(time, use_prec);
	}
	
}
