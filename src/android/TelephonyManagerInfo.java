/* TelephonyManagerInfo Plugin - Egemen Mede - @delipenguen - 24.03.2016 */
package com.egemenmede.telephonymanagerinfo;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat
import android.os.Build;

import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.gsm.GsmCellLocation;
import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.Executors;

import android.provider.Settings;

import android.util.Log;
import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;

public class TelephonyManagerInfo extends CordovaPlugin {

    private static final int PERMISSION_REQUEST_CODE = 1;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        requestPermission();
        if ("getInfo".equals(action)) {
            JSONObject r = new JSONObject();
            r.put("phone", this.getPhoneNumber());
            //r.put("simSerialNumber", this.getSimSerialNumber());
            r.put("simOperatorName", this.getSimOperatorName());
            r.put("simOperator", this.getSimOperator());
            r.put("networkOperatorName", this.getNetworkOperatorName());
            r.put("networkOperator", this.getNetworkOperator());
            r.put("networkCountryIso", this.getNetworkCountryIso());
            r.put("deviceSoftwareVersion", this.getDeviceSoftwareVersion());
            //r.put("deviceId", this.getDeviceId());
            r.put("phoneType", this.getPhoneType());
            r.put("isNetworkRoaming", this.isNetworkRoaming());
            r.put("simState", this.getSimState());
            r.put("networkType", this.getNetworkType());
            r.put("callState", this.getCallState());
            r.put("dataState", this.getDataState());
            r.put("groupIdLevel", this.getGroupIdLevel1());
            r.put("simCountryIso", this.getSimCountryIso());
            //r.put("subscriberId", this.getSubscriberId());
            r.put("voiceMailAlphaTag", this.getVoiceMailAlphaTag());
            r.put("voiceMailNumber", this.getVoiceMailNumber());
            r.put("hasIccCard", this.hasIccCard());
            r.put("dataActivity", this.getDataActivity());
            r.put("signalQuality", this.getSignalQuality());
            
            callbackContext.success(r.toString());
            return true;
        }else if("getCellTowerInfo".equals(action)){
            JSONObject r = new JSONObject();
            r.put("cellTowerInfo", this.getCellTowerInfo());
            callbackContext.success(r.toString());
            return true;
        }else {
            return false;
        }
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    public String getPhoneNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        return number;
    }

    public String getSimSerialNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimSerialNumber();
        return number;
    }

    public String getSimOperatorName(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimOperatorName();
        return number;
    }

    public String getSimOperator(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimOperator();
        return number;
    }

    public String getNetworkOperatorName(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkOperatorName();
        return number;
    }

    public String getNetworkOperator(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkOperator();
        return number;
    }

    public String getNetworkCountryIso(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkCountryIso();
        return number;
    }

    public String getDeviceSoftwareVersion(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getDeviceSoftwareVersion();
        return number;
    }

    public String getDeviceId(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getDeviceId();
        return number;
    }

    public String getPhoneType(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = tm.getPhoneType();
        String returnValue = new String();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                returnValue = new String("CDMA");
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                returnValue = new String("GSM");
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                returnValue = new String("NONE");
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                returnValue = new String("SIP");
                break;
        }
        return returnValue;
    }
    /*
    // This is returning MAX integer value for 4G and 5G at the moment.
    public JSONObject getCellTowerInfo() throws JSONException {      
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        //tm.requestCellInfoUpdate();
       
        JSONObject cellTowerInfo = new JSONObject();
        List<CellInfo> allCellInfo = tm.getAllCellInfo();     
        if (allCellInfo != null) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    cellTowerInfo.put("type", "gsm");
                    cellTowerInfo.put("cellId", cellInfoGsm.getCellIdentity().getCid());
                    cellTowerInfo.put("lac", cellInfoGsm.getCellIdentity().getLac());
                    cellTowerInfo.put("mcc", cellInfoGsm.getCellIdentity().getMcc());
                    cellTowerInfo.put("mnc", cellInfoGsm.getCellIdentity().getMnc());
                    cellTowerInfo.put("signalStrength", cellInfoGsm.getCellSignalStrength().getDbm());
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    cellTowerInfo.put("type", "lte");
                    cellTowerInfo.put("cellId", cellInfoLte.getCellIdentity().getCi());
                    cellTowerInfo.put("tac", cellInfoLte.getCellIdentity().getTac());
                    cellTowerInfo.put("mcc", cellInfoLte.getCellIdentity().getMcc());
                    cellTowerInfo.put("mnc", cellInfoLte.getCellIdentity().getMnc());
                    cellTowerInfo.put("signalStrength", cellInfoLte.getCellSignalStrength().getDbm());
                } else if (cellInfo instanceof CellInfoWcdma) {
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    cellTowerInfo.put("type", "wcdma");
                    cellTowerInfo.put("cellId", cellInfoWcdma.getCellIdentity().getCid());
                    cellTowerInfo.put("lac", cellInfoWcdma.getCellIdentity().getLac());
                    cellTowerInfo.put("mcc", cellInfoWcdma.getCellIdentity().getMcc());
                    cellTowerInfo.put("mnc", cellInfoWcdma.getCellIdentity().getMnc());
                    cellTowerInfo.put("signalStrength", cellInfoWcdma.getCellSignalStrength().getDbm());
                }
            }
        }
        return cellTowerInfo;
    }
    */
    
    public JSONObject getCellTowerInfo() throws JSONException {      
    TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
    //tm.requestCellInfoUpdate();
   
    JSONObject cellTowerInfo = new JSONObject();
    JSONArray neighboringCells = new JSONArray();
    List<CellInfo> allCellInfo = tm.getAllCellInfo();     
    if (allCellInfo != null) {
        for (CellInfo cellInfo : allCellInfo) {
            JSONObject currentCell = new JSONObject();
            if (cellInfo instanceof CellInfoGsm) {
                CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                currentCell.put("type", "gsm");
                currentCell.put("cellId", cellInfoGsm.getCellIdentity().getCid());
                currentCell.put("lac", cellInfoGsm.getCellIdentity().getLac());
                currentCell.put("mcc", cellInfoGsm.getCellIdentity().getMcc());
                currentCell.put("mnc", cellInfoGsm.getCellIdentity().getMnc());
                currentCell.put("signalStrength", cellInfoGsm.getCellSignalStrength().getDbm());
            } else if (cellInfo instanceof CellInfoLte) {
                CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                currentCell.put("type", "lte");
                currentCell.put("cellId", cellInfoLte.getCellIdentity().getCi());
                currentCell.put("tac", cellInfoLte.getCellIdentity().getTac());
                currentCell.put("mcc", cellInfoLte.getCellIdentity().getMcc());
                currentCell.put("mnc", cellInfoLte.getCellIdentity().getMnc());
                currentCell.put("signalStrength", cellInfoLte.getCellSignalStrength().getDbm());
            } else if (cellInfo instanceof CellInfoWcdma) {
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                currentCell.put("type", "wcdma");
                currentCell.put("cellId", cellInfoWcdma.getCellIdentity().getCid());
                currentCell.put("lac", cellInfoWcdma.getCellIdentity().getLac());
                currentCell.put("mcc", cellInfoWcdma.getCellIdentity().getMcc());
                currentCell.put("mnc", cellInfoWcdma.getCellIdentity().getMnc());
                currentCell.put("signalStrength", cellInfoWcdma.getCellSignalStrength().getDbm());
            }
            neighboringCells.put(currentCell);
        }
    }
    cellTowerInfo.put("neighboringCells", neighboringCells);
    return cellTowerInfo;
}

    public String isNetworkRoaming(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        boolean isRoaming = tm.isNetworkRoaming();
        String returnValue = new String();
        if (isRoaming){
            returnValue = new String("YES");
        }else{
            returnValue = new String("NO");
        }
        return returnValue;
    }

    public String getSimState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int simState = tm.getSimState();
        String returnValue = new String();
        switch (simState) {
            case (TelephonyManager.SIM_STATE_ABSENT):
                returnValue = new String("ABSENT");
                break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED):
                returnValue = new String("NETWORK_LOCKED");
                break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED):
                returnValue = new String("PIN_REQUIRED");
                break;
            case (TelephonyManager.SIM_STATE_PUK_REQUIRED):
                returnValue = new String("PUK_REQUIRED");
                break;
            case (TelephonyManager.SIM_STATE_READY):
                returnValue = new String("READY");
                break;
            case (TelephonyManager.SIM_STATE_UNKNOWN):
                returnValue = new String("UNKNOWN");
                break;
        }
        return returnValue;
    }

    public String getNetworkType(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            networkType = tm.getDataNetworkType();
        }else{
            networkType = tm.getNetworkType();
        }
        String returnValue = new String();
        switch (networkType) {
            case (TelephonyManager.NETWORK_TYPE_1xRTT):
                returnValue = new String("1xRTT");
                break;
            case (TelephonyManager.NETWORK_TYPE_CDMA):
                returnValue = new String("CDMA");
                break;
            case (TelephonyManager.NETWORK_TYPE_EDGE):
                returnValue = new String("EDGE");
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_0):
                returnValue = new String("EVDO_0");
                break;
            case (TelephonyManager.NETWORK_TYPE_LTE):
                returnValue = new String("LTE");
                break;
            case (TelephonyManager.NETWORK_TYPE_GPRS):
                returnValue = new String("GPRS");
                break;
            case (TelephonyManager.NETWORK_TYPE_UNKNOWN):
                returnValue = new String("UNKNOWN");
                break;    
        }
        return returnValue;
    }

    public String getCallState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int callState = tm.getCallState();
        String returnValue = new String();
        switch (callState) {
            case (TelephonyManager.CALL_STATE_RINGING):
                returnValue = new String("RINGING");
                break;
            case (TelephonyManager.CALL_STATE_OFFHOOK):
                returnValue = new String("OFFHOOK");
                break;
            case (TelephonyManager.CALL_STATE_IDLE):
                returnValue = new String("IDLE");
                break;
        }
        return returnValue;
    }

    public String getDataState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int dataState = tm.getDataState();
        String returnValue = new String();
        switch (dataState) {
            case (TelephonyManager.DATA_DISCONNECTED):
                returnValue = new String("DISCONNECTED");
                break;
            case (TelephonyManager.DATA_CONNECTING):
                returnValue = new String("CONNECTING");
                break;
            case (TelephonyManager.DATA_CONNECTED):
                returnValue = new String("CONNECTED");
                break;
            case (TelephonyManager.DATA_SUSPENDED):
                returnValue = new String("SUSPENDED");
                break;
        }
        return returnValue;
    }

    public String getGroupIdLevel1(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getGroupIdLevel1();
        return number;
    }

    public String getSimCountryIso(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimCountryIso();
        return number;
    }

    public String getSubscriberId(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSubscriberId();
        return number;
    }

    public String getVoiceMailAlphaTag(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getVoiceMailAlphaTag();
        return number;
    }

    public String getVoiceMailNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getVoiceMailNumber();
        return number;
    }

    public String hasIccCard(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        boolean hasIccCard = tm.hasIccCard();
        String returnValue = new String();
        if (hasIccCard){
            returnValue = new String("TRUE");
        }else{
            returnValue = new String("FALSE");
        }
        return returnValue;
    }

    public String getDataActivity(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int dataActivity = tm.getDataActivity();
        String returnValue = new String();
        switch (dataActivity) {
            case (TelephonyManager.DATA_ACTIVITY_NONE):
                returnValue = new String("NONE");
                break;
            case (TelephonyManager.DATA_ACTIVITY_IN):
                returnValue = new String("IN");
                break;
            case (TelephonyManager.DATA_ACTIVITY_OUT):
                returnValue = new String("OUT");
                break;
            case (TelephonyManager.DATA_ACTIVITY_INOUT):
                returnValue = new String("INOUT");
                break;
            case (TelephonyManager.DATA_ACTIVITY_DORMANT):
                returnValue = new String("DORMANT");
                break;
        }
        return returnValue;
    }

    public JSONObject getSignalQuality() {
        JSONObject jsonQuality = new JSONObject();
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = tm.getAllCellInfo();
        int calidaSignal;
        for (CellInfo cellInfo : cellInfoList) {
            if (cellInfo instanceof CellInfoLte) {
                try {
                    jsonQuality.put("Dbm",String.valueOf(((CellInfoLte)cellInfo).getCellSignalStrength().getDbm()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        jsonQuality.put("Rsrp",String.valueOf(((CellInfoLte)cellInfo).getCellSignalStrength().getRsrp()));
                        jsonQuality.put("Rsrq",String.valueOf(((CellInfoLte)cellInfo).getCellSignalStrength().getRsrq()));
                        jsonQuality.put("Cqi",String.valueOf(((CellInfoLte)cellInfo).getCellSignalStrength().getCqi()));
                        jsonQuality.put("Rssnr",String.valueOf(((CellInfoLte)cellInfo).getCellSignalStrength().getRssnr()));
                    }else{
                        jsonQuality.put("Rsrp","");
                        jsonQuality.put("Rsrq","");
                        jsonQuality.put("Cqi","");
                        jsonQuality.put("Rssnr","");
                    }
                    calidaSignal = ((CellInfoLte) cellInfo).getCellSignalStrength().getLevel();
                    if (calidaSignal == 1) {
                        jsonQuality.put("QualitySignal", new String("Pobre -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 2) {
                        jsonQuality.put("QualitySignal", new String("Moderado -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 3) {
                        jsonQuality.put("QualitySignal", new String("Bueno -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 4) {
                        jsonQuality.put("QualitySignal", new String("Estupendo -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 0) {
                        jsonQuality.put("QualitySignal", new String("Nulo -" + String.valueOf(calidaSignal)));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                
            }else if(cellInfo instanceof CellInfoGsm){
                calidaSignal = ((CellInfoGsm) cellInfo).getCellSignalStrength().getLevel();
                try {
                    jsonQuality.put("Dbm",String.valueOf(((CellInfoGsm)cellInfo).getCellSignalStrength().getDbm()));
                    jsonQuality.put("Rsrp","");
                    jsonQuality.put("Rsrq","");
                    jsonQuality.put("Cqi","");
                    jsonQuality.put("Rssnr","");
                    if (calidaSignal == 1) {
                        jsonQuality.put("QualitySignal", new String("Pobre -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 2) {
                        jsonQuality.put("QualitySignal", new String("Moderado -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 3) {
                        jsonQuality.put("QualitySignal", new String("Bueno -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 4) {
                        jsonQuality.put("QualitySignal", new String("Estupendo -" + String.valueOf(calidaSignal)));
                    } else if (calidaSignal == 0) {
                        jsonQuality.put("QualitySignal", new String("Nulo -" + String.valueOf(calidaSignal)));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
               
            }else if(cellInfo instanceof CellInfoWcdma){
                try {
                    jsonQuality.put("Dbm",String.valueOf(((CellInfoWcdma)cellInfo).getCellSignalStrength().getDbm()));
                    jsonQuality.put("Rsrp","");
                    jsonQuality.put("Rsrq","");
                    jsonQuality.put("Cqi","");
                    jsonQuality.put("Rssnr","");
                    jsonQuality.put("QualitySignal", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonQuality;
    }
    
    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                    this.cordova.getActivity(),
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_NUMBERS,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    },
                    PERMISSION_REQUEST_CODE
            );
        } else {
            ActivityCompat.requestPermissions(
                    this.cordova.getActivity(),
                    new String[] {
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_NUMBERS,
                            Manifest.permission.READ_SMS
                    },
                    PERMISSION_REQUEST_CODE
            );
        }
    }
}
