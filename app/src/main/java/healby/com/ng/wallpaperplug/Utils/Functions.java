package healby.com.ng.wallpaperplug.Utils;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import healby.com.ng.wallpaperplug.R;

public class Functions {

    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,fragment)
                .commit();
    }
}
