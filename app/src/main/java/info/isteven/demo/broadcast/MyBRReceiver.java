package info.isteven.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyBRReceiver extends BroadcastReceiver {
    public MyBRReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"网络状态发生改变~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
