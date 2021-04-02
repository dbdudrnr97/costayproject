package product.model.vo;

public class ImageStore {
	
	private String originalFilename;	//not null
	private String renamedFilename;		//not null
	private int imageNo;
	private String playId;			
	private String placeId;	
	
	public ImageStore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageStore(int imageNo,String originalFilename, String renamedFilename, String playId,
			String placeId) {
		super();
		this.imageNo = imageNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.playId = playId;
		this.placeId = placeId;
	}


	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public int getImageNo() {
		return imageNo;
	}
	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
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
		return "ImageStrore [originalFilename=" + originalFilename + ", renamedFilename="
				+ renamedFilename + ", imageNo=" + imageNo + ", playId=" + playId + ", placeId=" + placeId + "]";
	}
	
}
