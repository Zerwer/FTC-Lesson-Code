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
    private Servo front;
    private Servo back;
    
    private double speed;
    
    private double servoPos;
    private boolean oldServoButton;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class, "rightMotor");
        front = hardwareMap.get(Servo.class, "frontServo");
        back = hardwareMap.get(Servo.class, "backServo");
        
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        
        speed = 0.1;
        
        servoPos = 0;
        oldServoButton = false;
    }
    
    @Override
    public void loop() {
        boolean motorButton = gamepad1.a;
        boolean servoButton = gamepad1.x;
        
        if(motorButton) {
            left.setPower(speed);
            right.setPower(speed);
        }
        else {
            left.setPower(0);
            right.setPower(0);
        }
        
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