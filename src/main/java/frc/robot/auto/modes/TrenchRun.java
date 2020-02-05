package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DriveStraightActionTime;
import frc.robot.auto.actions.DriveStraightActionDistance;

import frc.robot.auto.actions.WaitAction;


public class TrenchRun extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

		runAction(new DriveStraightActionDistance(-60));
		
	}

}