package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OperatorDriveLessonTwo")
public class OperatorDriveLessonTwo extends OpMode{
    
    private DcMotor left;
    private DcMotor right;
    private Servo servo;

    private double servoPos;
    private boolean oldServoButton;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class, "rightMotor");
        servo = hardwareMap.get(Servo.class, "servo");

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        servoPos = 0;
        oldServoButton = false;
    }

    @Override
    public void loop() {
        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
        double turn = gamepad1.left_stick_x;
        boolean servoButton = gamepad1.x;

        left.setPower(speed + turn);
        right.setPower(speed - turn);

        if(servoButton && !oldServoButton) {
            if(servoPos == 0){
                servo.setPosition(1);
                servoPos = 1;
            }
            else {
                servo.setPosition(0);
                servoPos = 0;
            }
        }
        
        oldServoButton = servoButton;
    }
}
