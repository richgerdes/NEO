package com.richgerdes.robotics;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;


public class BasicDrive extends SimpleRobot {
	
	RobotDrive drive;
	Jaguar jagLeft, jagRight;
	
	Joystick xBox;
	
	int fbFix = -1;						//fix if driving forward or backward
	
	int power = 2;						//standard drive power axis
	int turn = 1;						//standard drive turn axis
	int left = 2;						//tank drive Right axis
	int right = 5;						//tank drive Right axis
	
	int toggleDrive = 8;				//toggle drive key
	int toggleFB = 7;					//toggle forward backward
	
	boolean tankDrive = false;			//Switch between tank and standard drives
	
	public BasicDrive(){
		xBox = new Joystick(1);			//game controller set to joy 1
		jagLeft = new Jaguar(1); 		//in pwm 1
		jagRight = new Jaguar(2);		//in pwn 2
	}
	
	public void operatorControl(){
		
		if(xBox.getRawButton(toggleDrive)){
			tankDrive = !tankDrive;
		}
		if(xBox.getRawButton(toggleFB)){
			fbFix  *= -1;
		}
		
		if(tankDrive){
			tankDrive();
		}else{
			standardDrive();
		}
		
	}
	
	public void tankDrive(){

		jagLeft.set(xBox.getRawAxis(left) * -1 * fbFix);
		jagRight.set(xBox.getRawAxis(right) * fbFix);
		
	}
	
	public void standardDrive(){
		double lspeed = 0;
		double rspeed = 0;
		
		lspeed = xBox.getRawAxis(power);
		rspeed = xBox.getRawAxis(power);
		
		double adj = xBox.getRawAxis(turn);
		
		adj += 1;
		adj /= 2;
		
		rspeed *= adj;
		lspeed *= adj;
		
		lspeed *= -1 * fbFix;		
		rspeed *= fbFix;
		
		jagLeft.set(lspeed);
		jagRight.set(rspeed);
		
	}

	
}
