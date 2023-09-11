/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pepegacorp.hud;

import java.awt.MouseInfo;

/**
 *
 * @author eduardo
 */
public class MouseUtils extends Thread {
    public void printMouseCoords() {
        System.out.println(MouseInfo.getPointerInfo().getLocation());
    }

    @Override
    public void run() {
        while (true) {
            printMouseCoords();
        }
    }
    
}
