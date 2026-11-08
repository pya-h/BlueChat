package com.thcplusplus.bluechat;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.thcplusplus.bluechat", "com.thcplusplus.bluechat.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.thcplusplus.bluechat", "com.thcplusplus.bluechat.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.thcplusplus.bluechat.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Serial _vvvvv4 = null;
public static anywheresoftware.b4a.objects.Timer _vvvvv5 = null;
public static anywheresoftware.b4a.sql.SQL _vvvvv6 = null;
public static String _db_name = "";
public static String _db_tbl_name = "";
public static String _db_col_name = "";
public static String _db_col_msg = "";
public anywheresoftware.b4a.objects.ListViewWrapper _lstmessages = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtmessage = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsend = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvv7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvv0 = null;
public static boolean _vvvvvv3 = false;
public anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _vvvvvv6 = null;
public anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _vvvvvv7 = null;
public com.thcplusplus.bluechat.starter _vvvvvvv3 = null;
public com.thcplusplus.bluechat.texts _vvvvvv1 = null;
public com.thcplusplus.bluechat.commands _vvvvvvv1 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
boolean _tablecreated = false;
 //BA.debugLineNum = 147;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 150;BA.debugLine="Activity.LoadLayout(\"frmMain\")";
mostCurrent._activity.LoadLayout("frmMain",mostCurrent.activityBA);
 //BA.debugLineNum = 151;BA.debugLine="lstMessages.Padding = Array As Int(10,10,10,10)";
mostCurrent._lstmessages.setPadding(new int[]{(int) (10),(int) (10),(int) (10),(int) (10)});
 //BA.debugLineNum = 152;BA.debugLine="lblListFirst = lstMessages.TwoLinesLayout.Label";
mostCurrent._vvvvv7 = mostCurrent._lstmessages.getTwoLinesLayout().Label;
 //BA.debugLineNum = 153;BA.debugLine="lblListSecond = lstMessages.TwoLinesLayout.Second";
mostCurrent._vvvvv0 = mostCurrent._lstmessages.getTwoLinesLayout().SecondLabel;
 //BA.debugLineNum = 154;BA.debugLine="lstMessages.TwoLinesLayout.ItemHeight = 55dip";
mostCurrent._lstmessages.getTwoLinesLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
 //BA.debugLineNum = 155;BA.debugLine="lblListFirst.TextSize = 14";
mostCurrent._vvvvv7.setTextSize((float) (14));
 //BA.debugLineNum = 156;BA.debugLine="lblListSecond.TextSize = 12";
mostCurrent._vvvvv0.setTextSize((float) (12));
 //BA.debugLineNum = 157;BA.debugLine="lblListFirst.TextColor = Colors.Black";
mostCurrent._vvvvv7.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 158;BA.debugLine="lblListSecond.TextColor = Colors.Magenta";
mostCurrent._vvvvv0.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 160;BA.debugLine="Activity.AddMenuItem( Texts.GetString(Texts.Selec";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv2 /*byte*/ )),"SelectLanguage");
 //BA.debugLineNum = 161;BA.debugLine="Activity.Title = Texts.GetString(Texts.AppTitle)";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._v5 /*byte*/ )));
 //BA.debugLineNum = 163;BA.debugLine="Dim tableCreated As Boolean";
_tablecreated = false;
 //BA.debugLineNum = 164;BA.debugLine="tableCreated = File.Exists(File.DirInternal, DB_N";
_tablecreated = anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_db_name);
 //BA.debugLineNum = 165;BA.debugLine="sqlite.Initialize( File.DirInternal, DB_NAME, Tru";
