package com.raphxyz.natif;

import android.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.InputType;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


 
public class RaphxyzNatif extends CordovaPlugin {
 
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final CallbackContext cbContext = callbackContext;
		
		PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
		result.setKeepCallback(true);
		cbContext.sendPluginResult(result);
		
		if(this.cordova.getActivity().isFinishing()) return true;
		
		if("alert".equals(action)){
			alert(args.getString(0), args.getString(1), cbContext);
			return true;
		}
		else if("confirm".equals(action)) {
			confirm(args.getString(0), args.getString(1), cbContext);
			return true;
		}
		else if("prompt".equals(action)) {
			promt(args.getString(0), args.getString(1), cbContext);
			return true;
		}
		else if("promtPassword".equals(action)) {
			promtPassword(args.getString(0), args.getString(1), cbContext);
			return true;
		}
		else if("beep".equals(action)) {
			beep(args.getString(0));
			return true;
		}
		else {
			cbContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
			return false;
		}
	}
 
	private synchronized void alert(final String title, final String content, final CallbackContext cbContext){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		alertDialog.setTitle(title)
		.setMessage(content)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			}
		})
		.show();
	}
	
	private synchronized void confirm(final String title, final String message, final CallbackContext cbContext) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		alertDialog.setTitle(title)
		.setMessage(message)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
			}
		})
		.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, false));
			}
		})
		.show();
    }
	
	private synchronized void promt(final String title, final String message, final CallbackContext cbContext) {
		final EditText promptInput =  new EditText(cordova.getActivity());
		promptInput.setHint("");
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		final JSONObject result = new JSONObject();
		
		alertDialog.setTitle(title)
		.setMessage(message)
		.setCancelable(true)
		.setView(promptInput)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				try {
					result.put("cancel",false);
					result.put("value", promptInput.getText().toString().trim().length()==0 ? "" : promptInput.getText());											
				} 
				catch (JSONException e) { }
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				try {
					result.put("cancel",true);
					result.put("value", null);
				}
				catch (JSONException e) { }
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
			}
		})
		.show();
    }
	
	private synchronized void promtPassword(final String title, final String message, final CallbackContext cbContext) {
		final EditText promptInput =  new EditText(cordova.getActivity());
		promptInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
		promptInput.setHint("");
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		final JSONObject result = new JSONObject();
		
		alertDialog.setTitle(title)
		.setMessage(message)
		.setCancelable(true)
		.setView(promptInput)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				try {
					result.put("cancel",false);
					result.put("value", promptInput.getText().toString().trim().length()==0 ? "" : promptInput.getText());											
				} 
				catch (JSONException e) { }
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				try {
					result.put("cancel",true);
					result.put("value", null);
				}
				catch (JSONException e) { }
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
			}
		})
		.show();
    }
	/**
     * Beep plays the default notification ringtone.
     *
     * @param count     Number of times to play notification
     */
    private void beep(final String c) {
		
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone notification = RingtoneManager.getRingtone(cordova.getActivity().getBaseContext(), ringtone);
				int count = Integer.parseInt(c);
                // If phone is not set to silent mode
                if (notification != null) {
                    for (long i = 0; i < count; ++i) {
                        notification.play();
                        long timeout = 5000;
                        while (notification.isPlaying() && (timeout > 0)) {
                            timeout = timeout - 100;
                            try {
                                Thread.sleep(100);
                            }
							catch (InterruptedException e) { }
                        }
                    }
                }
            }
        });
    }
}
