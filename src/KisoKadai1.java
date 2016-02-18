import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

/**
 * @author internous
 *
 */
public class KisoKadai1 {

	private static void SpredSheetlike(int left, int right) {
		List<String> arr = new ArrayList<String>();

		//スペース調整のため、最大桁を求め、埋め合わせる
		int maxdigit = String.valueOf(left * right).length();

		for (int i = 1; i <= left; i++) {
			String text = "";
			for (int j = 1; j <= right; j++) {
				int digit = String.valueOf(i * j).length();
				for(int k = 0; k < maxdigit - digit; k++){
					text += " ";
				}
				text += i * j + " ";
			}
			arr.add(text);
		}
//		System.out.println(text);
		for(String s: arr){
			System.out.println(s);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);

			int left = 0;
			int right = 0;
			System.out.println("");
			System.out.println("一つ目の数字入力");

			str = br.readLine();
			left = Integer.parseInt(str);
			System.out.println("二つ目の数字入力");
			str = br.readLine();
			right = Integer.parseInt(str);

			SpredSheetlike(left, right);
		} catch (Exception e) {
			System.out.println("エラー:入力された文字列はこのプログラムでは対応していません。\n       終了します。\n入力値:" + str);
		}
	}

}