_vvvvv6.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_db_name,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 166;BA.debugLine="If tableCreated = False Then";
if (_tablecreated==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 167;BA.debugLine="sqlite.ExecNonQuery(\"create table \" & DB_TBL_NAM";
_vvvvv6.ExecNonQuery("create table "+_db_tbl_name+" ( "+_db_col_name+" text not null, "+_db_col_msg+" text ) ");
 //BA.debugLineNum = 170;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.DatabaseI";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv2 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 174;BA.debugLine="LoadPreviousMsgs";
_vvvvvv2();
 //BA.debugLineNum = 175;BA.debugLine="btHandler.Initialize(\"BluetoothHandler\")";
_vvvvv4.Initialize("BluetoothHandler");
 //BA.debugLineNum = 176;BA.debugLine="appTimer.Initialize(\"appTimer\", 1000)";
_vvvvv5.Initialize(processBA,"appTimer",(long) (1000));
 //BA.debugLineNum = 177;BA.debugLine="appTimer.Enabled = False";
_vvvvv5.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 178;BA.debugLine="btConnected = False";
_vvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 213;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 214;BA.debugLine="If UserClosed And btHandler.IsEnabled And btConne";
if (_userclosed && _vvvvv4.IsEnabled() && _vvvvvv3) { 
 //BA.debugLineNum = 215;BA.debugLine="DisconnectFriend";
_vvvvvv4();
 };
 //BA.debugLineNum = 218;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 183;BA.debugLine="TryToConnect";
_vvvvvv5();
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _apptimer_tick() throws Exception{
String _friensnewmsg = "";
 //BA.debugLineNum = 202;BA.debugLine="Sub appTimer_Tick";
 //BA.debugLineNum = 203;BA.debugLine="If btConnected = True And txtReader.Ready Then";
if (_vvvvvv3==anywheresoftware.b4a.keywords.Common.True && mostCurrent._vvvvvv6.Ready()) { 
 //BA.debugLineNum = 204;BA.debugLine="Dim friensNewMsg As String";
_friensnewmsg = "";
 //BA.debugLineNum = 205;BA.debugLine="friensNewMsg = txtReader.ReadLine";
_friensnewmsg = mostCurrent._vvvvvv6.ReadLine();
 //BA.debugLineNum = 206;BA.debugLine="lstMessages.AddTwoLines(Texts.StrTab & friensNew";
mostCurrent._lstmessages.AddTwoLines(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +_friensnewmsg),BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +mostCurrent._vvvvvv1._vvv7 /*String*/ ));
 //BA.debugLineNum = 208;BA.debugLine="sqlite.ExecNonQuery(\"insert into \" & DB_TBL_NAME";
_vvvvv6.ExecNonQuery("insert into "+_db_tbl_name+" values ( '"+mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,(byte)(Double.parseDouble(mostCurrent._vvvvvv1._vvv7 /*String*/ )))+"', '"+_friensnewmsg+"')");
 };
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _bluetoothhandler_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub BluetoothHandler_Connected(success As Boolean)";
 //BA.debugLineNum = 187;BA.debugLine="If success = True Then";
