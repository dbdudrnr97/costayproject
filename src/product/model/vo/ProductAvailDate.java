package product.model.vo;

import java.sql.Date;

public class ProductAvailDate {
    private Date availDate;
    private char category;
    private String playId;
    private String placeId;
	public ProductAvailDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductAvailDate(Date availDate, char category, String playId, String placeId) {
		super();
		this.availDate = availDate;
		this.category = category;
		this.playId = playId;
		this.placeId = placeId;
	}
	@Override
	public String toString() {
		return "ProductAvailDate [availDate=" + availDate + ", category=" + category + ", playId=" + playId
				+ ", placeId=" + placeId + "]";
	}
	public Date getAvailDate() {
		return availDate;
	}
	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
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
    
    
}
