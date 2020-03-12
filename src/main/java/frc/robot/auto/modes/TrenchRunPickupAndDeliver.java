package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DeliveryAction;
import frc.robot.auto.actions.DriveStraightActionDistance;
import frc.robot.auto.actions.StopDeliveryAction;
import frc.robot.auto.actions.TurnActionAngle;
import frc.robot.auto.actions.WaitAction;
import frc.robot.auto.actions.IntakeArmDownAction;
import frc.robot.auto.actions.IntakeAction;


public class TrenchRunPickupAndDeliver extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

        //Go Straight
        runAction(new DriveStraightActionDistance(20));
        runAction(new WaitAction(.5));

        //Pickup Balls
       // runAction(new ChaseBall());
        runAction(new WaitAction(.5));
        runAction(new IntakeArmDownAction());
        runAction(new IntakeAction());

        //Go Backwards
        runAction(new DriveStraightActionDistance(-20));
        runAction(new WaitAction(.5));

        //Turn
        runAction(new TurnActionAngle(20));
        runAction(new WaitAction(.5));

        //Allign with goal

        //Go Straight
        runAction(new DriveStraightActionDistance(20));
        runAction(new WaitAction(.5));

        //Deliver
        runAction(new DeliveryAction());
        runAction(new WaitAction(10));
        runAction(new StopDeliveryAction());

	}

}