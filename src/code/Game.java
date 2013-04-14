package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game {
	JFrame frame;
	JFrame controlframe;
	JPanel panel;
	JPanel controlpanel;
	JButton next;
	JButton out;
	JButton show;
	int dienumber = 4;
	int total = 0;
	public Game(){
		//Set up the counter frame for the first time
		frame = new JFrame("Countdown Timer:");
		controlframe = new JFrame("Controls");
		//The panel
		panel = new JPanel();
		controlpanel = new JPanel();

		BufferedImage logopre = HelpfulImageMethods.loadImage("Images/"+ dienumber +".png");
		ImageIcon iconlogopost = new ImageIcon(logopre);
		JLabel logolabel = new JLabel(iconlogopost);
		BufferedImage ligo = HelpfulImageMethods.loadImage("Images/title.png");
		ImageIcon ligo1 = new ImageIcon(ligo);
		JLabel logo = new JLabel(ligo1);


		next = new JButton("NEXT");
		next.addActionListener(new NextAction());
		out = new JButton("OUT");
		out.addActionListener(new GOAction());
		controlpanel.add(next);
		controlpanel.add(out);

		show = new JButton("SHOW TOTAL");
		show.addActionListener(new ShowAction());
		controlpanel.add(show);
		controlpanel.add(logo);
		controlframe.add(controlpanel);
		controlframe.setBackground(java.awt.Color.BLACK);
		controlframe.setVisible(true);
		controlframe.setBounds(5,5,500,500);
		controlframe.setResizable(false);
		controlframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.add(logolabel);
		frame.add(panel);
		frame.setBackground(java.awt.Color.BLACK);
		frame.setVisible(true);
		frame.setBounds(5,5,500,400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	public void updater(int slidenumber){
		String picname;
		if(slidenumber<6){
			picname = "Images/"+ slidenumber +".png";
		}
		else{
			picname = "Images/title.png";
		}
		frame.remove(panel);
		panel = new JPanel();
		BufferedImage logopre = HelpfulImageMethods.loadImage(picname);
		ImageIcon iconlogopost = new ImageIcon(logopre);
		JLabel logolabel = new JLabel(iconlogopost);
		panel.add(logolabel);
		frame.add(panel);
		panel.repaint();
		frame.validate();
		frame.repaint();

	}
	public class NextAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			updater(6);
			long t2,t3;
			t2=System.currentTimeMillis();
			do{
				t3=System.currentTimeMillis();
			}
			while (t3-t2<1000);
			Random randomGenerator = new Random();
			for (int idx = 1; idx <= 10; ++idx){
				dienumber = randomGenerator.nextInt(5);
			}
			updater(dienumber);
			if(dienumber!=4){
				total = total + dienumber+1;
			}
			else{
				long t1,t0;
				t0=System.currentTimeMillis();
				do{
					t1=System.currentTimeMillis();
				}
				while (t1-t0<1000);
				JOptionPane.showMessageDialog(frame,"Your Loss: "+total+" BUT NO GET 0","Game Over",JOptionPane.ERROR_MESSAGE);	
				total = 0;
			}
		}
	}
	public class GOAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(frame,"Congrats! Your Winnings: "+total,"Game Over",JOptionPane.ERROR_MESSAGE);

			total = 0;
		}
	}
	public class ShowAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(frame,"Current Total: "+total,"Total",JOptionPane.ERROR_MESSAGE);

		}
	}
}

