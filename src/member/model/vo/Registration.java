package member.model.vo;

import java.sql.Date;

public class Registration {
	private String regId;
	private int guestCount;
	private Date regDate;
	private Date regChkinDate;
	private Date regChkOutDate;
	private char regPayYN;
	private char regDelFlag;
	private String regMemberId;
	private String playId;
	private String placeId;
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Registration(String regId, int guestCount, Date regDate, Date regChkinDate, Date regChkOutDate,
			char regPayYN, char regDelFlag, String regMemberId, String playId, String placeId) {
		super();
		this.regId = regId;
		this.guestCount = guestCount;
		this.regDate = regDate;
		this.regChkinDate = regChkinDate;
		this.regChkOutDate = regChkOutDate;
		this.regPayYN = regPayYN;
		this.regDelFlag = regDelFlag;
		this.regMemberId = regMemberId;
		this.playId = playId;
		this.placeId = placeId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public int getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getRegChkinDate() {
		return regChkinDate;
	}
	public void setRegChkinDate(Date regChkinDate) {
		this.regChkinDate = regChkinDate;
	}
	public Date getRegChkOutDate() {
		return regChkOutDate;
	}
	public void setRegChkOutDate(Date regChkOutDate) {
		this.regChkOutDate = regChkOutDate;
	}
	public char getRegPayYN() {
		return regPayYN;
	}
	public void setRegPayYN(char regPayYN) {
		this.regPayYN = regPayYN;
	}
	public char getRegDelFlag() {
		return regDelFlag;
	}
	public void setRegDelFlag(char regDelFlag) {
		this.regDelFlag = regDelFlag;
	}
	public String getRegMemberId() {
		return regMemberId;
	}
	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}
	public String getPlayId() {
		return playId;
	}
	public void setPlayId(String playId) {
		this.playId = playId;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	@Override
	public String toString() {
		return "Registration [regId=" + regId + ", guestCount=" + guestCount + ", regDate=" + regDate
				+ ", regChkinDate=" + regChkinDate + ", regChkOutDate=" + regChkOutDate + ", regPayYN=" + regPayYN
				+ ", regDelFlag=" + regDelFlag + ", regMemberId=" + regMemberId + ", playId=" + playId + ", placeId="
				+ placeId + "]";
	}
}
