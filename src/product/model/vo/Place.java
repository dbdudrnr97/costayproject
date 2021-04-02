package product.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Place { //숙소
	private String placeId;			//숙소 id, 기본키, not null
	private String placeThumbnailOriginalFileName; //썸네일 원본 파일 이름 not null
	private String placeThumbnailRenamedFileName;  //썸네일 저장될 파일 이름 not null
	private String placeName; 		// 숙소 이름 not null
	private int placeScore;		// 숙소 평점  not null
	private String placeLoc;		// 숙소 간략한 위치 not null
	private String placeAddr;		// 숙소 주소 not null
	private int placePrice;		// 숙소 가격 not null
	private int placeAvailCount;	// 숙소 이용 가능 인원 not null
	private String placeNote;		// 숙소 설명 not null
	private int placeRoomCount;		// 숙소 방 갯수 not null
	private String placeFacility;	// 숙소 부대시설 not null
	private char placeDelFlag;		// 숙소 삭제여부 not null
	private String hostMemberId;	// 숙소의 호스트 id not null
	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Place(String placeId, String placeThumbnailOriginalFileName, String placeThumbnailRenamedFileName,
			String placeName, int placeScore, String placeLoc, String placeAddr, int placePrice,
			int placeAvailCount, String placeNote,
			int placeRoomCount, String placeFacility, char placeDelFlag, String hostMemberId) {
		super();
		this.placeId = placeId;
		this.placeThumbnailOriginalFileName = placeThumbnailOriginalFileName;
		this.placeThumbnailRenamedFileName = placeThumbnailRenamedFileName;
		this.placeName = placeName;
		this.placeScore = placeScore;
		this.placeLoc = placeLoc;
		this.placeAddr = placeAddr;
		this.placePrice = placePrice;
		this.placeAvailCount = placeAvailCount;
		this.placeNote = placeNote;
		this.placeRoomCount = placeRoomCount;
		this.placeFacility = placeFacility;
		this.placeDelFlag = placeDelFlag;
		this.hostMemberId = hostMemberId;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getPlaceThumbnailOriginalFileName() {
		return placeThumbnailOriginalFileName;
	}
	public void setPlaceThumbnailOriginalFileName(String placeThumbnailOriginalFileName) {
		this.placeThumbnailOriginalFileName = placeThumbnailOriginalFileName;
	}
	public String getPlaceThumbnailRenamedFileName() {
		return placeThumbnailRenamedFileName;
	}
	public void setPlaceThumbnailRenamedFileName(String placeThumbnailRenamedFileName) {
		this.placeThumbnailRenamedFileName = placeThumbnailRenamedFileName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public int getPlaceScore() {
		return placeScore;
	}
	public void setPlaceScore(int placeScore) {
		this.placeScore = placeScore;
	}
	public String getPlaceLoc() {
		return placeLoc;
	}
	public void setPlaceLoc(String placeLoc) {
		this.placeLoc = placeLoc;
	}
	public String getPlaceAddr() {
		return placeAddr;
	}
	public void setPlaceAddr(String placeAddr) {
		this.placeAddr = placeAddr;
	}
	public int getPlacePrice() {
		return placePrice;
	}
	public void setPlacePrice(int placePrice) {
		this.placePrice = placePrice;
	}
	public int getPlaceAvailCount() {
		return placeAvailCount;
	}
	public void setPlaceAvailCount(int placeAvailCount) {
		this.placeAvailCount = placeAvailCount;
	}
	public String getPlaceNote() {
		return placeNote;
	}
	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}
	public int getPlaceRoomCount() {
		return placeRoomCount;
	}
	public void setPlaceRoomCount(int placeRoomCount) {
		this.placeRoomCount = placeRoomCount;
	}
	public String getPlaceFacility() {
		return placeFacility;
	}
	public void setPlaceFacility(String placeFacility) {
		this.placeFacility = placeFacility;
	}
	public char getPlaceDelFlag() {
		return placeDelFlag;
	}
	public void setPlaceDelFlag(char placeDelFlag) {
		this.placeDelFlag = placeDelFlag;
	}
	public String getHostMemberId() {
		return hostMemberId;
	}
	public void setHostMemberId(String hostMemberId) {
		this.hostMemberId = hostMemberId;
	}
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeThumbnailOriginalFileName=" + placeThumbnailOriginalFileName
				+ ", placeThumbnailRenamedFileName=" + placeThumbnailRenamedFileName + ", placeName=" + placeName
				+ ", placeScore=" + placeScore + ", placeLoc=" + placeLoc + ", placeAddr=" + placeAddr + ", placePrice="
				+ placePrice + ", placeAvailDate=" + ", placeChkinTime=" 
				+ ", placeChkoutTime=" +   ", placeAvailCount=" + placeAvailCount + ", placeNote="
				+ placeNote + ", placeRoomCount=" + placeRoomCount + ", placeFacility=" + placeFacility
				+ ", placeDelFlag=" + placeDelFlag + ", hostMemberId=" + hostMemberId + "]" + "\n";
	}
	
	
}
