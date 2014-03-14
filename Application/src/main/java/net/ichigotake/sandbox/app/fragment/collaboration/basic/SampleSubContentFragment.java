package net.ichigotake.sandbox.app.fragment.collaboration.basic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ichigotake.sandbox.app.R;

public class SampleSubContentFragment extends Fragment implements Pane {

    public static FragmentFactory<SampleSubContentFragment> factory() {
        return new FragmentFactory<SampleSubContentFragment>() {
            @Override
            public Fragment create() {
                return new SampleSubContentFragment();
            }
        };
    }

    // onStop, onDestroy等の離脱・破棄系のイベントでは null になる場合があるので注意
    private SampleCounterContainer counterContainer;

    private final String KEY_COUNT = "count";
    private int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        count = restoreCounter(savedInstanceState);
        View container = inflater.inflate(R.layout.fragment_collaboration_fragment_content, null);
        counterContainer = new SampleCounterContainer(container);
        counterContainer.setCount(count);
        counterContainer.setButtonClickListener(new RequestOnClickListener(getActivity()));
        return container;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            outState = new Bundle();
        }
        outState.putInt(KEY_COUNT, count);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void tap() {
        counterContainer.setCount(++count);
    }

    private int restoreCounter(Bundle savedInstanceState) {
        return (savedInstanceState != null) ? savedInstanceState.getInt(KEY_COUNT) : 0;
    }

    private static class RequestOnClickListener implements View.OnClickListener {

        private final FragmentActivity activity;

        RequestOnClickListener(FragmentActivity activity) {
            this.activity = activity;
        }

        // 別のフラグメントへ更新リクエストを送る
        @Override
        public void onClick(View v) {
            TwoColumnContainerImpl.get(activity).requestForContent();
        }
    }
}
