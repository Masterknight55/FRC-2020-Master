package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DeliveryAction;
import frc.robot.auto.actions.DriveStraightActionDistance;
import frc.robot.auto.actions.StopDeliveryAction;
import frc.robot.auto.actions.TurnActionAngle;
import frc.robot.auto.actions.WaitAction;


public class TrenchRunPickupAndDeliver extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

        //Go Straight
        runAction(new DriveStraightActionDistance(20));
        runAction(new WaitAction(.5));

        //Pickup Balls

        //Go Backwards
        //Turn
        //Allign with goal
        //Go Straight
        //Deliver

	}

}