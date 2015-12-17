package cn.dreamsyk.monitor.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dreamsyk.monitor.dao.MemoryRecordDao;
import cn.dreamsyk.monitor.util.DbConn;
import cn.dreamsyk.monitor.vo.MemoryUseRecord;

/**
 * 内存利用率记录数据库操作实体类
 * @author SYK
 * @time 2015年12月16日11:08:31
 */
public class MemoryRecordDaoImpl implements MemoryRecordDao {
	
	private Connection conn;

	public MemoryRecordDaoImpl() {
		conn = DbConn.getConn();
	}
	
	@Override
	public boolean addRecord(MemoryUseRecord record) {
		
		boolean flag = false;
		
		String sql = "INSERT memory (time,use_prec)  VALUES (?,?)";
		
		try {

			PreparedStatement pmst = conn.prepareStatement(sql);
			
			pmst.setLong(1, record.getTime());
			pmst.setFloat(2, record.getUse_prec());
			
			if (pmst.executeUpdate() == 1)
				flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Override
	public boolean deleteRecord(MemoryUseRecord record) {
		
		boolean flag = false;
		
		String sql="DELETE FROM memory WHERE time=(?) AND use_prec=(?)";
		
		try {
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setLong(1, record.getTime());
			psmt.setFloat(2, record.getUse_prec());
			
			if (psmt.executeUpdate() > 0)
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int deleteRecordAt(long time) {
		
		int count = 0;
		
		String sql ="DELETE FROM memory WHERE time=(?)";
		
		try {
			
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setLong(1, time);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public MemoryUseRecord getRecordAt(long time) {
				
		String sql="SELECT use_prec FROM memory WHERE time=(?)";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setLong(1, time);
			
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				MemoryUseRecord record = new MemoryUseRecord();
				record.setTime(time);
				record.setUse_prec(rs.getFloat(1));
				return record;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<MemoryUseRecord> getRecordsBefore(long time) {
		
		List<MemoryUseRecord> list=new ArrayList<MemoryUseRecord>();
		
		String sql="select * from memory where time < (?)";
		
		try {
			
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setLong(1, time);
			
			ResultSet rs=psmt.executeQuery();
			
			while (rs.next()) {

				MemoryUseRecord record = new MemoryUseRecord();
				record.setTime(rs.getLong(1));
				record.setUse_prec(rs.getFloat(2));
				list.add(record);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<MemoryUseRecord> getRecordsAfter(long time) {
		
		List<MemoryUseRecord> list=new ArrayList<MemoryUseRecord>();
		
		String sql="select * from memory where time > (?)";
		
		try {
			
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setLong(1, time);
			
			ResultSet rs=psmt.executeQuery();
			
			while (rs.next()) {

				MemoryUseRecord record = new MemoryUseRecord();
				record.setTime(rs.getLong(1));
				record.setUse_prec(rs.getFloat(2));
				list.add(record);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public List<MemoryUseRecord> getRecordsBetween(long start_time, long end_time) {
		
		List<MemoryUseRecord> list=new ArrayList<MemoryUseRecord>();
		
		String sql="select * from memory where time > (?) AND time < (?)";
		
		try {
			
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setLong(1, start_time);
			psmt.setLong(2, end_time);
			
			ResultSet rs=psmt.executeQuery();
			
			while (rs.next()) {

				MemoryUseRecord record = new MemoryUseRecord();
				record.setTime(rs.getLong(1));
				record.setUse_prec(rs.getFloat(2));
				list.add(record);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public List<MemoryUseRecord> getAllRecords() {
		
		List<MemoryUseRecord> list=new ArrayList<MemoryUseRecord>();
		
		String sql="select * from memory";
		
		try {
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			
			while (rs.next()) {

				MemoryUseRecord record = new MemoryUseRecord();
				record.setTime(rs.getLong(1));
				record.setUse_prec(rs.getFloat(2));
				list.add(record);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public boolean clearAllRecords() {
		
		boolean flag = false;
		
		String sql="TRUNCATE table memory";
		
		try {
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			int i=psmt.executeUpdate();
			
			if (i >= 0) {
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	public void close(){
		DbConn.closeCon(conn);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String []args){
		
		MemoryRecordDao recordDao = new MemoryRecordDaoImpl();
		
		/*getRecordsAfter*/
		List<MemoryUseRecord> records =recordDao.getRecordsAfter(Long.parseLong("1450254237623"));
		
		for (MemoryUseRecord rec : records) {
			System.out.println(String.format("[%1$s] -> %2$f %%",
							new Date(rec.getTime()).toLocaleString(),
							rec.getUse_prec()));
		}
		
 	    /*clearAllRecords*/
		
//		if(recordDao.clearAllRecords()) 
//			System.out.println("clear records susscess!!!");
		
	}
	
}
