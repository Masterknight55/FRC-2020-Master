package frc.robot.auto.modes;

import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DriveStraightActionDistance;
import frc.robot.auto.actions.TurnActionAngle;
import frc.robot.auto.actions.DeliveryAction;
import frc.robot.auto.actions.StopDeliveryAction;
import frc.robot.auto.actions.WaitAction;


public class TrenchRunDeliver extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

        //Turn
        runAction(new TurnActionAngle(20));
        runAction(new WaitAction(.5));

        //Auto Allign with Goal

        //Go Straight
        runAction(new DriveStraightActionDistance(20));
        runAction(new WaitAction(.5));

        //Deliver
        runAction(new DeliveryAction());
        runAction(new WaitAction(10));
        runAction(new StopDeliveryAction());

		
	}

}