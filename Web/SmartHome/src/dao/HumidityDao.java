package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Humidity;
import JDBC.DBHelper;

public class HumidityDao {
	public List<Humidity> searchHumidity()
	{
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		List<Humidity> humidityList = new ArrayList<Humidity>();
		
		PreparedStatement pst;
		try {
			String sql = "select humidity,datetime,period from humidity";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Humidity humidity = new Humidity();
				humidity.setHumidity(rs.getDouble("humidity"));
				humidity.setDatetime(rs.getTimestamp("datetime"));
				humidity.setPeriod(rs.getString("period"));
				humidityList.add(humidity);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return humidityList;
		
	}
	
	public Humidity searchHumidityByPeriod(String period)
	{
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		Humidity humidity = new Humidity();
		
		PreparedStatement pst;
		try {
			String sql = "select humidity,datetime from humidity where period=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, period);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				humidity.setHumidity(rs.getDouble("humidity"));
				humidity.setDatetime(rs.getTimestamp("datetime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return humidity;
		
	}
	
	public int saveHumidity(Humidity humidity){
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		String sql = "insert into humidity(humidity,datetime,period) values(?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, humidity.getHumidity());
			pst.setTimestamp(2, humidity.getDatetime());
			pst.setString(3,humidity.getPeriod());
			
			int i  = pst.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 0;
	}
	
//	public int updateFlower(Flower flower){
//		DBHelper db = new DBHelper();
//		Connection conn = db.getConnection();
//		String sql = "update flower set fname=?,type=?,inf=?,meaning=?,price=?,amount=? where fno=?";
//		try {
//			PreparedStatement pst = conn.prepareStatement(sql);
//			
//			pst.setString(1, flower.getFname());
//			pst.setString(2, flower.getType());
//			pst.setString(3, flower.getInf());
//			pst.setString(4, flower.getMeaning());
//			pst.setFloat(5, flower.getPrice());
//			pst.setInt(6, flower.getAmount());
//			pst.setInt(7, flower.getFno());
//			
//			int i  = pst.executeUpdate();
//			return i;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return 0;
//	}
//	
//	public int updateFlowerPic(String fno,String img){
//		DBHelper db = new DBHelper();
//		Connection conn = db.getConnection();
//		String sql = "update flower set img=? where fno=?";
//		try {
//			PreparedStatement pst = conn.prepareStatement(sql);
//			
//			
//			pst.setString(1, img);
//			pst.setString(2, fno);
//			
//			int i  = pst.executeUpdate();
//			return i;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return 0;
//	}
	
	public void deleteHumidity(String period){
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		String sql = "delete from humidity where period=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, period);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
