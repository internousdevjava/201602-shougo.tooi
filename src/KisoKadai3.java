import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author internous
 * 機能
 * ・ファイル名に無効な文字が含まれている場合、終了する
 * ・複数行のテキストをファイルに追記、上書きできる
 * ・存在しないフォルダ内にファイルを作成できる(フォルダも同時生成)
 */
public class KisoKadai3 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// コマンドライン引数からパスを取得
		if (args.length == 0) {
			System.out.println("対象パスが空白です。以下のように操作する対象のファイルを指定してください。");
			System.out.println("例:java KisoKadai3 C:\\test\\test.txt");
			System.out.println("終了します。");
			System.exit(0);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);

		String path = args[0];
		File file = new File(path);
		File dir = new File(file.getParent());
		try {
			// フォルダ存在確認
			if (!dir.exists()) {

				System.out.println("指定されたフォルダは存在しません。新規作成しますか？\ny/n");
				if (br.readLine().trim().equals("y")) {
					// 新規作成
					file.mkdirs();
					System.out.println("フォルダの作成が完了しました。\npath : " + file.getPath());
				}

			} else {
				System.out.println("そのフォルダはすでに存在しています。");
			}

			// ファイル存在確認
			if (!file.exists()) {
				// 無効な文字が含まれているか確認

				System.out.println(file.getName());
				for (char c : file.getName().toCharArray()) {
					if (String.valueOf(c).matches("[\\:/*?<>!]") || String.valueOf(c).matches(String.valueOf('"'))) {
						System.out.println("ファイル名に無効な文字が含まれています。終了します。");
						System.exit(0);
					}
				}

				System.out.println("指定されたファイルは存在しません。新規作成しますか？\ny/n");
				if (br.readLine().trim().equals("y")) {
					// 新規作成
					file.createNewFile();
					System.out.println("ファイルの作成が完了しました。\npath : " + file.getPath());
				}

			} else {
				System.out.println("そのファイルはすでに存在しています。");
			}
		} catch (Exception e) {
			System.out.println("ファイルの作成に失敗しました。\n終了します。");
			System.exit(0);
		}

		while (true) {
			try {
				System.out.println("なにをしますか？");
				System.out.println("1:読む 2:追記 3:上書き 99:終了");
				String str = br.readLine();
				int num = 0;
				if (str.matches("^[0-9]+$")) {
					num = Integer.parseInt(str);
				} else {
					System.out.println("\n----------\n半角数値でメニューを選択してください。\n----------\n");
				}

				if (num == 1) {// 開く
					System.out.println(getFileText(file));

				} else if (num == 2) {// 追記
					System.out.println("空白のまま改行することで、保存メニューに移ります。");
					writeText(br, file, true);
				} else if (num == 3) {// 上書き
					System.out.println("空白のまま改行することで、保存メニューに移ります。");
					writeText(br, file, false);
				} else if (num == 99) {
					System.out.println("終了します。");
					System.exit(0);
				} else {
					System.out.println("無効なコードです。");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void writeText(BufferedReader br, File file, boolean append) throws IOException {
		// 複数行書き込みに対応
		String str;
		System.out.println("--------------------------------------------------");
		System.out.println("ファイル:" + file.getPath());
		System.out.println("--------------------------------------------------");
		//追記する場合、ファイルの内容を表示するが、上書きの場合は表示しない
		if (append) {
			System.out.println(getFileText(file));
		}
		String Line = "";
		while (true) {
			String tex = br.readLine();
			if (tex.equals("")) {// 空っぽで改行されたら保存確認、それ以外なら保存用変数に追加
				// 確認
				System.out.println("保存しますか？");
				System.out.println("保存:y 保存せずに終了:n キャンセル:c");
				str = br.readLine();
				if (str.equals("y")) {
					FileWriter fw = new FileWriter(file, append);
					// 末尾の改行を消去
					fw.write(Line.trim());
					System.out.println("保存しました。");
					fw.close();
					break;
				} else if (str.equals("c")) {
					System.out.println(getFileText(file));
					System.out.print(Line);
				} else {
					System.out.println("保存せずに終了しました。");
					break;
				}
			}
			Line += tex + "\n";
		}
	}

	/*
	 * ファイルテキストを格納した文字列を返却します。
	 */
	private static String getFileText(File file) throws IOException {
		String str = "";
		FileReader filereader = new FileReader(file);
		int ch = filereader.read();
		while (ch != -1) {
			str += (char) ch;

			ch = filereader.read();
		}
		filereader.close();

		return str;
	}
}
