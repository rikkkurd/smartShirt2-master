package com.polkapolka.bluetooth.le;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class userActivity extends Activity {



     public static int activityCounterBed = 0;
     public static int activityCounterDesk = 0;
     public static int activityCounterWalking=0;
     public static int activityCounterPatient=0;
     public static int activityCounterMachine=0;
     public static int activityCounterOther=0;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String[] activityOptionsUser = {"Standing next to a bed", "Working at a desk",
                "Walking", "Handling a patient", "Operating machine", "Other"};

// The ListAdapter acts as a bridge between the data and each ListItem
        // You fill the ListView with a ListAdapter. You pass it a context represented by
        // this. A Context provides access to resources you need.
        // android.R.layout.simple_list_item_1 is one of the resources needed.
        // It is a predefined layout provided by Android that stands in as a default
        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityOptionsUser);
        // listview display data in a scrollable list
        ListView theListView = (ListView) findViewById(R.id.theListView);
        // tell the ListView what data to use
        theListView.setAdapter(theAdapter);



        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String activityOptionsUser = "You selected " + String.valueOf(adapterView.getItemAtPosition(position));
                Toast.makeText(userActivity.this, activityOptionsUser, Toast.LENGTH_SHORT).show();

                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Standing next to a bed")) {
                    activityCounterBed++;
                    System.out.print("Bed");
                    System.out.println(activityCounterBed);
                    // dataHandler.updateActivityAtmeasurement()
                }
                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Working at a desk")) {
                    activityCounterDesk++;
                    System.out.print("Desk");
                    System.out.println(activityCounterDesk);
                }
                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Walking")) {
                    activityCounterWalking++;
                    System.out.print("Walking");
                    System.out.println(activityCounterWalking);
                }
                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Handling a patient")) {
                    activityCounterPatient++;
                    System.out.print("Patient");
                    System.out.println(activityCounterPatient);
                }
                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Operating machine")) {
                    activityCounterMachine++;
                    System.out.print("machine");
                    System.out.println(activityCounterMachine);
                }
                if (String.valueOf(adapterView.getItemAtPosition(position)).equalsIgnoreCase("Other")) {
                    activityCounterOther++;
                    System.out.print("Other");
                    System.out.println(activityCounterOther);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_useractivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}