package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;
import edu.wpi.first.wpilibj.DigitalOutput;


public class LED extends Subsystem
{

    static LED mInstance = new LED();

    public static LED getInstance() {
        return mInstance;
    }

    private PWM mLEDPWMR;
    private PWM mLEDPWMG;
    private PWM mLEDPWMB;

    private DigitalOutput mLEDPower;

    private int RedChannel;
    private int GreenChannel;
    private int BlueChannel; 

    public LED()
    {
        
        mLEDPWMR = new PWM(Setup.kLEDPWMR);
        mLEDPWMG = new PWM(Setup.kLEDPWMG);
        mLEDPWMB = new PWM(Setup.kLEDPWMB);

        mLEDPower = new DigitalOutput(Setup.kLEDPower);

    }   

    public void SetColor(int Red, int Green, int Blue)
    {
        RedChannel = Red;
        BlueChannel = Blue;
        GreenChannel = Green;
    }

    public void SetBlue()
    {
        SetColor(26, 0, 255);
    }

    public void SetRed()
    {
        SetColor(255, 0, 0);
    }

    public void TurnOnLED()
    {
        mLEDPower.set(true);
    }

    public void TurnOffLED()
    {
        mLEDPower.set(false);
    }

    @Override
    public void updateSubsystem()
    {
        mLEDPWMR.setRaw(RedChannel);
        mLEDPWMG.setRaw(GreenChannel);
        mLEDPWMB.setRaw(BlueChannel);
    }

    @Override
    public void outputToSmartDashboard()
    {
       
        SmartDashboard.putNumber("LED Red Channel", RedChannel);
        SmartDashboard.putNumber("LED Green Channel", GreenChannel);
        SmartDashboard.putNumber("LED Blue Channel", BlueChannel);

    }
    @Override
    public void stop()
    {
        TurnOffLED();
    }


}