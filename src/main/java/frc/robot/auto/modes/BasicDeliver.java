package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DeliveryAction;
import frc.robot.auto.actions.DriveStraightActionDistance;
import frc.robot.auto.actions.DriveStraightActionTime;

import frc.robot.auto.actions.WaitAction;


public class BasicDeliver extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

        //Allign with goal
        //Go Straight
        runAction(new DriveStraightActionDistance(20));
        runAction(new WaitAction(.5));
        //Deliver
        runAction(new DeliveryAction());
        runAction(new WaitAction(.5));

	}

}