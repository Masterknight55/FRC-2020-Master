/*----------------------------------------------------------------------------*/
/* Copyright (c) 2072-2073 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.actions;

import frc.robot.Setup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PixyCam;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
/*
public class ChaseBall implements Action
{
    private double mLeftSpeed;
    private double mRightSpeed;
    private int scale = 1;
    private Drivetrain mDrivetrain = Drivetrain.getInstance();
    //You made an oopsie: private PixyCam pixy = PixyCam.getInstance();

    public ChaseBall()
    {
        
    }

	@Override
	public void start() {
		mDrivetrain.lowGear();
	}

    @Override
	public void update() {
        if(pixy.blockDetected() && !pixy.inDeadzone())
		{
			mLeftSpeed = pixy.value();
			mRightSpeed = pixy.value();
		}
		else if(pixy.blockDetected() && pixy.inDeadzone())
		{
			mLeftSpeed = 1;
			mRightSpeed = 1;
		}
		else
		{
			mLeftSpeed = 0;
			mRightSpeed = 0;
		}
    	mDrivetrain.setTankDriveSpeed(mLeftSpeed, mRightSpeed, 1);
	}
    
    @Override
	public boolean isFinished() {
		return !pixy.blockDetected();
    }
    
	@Override
	public void done() {
		mDrivetrain.setTankDriveSpeed(0, 0, 0);
		mDrivetrain.resetEncoders();
		System.out.println("finished with drive straight (distance) action");
	}
        
}
*/