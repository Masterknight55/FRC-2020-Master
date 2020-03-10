/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.actions;
import frc.robot.subsystems.PixyCam;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

/**
 * Add your docs here.
 */
public class ChaseBall implements Action{

	private Drivetrain mDrivetrain = Drivetrain.getInstance();
	private Intake mIntake = Intake.getInstance();
    private PixyCam pixy = PixyCam.getInstance(0);
    private int goalPixyDelay;
    private double mLeftSpeed;
    private double mRightSpeed;
    private boolean goalPixyDisable;
    private boolean finished;
    private boolean alignedOngoal;

    public ChaseBall()
    {
    }

    public void start() 
    {
		mIntake.IntakePowercell();
        mDrivetrain.lowGear();
        goalPixyDelay = 0;
        mLeftSpeed = 0;
        mRightSpeed = 0;
        goalPixyDisable = false;
        finished = false;
        alignedOngoal = false;
        mDrivetrain.isFinished = false;
    }

    public void update()
    {
        //setLowGear();
		mDrivetrain.autoAlign(pixy, 1);
    }

    public void done()
    {
		mIntake.StopIntaking();
        mLeftSpeed = 0;
        mRightSpeed = 0;
        mDrivetrain.setTankDriveSpeed(mLeftSpeed, mRightSpeed, 0);
        System.out.println("Is eat ball: " + alignedOngoal);
    }

    public boolean isFinished()
    {
        return mDrivetrain.isFinished;
    }
}
