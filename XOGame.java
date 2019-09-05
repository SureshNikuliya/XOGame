package co.game.xo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class XOGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JFrame XOFrame;
	JButton[] button;
	String[][] XO;
	String turn;
	
	JLabel label;
	int moves = 0;
	boolean status = false;
	JButton reStart = new JButton("ReStart");

	public XOGame() {
		
		XOFrame = new JFrame("XO");
		button = new JButton[9];
		
		Random rdm = new Random();
		int temp = rdm.nextInt(2);
		turn = temp == 0 ? "O" : "X";
			
		XOFrame.setSize(210, 300);
		XOFrame.setLocation(200, 100);
		XOFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label = new JLabel(turn + " Turn", JLabel.CENTER);
		label.setBounds(0, 0, 200, 50);
		XOFrame.add(label);
		
		reStart.addActionListener(this);
		reStart.setBounds(55, 210, 80, 50);
		XOFrame.add(reStart);
		
		
		for (int i = 0; i < 9; i++) 
		{
			int[] x = { 20, 70, 120, 20, 70, 120, 20, 70, 120 };
			int[] y = { 50, 50, 50, 100, 100, 100, 150, 150, 150 };
			
			button[i] = new JButton();
			button[i].addActionListener(this);
			button[i].setBounds(x[i], y[i], 50, 50);
			XOFrame.add(button[i], i);
		}
		
		XOFrame.setLayout(null);
		XOFrame.setVisible(true);
	}

	public static void main(String[] args) {

		new XOGame();
		

	}
	
	public void reStart()
	{
		Random rdm = new Random();
		int temp = rdm.nextInt(2);
		turn = temp == 0 ? "O" : "X";
		label.setText(turn + " Turn");
		status=false;
		moves=0;
		
		for(JButton btn:button)
		{
			btn.setEnabled(true);
			btn.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		JButton btn = new JButton();
		btn = (JButton) source;
		
		if(btn.getText().equals("ReStart"))
		{
			reStart();
		}
		else
		{
			checkWinner(btn);
		}		
		
	}
	
	public void checkWinner(JButton btn)
	{
		
		moves++;
		if (turn.equals("X")) {
			btn.setText("X");
			turn = "O";
			label.setText(turn + " Turn");
			btn.setEnabled(false);
		} 
		else 
		{
			btn.setText("O");
			turn = "X";
			label.setText(turn + " Turn");
			btn.setEnabled(false);
		}
		
		
		//for Row Check
		int j = 0;
		for (int i = 0; i < 3; i++) {
			if (button[j].getText().equals("O")&& button[j + 1].getText().equals("O")&& button[j + 2].getText().equals("O")) {
				label.setText("Player O Win!!!!!!!");
				status = true;
				break;
			} else if (button[j].getText().equals("X")&& button[j + 1].getText().equals("X")&& button[j + 2].getText().equals("X")) {
				label.setText("Player X Win!!!!!!!");
				status = true;
				break;
			}
			j += 3;
		}
		
		//for Column Check
		j = 0;
		for (int i = 0; i < 3; i++) {
			if (button[j].getText().equals("O")&& button[j + 3].getText().equals("O")&& button[j + 6].getText().equals("O")) {
				label.setText("Player O Win!!!!!!!");
				status = true;
				break;
			}

			else if (button[j].getText().equals("X")&& button[j + 3].getText().equals("X")&& button[j + 6].getText().equals("X")) {
				label.setText("Player X Win!!!!!!!");
				status = true;
				break;
			}
			j++;
		}
		
		
		//for Diagonal Computation
		
			if((button[0].getText().equals("X")&& button[4].getText().equals("X")&& button[8].getText().equals("X")) || 
					(button[2].getText().equals("X")&& button[4].getText().equals("X")&& button[6].getText().equals("X"))) {
					label.setText("Player X Win!!!!!!!");
					status = true;
			}
			else if((button[0].getText().equals("O")&& button[4].getText().equals("O")&& button[8].getText().equals("O")) || 
					(button[2].getText().equals("O")&& button[4].getText().equals("O")&& button[6].getText().equals("O"))) {
					label.setText("Player O Win!!!!!!!");
					status = true;
			}
			
		
		//for Draw Condition
		if (moves==9) {
			label.setText("Game Draw!!!!!!!");
		}
		
		//for game Status while Playing
		else if(status)
		{
			for(JButton btn1 : button)
			{
				if(btn1.isEnabled())
				{
					btn1.setEnabled(false);
				}
			}
		}
	}
}