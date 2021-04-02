package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.Registration;
import product.model.vo.Place;
import product.model.vo.Play;

public class MemberService {

    public static final char ADMIN_MEMBER_ROLE = 'A';//관리자 롤
    public static final char GUEST_MEMBER_ROLE = 'G';//일반사용자 롤
    public static final char HOST_MEMBER_ROLE = 'H';//일반사용자 롤
    
	private MemberDao memberDao = new MemberDao();

	public Member selectOne(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		Member member =  memberDao.selectOne(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return member;
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int chk = memberDao.insertMember(conn, member);
		if(chk>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return chk;
	}
	
	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(conn, member);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Registration> selectRegistrationList(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		List<Registration> list =  memberDao.selectRegistrationList(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return list;
	}
	public List<Registration> selectWaitRegistrationList(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		List<Registration> list =  memberDao.selectWaitRegistrationList(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return list;
	}
	
	public Registration selectRegistrationOne(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		Registration reg =  memberDao.selectRegistrationOne(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return reg;
	}
	
	public Registration selectWaitRegistrationOne(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		Registration reg =  memberDao.selectWaitRegistrationOne(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return reg;
	}
	
	public Map<String, Play> selectExpectedPlay(String memberId, Map<String, Play> map) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		map =  memberDao.selectExpectedPlay(conn, memberId, map);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return map;
	}

	public Map<String, Play> selectPreviousPlay(String memberId, Map<String, Play> map) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		map =  memberDao.selectPreviousPlay(conn, memberId, map);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return map;
	}
	
	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int chk = memberDao.deleteMember(conn, memberId);
		if(chk>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return chk;
	}

	public Map<String, Place> selectExpectedPlace(String memberId, Map<String, Place> map) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		map =  memberDao.selectExpectedPlace(conn, memberId, map);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return map;
	}

	public Map<String, Place> selectPreviousPlace(String memberId, Map<String, Place> map) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		map =  memberDao.selectPreviousPlace(conn, memberId, map);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return map;
	}

	public Map<String, Place> selectWaitPlace(String memberId, Map<String, Place> map) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		
		map =  memberDao.selectWaitPlace(conn, memberId, map);
		//3.트랜잭션관리(DML만)
		//4.자원반납
		close(conn);
		return map;
	}
}
