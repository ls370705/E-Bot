package com.example.e_bot;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.util.Log;

public class SamplesUtils {
	private static Object obj=new Object();
	
	public static void indeterminate(Context context, Handler handler,
			String message, final Runnable runnable,
			OnDismissListener dismissListener) {
		try {
			indeterminateInternal(context, handler, message, runnable,
					dismissListener, true);
		} catch (Exception e) {
		}
	}

	public static void indeterminate(Context context, Handler handler,
			String message, final Runnable runnable,
			OnDismissListener dismissListener, boolean cancelable) {
		try {
			indeterminateInternal(context, handler, message, runnable,
					dismissListener, cancelable);
		} catch (Exception e) {
		}
	}

	private static ProgressDialog createProgressDialog(Context context,
			String message) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setIndeterminate(false);
		dialog.setMessage(message);
		return dialog;
	}

	private static void indeterminateInternal(Context context,
			final Handler handler, String message, final Runnable runnable,
			OnDismissListener dismissListener, boolean cancelable) {
		final ProgressDialog dialog = createProgressDialog(context, message);
		dialog.setCancelable(cancelable);
		if (dismissListener != null) {
			dialog.setOnDismissListener(dismissListener);
		}
		dialog.show();
		new Thread() {
			@Override
			public void run() {
				runnable.run();
				handler.post(new Runnable() {
					public void run() {
						try {
							dialog.dismiss();
						} catch (Exception e) {
						}
					}
				});
			};
		}.start();
	}

	/**
	 * String -> Hex
	 * 
	 * @param s
	 * @return
	 */
	public static String stringToHex(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			if (s4.length() == 1) {
				s4 = '0' + s4;
			}
			str = str + s4 + " ";
		}
		return str;
	}

	/**
	 * Hex -> String
	 * 
	 * @param s
	 * @return
	 */

	/**
	 * Hex -> Byte
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */

	/**
	 * Byte -> Hex
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHex(byte[] bytes, int count) {
		StringBuffer sb = new StringBuffer();
		synchronized (obj) {
			for (int i = 0; i < count; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
//				Log.d("MonitorActivity",i+":"+hex);
				sb.append(hex).append(" ");
			}
		}
		return sb.toString();
	}
	
	public static String hexToAscii(String s){
		String[] array=s.split(" ");
		String[] newArray=array;
		String newString=new String();
		for(int i=0;i<array.length;i++){
			newArray[i]=Integer.toString(Integer.parseInt(array[i],16));
			newString+=" "+newArray[i];
		}
		return newString;
	}
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer(); 
	    String[] chars = value.split(" ");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	        
	    }  
	    return sbu.toString();  
	} 
	public static String trimString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer(); 
	    String[] chars = value.split(" ");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i].trim(),10));
	    }  
	    return sbu.toString();  
	} 
	public static String[] splitString(String value){
		String[] list=value.split(";");
		return list;
	}
}