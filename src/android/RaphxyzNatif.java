package com.raphxyz.natif;

import android.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
			final String content = args.getString(0);
			alert(content, cbContext);
			return true;
		}
		else if("confirm".equals(action)) {
			confirm(args.getString(0), cbContext);
			return true;
		}
		else {
			cbContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
			return false;
		}
	}
 
	private synchronized void alert(final String content, final CallbackContext cbContext){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		alertDialog.setTitle("Alert")
		.setMessage(content)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			}
		})
		.show();
	}
	
	private synchronized void confirm(final String message, final CallbackContext cbContext) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		
		alertDialog.setTitle("Confirmation")
		.setMessage(message)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
				cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, false));
			}
		})
		.show();
    }
}