if (_success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 188;BA.debugLine="txtReader.Initialize(btHandler.InputStream)";
mostCurrent._vvvvvv6.Initialize(_vvvvv4.getInputStream());
 //BA.debugLineNum = 189;BA.debugLine="txtWriter.Initialize(btHandler.OutputStream)";
mostCurrent._vvvvvv7.Initialize(_vvvvv4.getOutputStream());
 //BA.debugLineNum = 190;BA.debugLine="appTimer.Enabled = True";
_vvvvv5.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 191;BA.debugLine="btConnected = True";
_vvvvvv3 = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 192;BA.debugLine="ToastMessageShow( Texts.GetString(Texts.Connecte";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv0 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 195;BA.debugLine="btConnected = False";
_vvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 196;BA.debugLine="appTimer.Enabled = False";
_vvvvv5.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 197;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.CantConne";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv3 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 200;BA.debugLine="End Sub";
return "";
}
public static String  _btnsend_click() throws Exception{
 //BA.debugLineNum = 224;BA.debugLine="Sub btnSend_Click";
 //BA.debugLineNum = 225;BA.debugLine="If CheckTextIsCommand(txtMessage.Text) = False Th";
if (_vvvvvv0(mostCurrent._txtmessage.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 226;BA.debugLine="If btConnected = True Then";
if (_vvvvvv3==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 227;BA.debugLine="Try";
try { //BA.debugLineNum = 228;BA.debugLine="txtWriter.WriteLine(txtMessage.Text)";
mostCurrent._vvvvvv7.WriteLine(mostCurrent._txtmessage.getText());
 //BA.debugLineNum = 230;BA.debugLine="lstMessages.AddTwoLines(Texts.StrTab & txtMess";
mostCurrent._lstmessages.AddTwoLines(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +mostCurrent._txtmessage.getText()),BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv6 /*byte*/ )));
 //BA.debugLineNum = 232;BA.debugLine="sqlite.ExecNonQuery(\"insert into \" & DB_TBL_NA";
_vvvvv6.ExecNonQuery("insert into "+_db_tbl_name+" values ( '"+mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv6 /*byte*/ )+"', '"+mostCurrent._txtmessage.getText()+"')");
 //BA.debugLineNum = 235;BA.debugLine="txtWriter.Flush";
mostCurrent._vvvvvv7.Flush();
 //BA.debugLineNum = 236;BA.debugLine="txtMessage.Text = \"\"";
mostCurrent._txtmessage.setText(BA.ObjectToCharSequence(""));
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 238;BA.debugLine="ToastMessageShow( Texts.GetString(Texts.Sendin";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv4 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 239;BA.debugLine="DisconnectFriend";
_vvvvvv4();
 };
 }else {
 //BA.debugLineNum = 243;BA.debugLine="TryToConnect";
_vvvvvv5();
 };
 };
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _btnsend_longclick() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Sub btnSend_LongClick";
 //BA.debugLineNum = 250;BA.debugLine="DisconnectFriend";
