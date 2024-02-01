package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;

@Repository
public class JoDAO {
	
	//** JDBC 처리를 위한 전역변수정의
	private static Connection cn=DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	//1. selectList
	// => ver01 : 기본형
	// => ver02 : 조장이름 추가
	//            Outer Join : 조장이 없는조도 출력 되어야 함 
	//            ( LEFT / RIGHT : 출력_자료가 있는쪽 기준 ) 
	public List<JoDTO> selectList() {
		//sql="select * from jo"; //-> ver01
		
		//=> ver02
		sql="select j.jno, jname, captain, m.name, project, slogan"
			+ "	from jo j LEFT OUTER JOIN member m ON j.captain=m.id ";
		List<JoDTO> list = new ArrayList<JoDTO>();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setCname(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));
					list.add(dto);
				}while(rs.next());
			}else {
				System.out.println("** Jo selectList: 출력자료가 1도 없습니다. **");
				list=null;
			} //else
		} catch (Exception e) {
			System.out.println("** Jo selectList Exception => "+e.toString());
			list=null;
		} //try
		return list;
	} //selectList
	
	//2. selectOne: Detail
	public JoDTO selectOne(JoDTO dto) {
		sql = "select * from jo where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			rs=pst.executeQuery();
			if ( rs.next() ) {
				// select 성공
				dto.setJno(rs.getInt(1));
				dto.setJname(rs.getString(2));
				dto.setCaptain(rs.getString(3));
				dto.setProject(rs.getString(4));
				dto.setSlogan(rs.getString(5));
			}else {
				// select NotFound
				System.out.println("** Jo selectOne: jno 에 해당하는 자료가 없습니다. **");
				dto=null;
			}
		} catch (Exception e) {
			System.out.println("** Jo selectOne Exception => "+e.toString());
			dto=null;
		}
		return dto;
	} //selectOne
	
	//3. insert
	public int insert(JoDTO dto) {
		sql="insert into jo values(?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getCaptain());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Jo insert Exception => "+e.toString());
			return 0;
		}
	} //insert
	
	//4. update
	// => jno (P.Key) 제외한 모든 컬럼수정
	public int update(JoDTO dto) {
		sql="update jo set jname=?,captain=?,project=?,slogan=? where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getCaptain());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Jo update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	//5. delete
	public int delete(JoDTO dto) {
		sql="delete from jo where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Jo delete Exception => "+e.toString());
			return 0;
		}
	} //delete

} //class
