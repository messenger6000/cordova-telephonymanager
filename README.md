
Cordova Plugin TelephonyManagerInfo
=================================

Plugin nativo Android haciendo uso de la clase `TelephonyManager`.

Se encuentra los siguientes atributos;

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

Puede ver el detalle de Telephony Manager en la web

http://developer.android.com/reference/android/telephony/TelephonyManager.html

Usos
=================================

Instalación
```
cordova plugin add cordova-plugin-telephonymanagerinfo
```
Vía GitHub
```
cordova plugin add https://github.com/GPeraltP/cordova-plugin-telephonymanagerinfo
```
Remover
```
cordova plugin remove cordova-plugin-telephonymanagerinfo
```

Atributos
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
Permisos
=================================


```
android.permission.READ_PHONE_STATE
android.permission.ACCESS_FINE_LOCATION
android.permission.ACCESS_BACKGROUND_LOCATION (Android Q)
```
Plataforma
=================================
```
- Android
```
