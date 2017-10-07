package boot;

import javax.swing.JFrame;

import Interface.FrameMain;

public class Boot {

	public static void main(String args[]) {
		
		System.out.println("1");
		
		JFrame frame=new FrameMain();
		//JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		
		System.out.println("2");
	  }

}
