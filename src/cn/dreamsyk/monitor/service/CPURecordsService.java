package cn.dreamsyk.monitor.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dreamsyk.monitor.dao.impl.CPURecordDaoImpl;
import cn.dreamsyk.monitor.vo.CPUUseRecord;

/**
 * CPU使用率数据获取接口
 * 
 * 采用前缀时间戳的方式来进行增量数据的查询反馈
 * 
 * @author SYK
 * @time 2015-12-17 14:50:28
 */
public class CPURecordsService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private long temp_time;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		//read records
		CPURecordDaoImpl recordDao=new CPURecordDaoImpl();
		List<CPUUseRecord> records= null;
		
		if (temp_time > 0) {
			records = recordDao.getRecordsAfter(temp_time);
		} else {
			records = recordDao.getAllRecords();
		}
			
		if (records.size() > 0)
			temp_time = records.get(records.size() - 1).getTime();
			
		out.println(getMemoryRecordsList(records));
		out.flush();
		
		out.close();
		recordDao.close();
		
		}
	
	/**
	 *	获取所有内存利用率记录的列表,列表项：[时间戳，利用率] 
	 */
	private String getMemoryRecordsList(List<CPUUseRecord> records){
		
		//generate records string 
		StringBuilder builder=new StringBuilder();
		builder.append("[\n");
		
		for (CPUUseRecord rec : records) {
			builder.append(String.format("[%1$s,%2$f],\n", 
									rec.getTime(),
									rec.getUse_prec()));
		}
		
		if (records.size() > 0)
			builder.replace(builder.length() - 2, builder.length() - 1, "");
		builder.append("]");
		
		return builder.toString();
		
	}
	

}
