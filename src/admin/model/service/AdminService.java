package admin.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import admin.model.dao.AdminDao;
import product.model.vo.Place;
import product.model.vo.Play;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	public List<Place> selectCPlace() {
		Connection conn = getConnection();
		List<Place> list = adminDao.selectCPlace(conn);
		close(conn);
		return list;
	}
	public int placeAccept(String placeId) {
		Connection conn = getConnection();
		int result = adminDao.placeAccept(conn,placeId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int placeCancel(String placeId) {
		Connection conn = getConnection();
		int result = adminDao.placeCancel(conn,placeId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public List<Play> selectAllPlay() {
		Connection conn = getConnection();
		List<Play> list = adminDao.selectAllPlay(conn);
		close(conn);
		return list;
	}
}
