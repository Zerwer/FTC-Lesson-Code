package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.util.ArrayList;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto")
public class LessonSevenJava extends LinearOpMode{

    private DcMotor left;
    private DcMotor right;

    private int leftPosition;
    private int rightPosition;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class, "rightMotor");
        
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        
        rightPosition = 0;
        leftPosition = 0;
        
        waitForStart();
        
        drive(1000, 1000);
        drive(-1000, 1000);
    }
    
    private void drive(int leftTarget, int rightTarget) {
        leftPosition += leftTarget;
        rightPosition += rightTarget;
        
        left.setTargetPosition(leftPosition);
        right.setTargetPosition(rightPosition);
        
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        left.setPower(0.25);
        right.setPower(0.25);
        
        while(opModeIsActive() && left.isBusy() && right.isBusy()) {
            idle();
        }
    }
}
