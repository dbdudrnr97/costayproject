package admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;

import product.model.dao.PlaceDao;
import product.model.vo.Place;
import product.model.vo.Play;

public class AdminDao {
	
	private Properties prop = new Properties();
	public AdminDao() {
		String fileName = "/sql/admin/admin-query.properties";
		String path = AdminDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Place> selectCPlace(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCPlace");
		Place place = null;
		List<Place> list = new ArrayList<Place>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				place = new Place();
				place.setPlaceId(rset.getString("place_id"));
				place.setPlaceThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				place.setPlaceThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				place.setPlaceName(rset.getString("place_name"));
				place.setPlaceScore(rset.getInt("place_score"));
				place.setPlaceLoc(rset.getString("place_loc"));
				place.setPlaceAddr(rset.getString("place_addr"));
				place.setPlacePrice(rset.getInt("place_price"));
				place.setPlaceAvailCount(rset.getInt("place_avail_count"));
				place.setPlaceNote(rset.getString("place_note"));
				place.setPlaceRoomCount(rset.getInt("place_room_count"));
				place.setPlaceFacility(rset.getString("place_facility"));
				place.setPlaceDelFlag(rset.getString("place_del_flag").charAt(0));
				place.setHostMemberId(rset.getString("host_member_id"));
				
				list.add(place);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int placeRoleChange(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeRoleChange");
		//update place set place_del_flag = 'N' where place_id = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int placeAccept(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeAccept");
		//update place set place_del_flag = 'N' where place_id = ?
		placeRoleChange(conn, placeId);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int placeCancel(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeCancel");
		//update place set place_del_flag = 'Y' where place_id = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Play> selectAllPlay(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllPlay");
		Play play = null;
		List<Play> list = new ArrayList<Play>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("THUMBNAIL_ORIGINAL_FILENAME"));
				play.setPlayThumbnailRenamedFileName(rset.getString("THUMBNAIL_RENAMED_FILENAME"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_language"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("PLAY_EQUIPMENT"));
				play.setPlayTransport(rset.getString("PLAY_TRANSPORT"));
				
				list.add(play);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
