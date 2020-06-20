package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OperatorDrive")
public class OperatorDrive extends OpMode{

    private DcMotor left;
    private DcMotor right;
    private DcMotor arm;
    private Servo front;
    private Servo back;
    
    private double servoPos;
    private boolean oldServoButton;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class, "rightMotor");
        arm = hardwareMap.get(DcMotor.class, "armMotor");
        front = hardwareMap.get(Servo.class, "frontServo");
        back = hardwareMap.get(Servo.class, "backServo");
        
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        
        servoPos = 0;
        oldServoButton = false;
    }
    
    @Override
    public void loop() {
        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
        double turn = gamepad1.left_stick_x;
        double armSpeed = gamepad1.right_stick_y;
        boolean servoButton = gamepad1.x;
        
        left.setPower(speed + turn);
        right.setPower(speed - turn);
        
        arm.setPower(armSpeed);
        
        if(servoButton && !oldServoButton) {
            if(servoPos == 0) {
                servoPos = 0.5;
            }
            else {
                servoPos = 0;
            }
            front.setPosition(servoPos);
            back.setPosition(servoPos);
        }
        
        oldServoButton = servoButton;
    }
}