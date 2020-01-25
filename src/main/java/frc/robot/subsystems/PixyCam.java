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
public class PixyCam {

    //Declares Instance Variables
    private int analogPort;
    private AnalogInput PixyInput;

    //The inner deadzone is when the target is close enough to the center of the camera.  The outer deadzone makes sure the robot does not read a false detection
    private static final double innerDeadzone = .10;
    private static final double outerDeadzone = .95;

    //Decleares the PixyCam object and creates the PixyInput analogPort
    public PixyCam(int analogPort)
    {
        this.analogPort = analogPort;
        PixyInput = new AnalogInput(analogPort);
    }

    //Returns a value between -1 and 1, -1 being far left and 1 being far right
    public double value()
    {
        return (((double)(PixyInput.getValue()) - 1500.0) / 1500.0);
    }

    //Returns a boolean, "true" if a block is detected
    public boolean blockDetected()
    {
        if (Math.abs(this.value()) < outerDeadzone)
        {
            return true;
        }
        return false;
    }

    //Returns a boolean, "true" if a block is in inner deadzone
    public boolean inDeadzone()
    {
        if (Math.abs(this.value()) < innerDeadzone)
        {
            return true;
        }
        return false;
    }

    //Puts a value between -1 and 1 on smart dashboard
    public void outputToSmartDashboard()
    {

        SmartDashboard.putNumber("PixyCam", this.value());

    }
}
