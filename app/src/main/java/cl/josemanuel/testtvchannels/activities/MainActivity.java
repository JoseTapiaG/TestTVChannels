package cl.josemanuel.testtvchannels.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import cl.josemanuel.testtvchannels.R;
import cl.josemanuel.testtvchannels.fragments.ChannelsFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 166 || keyCode == 167){
            ChannelsFragment channelsFragment = (ChannelsFragment) getFragmentManager().findFragmentById(R.id.channelFragment);
            channelsFragment.changeChannel(keyCode);
        }
        return super.onKeyDown(keyCode, event);
    }
}
