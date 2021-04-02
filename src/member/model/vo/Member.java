package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Member implements Serializable, HttpSessionBindingListener{

	private String memberId; // 필수
	private String password; // 필수
	private String memberName;     //필수 
	private String memberProfileOriginalFileName; // 안필수
	private String memberProfileRenamedFileName; // 안필수
	private String memberPassport; // 필수
	private String phone; // 필수
	private String gender; // 필수
	private int age; //필수
	private Date birth; //필수
	private String memberAddress; //필수
	private String memberEmail; // 필수
	private Date memberEnrollDate;
	private char memberDelFlag;
	private char memberRole;
	

	
	public Member() {
		super();
	}
	
	
	public Member(String memberId, String password, String memberName, String memberProfileOriginalFileName,
			String memberProfileRenamedFileName, String memberPassport, String phone, String gender, int age,
			Date birth, String memberAddress, String memberEmail, Date memberEnrollDate, char memberDelFlag,
			char memberRole) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.memberProfileOriginalFileName = memberProfileOriginalFileName;
		this.memberProfileRenamedFileName = memberProfileRenamedFileName;
		this.memberPassport = memberPassport;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.birth = birth;
		this.memberAddress = memberAddress;
		this.memberEmail = memberEmail;
		this.memberEnrollDate = memberEnrollDate;
		this.memberDelFlag = memberDelFlag;
		this.memberRole = memberRole;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberProfileOriginalFileName() {
		return memberProfileOriginalFileName;
	}


	public void setMemberProfileOriginalFileName(String memberProfileOriginalFileName) {
		this.memberProfileOriginalFileName = memberProfileOriginalFileName;
	}


	public String getMemberProfileRenamedFileName() {
		return memberProfileRenamedFileName;
	}


	public void setMemberProfileRenamedFileName(String memberProfileRenamedFileName) {
		this.memberProfileRenamedFileName = memberProfileRenamedFileName;
	}


	public String getMemberPassport() {
		return memberPassport;
	}


	public void setMemberPassport(String memberPassport) {
		this.memberPassport = memberPassport;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getMemberAddress() {
		return memberAddress;
	}


	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}


	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}


	public char getMemberDelFlag() {
		return memberDelFlag;
	}


	public void setMemberDelFlag(char memberDelFlag) {
		this.memberDelFlag = memberDelFlag;
	}


	public char getMemberRole() {
		return memberRole;
	}


	public void setMemberRole(char memberRole) {
		this.memberRole = memberRole;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName
				+ ", memberProfileOriginalFileName=" + memberProfileOriginalFileName + ", memberProfileRenamedFileName="
				+ memberProfileRenamedFileName + ", memberPassport=" + memberPassport + ", Phone=" + phone + ", gender="
				+ gender + ", age=" + age + ", birth=" + birth + ", memberAddress=" + memberAddress + ", memberEmail="
				+ memberEmail + ", memberEnrollDate=" + memberEnrollDate + ", memberDelFlag=" + memberDelFlag
				+ ", memberRole=" + memberRole + "]";
	}


	@Override
	public void valueBound(HttpSessionBindingEvent event) {
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
	}
}
