package com.polkapolka.bluetooth.le;

import android.content.Context;
import android.content.Intent;
//import java.util.Arrays;

/**
 * Created by s104419 on 4/2/2015.
 */
public class ArduinoHandler {

    /**
     * Contants
     */
    private final static int SENSOR_ONE_PACKET_ID = 0;
    private final static int SENSOR_TWO_PACKET_ID = 1;
    private final static int BAD_PITCH_THRESHOLD = 20;
    private final static int BAD_PITCH_TIME = 3000;

    /**
     * Object references
     */

    /**
     * Pitch listener
     */
    private long lastBadPitchTime = 0;
    private boolean pitchNotificationShown = false;

    /**
     * Sensor variables
     */
    private int roll1 = 0;
    private int heading1 = 0;
    private int pitch1 = 0;
    private int roll2 = 0;
    private int heading2 = 0;
    private int pitch2 = 0;
    public Context context;
    /**
     * Constructor
     */
    public ArduinoHandler() {

        // variables

    }

    /**
     * Handle bytes from the Arduino.
     *
     * @param intent
     */
    public void handleArduinoBytes(Intent intent) {

        // guard: check if the bytes are available
        if (!intent.hasExtra(BluetoothLeService.EXTRA_DATA)) {
            return;
        }

        // variables
        String dataString = intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
        String[] dataStringArr = dataString.split(",");
        int[] intArr = new int[dataStringArr.length];

        // loop all data pieces
        for (int i = 0; i < dataStringArr.length; i++) {
            intArr[i] = Integer.parseInt(dataStringArr[i]);
        }

        // System.out.println(Arrays.toString(intArr));

        // guard: check if not null
        if (intArr == null) {
            return;
        }

        // guard: check if lenght is valid
        if (intArr.length != 4) {
            return;
        }

        // get the packet id
        int packetId = intArr[0];

        // switch on the packet id
        switch (packetId) {
            case SENSOR_ONE_PACKET_ID:

                roll1 = intArr[1];
                pitch1 = intArr[2];
                heading1 = intArr[3];

                System.out.println("[DATA SENSOR ONE]: " + roll1 + ", " + pitch1 + ", " + heading1);
                break;

            case SENSOR_TWO_PACKET_ID:
                roll2 = intArr[1];
                pitch2 = intArr[2];
                heading2 = intArr[3];
                System.out.println("[DATA SENSOR TWO]: " + roll2 + ", " + pitch2 + ", " + heading2);
                break;
        }

        // check the pitch posture
        checkPitchPosture();
    }

    /**
     * Check the pitch posture
     */
    private void checkPitchPosture() {

        // variables
        //int deltaPitch = pitch1 - pitch2;
        int deltaPitch = pitch1;
      //  deltaPitch = (deltaPitch < 0) ? -deltaPitch : deltaPitch;
        //int deltaPitch = pitch1 - pitch2;
        System.out.print("deltaPitch");
        System.out.println(deltaPitch);

        // guard: check if the threshold is exceeded //|| (deltaPitch >= -BAD_PITCH_THRESHOLD && <= 0;))
        if (deltaPitch < BAD_PITCH_THRESHOLD)  {
            lastBadPitchTime = 0;
            pitchNotificationShown = false;
            return;
        }

        // variables
        long currentMillis = System.currentTimeMillis();

        // guard: check if the timer is already set
        if (lastBadPitchTime <=  0) {
            System.out.println("Set last bad pitch time!");
            lastBadPitchTime = currentMillis;
            return;
        }

        // check if the timer is exceeded and the user needs a warning
        if (currentMillis - lastBadPitchTime > BAD_PITCH_TIME && !pitchNotificationShown) {

            // show notification
            System.out.println("Notification shown!");
            DeviceControlActivity.showNotificationInMenu(context);
            pitchNotificationShown = true;
        }
    }



}

