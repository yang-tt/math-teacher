import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

/**
 * Title      : MathTeacher.java
 * Description: This class is for the complete miniproject.
 * Copyright  : Copyright (c) 2018 - 2019
 * @author  Tao Yang
 * @version 2.0
 */
public class MathTeacher extends JFrame implements ActionListener
{
	private JLabel calculateLabel;	//Label to print random arithmetic questions 
	private JLabel Label;	//Label to print correct times
	private JTextField answerField;	//Text field for student to input their answer  
	private JButton buttons[] = new JButton[12];	//Buttons for numbers, minus, and delete  
	private JButton checkButton;	//"Press for answer" button
	public String output = "";	//Store output question
	public int correctNum;	//Store the correct value of the current arithmetic question
	public int time;	//Correct answer times
	public int F;	//Current answer times
	
	/**
	 *Constructor of class MathTeaher
	 */
	public MathTeacher()
	{
		super("Math Teacher");	//Set title for GUI
  
		calculateLabel = new JLabel(Calculate(),JLabel.CENTER);	//Question label 
		Label = new JLabel( "< " + time + " correct out of " + F + " >", JLabel.CENTER);	//Correct time label
  
		answerField = new JTextField(10);	//Students' answer text field
		answerField.setHorizontalAlignment(JTextField.RIGHT);	//Set text start from right
		answerField.setEditable(true);	//Set answerField can be edit by keyboard
		
		answerField.addKeyListener(new KeyAdapter()
		{
			String input;
			
			/**
			 * This method listens the action of answerField. 
			 * When the user enters through the keyboard, 
			 * it will limit the input value to a number, 
			 * and the value can only be between -99 and 999.
			 */
			public void keyTyped(KeyEvent e){
				int i;
				input = answerField.getText()+e.getKeyChar();	//Current keyboard input
				for(i=-99; i<=999; i++)	//When it between -99 and 999
				{
					if(input.equals(""+i) || input.equals(""))
						break;
					if(input.equals(""+'-'))
						break;	
				}
				if(i == 1000)	//When it out of range
					e.consume();	//Destroy the input
			}
		});
	 

		JPanel calPanel = new JPanel(new GridLayout(4,3)); //Panel calPanel to store buttons using GridLayout
															
		String name[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "-", "0", "Delete"};	//Name array, store buttons' name
		for(int p=0;p<12;p++)	//create button array
		{  
			buttons[p] = new JButton(name[p]);	//name the buttons
			calPanel.add(buttons[p]);	//add the buttons into calPanel
			buttons[p].addActionListener(this);	//Add listener to buttons  
		}  
		
		checkButton = new JButton("Press to answer");	//Keyboard button ‘Press to answer’
		checkButton.addActionListener(new ActionListener() {	//Action listener
			
			/**
			 * This method listens the action of button "Press for answer".
			 * When user click the button, the program will check the answer 
			 * and show user the correct answer.
			 */
			public void actionPerformed(ActionEvent e) {
				int num;
				String input = answerField.getText(); //Store the input in answer field
	    
				if(input.isEmpty()) //If there are no input in answer field, press this button will have no reaction
					return;
				else
				{
					if(input.matches("-?[0-9]+.*[0-9]*")) //Identify whether input is composed by digits 
					{
						num = Integer.parseInt(input); //Convert input to integer
						if(num<=100 && num>=-9)	//When the user's input is between -9 and 100
						{
							if(num == correctNum) //If the answer is correct
							{
								time++; //Correct answer times +1
								F++; //Current answer times +1
								Label.setText( "< " + time + " correct out of " + F + " >");
								JOptionPane.showMessageDialog( null, "Correct! <Correct answer is " + correctNum + ">");
								calculateLabel.setText(Calculate()); //Set question label to 'Correct', and print the next question
							}
							else 
							{
								F++; //Current answer times +1
								Label.setText("< " + time + " correct out of " + F + " >");	
								//Set question label to 'Wrong', and print the correct answer and next question
								JOptionPane.showMessageDialog( null, "Wrong! <Correct answer is " + correctNum + ">");
								calculateLabel.setText(Calculate());
							}
							answerField.setText(""); //Set answer field to null
						}
						else
						{
							JOptionPane.showMessageDialog( null, "Please input the correct number format! <i.e. an integer number between -9 and 100 (inclusive)>");
							answerField.setText(""); //Set answer field to null
						}
					}	
					else //If input is not composed by digits
					{
						JOptionPane.showMessageDialog( null, "Please input the correct number format! <i.e. an integer number between -9 and 100 (inclusive)>");
						answerField.setText(""); //Set answer field to null
					}
				}
			}
	  		});
	  
	  		JPanel QA = new JPanel(new GridLayout(3,1)); //Panel QA to store calculateLabel, Label and answerField using GridLayout
	  		
	  		//Add buttons into JPanel QA
	  		QA.add(calculateLabel);
	  		QA.add(Label);
	  		QA.add(answerField);
	  
	  		setLayout(new FlowLayout());	//eSt frame's layout
	  		
	  		//Add panels and button into frame
	  		add(QA);
	  		add(calPanel);
	  		add(checkButton);
	  
	  		setSize(350,290); //Set frame's size
	  		setVisible(true); //Display frame
	  		setLocationRelativeTo(null); //Display in the center of window
		}	
	 
