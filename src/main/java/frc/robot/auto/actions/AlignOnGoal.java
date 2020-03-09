/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.actions;
import frc.robot.subsystems.PixyCam;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class AlignOnGoal implements Action{

    private Drivetrain mDrivetrain = Drivetrain.getInstance();
    private PixyCam pixy = PixyCam.getInstance(1);
    private int goalPixyDelay;
    private double mLeftSpeed;
    private double mRightSpeed;
    private boolean goalPixyDisable;
    private boolean finished;
    private boolean alignedOngoal;

    public AlignOnGoal()
    {
    }

    public void start() 
    {
        mDrivetrain.lowGear();
        goalPixyDelay = 0;
        mLeftSpeed = 0;
        mRightSpeed = 0;
        goalPixyDisable = false;
        finished = false;
        alignedOngoal = false;
    }

    public void update()
    {
        if(pixy.blockDetected() && !goalPixyDisable)
	    {
			goalPixyDelay = 60;

			mLeftSpeed = -pixy.value();
			mRightSpeed = pixy.value();
			if(pixy.inDeadzone())
			{
				mLeftSpeed = mLeftSpeed * 0.5 - 0.4;
				mRightSpeed = -mRightSpeed * 0.5 - 0.4;
            }
		}
		else if(goalPixyDelay > 0)
		{
            alignedOngoal = true;
			goalPixyDisable = true;
			goalPixyDelay--;
			mLeftSpeed = -0.2;
			mRightSpeed = -0.2;
		}
		else
		{
			mLeftSpeed = 0;
            mRightSpeed = 0;
            finished = true;
        }
        mDrivetrain.setTankDriveSpeed(mLeftSpeed, mRightSpeed);
    }

    public void done()
    {
        mLeftSpeed = 0;
        mRightSpeed = 0;
        mDrivetrain.setTankDriveSpeed(mLeftSpeed, mRightSpeed, 0);
        System.out.println("Is aligned on goal: " + alignedOngoal);
    }

    public boolean isFinished()
    {
        return finished;
    }
}
