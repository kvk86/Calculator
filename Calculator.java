package P1_Calculator;

import java.awt.*;//used for Layout and color
import java.awt.event.*;
import java.util.Arrays;
import java.util.Arrays.*;
import javax.swing.*;//used for Jframe cration
import javax.swing.border.LineBorder;
import java.math.*;


public class Calculator {
	int boardWidth = 360;
	int boardHeight = 540;
	
	Color customLightGray = new Color(212,212,210);
	Color customDarkGray = new Color(80,80,80);
	Color customBlack = new Color(28,28,28);
	Color customOrange = new Color(255,149,0);
	
	//buttons
	String[] buttonValues = {
	        "AC", "+/-", "%", "÷", 
	        "7", "8", "9", "×", 
	        "4", "5", "6", "-",
	        "1", "2", "3", "+",
	        "0", ".", "√", "="
	    };
	//symbols
	String[] rightSymbols = {"÷", "×", "-", "+", "="};
	String[] topSymbols = {"AC", "+/-", "%"};
	
	JFrame frame = new JFrame("Calculator");
	//place text in label and label into panel and panel into window(Jframe)
	JLabel displayLabel = new JLabel();
	JPanel displayPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	
	//keeps tracks of numbers(A,B) and operations
	String A = "0";
	String operator = null;
	String B = null;
	
	Calculator(){
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		//styling for label and panel
		displayLabel.setBackground(customBlack);
		displayLabel.setForeground(Color.white);//text color
		displayLabel.setFont(new Font("Arial", Font.PLAIN,80));//text font and its size
		displayLabel.setHorizontalAlignment(JLabel.RIGHT);//align the text to right
		displayLabel.setText("0");//initilally text shows zero
		displayLabel.setOpaque(true);
		
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(displayLabel);
		frame.add(displayPanel, BorderLayout.NORTH);
		
		//buttons panel
		buttonsPanel.setLayout(new GridLayout(5,4));
		buttonsPanel.setBackground(customBlack);
		frame.add(buttonsPanel);
		
		for(int i =0;i < buttonValues.length; i++) {
			JButton button = new JButton();
			String buttonValue = buttonValues[i];
			button.setFont(new Font("Arial", Font.PLAIN, 30));
			button.setText(buttonValue);
			button.setFocusable(false);
			button.setBorder(new LineBorder(customBlack));
			if(Arrays.asList(topSymbols).contains(buttonValue)) {
				button.setBackground(customLightGray);
				button.setForeground(Color.white);
			}
			else if(Arrays.asList(rightSymbols).contains(buttonValue)) {
				button.setBackground(customOrange);
				button.setForeground(Color.white);
			}
			else {
				button.setBackground(customDarkGray);
				button.setForeground(Color.white);
			}
			buttonsPanel.add(button);
			
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
					String buttonValue = button.getText();
					if(Arrays.asList(rightSymbols).contains(buttonValue)) {
						if(buttonValue == "=") {
							if(A != null) {
								B = displayLabel.getText();
								double numA = Double.parseDouble(A);
								double numB = Double.parseDouble(B);
								
								if(operator == "+") {
									displayLabel.setText(removeZeroDecimal(numA + numB));
								}
								else if(operator == "-") {
									displayLabel.setText(removeZeroDecimal(numA - numB));
								}
								else if(operator == "×") {
									displayLabel.setText(removeZeroDecimal(numA * numB));
								}
								else if(operator == "÷") {
									displayLabel.setText(removeZeroDecimal(numA / numB));
								}
								clearAll();
							}
						}
						else if("+-×÷".contains(buttonValue)){
							if(operator == null) {
								A = displayLabel.getText();
								displayLabel.setText("0");
								B = "0";
							}
							operator = buttonValue;
						}
					}
					else if(Arrays.asList(topSymbols).contains(buttonValue)) {
						if(buttonValue == "AC") {
							clearAll();
							displayLabel.setText("0");
						}
						else if(buttonValue == "+/-") {
							double numDisplay = Double.parseDouble(displayLabel.getText());
							numDisplay *= -1;
							displayLabel.setText(removeZeroDecimal(numDisplay));
							
						}
						else if(buttonValue == "%"){
							double numDisplay = Double.parseDouble(displayLabel.getText());
							numDisplay /= 100;
							displayLabel.setText(removeZeroDecimal(numDisplay));
						}
					}
					else if(buttonValue == "√") {
						double numDisplay = Double.parseDouble(displayLabel.getText());
						numDisplay = Math.sqrt(numDisplay);
						displayLabel.setText(removeZeroDecimal(numDisplay));
					}
					else {
						if(buttonValue == ".") {
							if(!displayLabel.getText().contains(buttonValue)) {
								displayLabel.setText(displayLabel.getText() + buttonValue);
							}
						}
						else if ("0123456789".contains(buttonValue)) {
							if(displayLabel.getText() == "0"){
								displayLabel.setText(buttonValue);
							}
							else {
								displayLabel.setText(displayLabel.getText() + buttonValue);
							}
						}
						else {
							if(displayLabel.getText() == "0"){
								displayLabel.setText(buttonValue);
							}
							else {
								displayLabel.setText(displayLabel.getText() + buttonValue);
							}
						}
					}
				}

				
			});
		}
	}
	void clearAll() {
		A = "0";
		operator = null;
		B = null;
	}
	
	String removeZeroDecimal(double numDisplay) {
		if(numDisplay%1 == 0)
			return Integer.toString((int)numDisplay);
		else
			return Double.toString(numDisplay);
	}
}
