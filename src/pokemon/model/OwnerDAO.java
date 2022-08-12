package pokemon.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.util.DBUtil;
//포켓몬 소유자와 관계된 DAO 클래스
public class OwnerDAO {
	// 신규 등록(insert)
	public static boolean addOwner(OwnerDTO owner) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(“insert into owner values (?, ?, ?, ?“);
			pstmt.setInt(1, owner.getOwnerId());
			pstmt.setString(2, owner.getOwnerName());
			pstmt.setInt(3, owner.getOwnerAge());
			pstmt.setString(4, owner.getOwnerTier());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	// ownerId로 검색
	public static boolean deleteOwner(String ownerId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(“delete from owner where ownerid=?“);
			pstmt.setString(1, ownerId);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	// 모든 정보 반환
	public static ArrayList<OwnerDTO> getOwner() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OwnerDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(“select * from owner”);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(OwnerDTO.ownerDTOBuilder().ownerId(rset.getInt(1)).ownerName(rset.getString(2))
						.ownerAge(rset.getInt(3)).ownerTier(rset.getString(4)).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	// 수정
	// 해당하는 ownerId의 Tier를 수정
	public static boolean updateOwner(String ownerId, String Tier) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(“update activist set major=? where activist_id=?“);
			pstmt.setString(1, ownerId);
			pstmt.setString(2, Tier);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// ownerId로 해당하는 owner정보 검색
	public static OwnerDTO getOneOwner(String ownerId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OwnerDTO result = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(“select * from activist where ownerid=?“);
			pstmt.setString(1, ownerId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = OwnerDTO.ownerDTOBuilder().ownerId(rset.getInt(1)).ownerName(rset.getString(2))
						.ownerAge(rset.getInt(3)).ownerTier(rset.getString(4)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return result;
	}
	
}