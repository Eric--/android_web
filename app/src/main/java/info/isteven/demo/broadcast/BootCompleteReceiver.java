package info.isteven.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/17.
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "com.example.broadcasttest.MY_BROADCAST";

    public BootCompleteReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION_BOOT.equals(intent.getAction()))
            Toast.makeText(context, "收到告白啦~",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
