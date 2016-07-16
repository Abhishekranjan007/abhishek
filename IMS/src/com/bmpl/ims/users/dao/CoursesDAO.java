package com.bmpl.ims.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bmpl.ims.common.dao.CommonDAO;
import com.bmpl.ims.common.dao.CommonSQLConstants;
import com.bmpl.ims.users.dto.AddCourseDTO;

public class CoursesDAO {

	public boolean addCourse(AddCourseDTO addcourseDTO) throws ClassNotFoundException, SQLException {
		boolean courseAdded = false;
		String sql = CommonSQLConstants.ADDCOURSE_SQL;
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs ;
		try {
			con = CommonDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addcourseDTO.getCourse_name());
			pstmt.setLong(2, addcourseDTO.getFees());
			pstmt.setString(3, addcourseDTO.getCourse_description());
			pstmt.setLong(4, addcourseDTO.getDuration());
			pstmt.setString(5, addcourseDTO.getTrainer_name());
			rs = pstmt.executeUpdate();
			try {

				courseAdded = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("can't execiute");

			}
		} finally {
			// logger.debug("Resource Close Inside Finally");
			con.close();
		}
		return courseAdded;

	}
	
	
	public ArrayList<String> getCourse() throws SQLException{
		boolean coursesShown=false;
		String sql=CommonSQLConstants.VIEWCOURSE_SQL;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs ;
		ArrayList<String> list=new ArrayList<String>();
		try{
			con = CommonDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
					list.add(rs.getString("course_name"));
				
				coursesShown = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			coursesShown=false;
			
		}
		finally{
			con.close();
		}
		return list;
		
	}
	

}
