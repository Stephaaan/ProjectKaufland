package sk.itsovy.kaufland.gfx;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	static Window w = new Window();
	private JFrame frame;
	private Window() {
		init(frame);
	}
	public static Window getInstance () {
		return w;
	}
	public JFrame getWindow() {
		return frame;
	}
	@SuppressWarnings("deprecation")
	private void init(JFrame frame) {
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.show(true);
	}
	

}
