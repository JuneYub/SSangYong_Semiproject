package parkjuneyub.board.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parkjuneyub.order.model.OrderDeatailVO;
import parkjuneyub.order.model.OrderVO;

public interface InterBoardDAO {
	
	// 상품번호를 가지고 관련 리뷰 글을 가져오는 메소드
	List<ReviewVO> getReviewList(Map<String, String> paraMap) throws SQLException;

	// 리뷰를 작성하는 메소드
	int insertReview(Map<String, Object> map) throws SQLException;
	
	// 아이디랑 상품번호로 주문번호를 조회해오는 메소드
	List<OrderVO> getOrderDeatailList(String user_id, String product_num) throws SQLException;

	//한 상품의 전체 리뷰를 가져온다.
	int getTotalPage(Map<String, String> paraMap) throws SQLException;
	
	// 리뷰 id를 이용해 후기를 삭제하는 메소드
	int deleteReviewByReviewId(String purchase_review_id) throws SQLException;
	
}
