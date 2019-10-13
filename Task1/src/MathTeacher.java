import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

/**
 * Title      : MathTeacher.java
 * Description: This class is for the Task 1 of miniproject.
 * Copyright  : Copyright (c) 2018 - 2019
 * @author  Tao Yang
 * @version 1.0
 */

public class MathTeacher extends JFrame
{
	private JLabel calculateLabel;	//Question label 
	private JTextField answerField;	//Students' answer text field 
	private JButton button;	//"Press for answer" button
	public String output = "";	//Store output question
	public int correctNum;	//Store the correct value of the current arithmetic question
 
	/**
  	*Constructor of class MathTeaher
  	*/
	public MathTeacher()
	{
		super("Math Teacher"); //Set title for GUI
  
		Container container = getContentPane();	//Create container
		container.setLayout(new FlowLayout());	//Set layout
  
		calculateLabel = new JLabel(Calculate(),JLabel.CENTER); //Question label 
		container.add(calculateLabel);	//Add calculateLabel to container
		answerField = new JTextField(5); //Students' answer text field
		answerField.setHorizontalAlignment(JTextField.RIGHT); //Set text start from right
		container.add(answerField);	//Add answerField to container
  
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
		
		button = new JButton("Press for answer");	//Button ‘Press to answer’
		button.addActionListener(new ActionListener() {
   
			/**
			 * This method listens the action of button "Press for answer".
			 * When user click the button, the program will check the answer
			 * and show the correct answer.
			 */
			public void actionPerformed(ActionEvent e) {
				long num = 0;
				String input = answerField.getText();	//Store the input in answer field
    
				if(input.isEmpty())	//If there are no input in answer field, press this button will have no reaction
					return;
				else
				{
					if(input.matches("-?[0-9]+.*[0-9]*"))	//Identify whether input is composed by digits 
					{
						try{
							num = Long.parseLong(input);
							if(num>=-9 && num<=100) 
							{
								if(num == correctNum)	//If the answer is correct
								{
									JOptionPane.showMessageDialog( null, "Correct! <Correct answer is " + correctNum + ">");
									calculateLabel.setText(Calculate());	//Set question label to 'Correct', and print the next question
								}
								else 
								{
									JOptionPane.showMessageDialog( null, "Wrong! <Correct answer is " + correctNum + ">");
									calculateLabel.setText(Calculate());	//Set question label to 'Wrong', and print the correct answer and next question
								}
								answerField.setText(""); //Set answer field to null 
							}
          
							else //If input is out of range
							{
								JOptionPane.showMessageDialog( null, "Please input the correct number format! <i.e. an integer number between -9 and 100 (inclusive)>");
								answerField.setText(""); //Set answer field to null
							}
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog( null, "Please input the correct number format! <i.e. an integer number between -9 and 100 (inclusive)>");
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

		container.add(button);	//Add button to container
		setSize(330,100); //Set frame's size
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

}