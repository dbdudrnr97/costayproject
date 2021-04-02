package product.model.vo;

import java.sql.Date;

public class Play {
	private String playId;
	private String company; //NOT NULL //주최
	private String playThumbnailOriginalFileName;  
	private String playThumbnailRenamedFileName;
	private String playName;	//NOT NULL   //체험명
	private int playScore;		//NOT NULL  //별점
	private String playLoc;	//NOT NULL   //지역
	private String playAddr;		//NOT NULL   //주소
	private int playPrice;		//NOT NULL  //가격
	private int playTime;		//NOT NULL  //이용가능시간
	private int playAvailCount;		//NOT NULL  //이용가능인원
	private String playLanguage;		//NOT NULL //언어(default = korean)
	private String playNote;		//NOT NULL  //체험 설명
	private String playFood;		//NULL  //재료
	private String playEquipment;		//NULL   //도구
	private String playTransport;	//NOT NULL   //교통편
	private char playDelFlag;	//NOT NULL 
	
	public Play() {
	}
	
	public Play(String playId, String company, String playThumbnailOriginalFileName, String playThumbnailRenamedFileName,
			String playName, int playScore, String playLoc, String playAddr, int playPrice, int playTime,
			int playAvailCount, String playLanguage, String playNote, String playFood, String playEquipment,
			String playTransport, char playDelFlag) {
		super();
		this.playId = playId;
		this.company = company;
		this.playThumbnailOriginalFileName = playThumbnailOriginalFileName;
		this.playThumbnailRenamedFileName = playThumbnailRenamedFileName;
		this.playName = playName;
		this.playScore = playScore;
		this.playLoc = playLoc;
		this.playAddr = playAddr;
		this.playPrice = playPrice;
		this.playTime = playTime;
		this.playAvailCount = playAvailCount;
		this.playLanguage = playLanguage;
		this.playNote = playNote;
		this.playFood = playFood;
		this.playEquipment = playEquipment;
		this.playTransport = playTransport;
		this.playDelFlag = playDelFlag;
	}
	
	
	public String getPlayId() {
		return playId;
	}

	public void setPlayId(String playId) {
		this.playId = playId;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPlayThumbnailOriginalFileName() {
		return playThumbnailOriginalFileName;
	}
	public void setPlayThumbnailOriginalFileName(String playThumbnailOriginalFileName) {
		this.playThumbnailOriginalFileName = playThumbnailOriginalFileName;
	}
	public String getPlayThumbnailRenamedFileName() {
		return playThumbnailRenamedFileName;
	}
	public void setPlayThumbnailRenamedFileName(String playThumbnailRenamedFileName) {
		this.playThumbnailRenamedFileName = playThumbnailRenamedFileName;
	}
	public String getPlayName() {
		return playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}
	public int getPlayScore() {
		return playScore;
	}
	public void setPlayScore(int playScore) {
		this.playScore = playScore;
	}
	public String getPlayLoc() {
		return playLoc;
	}
	public void setPlayLoc(String playLoc) {
		this.playLoc = playLoc;
	}
	public String getPlayAddr() {
		return playAddr;
	}
	public void setPlayAddr(String playAddr) {
		this.playAddr = playAddr;
	}
	public int getPlayPrice() {
		return playPrice;
	}
	public void setPlayPrice(int playPrice) {
		this.playPrice = playPrice;
	}
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	public int getPlayAvailCount() {
		return playAvailCount;
	}
	public void setPlayAvailCount(int playAvailCount) {
		this.playAvailCount = playAvailCount;
	}
	public String getPlayLanguage() {
		return playLanguage;
	}
	public void setPlayLanguage(String playLanguage) {
		this.playLanguage = playLanguage;
	}
	public String getPlayNote() {
		return playNote;
	}
	public void setPlayNote(String playNote) {
		this.playNote = playNote;
	}
	public String getPlayFood() {
		return playFood;
	}
	public void setPlayFood(String playFood) {
		this.playFood = playFood;
	}
	public String getPlayEquipment() {
		return playEquipment;
	}
	public void setPlayEquipment(String playEquipment) {
		this.playEquipment = playEquipment;
	}
	public String getPlayTransport() {
		return playTransport;
	}
	public void setPlayTransport(String playTransport) {
		this.playTransport = playTransport;
	}
	public char getPlayDelFlag() {
		return playDelFlag;
	}
	public void setPlayDelFlag(char playDelFlag) {
		this.playDelFlag = playDelFlag;
	}
	
	@Override
	public String toString() {
		return "Play [company=" + company + ", playThumbnailOriginalFileName=" + playThumbnailOriginalFileName
				+ ", playThumbnailRenamedFileName=" + playThumbnailRenamedFileName + ", playName=" + playName
				+ ", playScore=" + playScore + ", playLoc=" + playLoc + ", playAddr=" + playAddr
				+ ", playPrice=" + playPrice + ", playTime=" + playTime + ", playAvailCount=" + playAvailCount
				+ ", playLanguage=" + playLanguage + ", playNote=" + playNote + ", playFood=" + playFood
				+ ", playEquipment=" + playEquipment + ", playTransport=" + playTransport + ", playDelFlag="
				+ playDelFlag + "]" + "\n";
	}
	
	
	
}
