package com.lack006.nfc;

import android.annotation.TargetApi;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import java.util.List;

public class NFCSwitchTileService extends TileService {

    public NFCSwitchTileService() {
    }

    @Override
    public void onTileAdded() {
        Tile tile = getQsTile();
        if (Util.getNFCStatus(this)) {
            tile.setLabel(getString(R.string.nfc_on));
            tile.setIcon(Icon.createWithResource(getApplicationContext(),
                    R.mipmap.ic_nfc_white));
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setLabel(getString(R.string.nfc_off));
            tile.setIcon(Icon.createWithResource(getApplicationContext(),
                    R.mipmap.ic_nfc_grey));
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        if (Util.getNFCStatus(this)) {
            tile.setLabel(getString(R.string.nfc_on));
            tile.setIcon(Icon.createWithResource(getApplicationContext(),
                    R.mipmap.ic_nfc_white));
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setLabel(getString(R.string.nfc_off));
            tile.setIcon(Icon.createWithResource(getApplicationContext(),
                    R.mipmap.ic_nfc_grey));
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();
    }


    @Override
    public void onClick() {
        List list;

        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_ACTIVE) {
            list = Util.NFCOff();
            if (list.isEmpty()) {
                tile.setState(Tile.STATE_INACTIVE);
                tile.setLabel(getString(R.string.nfc_off));
                tile.setIcon(Icon.createWithResource(getApplicationContext(),
                        R.mipmap.ic_nfc_grey));
            } else {
                Toast.makeText(this, getResources().getString(R.string.error) + "\n" + list.get(0), Toast.LENGTH_LONG).show();
            }

        } else {
            list = Util.NFCOn();
            if (list.isEmpty()) {
                tile.setState(Tile.STATE_ACTIVE);
                tile.setLabel(getString(R.string.nfc_on));
                tile.setIcon(Icon.createWithResource(getApplicationContext(),
                        R.mipmap.ic_nfc_white));
            } else {
                Toast.makeText(this, getResources().getString(R.string.error) + "\n" + list.get(0), Toast.LENGTH_LONG).show();
            }

        }

        tile.updateTile();
    }

}
