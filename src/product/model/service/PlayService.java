package product.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.vo.Registration;
import product.model.dao.PlayDao;
import product.model.vo.ImageStore;
import product.model.vo.Play;

public class PlayService {
	private PlayDao playDao = new PlayDao();
	public static final int TOTAL_SEARCH = 1;
	public static final int LOCAL_SEARCH = 2;
	public static final int PRICE_SEARCH = 3;
	public static final int LOC_PRICE_SEARCH = 4;	
	
	public Play selectPlayOne(String playId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		Play play =  playDao.selectPlayOne(conn, playId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return play;
	}

	public List<Play> selectPlayList(Map<String, Object> param) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		List<Play> list =  playDao.selectPlayList(conn, param);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return list;
	}

	public List<Play> selectLocalPlayList(Map<String, Object> param, String playLoc) throws Exception {
		Connection conn = getConnection();
		List<Play> list = playDao.selectLocalPlayList(conn, param, playLoc);
		close(conn);
		return list;	
	}
	public List<Play> selectPricePlayList(Map<String, Object> param, String price) {
		Connection conn = getConnection();
		List<Play> list = playDao.selectPricePlayList(conn, param, price);
		close(conn);
		return list;
	}
	public List<Play> selectLocPricePlayList(Map<String, Object> param, String loc, String price) {
		Connection conn = getConnection();
		List<Play> list = playDao.selectLocPricePlayList(conn, param, loc, price);
		close(conn);
		return list;
	}

	public int selectPlayCount() {
		Connection conn = getConnection();
		int totalContents = playDao.selectPlayCount(conn);
		close(conn);
		return totalContents;
	}
	public int selectLocalPlayCount(String loc) {
		Connection conn = getConnection();
		int totalContents = playDao.selectLocalPlayCount(conn,loc);
		close(conn);
		return totalContents;
	}
	public int selectPricePlayCount(String price) {
		Connection conn = getConnection();
		int totalContents = playDao.selectPricePlayCount(conn,price);
		close(conn);
		return totalContents;
	}
	public int selectLocPricePlayCount(String loc, String price) {
		Connection conn = getConnection();
		int totalContents = playDao.selectLocPricePlayCount(conn, loc, price);
		close(conn);
		return totalContents;
	}
	public List<Play> selectLocalPlay(String playLoc) {
		Connection conn = getConnection();
		List<Play> list = playDao.selectLocalPlay(conn, playLoc);
		close(conn);
		return list;	
	}
	
	public int playEnroll(Play play) {
		Connection conn = getConnection();
		int result = playDao.playEnroll(conn, play);
		if(result > 0) {
			commit(conn);
			//숙소등록에 성공한 숙소ID값
			String playId = playDao.selectLastPlayId(conn);
			play.setPlayId(playId);
		}
		else rollback(conn);
		close(conn);
		
		return result;
		
	}

	public int playImgEnroll(ImageStore imageStore) {
		Connection conn = getConnection();
		int result = playDao.playImgEnroll(conn, imageStore);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int playUpdate(Play play) {
		Connection conn = getConnection();
		int result = playDao.playUpdate(conn, play);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public List<ImageStore> selectImageStore(String playId) {
		Connection conn = getConnection();
		List<ImageStore> list = playDao.selectImageStore(conn, playId);
		close(conn);
		return list;
	}
	
	public List<ImageStore> selectPlayImageStore(String playId) {
		Connection conn = getConnection();
		List<ImageStore> list = playDao.selectPlayImageStore(conn, playId);
		close(conn);
		return list;
	}

	public int playImgUpdate(ImageStore imageStore) {
		Connection conn = getConnection();
		int result = playDao.playImgUpdate(conn, imageStore);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public String selectLastPlayId() {
		Connection conn = getConnection();
		String playId = playDao.selectLastPlayId(conn);
		close(conn);
		return playId;
	}

	public int playDelete(String playId) {
		Connection conn = getConnection();
		int result = playDao.playDelete(conn, playId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<Play> selectPlaceRandomPick() {
		Connection conn = getConnection();
		List<Play> list = playDao.selectPlayRandomPick(conn);
		close(conn);
		return list;
	}

	public List<Play> selectPlayRandomPick() {
		Connection conn = getConnection();
		List<Play> list = playDao.selectPlayRandomPick(conn);
		close(conn);
		return list;
	}
	public int playRegistration(Registration reg) {
		Connection conn = getConnection();
		int result = playDao.playRegistration(conn, reg);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
}
}
