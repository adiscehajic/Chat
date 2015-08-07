package ba.bitcamp.w12d04_Chat;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 4156820733644634859L;

	public Main() {

		ChatPanel panel = new ChatPanel();

		add(panel);

		setSize(450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Chat");
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
