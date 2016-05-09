package org.simalliance.openmobileapi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.nfc.NfcAdapter;
import org.simalliance.openmobileapi.service.SmartcardService;

public class SmartcardServiceBootCompletedBroadcastReceiver extends BroadcastReceiver {
    public final static String _TAG = "SmartcardService";
    public final static String _SCAPI_SERVICE = "org.simalliance.openmobileapi.service.SmartcardService";

    @Override
    public void onReceive(Context context, Intent intent) {
        final boolean bootCompleted = intent.getAction().equals("android.intent.action.BOOT_COMPLETED");
        final boolean nfcAdapterAction = intent.getAction().equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
        final boolean nfcAdapterOn = nfcAdapterAction && (intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE, 1) == NfcAdapter.STATE_ON); // is NFC Adapter turned on ?

        if( (bootCompleted || nfcAdapterOn) && !SmartcardService.hasStarted){

            if (nfcAdapterOn)
                Log.v(_TAG, " Received broadcast NFC is ON - Starting smartcard service");
            else if (bootCompleted)
                Log.v(_TAG, " Received broadcast Boot completed - Starting smartcard service");

            Intent serviceIntent = new Intent(context, org.simalliance.openmobileapi.service.SmartcardService.class );
            context.startService(serviceIntent);
        } else {

        }
    }
};
