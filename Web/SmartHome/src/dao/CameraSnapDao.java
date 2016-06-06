package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.CameraSnaps;
import JDBC.DBHelper;

public class CameraSnapDao {
	
	public List<CameraSnaps> searchCameraSnap()
	{
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		List<CameraSnaps> cameraSnapList = new ArrayList<CameraSnaps>();
		
		PreparedStatement pst;
		try {
			String sql = "select cameraSnaps,datetime from camerasnaps";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				CameraSnaps cameraSnaps = new CameraSnaps();
				cameraSnaps.setCameraSnaps(rs.getString("cameraSnaps"));
				cameraSnaps.setDatetime(rs.getTimestamp("datetime"));
				cameraSnapList.add(cameraSnaps);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cameraSnapList;
		
	}
	
	public CameraSnaps searchCameraSnapByDatetime(Timestamp datetime)
	{
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		CameraSnaps cameraSnaps = new CameraSnaps();
		
		PreparedStatement pst;
		try {
			String sql = "select cameraSnaps from camerasnaps where datetime=?";
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, datetime);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				cameraSnaps.setCameraSnaps(rs.getString("cameraSnaps"));
				cameraSnaps.setDatetime(rs.getTimestamp("datetime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cameraSnaps;
		
	}
	
	public int saveCameraSnaps(CameraSnaps cameraSnaps){
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		String sql = "insert into camerasnaps(cameraSnaps,datetime) values(?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cameraSnaps.getCameraSnaps());
			pst.setTimestamp(2, cameraSnaps.getDatetime());
			
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
	
	public void deleteCameraSnaps(Timestamp datetime){
		DBHelper db = new DBHelper();
		Connection conn = db.getConnection();
		String sql = "delete from camerasnaps where datetime=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, datetime);
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