_vvvvvv4();
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static boolean  _vvvvvv0(String _text) throws Exception{
String[] _textarray = null;
 //BA.debugLineNum = 112;BA.debugLine="Private Sub CheckTextIsCommand(text As String) As";
 //BA.debugLineNum = 113;BA.debugLine="Dim textArray() As String = Regex.Split(\" \",text)";
_textarray = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",_text);
 //BA.debugLineNum = 114;BA.debugLine="If textArray(0) = Commands.StartSign Then";
if ((_textarray[(int) (0)]).equals(mostCurrent._vvvvvvv1._vvvv5 /*String*/ )) { 
 //BA.debugLineNum = 115;BA.debugLine="Select textArray(1)";
switch (BA.switchObjectToInt(_textarray[(int) (1)],mostCurrent._vvvvvvv1._vvvv7 /*String*/ ,mostCurrent._vvvvvvv1._vvvv0 /*String*/ )) {
case 0: {
 //BA.debugLineNum = 117;BA.debugLine="If textArray(2) = Commands.ParamaterStartSign";
if ((_textarray[(int) (2)]).equals(mostCurrent._vvvvvvv1._vvvv6 /*String*/ )) { 
 //BA.debugLineNum = 118;BA.debugLine="Try";
try { //BA.debugLineNum = 119;BA.debugLine="Texts.FriendName = textArray(3)";
mostCurrent._vvvvvv1._vvv7 /*String*/  = _textarray[(int) (3)];
 //BA.debugLineNum = 120;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.Frien";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv3 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 121;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 123;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.NameC";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv5 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 124;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 };
 break; }
case 1: {
 //BA.debugLineNum = 129;BA.debugLine="If textArray.Length = 2 Then";
if (_textarray.length==2) { 
 //BA.debugLineNum = 130;BA.debugLine="ShowLanguagesList";
_vvvvvvv2();
 }else if(_textarray.length==4 && (_textarray[(int) (2)]).equals(mostCurrent._vvvvvvv1._vvvv6 /*String*/ )) { 
 //BA.debugLineNum = 132;BA.debugLine="If textArray(3) = Commands.ParamEnglishLangua";
if ((_textarray[(int) (3)]).equals(mostCurrent._vvvvvvv1._vvvvv2 /*String*/ )) { 
 //BA.debugLineNum = 133;BA.debugLine="Texts.AppLanguage = Texts.English";
mostCurrent._vvvvvv1._vvvv2 /*byte*/  = mostCurrent._vvvvvv1._vvv0 /*byte*/ ;
 //BA.debugLineNum = 134;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.AppLa";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv7 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 }else if((_textarray[(int) (3)]).equals(mostCurrent._vvvvvvv1._vvvvv3 /*String*/ )) { 
 //BA.debugLineNum = 137;BA.debugLine="Texts.AppLanguage = Texts.Persian";
mostCurrent._vvvvvv1._vvvv2 /*byte*/  = mostCurrent._vvvvvv1._vvvv1 /*byte*/ ;
 //BA.debugLineNum = 138;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.AppLa";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv7 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 };
 };
 break; }
}
;
 };
 //BA.debugLineNum = 144;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return false;
}
public static String  _vvvvvv4() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Private Sub DisconnectFriend";
 //BA.debugLineNum = 70;BA.debugLine="btHandler.Disconnect";
_vvvvv4.Disconnect();
 //BA.debugLineNum = 71;BA.debugLine="btConnected = False";
_vvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 72;BA.debugLine="appTimer.Enabled = False";
_vvvvv5.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 73;BA.debugLine="txtReader.Close";
mostCurrent._vvvvvv6.Close();
 //BA.debugLineNum = 74;BA.debugLine="txtWriter.Close";
mostCurrent._vvvvvv7.Close();
 //BA.debugLineNum = 76;BA.debugLine="ToastMessageShow( Texts.GetString(Texts.Disconnec";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv1 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 28;BA.debugLine="Private lstMessages As ListView";
mostCurrent._lstmessages = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private txtMessage As EditText";
mostCurrent._txtmessage = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private btnSend As Button";
mostCurrent._btnsend = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim lblListFirst As Label";
mostCurrent._vvvvv7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim lblListSecond As Label";
mostCurrent._vvvvv0 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim btConnected As Boolean";
_vvvvvv3 = false;
 //BA.debugLineNum = 34;BA.debugLine="Dim txtReader As TextReader, txtWriter As TextWri";
mostCurrent._vvvvvv6 = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
mostCurrent._vvvvvv7 = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv2() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _messages = null;
int _i = 0;
 //BA.debugLineNum = 94;BA.debugLine="Private Sub LoadPreviousMsgs";
 //BA.debugLineNum = 95;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.PreviousMe";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv4 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 96;BA.debugLine="Try";
try { //BA.debugLineNum = 97;BA.debugLine="Dim messages As Cursor, i As Int";
_messages = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_i = 0;
 //BA.debugLineNum = 99;BA.debugLine="messages = sqlite.ExecQuery(\"select * from \" & D";
_messages.setObject((android.database.Cursor)(_vvvvv6.ExecQuery("select * from "+_db_tbl_name)));
 //BA.debugLineNum = 101;BA.debugLine="For i = 0 To messages.RowCount-1";
{
final int step5 = 1;
final int limit5 = (int) (_messages.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
 //BA.debugLineNum = 102;BA.debugLine="messages.Position = i";
_messages.setPosition(_i);
 //BA.debugLineNum = 103;BA.debugLine="lstMessages.AddTwoLines( Texts.StrTab & message";
mostCurrent._lstmessages.AddTwoLines(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +_messages.GetString(_db_col_msg)),BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv3 /*String*/ +_messages.GetString(_db_col_name)));
 }
};
 //BA.debugLineNum = 105;BA.debugLine="messages.Close";
_messages.Close();
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 107;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.PreviousM";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv6 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 109;BA.debugLine="ToastMessageShow(Texts.GetString(Texts.PreviousMe";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv5 /*byte*/ )),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _lstmessages_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 257;BA.debugLine="Sub lstMessages_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
texts._process_globals();
commands._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim btHandler As Serial";
_vvvvv4 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 17;BA.debugLine="Dim appTimer As Timer";
_vvvvv5 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 18;BA.debugLine="Dim sqlite As SQL";
_vvvvv6 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 20;BA.debugLine="Dim DB_NAME As String: DB_NAME = \"messages.db\"";
_db_name = "";
 //BA.debugLineNum = 20;BA.debugLine="Dim DB_NAME As String: DB_NAME = \"messages.db\"";
_db_name = BA.__b (new byte[] {101,120,-28,33,114,102,-14,103,39,123,-23}, 276444);
 //BA.debugLineNum = 21;BA.debugLine="Dim DB_TBL_NAME As String : DB_TBL_NAME = \"messag";
_db_tbl_name = "";
 //BA.debugLineNum = 21;BA.debugLine="Dim DB_TBL_NAME As String : DB_TBL_NAME = \"messag";
_db_tbl_name = BA.__b (new byte[] {101,121,44,-40,114,103,58,-98}, 607696);
 //BA.debugLineNum = 22;BA.debugLine="Dim DB_COL_NAME As String : DB_COL_NAME = \"person";
_db_col_name = "";
 //BA.debugLineNum = 22;BA.debugLine="Dim DB_COL_NAME As String : DB_COL_NAME = \"person";
_db_col_name = BA.__b (new byte[] {120,121,-18,101,124,110,-14,49,100,123}, 715905);
 //BA.debugLineNum = 23;BA.debugLine="Dim DB_COL_MSG As String: DB_COL_MSG = \"messagete";
_db_col_msg = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim DB_COL_MSG As String: DB_COL_MSG = \"messagete";
_db_col_msg = BA.__b (new byte[] {101,120,4,100,114,102,18,37,108,103,31}, 219529);
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _selectlanguage_click() throws Exception{
 //BA.debugLineNum = 220;BA.debugLine="Sub SelectLanguage_Click";
 //BA.debugLineNum = 221;BA.debugLine="ShowLanguagesList";
_vvvvvvv2();
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv2() throws Exception{
anywheresoftware.b4a.objects.collections.List _lstlanguages = null;
int _defaultchoice = 0;
int _userlanguagechoice = 0;
 //BA.debugLineNum = 79;BA.debugLine="Private Sub ShowLanguagesList";
 //BA.debugLineNum = 80;BA.debugLine="Dim lstLanguages As List";
_lstlanguages = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 81;BA.debugLine="lstLanguages.Initialize";
_lstlanguages.Initialize();
 //BA.debugLineNum = 82;BA.debugLine="Dim defaultChoice As Int = -1";
_defaultchoice = (int) (-1);
 //BA.debugLineNum = 83;BA.debugLine="If Texts.AppLanguage = Texts.English Then";
if (mostCurrent._vvvvvv1._vvvv2 /*byte*/ ==mostCurrent._vvvvvv1._vvv0 /*byte*/ ) { 
 //BA.debugLineNum = 84;BA.debugLine="defaultChoice = 0";
_defaultchoice = (int) (0);
 }else if(mostCurrent._vvvvvv1._vvvv2 /*byte*/ ==mostCurrent._vvvvvv1._vvvv1 /*byte*/ ) { 
 //BA.debugLineNum = 86;BA.debugLine="defaultChoice = 1";
_defaultchoice = (int) (1);
 };
 //BA.debugLineNum = 88;BA.debugLine="lstLanguages.Add(Texts.GetString(Texts.English))";
_lstlanguages.Add((Object)(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvv0 /*byte*/ )));
 //BA.debugLineNum = 89;BA.debugLine="lstLanguages.Add(Texts.GetString(Texts.Persian))";
_lstlanguages.Add((Object)(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vvvv1 /*byte*/ )));
 //BA.debugLineNum = 90;BA.debugLine="Dim userLanguageChoice As Int = InputList(lstLang";
_userlanguagechoice = anywheresoftware.b4a.keywords.Common.InputList(_lstlanguages,BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv2 /*byte*/ )),_defaultchoice,mostCurrent.activityBA);
 //BA.debugLineNum = 91;BA.debugLine="If userLanguageChoice >= Texts.English And userLa";
if (_userlanguagechoice>=mostCurrent._vvvvvv1._vvv0 /*byte*/  && _userlanguagechoice<=mostCurrent._vvvvvv1._vvvv1 /*byte*/ ) { 
mostCurrent._vvvvvv1._vvvv2 /*byte*/  = (byte) (_userlanguagechoice);};
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv5() throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapdevices = null;
anywheresoftware.b4a.objects.collections.List _lstdevices = null;
int _i = 0;
int _deviceselectresult = 0;
 //BA.debugLineNum = 39;BA.debugLine="Private Sub TryToConnect";
 //BA.debugLineNum = 40;BA.debugLine="If btHandler.IsEnabled = False Then";
if (_vvvvv4.IsEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 41;BA.debugLine="Msgbox(Texts.GetString(Texts.TurnOnBluetoothDesc";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._v7 /*byte*/ )),BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._v6 /*byte*/ )),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 43;BA.debugLine="btHandler.Listen";
_vvvvv4.Listen(processBA);
 //BA.debugLineNum = 45;BA.debugLine="If btConnected = False Then";
