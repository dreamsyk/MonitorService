package cn.dreamsyk.monitor.vo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**
 * CPU时间参数实例对象
 * @author SYK
 * @date 2015年12月17日13:57:44
 */
public class CPUTime implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String kenerl_FILE = "/proc/stat";

	/**
	 * 从系统启动开始累计到当前时刻，用户态的CPU时间（单位：jiffies） ，
	 * 不包含 nice值为负进程。1jiffies=0.01秒
	 */
	public long user;
	/**
	 * 从系统启动开始累计到当前时刻，nice值为负的进程所占用的CPU时间（单位：jiffies）
	 */
	public long nice;
	/**
	 * 从系统启动开始累计到当前时刻，核心时间（单位：jiffies）
	 */
	public long system;
	/**
	 * 从系统启动开始累计到当前时刻，除硬盘IO等待时间以外其它等待时间（单位：jiffies）
	 */
	public long idle;
	/**
	 * 从系统启动开始累计到当前时刻，硬盘IO等待时间（单位：jiffies）
	 */
	public long iowait;
	/**
	 * 从系统启动开始累计到当前时刻，硬中断时间（单位：jiffies）
	 */
	public long irq;
	/**
	 * 从系统启动开始累计到当前时刻，软中断时间（单位：jiffies）
	 */
	public long softirq;
	/**
	 * which is the time spent in other operating systems when running in a virtualized environment
	 */
	public long stealstolen;
	/**
	 * which is the time spent running a virtual  CPU  for  guest operating systems under the control of the Linux kernel
	 */
	public long guest;
	/**
	 * unknown
	 */
	public long unknown;
	
	public CPUTime(){
		
		try {
			
			FileInputStream f = new FileInputStream(new File(kenerl_FILE));
			DataInputStream dis = new DataInputStream(f);

			@SuppressWarnings("deprecation")
			String line = dis.readLine();
			String temp[] = line.split(" ");
				

			this.user = Long.parseLong(temp[2]);
			this.nice = Long.parseLong(temp[3]);
			this.system = Long.parseLong(temp[4]);
			this.idle = Long.parseLong(temp[5]);
			this.iowait = Long.parseLong(temp[6]);
			this.irq = Long.parseLong(temp[7]);
			this.softirq = Long.parseLong(temp[8]);
			this.stealstolen = Long.parseLong(temp[9]);
			this.guest = Long.parseLong(temp[10]);
			this.unknown = Long.parseLong(temp[11]);
			
			dis.close();
			f.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public long getTotalTime() {
		return user + nice + system + idle + iowait + irq + softirq
				+ stealstolen + guest + unknown;
	}

	@Override
	public String toString() {
		return "CPUTime [user=" + user
				+ ", nice=" + nice + ", system=" + system + ", idle=" + idle
				+ ", iowait=" + iowait + ", irq=" + irq + ", softirq="
				+ softirq + ", stealstolen=" + stealstolen + ", guest=" + guest
				+ ", unknown=" + unknown + "]";
	}
	
	
}
