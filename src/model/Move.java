/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author martin
 */
public class Move {
    private String time;
    private String velocity;
    private String angle;
    private String seconds;

    public Move(String time, String velocity, String angle, String seconds) {
        this.time = time;
        this.velocity = velocity;
        this.angle = angle;
        this.seconds = seconds;
    }

    public String getTime() {
        return time;
    }

    public String getVelocity() {
        return velocity;
    }

    public String getAngle() {
        return angle;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
    
}
