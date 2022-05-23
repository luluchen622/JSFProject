package com.hy.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

public class PersonDAO implements PersonDAOInterface {

	private static final String INSERT = "INSERT INTO PERSON (NAME, EMAIL) VALUES (?,?)";
	private static final String GET_ALL = "SELECT * FROM PERSON";
	private static final String GET_ONE = "SELECT * FROM PERSON WHERE ID = ?";
	private static final String UPDATE = "UPDATE PERSON SET NAME = ?, EMAIL = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM PERSON WHERE ID = ?";

	@Override
	public List<PersonVO> getPersonList() {

		List<PersonVO> list = new ArrayList();
		PersonVO vo = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			con = DBManagement.getConnection();
			pst = con.prepareStatement(GET_ALL);
			rs = pst.executeQuery();

			while (rs.next()) {

				vo = new PersonVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));

				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public String insert(PersonVO vo) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DBManagement.getConnection();
			pst = con.prepareStatement(INSERT);

			pst.setString(1, vo.getName());
			pst.setString(2, vo.getEmail());
			pst.executeUpdate();
		} catch (SQLException sqlErrors) {
			throw new RuntimeException("A database error occured. " + sqlErrors.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "SUCCESS";
	}

	@Override
	public PersonVO getOne(int id) {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PersonVO vo = null;
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		try {
			con = DBManagement.getConnection();
			pst = con.prepareStatement(GET_ONE);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while (rs.next()) {
				vo = new PersonVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				sessionMap.put("editPerson", vo);
			}
		} catch (SQLException sqlErrors) {
			throw new RuntimeException("A database error occured. " + sqlErrors.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}

	@Override
	public String update(PersonVO vo) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DBManagement.getConnection();
			pst = con.prepareStatement(UPDATE);

			pst.setString(1, vo.getName());
			pst.setString(2, vo.getEmail());
			pst.setInt(3, vo.getId());

			pst.executeUpdate();

		} catch (SQLException sqlErrors) {
			throw new RuntimeException("A database error occured. " + sqlErrors.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return "SUCCESS";
	}

	@Override
	public void delete(int id) {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DBManagement.getConnection();
			pst = con.prepareStatement(DELETE);
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (SQLException sqlErrors) {
			throw new RuntimeException("A database error occured. " + sqlErrors.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
