package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
	}
	
	public static void showMsg(String str) {
		
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "L�tfen t�m alanlar� doldurunuz!";
			break;
		case "success":
			msg = "��lem Ba�ar�l�!";
			break;
		case "error":
			msg= "HATA";
			break;
		case "wrong":
			msg = "Yanl�� de�er girdiniz!";
			break;
		default:
			msg = str;
			break;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean confirm(String str) {
		
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "del":
			msg = "Silmek istedi�inize emin misiniz?";
			break;
		case "update":
			msg = "De�i�tirmek istedi�inizden emin misiniz?";
			break;
		default:
			msg = str;	
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);
		
		if (res == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//FIXME : POL�KL�N�K TARAFINDA S�L�NEN DOKTORUN YER�NDE BO� SATIR KALIYOR.
	//FIXME : DOKTOR S�L�NCE user TABLOSUNDAN S�L�N�YOR AMA worker TABLOSUNDAN S�L�NM�YIOR. 
	//FIXME : POL�KL�N��� S�L�NCE clinic TABLOSUNDAN S�L�N�YOR AMA  worker TABLOSUNDAN S�L�NM�YIOR.
	//FIXME : DOKTOR POL�KL�N��E BA�LANMADAN �ALI�MA SAAT� SE�EB�L�YOR YAPMAMALI
	//FIXME : S�L�NEN DOKTORUN whour TABLOSUNDAK� �ALI�MA SAATLER� DE S�L�NMEL�.
	//FIXME : AYNI ANDA DOKTOR HASTA G�R��� YAPMALI
	//FIXME : STATUSDEN P LER� A YAPINCA APPO�NMENT DA RANDEVULAAR S�L�NM�YOR.

}
