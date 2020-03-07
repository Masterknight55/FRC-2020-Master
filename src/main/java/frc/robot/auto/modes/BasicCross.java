package frc.robot.auto.modes;

import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DriveStraightActionDistance;


public class BasicCross extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

		//go straight
		runAction(new DriveStraightActionDistance(20));

	}

}