if (_vvvvvv3==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 46;BA.debugLine="Dim mapDevices As Map , lstDevices As List";
_mapdevices = new anywheresoftware.b4a.objects.collections.Map();
_lstdevices = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 47;BA.debugLine="lstDevices.Initialize";
_lstdevices.Initialize();
 //BA.debugLineNum = 48;BA.debugLine="mapDevices.Initialize";
_mapdevices.Initialize();
 //BA.debugLineNum = 49;BA.debugLine="mapDevices = btHandler.GetPairedDevices";
_mapdevices = _vvvvv4.GetPairedDevices();
 //BA.debugLineNum = 51;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 52;BA.debugLine="For i = 0 To mapDevices.Size-1";
{
final int step11 = 1;
final int limit11 = (int) (_mapdevices.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 53;BA.debugLine="lstDevices.Add( mapDevices.GetKeyAt(i) )";
_lstdevices.Add(_mapdevices.GetKeyAt(_i));
 }
};
 //BA.debugLineNum = 56;BA.debugLine="Dim deviceSelectResult As Int : deviceSelectRes";
_deviceselectresult = 0;
 //BA.debugLineNum = 56;BA.debugLine="Dim deviceSelectResult As Int : deviceSelectRes";
_deviceselectresult = (int) (-1);
 //BA.debugLineNum = 57;BA.debugLine="deviceSelectResult = InputList(lstDevices, Text";
_deviceselectresult = anywheresoftware.b4a.keywords.Common.InputList(_lstdevices,BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._v0 /*byte*/ )),(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="If deviceSelectResult >= 0 Then";
if (_deviceselectresult>=0) { 
 //BA.debugLineNum = 59;BA.debugLine="btHandler.Connect( mapDevices.Get( lstDevices.";
_vvvvv4.Connect(processBA,BA.ObjectToString(_mapdevices.Get(_lstdevices.Get(_deviceselectresult))));
 }else {
 //BA.debugLineNum = 61;BA.debugLine="btConnected = False";
_vvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 62;BA.debugLine="appTimer.Enabled = False";
_vvvvv5.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 63;BA.debugLine="ToastMessageShow( Texts.GetString(Texts.Select";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._vvvvvv1._vvvv4 /*String*/ (mostCurrent.activityBA,mostCurrent._vvvvvv1._vv1 /*byte*/ )),anywheresoftware.b4a.keywords.Common.True);
 };
 };
 };
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _txtmessage_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Sub txtMessage_TextChanged (Old As String, New As";
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return "";
}
}
