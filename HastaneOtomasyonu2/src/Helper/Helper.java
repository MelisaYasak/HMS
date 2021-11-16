package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
	}
	
	public static void showMsg(String str) {
		
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz!";
			break;
		case "success":
			msg = "Ýþlem Baþarýlý!";
			break;
		case "error":
			msg= "HATA";
			break;
		case "wrong":
			msg = "Yanlýþ deðer girdiniz!";
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
			msg = "Silmek istediðinize emin misiniz?";
			break;
		case "update":
			msg = "Deðiþtirmek istediðinizden emin misiniz?";
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
	
	
	//FIXME : POLÝKLÝNÝK TARAFINDA SÝLÝNEN DOKTORUN YERÝNDE BOÞ SATIR KALIYOR.
	//FIXME : DOKTOR SÝLÝNCE user TABLOSUNDAN SÝLÝNÝYOR AMA worker TABLOSUNDAN SÝLÝNMÝYIOR. 
	//FIXME : POLÝKLÝNÝÐÝ SÝLÝNCE clinic TABLOSUNDAN SÝLÝNÝYOR AMA  worker TABLOSUNDAN SÝLÝNMÝYIOR.
	//FIXME : DOKTOR POLÝKLÝNÝÐE BAÐLANMADAN ÇALIÞMA SAATÝ SEÇEBÝLÝYOR YAPMAMALI
	//FIXME : SÝLÝNEN DOKTORUN whour TABLOSUNDAKÝ ÇALIÞMA SAATLERÝ DE SÝLÝNMELÝ.
	//FIXME : AYNI ANDA DOKTOR HASTA GÝRÝÞÝ YAPMALI
	//FIXME : STATUSDEN P LERÝ A YAPINCA APPOÝNMENT DA RANDEVULAAR SÝLÝNMÝYOR.

}
