package net.ichigotake.sandbox.app.fragment.collaboration.basic;

import android.support.v4.app.Fragment;

public interface FragmentFactory<T extends Fragment> {

    Fragment create();
}
