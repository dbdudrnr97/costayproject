package product.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.vo.Registration;
import product.model.dao.PlaceDao;
import product.model.vo.ImageStore;
import product.model.vo.Place;

public class PlaceService {
	public static final int TOTAL_SEARCH = 1;
	public static final int LOCAL_SEARCH = 2;
	public static final int PRICE_SEARCH = 3;
	public static final int LOC_PRICE_SEARCH = 4;	
	
	private PlaceDao placeDao = new PlaceDao();
	public int placeEnroll(Place place) {
		Connection conn = getConnection();
		int result = placeDao.placeEnroll(conn, place);
		if(result>0) {
			commit(conn);
			//숙소등록에 성공한 숙소ID값
			String placeId = placeDao.selectLastPlaceId(conn);
			place.setPlaceId(placeId);
		}
		else rollback(conn);
		close(conn);
		return result;
	}
	public Place selectPlaceId(String placeId) {
		Connection conn = getConnection();
		Place place = placeDao.selectPlaceId(conn, placeId);
		close(conn);
		return place;
	}
	public int placeUpdate(Place place) {
		Connection conn = getConnection();
		int result = placeDao.placeUpdate(conn, place);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public List<ImageStore> selectImageStore(String placeId) {
		Connection conn = getConnection();
		List<ImageStore> list = placeDao.selectImageStore(conn, placeId);
		close(conn);
		return list;
	}
	public int placeImgUpdate(ImageStore imageStore) {
		Connection conn = getConnection();
		int result = placeDao.placeImgUpdate(conn, imageStore);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int placeImgEnroll(ImageStore imageStore) {
		Connection conn = getConnection();
		int reuslt = placeDao.placeImgEnroll(conn, imageStore);
		if(reuslt>0) commit(conn);
		else rollback(conn);
		close(conn);
		return reuslt;
	}
	public List<Place> selectMyPlace(String memberId) {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectMyPlace(conn, memberId);
		close(conn);
		return list;
	}
	public Place selectPlaceOne(String placeId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		Place place =  placeDao.selectPlaceOne(conn, placeId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return place;
	}
	public int placeDelete(String placeId) {
		Connection conn = getConnection();
		int result = placeDao.placeDelete(conn, placeId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public List<Place> selectPlaceList() {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		List<Place> list =  placeDao.selectPlaceList(conn);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return list;
	}
	public List<Place> selectPlaceList(Map<String, Object> param) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		List<Place> list =  placeDao.selectPlaceList(conn, param);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return list;
	}

	public List<Place> selectLocalPlaceList(Map<String, Object> param, String placeLoc) throws Exception {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectLocalPlaceList(conn, param, placeLoc);
		close(conn);
		return list;	
	}
	public List<Place> selectPricePlaceList(Map<String, Object> param, String price) {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectPricePlaceList(conn, param, price);
		close(conn);
		return list;
	}
	public List<Place> selectLocPricePlaceList(Map<String, Object> param, String loc, String price) {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectLocPricePlaceList(conn, param, loc, price);
		close(conn);
		return list;
	}

	public int selectPlaceCount() {
		Connection conn = getConnection();
		int totalContents = placeDao.selectPlaceCount(conn);
		close(conn);
		return totalContents;
	}
	public int selectLocalPlaceCount(String loc) {
		Connection conn = getConnection();
		int totalContents = placeDao.selectLocalPlaceCount(conn,loc);
		close(conn);
		return totalContents;
	}
	public int selectPricePlaceCount(String price) {
		Connection conn = getConnection();
		int totalContents = placeDao.selectPricePlaceCount(conn,price);
		close(conn);
		return totalContents;
	}
	public int selectLocPricePlaceCount(String loc, String price) {
		Connection conn = getConnection();
		int totalContents = placeDao.selectLocPricePlaceCount(conn, loc, price);
		close(conn);
		return totalContents;
	}
	public List<Place> selectLocalPlace(String placeLoc, Map<String, Object> param) {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectLocalPlace(conn, placeLoc, param);
		close(conn);
		return list;	
	}
	public List<Place> selectPlaceRandomPick() {
		Connection conn = getConnection();
		List<Place> list = placeDao.selectPlaceRandomPick(conn);
		close(conn);
		return list;
	}
	public int placeRegEnroll(Registration reg) {
		Connection conn = getConnection();
		int result = placeDao.placeRegEnroll(conn, reg);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public List<Registration> myRegList(String placeId) {
		Connection conn = getConnection();
		List<Registration> list = placeDao.myRegList(conn, placeId);
		close(conn);
		return list;	
	}
	public int regAccept(String regId) {
		Connection conn = getConnection();
		int result = placeDao.regAccept(conn, regId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public int regCancel(String regId) {
		Connection conn = getConnection();
		int result = placeDao.regCancel(conn, regId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public int payUpdate(String regId) {
		Connection conn = getConnection();
		int result = placeDao.payUpdate(conn, regId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	public String selectPlaceFacility(String placeId) {
		Connection conn = getConnection();
		String facility =  placeDao.selectPlaceFacility(conn, placeId);
		close(conn);
		return facility;
	}
}
