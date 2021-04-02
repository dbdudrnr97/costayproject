package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.vo.Member;
import member.model.vo.Registration;
import product.model.vo.Place;
import product.model.vo.Play;

public class MemberDao {

	private Properties prop = new Properties();
	
	/**
	 * 객체 생성시 member-query.properties의 내용을 읽어다 prop필드에 저장
	 */
	public MemberDao() {
		String fileName = "/sql/member/member-query.properties";
		String path = MemberDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("path@MemberDao = " + path);
//		System.out.println("prop@MemberDao = " + prop);
	}
	
	public Member selectOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		Member member = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			while(rset.next()) {
				/**
				 * 	
				 * 	private String memberId; // 필수
					private String password; // 필수	
					private String memberName;     //필수 
					private String memberProfileOriginalFileName; // 안필수
					private String memberProfileRenamedFileName; // 안필수
					private String memberPassport; // 필수
					private String Phone; // 필수
					private String gender; // 필수
					private Date birth; //필수
					private String memberAddress; //필수
					private String memberEmail; // 필수
					private Date memberEnrollDate;
					private char memberDelFlag;
					private char memberRole;
				 */
				
				member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberProfileOriginalFileName(rset.getString("profile_original_file_name"));
				member.setMemberProfileRenamedFileName(rset.getString("profile_renamed_file_name"));
				member.setMemberPassport(rset.getString("member_passport"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setBirth(rset.getDate("birth"));
				member.setMemberAddress(rset.getString("member_address"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberEnrollDate(rset.getDate("member_enroll_date"));
				member.setMemberDelFlag(rset.getString("member_del_flag").charAt(0));
				member.setMemberRole(rset.getString("member_role").charAt(0));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
//		System.out.println("member@dao = " + member);
		return member;

	}
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("insertMember");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			//쿼리문 미완성 
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberProfileOriginalFileName());
			pstmt.setString(5, member.getMemberProfileRenamedFileName());
			pstmt.setString(6, member.getMemberPassport());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getGender());
			pstmt.setDate(9, member.getBirth());
			pstmt.setString(10, member.getMemberAddress());
			pstmt.setString(11, member.getMemberEmail());
					
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberProfileOriginalFileName());
			pstmt.setString(2, member.getMemberProfileRenamedFileName());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getMemberPassport());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setString(7, member.getMemberEmail());
			pstmt.setString(8, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Registration> selectWaitRegistrationList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWaitRegistrationList");
		List<Registration> list = new ArrayList<Registration>();
		Registration reg = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			while(rset.next()) {
				reg = new Registration();
				reg.setRegId(rset.getString("reg_id"));
				reg.setGuestCount(rset.getInt("guest_count"));
				reg.setRegDate(rset.getDate("reg_date"));
				reg.setRegChkinDate(rset.getDate("reg_chkin_date"));
				reg.setRegChkOutDate(rset.getDate("reg_chkout_date"));
				reg.setRegPayYN(rset.getString("reg_pay_yn").charAt(0));
				reg.setRegDelFlag(rset.getString("reg_del_flag").charAt(0));
				reg.setRegMemberId(rset.getString("reg_member_id"));
				reg.setPlayId(rset.getString("play_id"));
				reg.setPlaceId(rset.getString("place_id"));
				list.add(reg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Registration> selectRegistrationList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRegistrationList");
		List<Registration> list = new ArrayList<Registration>();
		Registration reg = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			while(rset.next()) {
				reg = new Registration();
				reg.setRegId(rset.getString("reg_id"));
				reg.setGuestCount(rset.getInt("guest_count"));
				reg.setRegDate(rset.getDate("reg_date"));
				reg.setRegChkinDate(rset.getDate("reg_chkin_date"));
				reg.setRegChkOutDate(rset.getDate("reg_chkout_date"));
				reg.setRegPayYN(rset.getString("reg_pay_yn").charAt(0));
				reg.setRegDelFlag(rset.getString("reg_del_flag").charAt(0));
				reg.setRegMemberId(rset.getString("reg_member_id"));
				reg.setPlayId(rset.getString("play_id"));
				reg.setPlaceId(rset.getString("place_id"));
				list.add(reg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public Map<String, Play> selectExpectedPlay(Connection conn, String memberId,
		Map<String, Play> map) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectExpectedPlay");
		
		Play play = null;
		List<Registration> list = selectRegistrationList(conn, memberId);
		Iterator<Registration> iter = list.iterator();
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			while(iter.hasNext()) {
				Registration reg = iter.next();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reg.getRegId());
				
				//2.Statement실행 및 결과처리:ResultSet -> Member
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
					play.setPlayLanguage(rset.getString("play_language"));
					play.setPlayNote(rset.getString("play_note")); 
					play.setPlayFood(rset.getString("play_food"));
					play.setPlayEquipment(rset.getString("play_equipment"));
					play.setPlayTransport(rset.getString("play_transport"));
					play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
					map.put(reg.getRegId(),play);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return map;
	}


	public Map<String, Play> selectPreviousPlay(Connection conn, String memberId,
			Map<String, Play> map) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectPreviousPlay");
			
			Play play = null;
			List<Registration> list = selectRegistrationList(conn, memberId);
			Iterator<Registration> iter = list.iterator();
			
			try {
				//1.PreparedStatement객체생성(미완성쿼리 값대입)
				while(iter.hasNext()) {
					Registration reg = iter.next();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reg.getRegId());
					
					//2.Statement실행 및 결과처리:ResultSet -> Member
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
						play.setPlayLanguage(rset.getString("play_language"));
						play.setPlayNote(rset.getString("play_note")); 
						play.setPlayFood(rset.getString("play_food"));
						play.setPlayEquipment(rset.getString("play_equipment"));
						play.setPlayTransport(rset.getString("play_transport"));
						play.setPlayDelFlag(rset.getString("play_del_flag").charAt(0));
						map.put(reg.getRegId(),play);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//3.자원반납(ResultSet, PreparedStatement)
				close(rset);
				close(pstmt);
			}
			return map;
	}

	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("deleteMember");
		int result = 0;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
//		System.out.println("member@dao = " + member);
//		System.out.println("result = " + result);
		return result;
	}

	public Registration selectRegistrationOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRegistrationOne");
		Registration reg = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
				reg = new Registration();
				reg.setRegId(rset.getString("reg_id"));
				reg.setGuestCount(rset.getInt("guest_count"));
				reg.setRegDate(rset.getDate("reg_date"));
				reg.setRegChkinDate(rset.getDate("reg_chkin_date"));
				reg.setRegChkOutDate(rset.getDate("reg_chkout_date"));
				reg.setRegPayYN(rset.getString("reg_pay_yn").charAt(0));
				reg.setRegDelFlag(rset.getString("reg_del_flag").charAt(0));
				reg.setRegMemberId(rset.getString("reg_member_id"));
				reg.setPlayId(rset.getString("play_id"));
				reg.setPlaceId(rset.getString("place_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return reg;
	}
	
	public Registration selectWaitRegistrationOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWaitRegistrationOne");
		Registration reg = null;
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2.Statement실행 및 결과처리:ResultSet -> Member
			rset = pstmt.executeQuery();
			if(rset.next()) {
				reg = new Registration();
				reg.setRegId(rset.getString("reg_id"));
				reg.setGuestCount(rset.getInt("guest_count"));
				reg.setRegDate(rset.getDate("reg_date"));
				reg.setRegChkinDate(rset.getDate("reg_chkin_date"));
				reg.setRegChkOutDate(rset.getDate("reg_chkout_date"));
				reg.setRegPayYN(rset.getString("reg_pay_yn").charAt(0));
				reg.setRegDelFlag(rset.getString("reg_del_flag").charAt(0));
				reg.setRegMemberId(rset.getString("reg_member_id"));
				reg.setPlayId(rset.getString("play_id"));
				reg.setPlaceId(rset.getString("place_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return reg;
	}

	public Map<String, Place> selectExpectedPlace(Connection conn, String memberId, Map<String, Place> map) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectExpectedPlace");
		
		Place place = null;
		List<Registration> list = selectRegistrationList(conn, memberId);
		Iterator<Registration> iter = list.iterator();
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			while(iter.hasNext()) {
				Registration reg = iter.next();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reg.getRegId());
				
				//2.Statement실행 및 결과처리:ResultSet -> Member
				rset = pstmt.executeQuery();
				while(rset.next()) {
					
					/*
PLACE_ID                    NOT NULL VARCHAR2(20)   
THUMBNAIL_ORIGINAL_FILENAME          VARCHAR2(200)  
THUMBNAIL_RENAMED_FILENAME           VARCHAR2(200)  
PLACE_NAME                  NOT NULL VARCHAR2(100)  
PLACE_SCORE                          NUMBER         
PLACE_LOC                   NOT NULL VARCHAR2(50)   
PLACE_ADDR                  NOT NULL VARCHAR2(200)  
PLACE_PRICE                 NOT NULL NUMBER         
PLACE_AVAIL_COUNT           NOT NULL NUMBER         
PLACE_NOTE                  NOT NULL VARCHAR2(1000) 
PLACE_ROOM_COUNT            NOT NULL NUMBER         
PLACE_FACILITY              NOT NULL VARCHAR2(150)  
PLACE_DEL_FLAG                       CHAR(1)        
HOST_MEMBER_ID                       VARCHAR2(20)   
					 * */
					
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
					map.put(reg.getRegId(),place);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return map;
	}

	public Map<String, Place> selectPreviousPlace(Connection conn, String memberId, Map<String, Place> map) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPreviousPlace");		
		Place place = null;
		List<Registration> list = selectRegistrationList(conn, memberId);
		Iterator<Registration> iter = list.iterator();
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			while(iter.hasNext()) {
				Registration reg = iter.next();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reg.getRegId());
				
				//2.Statement실행 및 결과처리:ResultSet -> Member
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
					map.put(reg.getRegId(),place);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return map;
	}

	public Map<String, Place> selectWaitPlace(Connection conn, String memberId, Map<String, Place> map) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWaitPlace");		
		Place place = null;
		List<Registration> list = selectWaitRegistrationList(conn, memberId);
		Iterator<Registration> iter = list.iterator();
		
		try {
			//1.PreparedStatement객체생성(미완성쿼리 값대입)
			while(iter.hasNext()) {
				Registration reg = iter.next();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reg.getRegId());
				
				//2.Statement실행 및 결과처리:ResultSet -> Member
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
					map.put(reg.getRegId(),place);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3.자원반납(ResultSet, PreparedStatement)
			close(rset);
			close(pstmt);
		}
		return map;
	}
}
