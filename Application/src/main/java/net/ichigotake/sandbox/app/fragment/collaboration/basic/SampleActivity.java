package net.ichigotake.sandbox.app.fragment.collaboration.basic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import net.ichigotake.sandbox.app.R;

public class SampleActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwoColumnContainer body = TwoColumnContainerImpl.create(this, R.layout.fragment_collaboration_activity);
        body.setContent(R.id.fragment_collaboration_fragment_content,
                SampleContentFragment.factory().create());
        body.setSubContent(R.id.fragment_collaboration_fragment_sub_content,
                SampleSubContentFragment.factory().create());
    }
}
