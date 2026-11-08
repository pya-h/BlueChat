package com.thcplusplus.bluechat;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class texts {
private static texts mostCurrent = new texts();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static byte _v5 = (byte)0;
public static byte _v6 = (byte)0;
public static byte _v7 = (byte)0;
public static byte _v0 = (byte)0;
public static byte _vv1 = (byte)0;
public static byte _vv2 = (byte)0;
public static byte _vv3 = (byte)0;
public static byte _vv4 = (byte)0;
public static byte _vv5 = (byte)0;
public static byte _vv6 = (byte)0;
public static byte _vv7 = (byte)0;
public static byte _vv0 = (byte)0;
public static byte _vvv1 = (byte)0;
public static byte _vvv2 = (byte)0;
public static byte _vvv3 = (byte)0;
public static byte _vvv4 = (byte)0;
public static byte _vvv5 = (byte)0;
public static byte _vvv6 = (byte)0;
public static String _vvv7 = "";
public static byte _vvv0 = (byte)0;
public static byte _vvvv1 = (byte)0;
public static byte _vvvv2 = (byte)0;
public static String _vvvv3 = "";
public com.thcplusplus.bluechat.main _vvvvvvv4 = null;
public com.thcplusplus.bluechat.starter _vvvvvvv3 = null;
public com.thcplusplus.bluechat.commands _vvvvvvv1 = null;
public static String  _vvvv4(anywheresoftware.b4a.BA _ba,byte _index) throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub GetString(Index As Byte) As String";
 //BA.debugLineNum = 25;BA.debugLine="Select Index";
switch (BA.switchObjectToInt(_index,_v5,_v6,_v7,_v0,_vv1,_vv3,_vv4,_vv0,_vvv1,_vvv6,_vv2,_vvv0,_vvvv1,_vvv3,_vv5,_vv7,_vvv2,_vvv5,_vvv5,_vv6)) {
case 0: {
 //BA.debugLineNum = 27;BA.debugLine="Return \"BlueChat\"";
if (true) return "BlueChat";
 break; }
case 1: {
 //BA.debugLineNum = 29;BA.debugLine="If AppLanguage = Persian Then Return \"بلوتوث رو";
if (_vvvv2==_vvvv1) { 
if (true) return "بلوتوث روشن نیست";};
 //BA.debugLineNum = 30;BA.debugLine="Return \"Bluetooth is off!\"";
if (true) return "Bluetooth is off!";
 break; }
case 2: {
 //BA.debugLineNum = 32;BA.debugLine="If AppLanguage = Persian Then Return \"لطفا ابتد";
if (_vvvv2==_vvvv1) { 
if (true) return "لطفا ابتدا بلوتوث را روشن کنید.";};
 //BA.debugLineNum = 33;BA.debugLine="Return \"Please turn the bluetooth on first.\"";
if (true) return "Please turn the bluetooth on first.";
 break; }
case 3: {
 //BA.debugLineNum = 35;BA.debugLine="If AppLanguage = Persian Then Return \"یکی از دس";
if (_vvvv2==_vvvv1) { 
if (true) return "یکی از دستگاه ها را انتخاب کنید";};
 //BA.debugLineNum = 36;BA.debugLine="Return \"Select a Device\"";
if (true) return "Select a Device";
 break; }
case 4: {
 //BA.debugLineNum = 38;BA.debugLine="If AppLanguage = Persian Then Return \"یک دستگاه";
if (_vvvv2==_vvvv1) { 
if (true) return "یک دستگاه باید برای اتصال انتخاب شود!";};
 //BA.debugLineNum = 39;BA.debugLine="Return \"A device must be select to connect!\"";
if (true) return "A device must be select to connect!";
 break; }
case 5: {
 //BA.debugLineNum = 41;BA.debugLine="If AppLanguage = Persian Then Return \"هنگام اتص";
if (_vvvv2==_vvvv1) { 
if (true) return "هنگام اتصال مشکلی پیش آمده! به بلوچت قادر به اتصال به دستگاه موردنظر نیست.";};
 //BA.debugLineNum = 42;BA.debugLine="Return \"Something went wrong while connecting!";
if (true) return "Something went wrong while connecting! BlueChat cannot connect to the selected device.";
 break; }
case 6: {
 //BA.debugLineNum = 44;BA.debugLine="If AppLanguage = Persian Then Return \"هنگام ارس";
if (_vvvv2==_vvvv1) { 
if (true) return "هنگام ارسال پیام مشکلی پیش امد! در حال قطع اتصال...";};
 //BA.debugLineNum = 45;BA.debugLine="Return \"Something went wrong while sending! Dis";
if (true) return "Something went wrong while sending! Disconnecting...";
 break; }
case 7: {
 //BA.debugLineNum = 47;BA.debugLine="If AppLanguage = Persian Then Return \"متصل شدید";
if (_vvvv2==_vvvv1) { 
if (true) return "متصل شدید";};
 //BA.debugLineNum = 48;BA.debugLine="Return \"Connected\"";
if (true) return "Connected";
 break; }
case 8: {
 //BA.debugLineNum = 50;BA.debugLine="If AppLanguage = Persian Then Return \"قطع اتصال";
if (_vvvv2==_vvvv1) { 
if (true) return "قطع اتصال شد";};
 //BA.debugLineNum = 51;BA.debugLine="Return \"Disconnected\"";
if (true) return "Disconnected";
 break; }
case 9: {
 //BA.debugLineNum = 53;BA.debugLine="If AppLanguage = Persian Then Return \"شما\"";
if (_vvvv2==_vvvv1) { 
if (true) return "شما";};
 //BA.debugLineNum = 54;BA.debugLine="Return \"You\"";
if (true) return "You";
 break; }
case 10: {
 //BA.debugLineNum = 56;BA.debugLine="Return \"Language/زبان\"";
if (true) return "Language/زبان";
 break; }
case 11: {
 //BA.debugLineNum = 58;BA.debugLine="If AppLanguage = Persian Then Return \"انگلیسی\"";
if (_vvvv2==_vvvv1) { 
if (true) return "انگلیسی";};
 //BA.debugLineNum = 59;BA.debugLine="Return  \"English\"";
if (true) return "English";
 break; }
case 12: {
 //BA.debugLineNum = 61;BA.debugLine="If AppLanguage = Persian Then Return \"فارسی\"";
if (_vvvv2==_vvvv1) { 
if (true) return "فارسی";};
 //BA.debugLineNum = 62;BA.debugLine="Return \"Persian\"";
if (true) return "Persian";
 break; }
case 13: {
 //BA.debugLineNum = 64;BA.debugLine="If AppLanguage = Persian Then Return \"نام دوست:";
if (_vvvv2==_vvvv1) { 
if (true) return "نام دوست: "+_vvv7;};
 //BA.debugLineNum = 65;BA.debugLine="Return \"Friend Name: \" & FriendName";
if (true) return "Friend Name: "+_vvv7;
 break; }
case 14: {
 //BA.debugLineNum = 67;BA.debugLine="If AppLanguage = Persian Then Return \"هنگام تغی";
if (_vvvv2==_vvvv1) { 
if (true) return "هنگام تغییر نام  مشکلی رخ داد...لطفا دوباره تلاش کنید.";};
 //BA.debugLineNum = 68;BA.debugLine="Return \"Error occured in changing your friend n";
if (true) return "Error occured in changing your friend name...try again please.";
 break; }
case 15: {
 //BA.debugLineNum = 70;BA.debugLine="If AppLanguage = Persian Then Return \"زبان برنا";
if (_vvvv2==_vvvv1) { 
if (true) return "زبان برنامه : فارسی";};
 //BA.debugLineNum = 71;BA.debugLine="Return \"App Language : English\"";
if (true) return "App Language : English";
 break; }
case 16: {
 //BA.debugLineNum = 73;BA.debugLine="If AppLanguage = Persian Then Return \"دیتابیس ب";
if (_vvvv2==_vvvv1) { 
if (true) return "دیتابیس برای ذخیره ی پیام ها اماده سازی شد";};
 //BA.debugLineNum = 74;BA.debugLine="Return \"Database initialized for saving message";
if (true) return "Database initialized for saving messages";
 break; }
case 17: {
 //BA.debugLineNum = 76;BA.debugLine="If AppLanguage = Persian Then Return \"بارگزاری";
if (_vvvv2==_vvvv1) { 
if (true) return "بارگزاری پیام های پیشین آغاز شد...";};
 //BA.debugLineNum = 77;BA.debugLine="Return \"Loading previous messages started...\"";
if (true) return "Loading previous messages started...";
 break; }
case 18: {
 //BA.debugLineNum = 79;BA.debugLine="If AppLanguage = Persian Then Return \"همه ی پیا";
if (_vvvv2==_vvvv1) { 
if (true) return "همه ی پیام های پیشین بارگزاری شدند";};
 //BA.debugLineNum = 80;BA.debugLine="Return \"Previous messages loaded\"";
if (true) return "Previous messages loaded";
 break; }
case 19: {
 //BA.debugLineNum = 82;BA.debugLine="If AppLanguage = Persian Then Return \"هنگام بار";
if (_vvvv2==_vvvv1) { 
if (true) return "هنگام بارگزاری پیام های قبلی خطایی اتفاق افتاد!";};
 //BA.debugLineNum = 83;BA.debugLine="Return \"Loading previous messages went wrong!\"";
if (true) return "Loading previous messages went wrong!";
 break; }
}
;
 //BA.debugLineNum = 85;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Dim AppTitle As Byte = 100, TurnOnBluetoothTitle";
_v5 = (byte) (100);
_v6 = (byte) (101);
_v7 = (byte) (102);
_v0 = (byte) (103);
_vv1 = (byte) (104);
_vv2 = (byte) (105);
 //BA.debugLineNum = 7;BA.debugLine="Dim CantConnectToDeviceError As Byte = 120 ,Sendi";
_vv3 = (byte) (120);
_vv4 = (byte) (121);
_vv5 = (byte) (122);
_vv6 = (byte) (123);
 //BA.debugLineNum = 10;BA.debugLine="Dim AppLanguageReport As Byte = 130, ConnectedRep";
_vv7 = (byte) (130);
_vv0 = (byte) (131);
_vvv1 = (byte) (132);
_vvv2 = (byte) (133);
_vvv3 = (byte) (134);
_vvv4 = (byte) (135);
_vvv5 = (byte) (136);
 //BA.debugLineNum = 14;BA.debugLine="Dim YourName As Byte = 50";
_vvv6 = (byte) (50);
 //BA.debugLineNum = 15;BA.debugLine="Dim FriendName As String = \"friend\"";
_vvv7 = BA.__b (new byte[] {110,111,-49,-22,125,101}, 304597);
 //BA.debugLineNum = 18;BA.debugLine="Dim English As Byte = 0, Persian As Byte = 1";
_vvv0 = (byte) (0);
_vvvv1 = (byte) (1);
 //BA.debugLineNum = 20;BA.debugLine="Dim AppLanguage As Byte = English";
_vvvv2 = _vvv0;
 //BA.debugLineNum = 21;BA.debugLine="Dim StrTab As String = \"    \"";
_vvvv3 = BA.__b (new byte[] {40,63,32,97}, 923239);
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
}
