package parkjuneyub.order.model;

import parkjuneyub.product.model.ProductVO;

public class OrderDeatailVO {

	private long order_details_num;
	private String order_name;
	private int order_quantity;
	private int product_selling_price;
	private String product_main_image;
	private String recipient_name;
	private String recipient_mobile;
	private String recipient_telephone;
	private String shipping_address;
	private String forwarded_message;
	private String orderer_mobile;
	private String delivery_status;
	private String payment_time;
	private int use_mileage;
	
	
	private ProductVO pvo;
	private OrderVO ovo;
	
	public long getOrder_details_num() {
		return order_details_num;
	}
	public void setOrder_details_num(long order_details_num) {
		this.order_details_num = order_details_num;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public int getProduct_selling_price() {
		return product_selling_price;
	}
	public void setProduct_selling_price(int product_selling_price) {
		this.product_selling_price = product_selling_price;
	}
	public String getProduct_main_image() {
		return product_main_image;
	}
	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_mobile() {
		return recipient_mobile;
	}
	public void setRecipient_mobile(String recipient_mobile) {
		this.recipient_mobile = recipient_mobile;
	}
	public String getRecipient_telephone() {
		return recipient_telephone;
	}
	public void setRecipient_telephone(String recipient_telephone) {
		this.recipient_telephone = recipient_telephone;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getForwarded_message() {
		return forwarded_message;
	}
	public void setForwarded_message(String forwarded_message) {
		this.forwarded_message = forwarded_message;
	}
	public String getOrderer_mobile() {
		return orderer_mobile;
	}
	public void setOrderer_mobile(String orderer_mobile) {
		this.orderer_mobile = orderer_mobile;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	public int getUse_mileage() {
		return use_mileage;
	}
	public void setUse_mileage(int use_mileage) {
		this.use_mileage = use_mileage;
	}
	public ProductVO getPvo() {
		return pvo;
	}
	public void setPvo(ProductVO pvo) {
		this.pvo = pvo;
	}
	public OrderVO getOvo() {
		return ovo;
	}
	public void setOvo(OrderVO ovo) {
		this.ovo = ovo;
	}

}
