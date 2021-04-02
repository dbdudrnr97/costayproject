package product.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.vo.Registration;
import product.model.vo.ImageStore;
import product.model.vo.Play;

public class PlayDao {
	
	private Properties prop = new Properties();
	
	public PlayDao() {
		String fileName = "/sql/product/product-query.properties";
		String path = PlayDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("path@MemberDao = " + path);
//		System.out.println("prop@MemberDao = " + prop);
	}
	
	public Play selectPlayOne(Connection conn, String playId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlayOne");
		Play play = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
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
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
//		System.out.println("member@dao = " + member);
		return play;

	}
	
	public List<Play> selectPlayList(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlayList");
		//selectPlayList = 
		//select * from( select row_number() over(order by play_id desc) 
		//rnum, p.*, (select * from play where play_del_flag = 'N') 
		//play_count from play p) v where rnum between ? and ?

		Play play = null;
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");

		List<Play> list = new ArrayList<Play>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(2, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
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
	
	public List<Play> selectLocalPlayList(Connection conn, Map<String, Object> param, String playLoc) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocalPlayList");
		List<Play> list = new ArrayList<Play>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		pstmt = conn.prepareStatement(sql);
		//selectLocalPlay = select * from( select row_number() over(order by p.play_id desc) 
		//rnum, p.* from (select * from play where play_loc=? and play_del_flag = 'N') p) v
		//where rnum between ? and ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playLoc);
			pstmt.setInt(2, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(3, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Play play = new Play();
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
				list.add(play);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Play> selectPricePlayList(Connection conn, Map<String, Object> param,  String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPricePlayList");
		List<Play> list = new ArrayList<Play>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		
		//selectPricePlay = select * from( select row_number() over(order by p.play_id desc) 
		//rnum, p.* from (select * from play where play_price>=? and play_del_flag = 'N') p) v 
		//where rnum between ? and ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(price));
			pstmt.setInt(2, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(3, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Play play = new Play();
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
				list.add(play);
				//System.out.println("locallist@dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Play> selectLocPricePlayList(Connection conn, Map<String, Object> param, String loc, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocPricePlayList");
		List<Play> list = new ArrayList<Play>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		//selectLocPricePlay = select * from( select row_number() over(order by p.play_id desc) 
		//rnum, p.* from (select * from play where play_loc=? and play_price>=? and play_del_flag = 'N') p) v 
		//where rnum between ? and ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc);
			pstmt.setInt(2, Integer.parseInt(price));
			pstmt.setInt(3, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(4, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Play play = new Play();
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
				list.add(play);
				//System.out.println("locallist@dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int selectPlayCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPlayCount");
		int totalContents= 0;
		//System.out.println("query@dao = " + query);
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(query);
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1); //컬럼순서로 가져옴.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public int selectLocalPlayCount(Connection conn, String loc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLocalPlayCount");
		int totalContents= 0;
		//System.out.println("query@dao = " + query);
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loc);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1); //컬럼순서로 가져옴.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public int selectPricePlayCount(Connection conn, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPricePlayCount");
		int totalContents= 0;
		//System.out.println("query@dao = " + query);
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(price));
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1); //컬럼순서로 가져옴.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public int selectLocPricePlayCount(Connection conn, String loc, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLocPricePlayCount");
		int totalContents= 0;
		//System.out.println("query@dao = " + query);
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loc);
			pstmt.setInt(2, Integer.parseInt(price));
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1); //컬럼순서로 가져옴.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	public List<Play> selectLocalPlay(Connection conn, String playLoc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocalPlay");
		List<Play> list = new ArrayList<Play>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playLoc);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Play play = new Play();
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
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
	
	public int playEnroll(Connection conn, Play play) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playEnroll");
		//insert into play values ('play_'||seq_play_id.nextval, ?, ?, ?, ?, default,?,?,?,?,?,?,?,?,?,?,default)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, play.getCompany());
			pstmt.setString(2, play.getPlayThumbnailOriginalFileName());
			pstmt.setString(3, play.getPlayThumbnailRenamedFileName());
			pstmt.setString(4, play.getPlayName());
			pstmt.setString(5, play.getPlayLoc());
			pstmt.setString(6, play.getPlayAddr());
			pstmt.setInt(7, play.getPlayPrice());
			pstmt.setInt(8, play.getPlayTime());
			pstmt.setInt(9, play.getPlayAvailCount());
			pstmt.setString(10, play.getPlayLanguage());
			pstmt.setString(11, play.getPlayNote());
			pstmt.setString(12, play.getPlayFood());
			pstmt.setString(13, play.getPlayEquipment());
			pstmt.setString(14, play.getPlayTransport());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int playImgEnroll(Connection conn, ImageStore imageStore) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playImgEnroll");
		//insert into IMAGESTORE values (SEQ_imagestore_no.nextval, ?, ?, ?, null)
		System.out.println("PlayDao = " + imageStore.getPlayId());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageStore.getOriginalFilename());
			pstmt.setString(2, imageStore.getRenamedFilename());
			pstmt.setString(3, imageStore.getPlayId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int playUpdate(Connection conn, Play play) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playUpdate");
		//update play set THUMBNAIL_ORIGINAL_FILENAME = ?, THUMBNAIL_RENAMED_FILENAME = ?, PLAY_ADDR = ?, 
		//PLAY_PRICE = ?, PLAY_AVAIL_COUNT =?, PLAY_NOTE = ?, PLAY_LANGUAGE = ?, PLAY_TIME = ?, 
		//PLAY_FOOD = ?, PLAY_EQUIPMENT =?, PLAY_TRANSPORT =? where play_id = ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, play.getPlayThumbnailOriginalFileName());
			pstmt.setString(2, play.getPlayThumbnailRenamedFileName());
			pstmt.setString(3, play.getPlayAddr());
			pstmt.setInt(4, play.getPlayPrice());
			pstmt.setInt(5, play.getPlayAvailCount());
			pstmt.setString(6, play.getPlayNote());
			pstmt.setString(7, play.getPlayLanguage());
			pstmt.setInt(8, play.getPlayTime());
			pstmt.setString(9, play.getPlayFood());
			pstmt.setString(10, play.getPlayEquipment());
			pstmt.setString(11, play.getPlayTransport());
			pstmt.setString(12, play.getPlayId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int playImgUpdate(Connection conn, ImageStore imageStore) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playImgUpdate");
		//update imagestore set ORIGINAL_FILENAME = ?,RENAMED_FILENAME = ? where Play_ID = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageStore.getOriginalFilename());
			pstmt.setString(2, imageStore.getRenamedFilename());
			pstmt.setString(3, imageStore.getPlayId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectLastPlayId(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String playId = "";
		String sql = prop.getProperty("selectLastPlayId");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				playId = rset.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return playId;
	}

	public int playDelete(Connection conn, String playId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playDelete");
		// update place set place_del_flag = 'Y' where play_id = ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public List<ImageStore> selectPlayImageStore(Connection conn, String playId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlayImageStore");
		// selectPlayImageStore = select * from imageStore where play_id = ?
		List<ImageStore> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playId);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<ImageStore>();
			
			while(rset.next()) {
				ImageStore i = new ImageStore();
				i.setOriginalFilename(rset.getString("ORIGINAL_FILENAME"));
				i.setRenamedFilename(rset.getString("RENAMED_FILENAME"));
				i.setPlayId(playId);
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<ImageStore> selectImageStore(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectImageStore");
		//select * from ImageStore place_Id = ?
		List<ImageStore> list = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<ImageStore>();
			
			while(rset.next()) {
				ImageStore i = new ImageStore();
				i.setOriginalFilename(rset.getString("ORIGINAL_FILENAME"));
				i.setRenamedFilename(rset.getString("RENAMED_FILENAME"));
				i.setPlayId(placeId);
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Play> selectPlayRandomPick(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlayRandomPick");
		Play play = null;
		List<Play> list = new ArrayList<Play>();
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				play = new Play();
				play.setPlayId(rset.getString("play_id"));
				play.setCompany(rset.getString("company"));
				play.setPlayThumbnailOriginalFileName(rset.getString("thumbnail_original_filename"));
				play.setPlayThumbnailRenamedFileName(rset.getString("thumbnail_renamed_filename"));
				play.setPlayName(rset.getString("play_name"));
				play.setPlayScore(rset.getInt("play_score"));
				play.setPlayLoc(rset.getString("play_loc"));
				play.setPlayAddr(rset.getString("play_addr"));
				play.setPlayPrice(rset.getInt("play_price"));
				play.setPlayTime(rset.getInt("play_time"));
				play.setPlayAvailCount(rset.getInt("play_avail_count"));
				play.setPlayLanguage(rset.getString("play_note"));
				play.setPlayNote(rset.getString("play_note"));
				play.setPlayFood(rset.getString("play_food"));
				play.setPlayEquipment(rset.getString("play_equipment"));
				play.setPlayTransport(rset.getString("play_transport"));
				play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
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
	public int playRegistration(Connection conn, Registration reg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("playRegistration");
		//insert into registration values ((select substr(member_id, 1, 2) from member where member_id=?) || 
		//to_char(sysdate,'yyyymmdd') || to_char(seq_place_id.nextval),
		//?, sysdate, ?, ?, 'Y', default, ?, ?, null)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reg.getRegMemberId());
			pstmt.setInt(2, reg.getGuestCount());
			pstmt.setDate(3, reg.getRegChkinDate());
			pstmt.setDate(4, reg.getRegChkinDate());
			pstmt.setString(5, reg.getRegMemberId());
			pstmt.setString(6, reg.getPlayId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
