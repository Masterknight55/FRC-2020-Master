/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;

/**
 * Add your docs here.
 */
public class PixyCam extends Subsystem{

    //Declares Instance Variables
    private int analogPort;
    private int age;
    private AnalogInput PixyInput;

    //The inner deadzone is when the target is close enough to the center of the camera.  The outer deadzone makes sure the robot does not read a false detection
    private static final double innerDeadzone = .10;
    private static final double outerDeadzone = .7;

    //InstanceGetting
    
    //static PixyCam mInstance = new PixyCam(0);
    public PixyCam getInstance() {
        return this;
	}
    
    //Decleares the PixyCam object and creates the PixyInput analogPort
    /**
     * This method creates a Pixycam object at the specified analog port
     * @param analogPort The analog port that the PixyCam is plugged into
     */
    public PixyCam(int analogPort)
    { 
        this.analogPort = analogPort;
        PixyInput = new AnalogInput(analogPort);
        int age = 0;
    }

    /**
     * This gets the value of the PixyCam
     * @return Returns a value between -1 and 1, -1 being far left and 1 being far right
     */
    public double value()
    {
        return (((double)(PixyInput.getValue()) - 1500.0) / 1500.0);
    }

    /**
     * This returns a boolean of the block detected
     * @return True if a block is detected, false otherwise
     */
    public boolean blockDetected()
    {
        if (Math.abs(this.value()) < outerDeadzone)
        {
            return true;
        }
        return false;
    }

    /**
     * This returns a boolean of the block inside of the deadzone
     * @return True if a block is in dead zone, false otherwise
     */
    public boolean inDeadzone()
    {
        if (Math.abs(this.value()) < innerDeadzone)
        {
            return true;
        }
        return false;
    }

    public boolean inBigDeadzone()
    {
        if (Math.abs(this.value()) < innerDeadzone * 3)
        {
            return true;
        }
        return false;
    }

    @Override
    public void updateSubsystem() {
        if(blockDetected())
        {
            age++;
        }
        else
        {
            age = 0;
        }

        outputToSmartDashboard();
    }
    //Puts a value between -1 and 1 on smart dashboard
    @Override
    public void outputToSmartDashboard()
    {

        SmartDashboard.putNumber("PixyCam" + analogPort, this.value());

    }

    public void stop()
    {
        //PixyInput.stop();

    }
}
