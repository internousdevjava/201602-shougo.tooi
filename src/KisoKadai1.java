import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai1 {

	// private static void Tatenarabe(int num1, int num2) {
	// // num1 : num2 = answer
	// String text = "";
	// for (int i = 1; i <= num1; i++) {
	// for (int j = 1; j <= num2; j++) {
	// text += "[" + String.valueOf(i) + " × " + String.valueOf(j) + "] = " +
	// String.valueOf(i * j) + "\n";
	// }
	// }
	// System.out.println(text);
	// }
	private static void SpredSheetlike(int num1, int num2) {
		// num1 : num2 = answer
		String text = "";
		//スペース調整のため、最大桁を求め、埋め合わせる
		int maxdigit = String.valueOf(num1 * num2).length();

		for (int i = 1; i <= num1; i++) {
			for (int j = 1; j <= num2; j++) {
				int digit = String.valueOf(i * j).length();
				for(int k = 0; k < maxdigit - digit; k++){
					text += " ";
				}
				text += i * j + " ";
			}
			text += "\n";
		}
		System.out.println(text);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("HelloWorld!");
		String str = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);

			int left = 0;
			int right = 0;
			System.out.println("一つ目の数字入力");
			str = br.readLine();
			left = Integer.parseInt(str);
			System.out.println("二つ目の数字入力");
			str = br.readLine();
			right = Integer.parseInt(str);

			SpredSheetlike(left, right);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("エラー:入力された文字列はこのプログラムでは対応していません。\n       終了します。\n入力値:" + str);
		}
	}

}
