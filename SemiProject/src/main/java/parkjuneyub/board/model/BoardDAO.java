package parkjuneyub.board.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import parkjuneyub.order.model.OrderDeatailVO;
import parkjuneyub.order.model.OrderVO;
import parkjuneyub.member.model.*;

public class BoardDAO implements InterBoardDAO {
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
				rs=null;
			}
			
			if(pstmt != null) {
				pstmt.close();
				pstmt=null;
			}
			
			if(conn != null) {
				conn.close();
				conn=null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semi_oracle");
			
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ReviewVO> getReviewList(Map<String, String> paraMap) throws SQLException {
		
		List<ReviewVO> reviewList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT RNO, purchase_review_id, order_details_num, userid, review_content, review_rating, review_date "+
					" FROM "+
					" ( "+
					" select rownum AS RNO, purchase_review_id, userid, order_details_num, product_num, review_content, review_rating, review_date "+
					" from "+
					" ( "+
					" 	select * "+
					"   from tbl_purchase_review "+
					"   where product_num = ? "+
					"   order by review_date desc "+
					"   ) V "+
					"  ) T "+
					" WHERE RNO between ? and ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("product_num"));
			pstmt.setInt(2, (Integer.parseInt(paraMap.get("currentShowPageNo"))*10)-(10-1) );
			pstmt.setInt(3, Integer.parseInt(paraMap.get("currentShowPageNo"))*10 );
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewVO rvo = new ReviewVO();
				rvo.setPurchase_review_id(rs.getInt("purchase_review_id"));
				
				MemberVO mvo = new MemberVO();
				mvo.setUser_id(rs.getString("userid"));
				rvo.setMvo(mvo);
				
				rvo.setOrder_details_num(rs.getLong("order_details_num"));
				rvo.setReview_content(rs.getString("review_content"));
				rvo.setReview_rating(rs.getInt("review_rating"));
				rvo.setReview_date(rs.getString("review_date"));
				
				reviewList.add(rvo);
			}
			
			
		} finally {
			close();
		}
		return reviewList;
	}

	@Override
	public int insertReview(Map<String, Object> map) throws SQLException {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = " select order_details_num from tbl_order_detail where order_num = ? and product_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)map.get("select_order_num"));
			pstmt.setLong(2, (Long)map.get("product_id"));
			rs = pstmt.executeQuery();
			rs.next();
			
			Long order_details_num = rs.getLong("order_details_num");
			
			sql = " select purchase_review_id from tbl_purchase_review where order_details_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, order_details_num);
			
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				
				sql = " insert into tbl_purchase_review "
						+ " (purchase_review_id, userid, order_details_num, product_num, "
						+ " review_content, review_rating, review_date) "
						+ " values(seq_purchase_review.nextval, ?, ?, ?, ?, ?, sysdate) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (String)map.get("user_id"));
				pstmt.setLong(2, order_details_num);
				pstmt.setLong(3, (Long)map.get("product_id"));
				pstmt.setString(4, (String)map.get("review_contents"));
				pstmt.setInt(5, (Integer)map.get("review_rating"));
				
				result = pstmt.executeUpdate();
			}
			
		} finally {
			close();
		}
		return result;
	}
	
	// 아이디랑 상품번호로 주문번호를 조회해오는 메소드
	@Override
	public List<OrderVO> getOrderDeatailList(String user_id, String product_num) throws SQLException {
		List<OrderVO> odrDeatailList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			String sql = " select A.order_num, to_char(A.order_date, 'yyyy-MM-dd')"+
					" from ( "+
					" select order_num, user_id, order_date "+
					" from tbl_order "+
					" where user_id = ? "+
					" ) A "+
					" join\n"+
					" ( "+
					" select order_details_num, order_num, product_num, order_quantity "+
					" from tbl_order_detail "+
					" where product_num = ? and order_details_num not in ( "
					+ " select order_details_num from tbl_purchase_review where product_num = ? ) "+
					" ) B "+
					" on A.order_num = B.order_num "+
					" order by order_date asc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setLong(2, Long.parseLong(product_num));
			pstmt.setLong(3, Long.parseLong(product_num));
			
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOrder_num(rs.getString(1));
				ovo.setOrder_date(rs.getString(2));
				odrDeatailList.add(ovo);
			}
			
		} finally {
			close();
		}
		
		
		return odrDeatailList;
	}
	
	// 한 상품의 전체 리뷰를 가져온다.
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
int totalPage = 0;
		
		try {
			conn = ds.getConnection();
			
			String  sql = " select ceil(count(*) / 10 ) from tbl_purchase_review where product_num = ? ";

			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,  paraMap.get("product_num"));
			
			rs = pstmt.executeQuery();
			rs.next();
			totalPage = rs.getInt(1);
			
		} 
		finally {
			close();
		}
		
		return totalPage;
		
	}

	@Override
	public int deleteReviewByReviewId(String purchase_review_id) throws SQLException {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " delete from tbl_purchase_review where purchase_review_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(purchase_review_id));
			
			result = pstmt.executeUpdate();
			
		} finally {
			
		}
		return result;
	}

}
