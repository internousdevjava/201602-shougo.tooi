import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JissenKadai1_2 {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String msg = "";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "mysql");
			msg = "ドライバのロードに成功しました";

            // ステートメント生成
            Statement stmt = con.createStatement();

            // SQLを実行
            String sqlStr = "SELECT * FROM test_table";
            ResultSet rs = stmt.executeQuery(sqlStr);

            // 結果行をループ
            while(rs.next()){
                // レコードの値
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");

                //表示
                System.out.println(id + "：" + name);
            }

            // 接続を閉じる
            rs.close();
            stmt.close();
            con.close();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			msg = "ドライバのロードに失敗しました";
		}
		System.out.println(msg);
	}
}
