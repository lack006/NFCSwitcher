package com.lack006.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import com.topjohnwu.superuser.Shell;

import java.util.List;



class Util {

    static boolean getNFCStatus(Context context) {

        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = null;
        if (manager != null) {
            adapter = manager.getDefaultAdapter();
        }
        return adapter != null && adapter.isEnabled();


    }

    static List NFCOn() {
        return Shell.su(Commands.NFC_ON_SHELL).exec().getOut();
    }

    static List NFCOff() {
        return Shell.su(Commands.NFC_OFF_SHELL).exec().getOut();
    }
}
