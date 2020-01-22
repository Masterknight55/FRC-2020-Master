package frc.robot;

public class MotionProfile {


    public double BasicCosineMotionProfile(double input, double scale)
    {
        if(input < 0)
			{
				return -1 * (2 * (Math.cos(input * scale + Math.PI) + 1));
			}
			if (input > 0)
			{
				return 2 * (Math.cos(input * scale + Math.PI) + 1);	
			}
			else
			{
				return 0;
			}
     
    }

    public double ScaledCosineProfileCurrentAxisPosition(double InitialAxisPosition, double FinalAxisPosition, double CurrentCyclePosition, double ProfileStartPointInCycle, double ProfileEndPointInCycle)
    {

       return InitialAxisPosition + ((FinalAxisPosition-InitialAxisPosition)/2) * (Math.cos(( 3.14 * (CurrentCyclePosition - ProfileStartPointInCycle) / (ProfileEndPointInCycle - ProfileStartPointInCycle) +1)+1));

    }


}