package com.lack006.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

class Util {

    static boolean getNFCStatus(Context context) {

        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = null;
        if (manager != null) {
            adapter = manager.getDefaultAdapter();
        }
        return adapter != null && adapter.isEnabled();


    }

    static boolean NFCOn() {
        List<String> list = Shell.SU.run(Commands.NFC_ON_SHELL);
        return list.isEmpty();
    }

    static boolean NFCOff() {
        List<String> list = Shell.SU.run(Commands.NFC_OFF_SHELL);
        return list.isEmpty();
    }
}
