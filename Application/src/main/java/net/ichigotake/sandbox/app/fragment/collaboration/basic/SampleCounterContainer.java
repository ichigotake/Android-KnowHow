package net.ichigotake.sandbox.app.fragment.collaboration.basic;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import net.ichigotake.sandbox.app.R;

class SampleCounterContainer {

    private final TextView counter;
    private final View button;
    private Resources resources;

    SampleCounterContainer(View container) {
        counter = (TextView)container.findViewById(
                R.id.fragment_collaboration_fragment_content_count);
        button = container.findViewById(
                R.id.fragment_collaboration_fragment_content_count_request);
        resources = container.getResources();
    }

    void setCount(int count) {
        counter.setText(resources.getString(R.string.fragment_collaboration_fragment_count, count));
    }

    public void setButtonClickListener(View.OnClickListener listener) {
        button.setOnClickListener(listener);
    }
}