	 	/**
	 	 * Generated math questions randomly
	 	 * @return return the question in calculateLabel
	 	 */
		public String Calculate() //calculate
		{
			Random random = new Random();	
			int r1, r2, r3; //create three random numbers
			r1 = random.nextInt(10) + 1; //argument1(1-10)
			r2 = random.nextInt(10) + 1; //argument2(1-10)
			r3 = random.nextInt(4) + 1; //arithmetic operation (+ - * /)
	  
			if(r3 == 1) // +
			{
				output = "Question: " + r1 + " + " + r2 + " = ";
				correctNum = r1 + r2; //correct answer
			}
	  
			else if(r3 == 2) // -
			{
				output = "Question: " + r1 + " - " + r2 + " = ";
				correctNum = r1 - r2; //correct answer
			}
	  
			else if(r3 == 3) // x
			{
				output = "Question: " + r1 + " x " + r2 + " = ";
				correctNum = r1 * r2; //correct answer
			}
	  
			else if(r3 == 4) // /
			{
				output = "Question: " + r1*r2 + " / " + r2 + " = ";
				correctNum = r1; //correct answer
			}
			return output; //output
		}
	 
	 
		public static void main(String[] args) {
			MathTeacher application = new MathTeacher();
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/**
		 * This method listens the action of buttons in button array.
		 * When the user clicks the buttons, the program will give corresponding response 
		 * in answerFiled.
		 */
		public void actionPerformed(ActionEvent e) {
			String L = e.getActionCommand();	//get the action
			if(L.equals("-"))	//When user click the "-" button
			{
				String input = answerField.getText(); //Store the input in answer field
				if (!input.isEmpty())	//if the answerField is empty, it will return no action
					return;
				else 
					answerField.setText("-");	//If the input in answer field is not empty, 
												//set answer field's text to ‘-’
			} 
			else if(L.equals("Delete"))
			{
				String input = answerField.getText();	//Store the input in answer field
				if(input.isEmpty())	//If there are no input in answer field, press this button will have no reaction
					return;
				answerField.setText(input.substring(0,input.length()-1)); //If the input in answer field is not empty, 
	                   													//set answer field's text to ‘original - 1’
			} 
			else if(L.equals("0"))	//When user click the "0" button
			{
				String input = answerField.getText();	//Store the input in answer field before add '0'
				answerField.setText(answerField.getText() + "0");
				String input2 = answerField.getText(); //Store the input in answer field after add '0'
				answerField.setText(input2);
				int num;
				num = Integer.parseInt(input2); //Convert input2 to integer
		    
				if(input2.equals("-0"))	//if input '-0', it will return '0'
					answerField.setText(input);
				if(input2.equals("00"))	//if input two '0', it will return only one '0'
					answerField.setText(input);
				
				if(num >= 999) //Set input is a number less than 999
				{
					if(num == 999)
						answerField.setText("999");
					else
						answerField.setText(input); //if input number is more than 999, the last number can not be input
				}
		    
				if(num < -99)	//Set input is a number greater than 999
					answerField.setText(input);
			}
			else	//When user click other buttons except '-', 'Delete' and '0'
			{
				String input = answerField.getText(); //Store the input in answer field before add 'number'
				answerField.setText(answerField.getText() + e.getActionCommand());
				String input2 = answerField.getText(); //Store the input in answer field after add 'number' 
				int num;
				num = Integer.parseInt(input2); //Convert input2 to integer
				if(input2.equals("0" + e.getActionCommand()))
				{
					answerField.setText(input);
				}
				if(num > 999)	//Set input is a number less than 999
					answerField.setText(input);
				if(num < -99)	//Set input is a number greater than -99
					answerField.setText(input);
			}
		}
	
}	//end of constructor