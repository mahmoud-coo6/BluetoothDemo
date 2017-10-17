package com.example2.acer.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA= BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( BA.isEnabled()){
            Toast.makeText(getApplicationContext(),"BLUETOOTH is on",Toast.LENGTH_LONG).show();
        }else{
            Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if (BA.isEnabled()){
                Toast.makeText(getApplicationContext(),"BLUETOOTH trurned on",Toast.LENGTH_LONG).show();
            }
        }

    }
    public  void turnBluotthOff(View view){
            BA.disable();
        if (BA.isEnabled()){
            Toast.makeText(getApplicationContext(),"BLUETOOTH trurned could not disabled ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"BLUETOOTH trurned off",Toast.LENGTH_LONG).show();
        }
    }

    public void turnBluotthOn(View view){
        Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivity(i);
        if (BA.isEnabled()){
            Toast.makeText(getApplicationContext(),"BLUETOOTH turned on ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"BLUETOOTH could not be turn on",Toast.LENGTH_LONG).show();
        }
    }

    public void findDiscoverableDevise(View view){
        Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPiredDevice(View view){
        Set<BluetoothDevice> pairdDevice= BA.getBondedDevices();
        ListView PairentDeviseListView=(ListView)findViewById(R.id.viewPairentDeviseList);
        ArrayList pairdDeviceArrayList=new ArrayList();
        for (BluetoothDevice bluetoothDevice: pairdDevice){
            pairdDeviceArrayList.add(bluetoothDevice.getName());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairdDeviceArrayList);
        PairentDeviseListView.setAdapter(arrayAdapter);
    }

}
