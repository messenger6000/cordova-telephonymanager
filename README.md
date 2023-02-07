This repository is forked from https://github.com/GPeraltP/cordova-plugin-telephonymanagerinfo 

Adding additional functionality from telephonymanager to scan for cell towers

Cordova Plugin TelephonyManagerInfo
=================================

Native Android plugin using the TelephonyManager class.

The following attributes can be found:

- phone
- simSerialNumber
- simOperatorName
- simOperator
- networkOperatorName
- networkOperator
- networkCountryIso
- deviceSoftwareVersion
- deviceId
- phoneType
- isNetworkRoaming
- simState
- networkType
- callState
- dataState
- groupIdLevel
- simCountryIso
- subscriberId
- voiceMailAlphaTag
- voiceMailNumber
- hasIccCard
- dataActivity

You can see the details of the Telephony Manager on the web

http://developer.android.com/reference/android/telephony/TelephonyManager.html

Usages
=================================

Installation
```
cordova plugin add cordova-plugin-telephonymanagerinfo
```
Via GitHub
```
cordova plugin add https://github.com/GPeraltP/cordova-plugin-telephonymanagerinfo
```
Remove
```
cordova plugin remove cordova-plugin-telephonymanagerinfo
```

Attributes
=================================
```
TelephonyManagerInfo.phone
TelephonyManagerInfo.simSerialNumber
TelephonyManagerInfo.simOperatorName
TelephonyManagerInfo.simOperator
TelephonyManagerInfo.networkOperatorName
TelephonyManagerInfo.networkOperator
TelephonyManagerInfo.networkCountryIso
TelephonyManagerInfo.deviceSoftwareVersion
TelephonyManagerInfo.deviceId
TelephonyManagerInfo.phoneType
TelephonyManagerInfo.isNetworkRoaming
TelephonyManagerInfo.simState
TelephonyManagerInfo.networkType
TelephonyManagerInfo.callState
TelephonyManagerInfo.dataState
TelephonyManagerInfo.groupIdLevel
TelephonyManagerInfo.simCountryIso
TelephonyManagerInfo.subscriberId
TelephonyManagerInfo.voiceMailAlphaTag
TelephonyManagerInfo.voiceMailNumber
TelephonyManagerInfo.hasIccCard
TelephonyManagerInfo.dataActivity
```
Permissions
=================================


```
android.permission.READ_PHONE_STATE
android.permission.ACCESS_FINE_LOCATION
android.permission.ACCESS_BACKGROUND_LOCATION (Android Q)
```
Platform
=================================
```
- Android
```
