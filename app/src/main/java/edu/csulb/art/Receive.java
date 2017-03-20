package edu.csulb.art;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by pinks on 3/16/2017.
 */

public class Receive extends BroadcastReceiver {

    NotificationCompat.Builder builder;
    private static final int uniqueID = 132;
    Context ctx;

    public void shownotification() {

        try {
            Intent intent = new Intent(ctx, MainActivity.class);
            PendingIntent pending = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            builder = new NotificationCompat.Builder(ctx);
            builder.setAutoCancel(true);

            builder.setSmallIcon(R.drawable.draw);
            builder.setTicker("Time to draw");
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("Art App");
            builder.setContentText("Reminder to draw");


            builder.setContentIntent(pending);

            NotificationManager nm = (NotificationManager) ctx.getSystemService(NOTIFICATION_SERVICE);
            nm.notify(uniqueID, builder.build());

        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        this.ctx = context;
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {

            shownotification();

        }
    }
}
