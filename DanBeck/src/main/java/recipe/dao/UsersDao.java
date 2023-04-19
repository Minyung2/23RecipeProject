package recipe.dao;

import DBpackage.DBConnectpool;

public class UsersDao extends DBConnectpool {
	public UsersDao() {
		super();
	}

	// 참고용 구
	public int getTotalCount() {
		int totalCount = 0;
		String sql = "select count(*) from test.users";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
	}
}
