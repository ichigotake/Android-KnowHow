package net.ichigotake.sandbox.app.fragment.collaboration.basic;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

class TwoColumnContainerImpl implements TwoColumnContainer {

    private final FragmentManager fragmentManager;
    private final String TAG_CONTENT = "two_column_container_content";
    private final String TAG_SUB_CONTENT = "two_column_container_sub_content";

    private TwoColumnContainerImpl(FragmentActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    public static TwoColumnContainer get(FragmentActivity activity) {
        return new TwoColumnContainerImpl(activity);
    }

    public void setContent(int containerId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, TAG_CONTENT)
                .commit();
    }

    public void setSubContent(int containerId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, TAG_SUB_CONTENT)
                .commit();
    }

    @Override
    public void requestForSubContent() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_SUB_CONTENT);
        if (fragment != null && fragment instanceof Pane) {
            ((Pane) fragment).tap();
        }
    }

    @Override
    public void requestForContent() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_CONTENT);
        if (fragment != null && fragment instanceof Pane) {
            ((Pane) fragment).tap();
        }
    }

    public static TwoColumnContainerImpl create(FragmentActivity activity, int layoutResourceId) {
        activity.setContentView(layoutResourceId);
        return new TwoColumnContainerImpl(activity);
    }
}