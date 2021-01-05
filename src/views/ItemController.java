package views;

import java.awt.Desktop;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ItemController {
	@FXML
	private Label lblTitle;

	@FXML
	private TextArea lblDesc;

	private String url;

	public void setData(String title, String desc, String url) {
		lblTitle.setText("질문 : " + title);
		lblDesc.setText("답변 : " + desc);
		this.url = url;
	}
	
	public void openBrowser() {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("브라우저 오픈중 오류 발생");
		}
	}
}
