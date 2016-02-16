import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Boolean flag = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);
		System.out.println("数当てゲーム！\n「exit」を入力すると終了します。");
		System.out.println("1～100の中から正解だと思う数字を入力してください。");
		//正解の数字
		int correct = 0;
		//試行回数
		int count = 0;
		// exitで抜ける
		while (true) {
			if (flag) {
				// 答え作成
				correct = (int) (Math.random() * 100) + 1;
				count = 0;
				flag = false;
			}
			// System.out.println(correct + " ans");
			String str = "";
			int num = 0;
			try {


				str = br.readLine();
				//注意点
				//文字列比較の罠。==演算子はオブジェクトの比較であり、文字列の比較では正確な結果が出ないことがある。
				if (str.equals("exit") ) {
					System.out.println("ゲームを終了します。\nお疲れ様でした。");
					System.exit(0);
				}
				num = Integer.parseInt(str);
				//範囲制限
				if(num > 100 || num < 1){
					System.out.println("エラー：入力された数字は1～100に含まれません。\n      再度入力をお願いします。\n入力値:" + num);
					continue;
				}

				if (num == correct) {
					System.out.println("正解です！" + count + "回でした。" + "\nおめでとうございます。");
					flag = true;
				} else {
					count++;
					System.out.println("残念！間違いです･･･");
					String text = "";
					if (num > correct) {
						text = "小さい";
					} else {
						text = "大きい";
					}
					System.out.println("もっと" + text + "です。");
				}

			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("エラー:入力された文字列はこのプログラムでは対応していません。再度入力をお願いします。");
			}
		}
	}

}
