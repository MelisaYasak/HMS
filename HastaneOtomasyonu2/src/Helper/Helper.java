package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayır");
	}
	
	public static void showMsg(String str) {
		
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanları doldurunuz!";
			break;
		case "success":
			msg = "İşlem Başarılı!";
			break;
		case "error":
			msg= "HATA";
			break;
		case "wrong":
			msg = "Yanlış değer girdiniz!";
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
			msg = "Silmek istediğinize emin misiniz?";
			break;
		case "update":
			msg = "Değiştirmek istediğinizden emin misiniz?";
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
	
	
	//FIXME : Poiklinik tarafında silinen doktorun yerinde boş satır kalıyor.
	//FIXME : Doktor silinince user tablosundan siliniyor ama worker tablosundan silinmiyor. // Relational Database bilmediğim için 
	//FIXME : Poliklinik silinince clinic tablosundan siliniyor ama worker tablosundan silinmiyor.
	//FIXME : Doktor polikliniğindebağlanmadan çalışma saati seçebiliyor, yapmamalı.
	//FIXME : Silinen doktorun whour tablosundaki çalışma saatleri de silinmeli.
	//FIXME : Aynı anda doktor hasta girişi yapmalı.
	//FIXME : Statuste p leri a yapınca appointment da randevular silinmiyor.

}
