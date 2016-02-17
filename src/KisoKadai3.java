import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * @author internous 機能 ・ファイル名に無効な文字が含まれている場合、終了する ・複数行のテキストをファイルに追記、上書きできる
 *         ・存在しないフォルダ内にファイルを作成できる(フォルダも同時生成) ・選択したファイルを削除できる
 */
public class KisoKadai3 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// コマンドライン引数からパスを取得
		String path = null;
		String str = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);
		while (true) {
			// ファイル指定画面
			while (true) {
				System.out.println("ファイルを指定してください。exitで終了します。");
				try {
					str = br.readLine();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					System.out.println("入力に誤りがあります。");
					continue;
				}

				if (str == null) {
					System.out.println("対象パスが空白です。");
					continue;
				}
				if (str.equals("exit")) {
					System.out.println("終了します。");
					System.exit(0);
				} else {
					// 省略記法や相対パスを、フルパスへ変換する
					// この後のディレクトリなのかファイルなのかを判別するために必要
					try {
						path = Paths.get(str).toAbsolutePath().normalize().toString();
						break;
					} catch (Exception e) {
						System.out.println("パスが無効です。");
						continue;
					}

				}
			}

			File file;
			File dir;
			try {
				file = new File(path);
				dir = new File(new File(file.getParent()).getAbsolutePath());

				// フォルダ存在確認
				// 入力されたパスに拡張子が含まれていない場合、ディレクトリとみなす
				boolean isdir = true;
				for (char c : file.getName().toCharArray()) {
					if (String.valueOf(c).equals(".")) {
						isdir = false;
						break;
					}
				}
				// フォルダのみ作成。
				if (isdir) {
					newCreateDir(file);
					continue;
				}

				// ファイル作成。親ディレクトリから作成する。
				newCreateDir(dir);

				// ファイル存在確認
				if (!file.exists()) {
					System.out.println();
					if (kakunin("指定されたファイルは存在しません。新規作成しますか？")) {
						// 新規作成
						file.createNewFile();
						System.out.println("ファイルの作成が完了しました。\npath : " + file.getAbsolutePath());
					} else {
						System.out.println("ファイルは作成されませんでした。");
						continue;
					}

				} else {
					// 自動で開いた扱い
					System.out.println(file.getAbsolutePath() + "を開きました。");
				}
			} catch (Exception e) {
				System.out.println("入力されたパスが不正です。");
				continue;
			}

			// モード選択画面＋閲覧画面
			while (true) {
				try {
					System.out.println("");
					System.out.println("なにをしますか？");
					System.out.println("read:閲覧 app:追記 over:上書き del:削除 back:ファイル指定モードへ戻る");
					str = br.readLine().toLowerCase().trim();

					if (str.equals("read")) {// 閲覧
						System.out.println("--------------------------------------------------");
						System.out.println("ファイル:" + file.getPath());
						System.out.println("--------------------------------------------------");
						System.out.println(getFileText(file));

					} else if (str.equals("app")) {// 追記
						System.out.println("空白のまま改行することで、保存メニューに移ります。");
						writeText(br, file, true);
					} else if (str.equals("over")) {// 上書き
						System.out.println("空白のまま改行することで、保存メニューに移ります。");
						writeText(br, file, false);
					} else if (str.equals("del")) {
						if (kakunin(file.getAbsolutePath() + "は削除されます。" + "\nよろしいですか？")) {
							file.delete();
							System.out.println(file.getAbsolutePath() + "は削除されました。");
							System.out.println("ファイル指定モードへ戻ります。");
							break;
						}
					} else if (str.equals("back")) {
						break;
					} else {
						System.out.println("エラー：無効なコードです。");
					}

				} catch (Exception e) {
					System.out.println("入力に失敗しました。");
				}
			}
		}

	}

	/*
	 * 確認画面
	 */
	private static boolean kakunin(String text) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);
		while (true) {
			System.out.println(text);
			System.out.println("y/n");
			String s = br.readLine().trim().toLowerCase();
			if (s.equals("y")) {
				return true;
			} else if (s.equals("n")) {
				return false;
			} else {
				System.out.println("入力内容が不正です。");
			}
		}
	}

	/*
	 * フォルダの作成画面（二回出てくる構成なので関数にした） フォルダが存在する結果になればtrue,作成されなければfalseが返る
	 */
	private static boolean newCreateDir(File dir) throws IOException {
		System.out.println(dir.getAbsolutePath());
		if (!dir.exists()) {

			if (kakunin("指定されたフォルダは存在しません。新規作成しますか？")) {
				// 新規作成
				dir.mkdirs();
				System.out.println("フォルダの作成が完了しました。\npath : " + dir.getPath());
			} else {
				System.out.println("フォルダは作成されませんでした。");
				return false;
			}

		} else {
			System.out.println("そのフォルダはすでに存在しています。");
		}
		return true;
	}

	/*
	 * ファイルにテキストを書き込む画面
	 */
	private static void writeText(BufferedReader br, File file, boolean append) throws IOException {
		// 複数行書き込みに対応
		String str;
		System.out.println("--------------------------------------------------");
		System.out.println("ファイル:" + file.getPath());
		System.out.println("--------------------------------------------------");
		// 追記する場合、ファイルの内容を表示するが、上書きの場合は表示しない
		if (append) {
			System.out.println(getFileText(file));
		}
		String Line = "";
		while (true) {
			String tex = br.readLine();
			if (tex.equals("")) {// 空っぽで改行されたら保存確認、それ以外なら保存用変数に追加
				// 確認
				while (true) {
					System.out.println("保存しますか？");
					System.out.println("保存:y 保存せずに終了:n キャンセル:c");
					str = br.readLine();
					if (str.toLowerCase().equals("y")) {
						FileWriter fw = new FileWriter(file, append);
						// 末尾の改行を消去
						fw.write(Line.trim());
						System.out.println("保存しました。");
						fw.close();
						return;
					} else if (str.toLowerCase().equals("c")) {
						System.out.println("--------------------------------------------------");
						System.out.println("ファイル:" + file.getPath());
						System.out.println("--------------------------------------------------");
						System.out.println(getFileText(file));
						System.out.print(Line);
						break;
					} else if (str.toLowerCase().equals("n")) {
						System.out.println("保存せずに終了しました。");
						return;
					} else {
						System.out.println("無効なコードです。再入力をお願いします。");
					}
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
