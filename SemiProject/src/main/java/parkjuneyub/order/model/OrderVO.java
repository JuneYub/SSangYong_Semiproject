package parkjuneyub.order.model;

import parkjuneyub.member.model.MemberVO;

public class OrderVO {

	private String order_num;
	private long order_price_total;
	private long order_mileage_total;
	private String order_date;
	private MemberVO mvo;
	
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public long getOrder_price_total() {
		return order_price_total;
	}
	public void setOrder_price_total(long order_price_total) {
		this.order_price_total = order_price_total;
	}
	public long getOrder_mileage_total() {
		return order_mileage_total;
	}
	public void setOrder_mileage_total(long order_mileage_total) {
		this.order_mileage_total = order_mileage_total;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public MemberVO getMvo() {
		return mvo;
	}
	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	
}
