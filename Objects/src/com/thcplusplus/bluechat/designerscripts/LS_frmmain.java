package com.thcplusplus.bluechat.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_frmmain{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="lstMessages.Left = 2%x"[frmMain/320x480,scale=1]
views.get("lstmessages").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 3;BA.debugLine="lstMessages.Top = 2%y"[frmMain/320x480,scale=1]
views.get("lstmessages").vw.setTop((int)((2d / 100 * height)));
//BA.debugLineNum = 4;BA.debugLine="lstMessages.Width = 96%x"[frmMain/320x480,scale=1]
views.get("lstmessages").vw.setWidth((int)((96d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="lstMessages.Height = 85%y"[frmMain/320x480,scale=1]
views.get("lstmessages").vw.setHeight((int)((85d / 100 * height)));
//BA.debugLineNum = 7;BA.debugLine="txtMessage.Left = 2%x"[frmMain/320x480,scale=1]
views.get("txtmessage").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 8;BA.debugLine="txtMessage.Top = lstMessages.Bottom + 2%y"[frmMain/320x480,scale=1]
views.get("txtmessage").vw.setTop((int)((views.get("lstmessages").vw.getTop() + views.get("lstmessages").vw.getHeight())+(2d / 100 * height)));
//BA.debugLineNum = 9;BA.debugLine="txtMessage.Width = 85%x"[frmMain/320x480,scale=1]
views.get("txtmessage").vw.setWidth((int)((85d / 100 * width)));
//BA.debugLineNum = 10;BA.debugLine="txtMessage.Height = 11%y"[frmMain/320x480,scale=1]
views.get("txtmessage").vw.setHeight((int)((11d / 100 * height)));
//BA.debugLineNum = 12;BA.debugLine="btnSend.Left = txtMessage.Right + 1%x"[frmMain/320x480,scale=1]
views.get("btnsend").vw.setLeft((int)((views.get("txtmessage").vw.getLeft() + views.get("txtmessage").vw.getWidth())+(1d / 100 * width)));
//BA.debugLineNum = 13;BA.debugLine="btnSend.Top = lstMessages.Bottom + 3%y"[frmMain/320x480,scale=1]
views.get("btnsend").vw.setTop((int)((views.get("lstmessages").vw.getTop() + views.get("lstmessages").vw.getHeight())+(3d / 100 * height)));
//BA.debugLineNum = 14;BA.debugLine="btnSend.Width = 10%x"[frmMain/320x480,scale=1]
views.get("btnsend").vw.setWidth((int)((10d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="btnSend.Height = 8%y"[frmMain/320x480,scale=1]
views.get("btnsend").vw.setHeight((int)((8d / 100 * height)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}