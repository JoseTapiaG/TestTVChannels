package cl.josemanuel.testtvchannels.fragments;

import android.app.Fragment;
import android.content.Context;
import android.media.tv.TvContract;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.media.tv.TvTrackInfo;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.josemanuel.testtvchannels.R;

public class ChannelsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.channels_fragment, container, false);
    }

    TvView tvView;
    String idInputList;
    int currentChannel;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvInputManager tvInputManager = (TvInputManager) getActivity().getSystemService(Context.TV_INPUT_SERVICE);
        List<TvInputInfo> tvInputList = tvInputManager.getTvInputList();
        tvView = (TvView) view.findViewById(R.id.tvView);
        idInputList = tvInputList.get(0).getId();
        tvView.tune(idInputList, Uri.EMPTY);
        tvView.setStreamVolume(1f);
        tvView.setCallback(new TvView.TvInputCallback() {

            @Override
            public void onContentAllowed(String inputId) {
                super.onContentAllowed(inputId);
            }



            @Override
            public void onChannelRetuned(String inputId, Uri channelUri) {
                currentChannel = Integer.parseInt(channelUri.getLastPathSegment());
                super.onChannelRetuned(inputId, channelUri);
            }

            @Override
            public void onTracksChanged(String inputId, List<TvTrackInfo> tracks) {
                super.onTracksChanged(inputId, tracks);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvView.setStreamVolume(1f);
    }


    public void changeChannel(int keyCode) {
        switch (keyCode){
            case 166:
                //arriba
                tvView.tune(idInputList, TvContract.buildChannelUri(++currentChannel));
                break;
            case 167:
                //abajo
                tvView.tune(idInputList, TvContract.buildChannelUri(--currentChannel));
                break;
        }
    }
}
