B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=8.3
@EndOfDesignText@

Sub Process_Globals
	' Titles & Descriptions
	Dim AppTitle As Byte = 100, TurnOnBluetoothTitle As Byte = 101 , TurnOnBluetoothDescription As Byte = 102,SelectDeviceTitle As Byte = 103, SelectDeviceDescription As Byte = 104,SelectLanguage As Byte = 105
	
	' Errors
	Dim CantConnectToDeviceError As Byte = 120 ,SendingWentWrongDisconnectingError As Byte = 121, NameChangeFailedError As Byte = 122, PreviousMessagesLoadingFailedError As Byte = 123
	
	' Reports
	Dim AppLanguageReport As Byte = 130, ConnectedReport As Byte = 131, DisconnectedReport As Byte = 132, DatabaseInitializedReport As Byte = 133, _
	 FriendNameChangedReport As Byte = 134, PreviousMessageLoadingStartedReport As Byte = 135, PreviousMessageLoadedReport As Byte = 136
	
	' Names
	Dim YourName As Byte = 50
	Dim FriendName As String = "friend"
	
	'Languages
	Dim English As Byte = 0, Persian As Byte = 1
	
	Dim AppLanguage As Byte = English
	Dim StrTab As String = "    "
End Sub

Sub GetString(Index As Byte) As String
	Select Index
		Case AppTitle
			Return "BlueChat"
		Case TurnOnBluetoothTitle
			If AppLanguage = Persian Then Return "بلوتوث روشن نیست"
			Return "Bluetooth is off!"
		Case TurnOnBluetoothDescription
			If AppLanguage = Persian Then Return "لطفا ابتدا بلوتوث را روشن کنید."
			Return "Please turn the bluetooth on first."
		Case SelectDeviceTitle
			If AppLanguage = Persian Then Return "یکی از دستگاه ها را انتخاب کنید"
			Return "Select a Device"
		Case SelectDeviceDescription
			If AppLanguage = Persian Then Return "یک دستگاه باید برای اتصال انتخاب شود!"
			Return "A device must be select to connect!"
		Case CantConnectToDeviceError
			If AppLanguage = Persian Then Return "هنگام اتصال مشکلی پیش آمده! به بلوچت قادر به اتصال به دستگاه موردنظر نیست."
			Return "Something went wrong while connecting! BlueChat cannot connect to the selected device."
		Case SendingWentWrongDisconnectingError
			If AppLanguage = Persian Then Return "هنگام ارسال پیام مشکلی پیش امد! در حال قطع اتصال..."
			Return "Something went wrong while sending! Disconnecting..."
		Case ConnectedReport
			If AppLanguage = Persian Then Return "متصل شدید"
			Return "Connected"
		Case DisconnectedReport
			If AppLanguage = Persian Then Return "قطع اتصال شد"
			Return "Disconnected"
		Case YourName
			If AppLanguage = Persian Then Return "شما"
			Return "You"
		Case SelectLanguage
			Return "Language/زبان"
		Case English
			If AppLanguage = Persian Then Return "انگلیسی"
			Return  "English"
		Case Persian
			If AppLanguage = Persian Then Return "فارسی"
			Return "Persian"
		Case FriendNameChangedReport
			If AppLanguage = Persian Then Return "نام دوست: " & FriendName
			Return "Friend Name: " & FriendName
		Case NameChangeFailedError
			If AppLanguage = Persian Then Return "هنگام تغییر نام  مشکلی رخ داد...لطفا دوباره تلاش کنید."
			Return "Error occured in changing your friend name...try again please."
		Case AppLanguageReport
			If AppLanguage = Persian Then Return "زبان برنامه : فارسی"
			Return "App Language : English"
		Case DatabaseInitializedReport
			If AppLanguage = Persian Then Return "دیتابیس برای ذخیره ی پیام ها اماده سازی شد"
			Return "Database initialized for saving messages"
		Case PreviousMessageLoadedReport
			If AppLanguage = Persian Then Return "بارگزاری پیام های پیشین آغاز شد..."
			Return "Loading previous messages started..."
		Case PreviousMessageLoadedReport
			If AppLanguage = Persian Then Return "همه ی پیام های پیشین بارگزاری شدند"
			Return "Previous messages loaded"
		Case PreviousMessagesLoadingFailedError
			If AppLanguage = Persian Then Return "هنگام بارگزاری پیام های قبلی خطایی اتفاق افتاد!"
			Return "Loading previous messages went wrong!"
	End Select
	Return ""
End Sub

