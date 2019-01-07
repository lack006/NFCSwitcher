package com.lack006.nfc;

import android.annotation.TargetApi;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

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
        boolean isSucceed;

        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_ACTIVE) {
            isSucceed = Util.NFCOff();
            if (isSucceed) {
                tile.setState(Tile.STATE_INACTIVE);
                tile.setLabel(getString(R.string.nfc_off));
                tile.setIcon(Icon.createWithResource(getApplicationContext(),
                        R.mipmap.ic_nfc_grey));
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }

        } else {

            isSucceed = Util.NFCOn();
            if (isSucceed) {
                tile.setState(Tile.STATE_ACTIVE);
                tile.setLabel(getString(R.string.nfc_on));
                tile.setIcon(Icon.createWithResource(getApplicationContext(),
                        R.mipmap.ic_nfc_white));
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }

        }

        tile.updateTile();
    }

}
