package kr.nectarine.uaspangyo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.fragment.MemberListFragment;


public class MainActivity extends Activity implements ShakeDetector.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MemberListFragment())
                    .commit();
        }

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

    }

    @Override
    public void hearShake() {
        Toast.makeText(this, getString(R.string.pizza_please), Toast.LENGTH_SHORT).show();
    }
}
