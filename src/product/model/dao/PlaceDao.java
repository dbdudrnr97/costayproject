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
import product.model.vo.Place;

public class PlaceDao {
	private Properties prop = new Properties();
	public PlaceDao() {
		String fileName = "/sql/product/product-query.properties";
		String path = PlaceDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int placeEnroll(Connection conn, Place place) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("placeEnroll");
		//insert into place values ('place_'||seq_place_id.nextval, ?, ?, ?, default, ?, ?, ?, ?, ?, ?, ?, default, ?)
		//System.out.println(place);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place.getPlaceThumbnailOriginalFileName());
			pstmt.setString(2, place.getPlaceThumbnailRenamedFileName());
			pstmt.setString(3, place.getPlaceName());
			pstmt.setString(4, place.getPlaceLoc());
			pstmt.setString(5, place.getPlaceAddr());
			pstmt.setInt(6, place.getPlacePrice());
			pstmt.setInt(7, place.getPlaceAvailCount());
			pstmt.setString(8, place.getPlaceNote());
			pstmt.setInt(9, place.getPlaceRoomCount());
			pstmt.setString(10, place.getPlaceFacility());
			pstmt.setString(11, place.getHostMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public String selectLastPlaceId(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String placeId = "";
		String sql = prop.getProperty("selectLastPlaceId");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				placeId = rset.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return placeId;
	}
	public int placeImgEnroll(Connection conn, ImageStore imageStore) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeImgEnroll");
		//insert into IMAGESTORE values (SEQ_imagestore_no.nextval, ?, ?, null, ?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageStore.getOriginalFilename());
			pstmt.setString(2, imageStore.getRenamedFilename());
			pstmt.setString(3, imageStore.getPlaceId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public List<Place> selectMyPlace(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMyPlace");
		Place place = null;
		List<Place> list = new ArrayList<Place>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
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
		}
		
		return list;
	}
	public Place selectPlaceOne(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceOne");
		Place place = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
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
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
//		System.out.println("member@dao = " + member);
		return place;

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
				i.setPlaceId(placeId);
				
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
	public int placeImgUpdate(Connection conn, ImageStore imageStore) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeImgUpdate");
		//update imagestore set ORIGINAL_FILENAME = ?,RENAMED_FILENAME = ? where PLACE_ID = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageStore.getOriginalFilename());
			pstmt.setString(2, imageStore.getRenamedFilename());
			pstmt.setString(3, imageStore.getPlaceId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int placeUpdate(Connection conn, Place place) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("placeUpdate");
		//update place set place_addr = ?,THUMBNAIL_ORIGINAL_FILENAME = ?,THUMBNAIL_RENAMED_FILENAME = ?,
		//PLACE_PRICE = ?,PLACE_AVAIL_COUNT = ?,PLACE_NOTE = ?,PLACE_ROOM_COUNT = ?,PLACE_FACILITY = ?where place_id = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place.getPlaceAddr());
			pstmt.setString(2, place.getPlaceThumbnailOriginalFileName());
			pstmt.setString(3, place.getPlaceThumbnailRenamedFileName());
			pstmt.setInt(4, place.getPlacePrice());
			pstmt.setInt(5, place.getPlaceAvailCount());
			pstmt.setString(6, place.getPlaceNote());
			pstmt.setInt(7, place.getPlaceRoomCount());
			pstmt.setString(8, place.getPlaceFacility());
			pstmt.setString(9, place.getPlaceId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public Place selectPlaceId(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceId");
		Place place = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return place;
	}

	public int placeDelete(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeDelete");
		// update place set place_del_flag = 'Y' where place_id = ?

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

	public List<Place> selectPlaceList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceList");
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
	
	public List<Place> selectPlaceList(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceList");
		//selectPlaceList = 
		//select * from( select row_number() over(order by place_id desc) 
		//rnum, p.*, (select * from place where place_del_flag = 'N') 
		//place_count from place p) v where rnum between ? and ?

		Place place = null;
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");

		List<Place> list = new ArrayList<Place>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(2, cpage*numPerPage); //마지막 rnum
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
	
	public List<Place> selectLocalPlaceList(Connection conn, Map<String, Object> param, String placeLoc) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocalPlaceList");
		List<Place> list = new ArrayList<Place>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		pstmt = conn.prepareStatement(sql);
		//selectLocalPlace = select * from( select row_number() over(order by p.place_id desc) 
		//rnum, p.* from (select * from place where place_loc=? and place_del_flag = 'N') p) v
		//where rnum between ? and ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeLoc);
			pstmt.setInt(2, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(3, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Place place = new Place();
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
				//System.out.println("locallist@dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Place> selectPricePlaceList(Connection conn, Map<String, Object> param,  String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPricePlaceList");
		List<Place> list = new ArrayList<Place>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		
		//selectPricePlace = select * from( select row_number() over(order by p.place_id desc) 
		//rnum, p.* from (select * from place where place_price>=? and place_del_flag = 'N') p) v 
		//where rnum between ? and ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(price));
			pstmt.setInt(2, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(3, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Place place = new Place();
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
				//System.out.println("locallist@dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Place> selectLocPricePlaceList(Connection conn, Map<String, Object> param, String loc, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocPricePlaceList");
		List<Place> list = new ArrayList<Place>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		
		//selectLocPricePlace = select * from( select row_number() over(order by p.place_id desc) 
		//rnum, p.* from (select * from place where place_loc=? and place_price>=? and place_del_flag = 'N') p) v 
		//where rnum between ? and ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc);
			pstmt.setInt(2, Integer.parseInt(price));
			pstmt.setInt(3, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(4, cpage*numPerPage); //마지막 rnum
			rset = pstmt.executeQuery(); 
			
			while(rset.next()) {
				Place place = new Place();
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
				//System.out.println("locallist@dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int selectPlaceCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPlaceCount");
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
	public int selectLocalPlaceCount(Connection conn, String loc) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLocalPlaceCount");
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
	public int selectPricePlaceCount(Connection conn, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPricePlaceCount");
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
	public int selectLocPricePlaceCount(Connection conn, String loc, String price) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLocPricePlaceCount");
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
	public List<Place> selectLocalPlace(Connection conn, String placeLoc, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLocalPlace");
		Place place = null;
		List<Place> list = new ArrayList<Place>();
		int cpage = (int)param.get("cpage");
		int numPerPage = (int)param.get("numPerPage");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeLoc);
			pstmt.setInt(2, (cpage-1)*numPerPage+1); //시작 rnum
			pstmt.setInt(3, cpage*numPerPage); //마지막 rnum
			
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
	public List<Place> selectPlaceRandomPick(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceRandomPick");
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
	public int placeRegEnroll(Connection conn, Registration reg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("placeRegEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reg.getRegMemberId());
			pstmt.setInt(2, reg.getGuestCount());
			pstmt.setDate(3, reg.getRegChkinDate());
			pstmt.setDate(4, reg.getRegChkOutDate());
			pstmt.setString(5, reg.getRegMemberId());
			pstmt.setString(6, reg.getPlaceId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public List<Registration> myRegList(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("myRegList");
		List<Registration> list = new ArrayList<Registration>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Registration reg = new Registration();
				reg.setRegId(rset.getString("reg_id"));
				reg.setGuestCount(rset.getInt("guest_count"));
				reg.setRegDate(rset.getDate("reg_date"));
				reg.setRegChkinDate(rset.getDate("reg_chkin_date"));
				reg.setRegChkOutDate(rset.getDate("reg_chkout_date"));
				reg.setRegMemberId(rset.getString("reg_member_id"));
				list.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	public int regAccept(Connection conn, String regId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("regAccept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int regCancel(Connection conn, String regId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("regCancel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int payUpdate(Connection conn, String regId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("payUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public String selectPlaceFacility(Connection conn, String placeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPlaceFacility");
		// selectPlaceFacility = select place_facility from place where place_id = ?
		String facility = "";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, placeId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				facility = rset.getString(1); //컬럼순서로 가져옴.
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("facility@dao = " + facility);
		return facility;

	}
}
