package com.polkapolka.bluetooth.le;

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

    /**
     * Constructor
     */
    public ArduinoHandler() {

    }

    /**
     * Handle bytes from the Arduino.
     *
     * @param intent
     */
    public static void handleArduinoBytes(Intent intent) {

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

                int roll1 = intArr[1];
                int pitch1 = intArr[2];
                int heading1 = intArr[3];

                System.out.println("[DATA SENSOR ONE]: " + roll1 + ", " + pitch1 + ", " + heading1);
                break;

            case SENSOR_TWO_PACKET_ID:
                int roll2 = intArr[1];
                int pitch2 = intArr[2];
                int heading2 = intArr[3];
                System.out.println("[DATA SENSOR TWO]: " + roll2 + ", " + pitch2 + ", " + heading2);
                break;

        }
    }
}
