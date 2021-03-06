/**
 * Copyright (C) 2013- Iordan Iordanov
 *
 * This is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
 * USA.
 */


package com.undatech.opaque;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;

public class ConnectionSettings implements Connection, Serializable {
    private static final String TAG = "ConnectionSettings";
    private static final long serialVersionUID = 1L;
    
    private String filename = "";
    private String connectionType = "";
    private String hostname = "";
    private String vmname = "";
    private String user = "";
    private String password = "";
    private String otpCode = "";
    private String inputMethod = "DirectSwipePan";
    private boolean rotationEnabled = true;
    private boolean requestingNewDisplayResolution = true;
    private boolean audioPlaybackEnabled = false;
    private boolean usingCustomOvirtCa = false;
    private boolean sslStrict = true;
    private boolean usbEnabled = true;

    private String ovirtCaFile = "";
    private String ovirtCaData = "";
    private String layoutMap = "";
    private String scaleMode = "";

    private int extraKeysToggleType = RemoteClientLibConstants.EXTRA_KEYS_ON;
    
    public ConnectionSettings(String filename) {
        super();
        this.filename = filename;
    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public void setNickname(String nickname) {

    }

    public String getConnectionTypeString() {
        return connectionType;
    }
    public void setConnectionTypeString(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public int getConnectionType() {
        return 0;
    }

    @Override
    public void setConnectionType(int connectionType) {
    }

    @Override
    public String getInputMode() {
        return getInputMethod();
    }

    @Override
    public void setInputMode(String inputMode) {
        setInputMethod(inputMode);
    }

    public String getInputMethod() {
        return inputMethod;
    }
    
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod;
    }
    
    public int getExtraKeysToggleType() {
        return extraKeysToggleType;
    }
    
    public void setExtraKeysToggleType(int extraKeysToggleType) {
        this.extraKeysToggleType = extraKeysToggleType;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public String getVmname() {
        return vmname.trim();
    }
    
    public void setVmname(String vmname) {
        this.vmname = vmname;
    }

    @Override
    public String getUserName() {
        return getUser();
    }

    @Override
    public void setUserName(String user) {
        setUser(user);
    }

    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isRotationEnabled() {
        return rotationEnabled;
    }

    public void setRotationEnabled(boolean rotationEnabled) {
        this.rotationEnabled = rotationEnabled;
    }

    public boolean isRequestingNewDisplayResolution() {
        return requestingNewDisplayResolution;
    }

    public void setRequestingNewDisplayResolution(
            boolean requestingNewDisplayResolution) {
        this.requestingNewDisplayResolution = requestingNewDisplayResolution;
    }

    public boolean isAudioPlaybackEnabled() {
        return audioPlaybackEnabled;
    }

    public void setAudioPlaybackEnabled(boolean audioPlaybackEnabled) {
        this.audioPlaybackEnabled = audioPlaybackEnabled;
    }

    public boolean isUsingCustomOvirtCa() {
        return usingCustomOvirtCa;
    }

    public void setUsingCustomOvirtCa(boolean useCustomCa) {
        this.usingCustomOvirtCa = useCustomCa;
    }

    public boolean isSslStrict() {
        return sslStrict;
    }

    public void setSslStrict(boolean sslStrict) {
        this.sslStrict = sslStrict;
    }
    
    public boolean isUsbEnabled() {
        return usbEnabled;
    }

    public void setUsbEnabled(boolean usbEnabled) {
        this.usbEnabled = usbEnabled;
    }

    public String getOvirtCaFile() {
        return ovirtCaFile;
    }

    public void setOvirtCaFile(String ovirtCaFile) {
        this.ovirtCaFile = ovirtCaFile;
    }

    public String getOvirtCaData() {
        return ovirtCaData;
    }

    public void setOvirtCaData(String ovirtCaData) {
        this.ovirtCaData = ovirtCaData;
    }

    public String getLayoutMap() {
        return layoutMap;
    }

    public void setLayoutMap(String layoutMap) {
        this.layoutMap = layoutMap;
    }

    @Override
    public void saveAndWriteRecent(boolean saveEmpty, Context c) {
        save(c);
    }

    public void save(Context context) {
        this.saveToSharedPreferences(context);
    }

    public void saveToSharedPreferences(Context context) {
        android.util.Log.d(TAG, "Saving settings to file: " + filename);
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("connectionType", connectionType);
        editor.putString("hostname", hostname);
        editor.putString("vmname", vmname);
        editor.putString("user", user);
        editor.putString("password", password);
        editor.putInt("extraKeysToggleType", extraKeysToggleType);
        editor.putString("inputMethod", inputMethod);
        editor.putBoolean("rotationEnabled", rotationEnabled);
        editor.putBoolean("requestingNewDisplayResolution", requestingNewDisplayResolution);
        editor.putBoolean("audioEnabled", audioPlaybackEnabled);
        editor.putBoolean("usingCustomCa", usingCustomOvirtCa);
        editor.putBoolean("sslStrict", sslStrict);
        editor.putBoolean("usbEnabled", usbEnabled);
        editor.putString("ovirtCaData", ovirtCaData);
        editor.putString("layoutMap", layoutMap);
        editor.putString("scaleMode", scaleMode);
        editor.apply();
        // Make sure the CA gets saved to a file if necessary.
        ovirtCaFile = saveCaToFile (context, ovirtCaData);
    }

    public void load(Context context) {
        loadFromSharedPreferences(context);
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public void setPort(int port) {

    }

    @Override
    public int getTlsPort() {
        return 0;
    }

    @Override
    public void setTlsPort(int port) {

    }

    @Override
    public ImageView.ScaleType getScaleMode() {
        return ImageView.ScaleType.valueOf(scaleMode);
    }

    @Override
    public void setScaleMode(ImageView.ScaleType value) {
        scaleMode = value.toString();
    }

    @Override
    public int getRdpResType() {
        return 0;
    }

    @Override
    public void setRdpResType(int rdpResType) {

    }

    @Override
    public boolean getFollowMouse() {
        return false;
    }

    @Override
    public void setFollowMouse(boolean followMouse) {

    }

    @Override
    public boolean getFollowPan() {
        return false;
    }

    @Override
    public void setFollowPan(boolean followPan) {

    }

    @Override
    public long getLastMetaKeyId() {
        return 0;
    }

    @Override
    public void setLastMetaKeyId(long lastMetaKeyId) {

    }

    @Override
    public boolean getUseDpadAsArrows() {
        return false;
    }

    @Override
    public void setUseDpadAsArrows(boolean useDpadAsArrows) {

    }

    @Override
    public String getColorModel() {
        return null;
    }

    @Override
    public void setColorModel(String colorModel) {

    }

    @Override
    public boolean getRotateDpad() {
        return false;
    }

    @Override
    public void setRotateDpad(boolean rotateDpad) {

    }

    @Override
    public String getIdHash() {
        return null;
    }

    @Override
    public void setIdHash(String idHash) {

    }

    @Override
    public int getIdHashAlgorithm() {
        return 0;
    }

    @Override
    public void setIdHashAlgorithm(int idHashAlgorithm) {

    }

    @Override
    public int getPrefEncoding() {
        return 0;
    }

    @Override
    public void setPrefEncoding(int prefEncoding) {

    }

    @Override
    public boolean getUseRepeater() {
        return false;
    }

    @Override
    public void setUseRepeater(boolean useRepeater) {

    }

    @Override
    public String getRepeaterId() {
        return null;
    }

    @Override
    public void setRepeaterId(String repeaterId) {

    }

    public void loadFromSharedPreferences(Context context) {
        android.util.Log.d(TAG, "Loading settings from file: " + filename);
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        connectionType = sp.getString("connectionType", "").trim();
        hostname = sp.getString("hostname", "").trim();
        vmname = sp.getString("vmname", "").trim();
        user = sp.getString("user", "").trim();
        password = sp.getString("password", "");
        loadAdvancedSettings (context, filename);
    }

    public void loadAdvancedSettings (Context context, String file) {
        SharedPreferences sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        extraKeysToggleType = sp.getInt("extraKeysToggleType", RemoteClientLibConstants.EXTRA_KEYS_ON);
        inputMethod = sp.getString("inputMethod", "DirectSwipePan").trim();
        audioPlaybackEnabled = sp.getBoolean("audioEnabled", false);
        rotationEnabled = sp.getBoolean("rotationEnabled", true);
        requestingNewDisplayResolution = sp.getBoolean("requestingNewDisplayResolution", true);
        usingCustomOvirtCa = sp.getBoolean("usingCustomCa", false);
        sslStrict = sp.getBoolean("sslStrict", true);
        usbEnabled = sp.getBoolean("usbEnabled", true);
        ovirtCaData = sp.getString("ovirtCaData", "").trim();
        layoutMap = sp.getString("layoutMap", RemoteClientLibConstants.DEFAULT_LAYOUT_MAP).trim();
        scaleMode = sp.getString("scaleMode", ImageView.ScaleType.MATRIX.toString()).trim();
        // Make sure the CAs get saved to files if necessary.
        ovirtCaFile = saveCaToFile (context, ovirtCaData);
    }
    
    /**
     * Saves provided CA to a file if it doesn't exist already, and returns file name
     * if it was saved or it already exists. Returns "" if caCertData is empty or an error
     * occurred.
     * @param caCertData
     * @return
     */
    public String saveCaToFile (Context context, String caCertData) {
        String fileName = "";
        if (!caCertData.equals("")) {
            // Write out CA to file if it doesn't exist.
            try {
                // Write out a unique file containing the cert and return the path.
                fileName = context.getFilesDir() + "/ca" + Integer.toString(caCertData.hashCode()) + ".crt";
                File file = new File(fileName);
                if (!file.exists()) {
                    android.util.Log.e(TAG, "Writing out CA to file: " + fileName);
                    PrintWriter fout = new PrintWriter(fileName);
                    fout.println(caCertData);
                    fout.close();
                } else {
                    android.util.Log.e(TAG, "File already exists: " + fileName);
                }
            } catch (FileNotFoundException e) {
                fileName = "";
            }
        }
        return fileName;
    }

    @Override
    public void populateFromContentValues(ContentValues values) {

    }

    @Override
    public boolean isReadyForConnection() {
        return true;
    }

    @Override
    public void parseFromUri(Uri dataUri) {

    }

    @Override
    public boolean isReadyToBeSaved() {
        return false;
    }

    @Override
    public String getCaCert() {
        return null;
    }

    @Override
    public void setCaCert(String caCert) {

    }

    @Override
    public String getCaCertPath() {
        return null;
    }

    @Override
    public void setCaCertPath(String caCertPath) {

    }

    @Override
    public String getCertSubject() {
        return null;
    }

    @Override
    public void setCertSubject(String certSubject) {

    }

    @Override
    public String getRdpDomain() {
        return null;
    }

    @Override
    public void setRdpDomain(String rdpDomain) {

    }

    @Override
    public int getRdpWidth() {
        return 0;
    }

    @Override
    public void setRdpWidth(int rdpWidth) {

    }

    @Override
    public int getRdpHeight() {
        return 0;
    }

    @Override
    public void setRdpHeight(int rdpHeight) {

    }

    @Override
    public int getRdpColor() {
        return 0;
    }

    @Override
    public void setRdpColor(int rdpColor) {

    }

    @Override
    public boolean getRemoteFx() {
        return false;
    }

    @Override
    public void setRemoteFx(boolean remoteFx) {

    }

    @Override
    public boolean getDesktopBackground() {
        return false;
    }

    @Override
    public void setDesktopBackground(boolean desktopBackground) {

    }

    @Override
    public boolean getFontSmoothing() {
        return false;
    }

    @Override
    public void setFontSmoothing(boolean fontSmoothing) {

    }

    @Override
    public boolean getDesktopComposition() {
        return false;
    }

    @Override
    public void setDesktopComposition(boolean desktopComposition) {

    }

    @Override
    public boolean getWindowContents() {
        return false;
    }

    @Override
    public void setWindowContents(boolean windowContents) {

    }

    @Override
    public boolean getMenuAnimation() {
        return false;
    }

    @Override
    public void setMenuAnimation(boolean menuAnimation) {

    }

    @Override
    public boolean getVisualStyles() {
        return false;
    }

    @Override
    public void setVisualStyles(boolean visualStyles) {

    }

    @Override
    public boolean getRedirectSdCard() {
        return false;
    }

    @Override
    public void setRedirectSdCard(boolean redirectSdCard) {

    }

    @Override
    public boolean getConsoleMode() {
        return false;
    }

    @Override
    public void setConsoleMode(boolean consoleMode) {

    }

    @Override
    public boolean getEnableSound() {
        return false;
    }

    @Override
    public void setEnableSound(boolean enableSound) {

    }

    @Override
    public boolean getEnableRecording() {
        return false;
    }

    @Override
    public void setEnableRecording(boolean enableRecording) {

    }

    @Override
    public int getRemoteSoundType() {
        return 0;
    }

    @Override
    public void setRemoteSoundType(int remoteSoundType) {

    }

    @Override
    public boolean getViewOnly() {
        return false;
    }

    @Override
    public void setViewOnly(boolean viewOnly) {

    }

    @Override
    public long getForceFull() {
        return 0;
    }

    @Override
    public void setForceFull(long forceFull) {

    }

    @Override
    public int getUseLocalCursor() {
        return 0;
    }

    @Override
    public void setUseLocalCursor(int useLocalCursor) {

    }

    @Override
    public String getSshServer() {
        return null;
    }

    @Override
    public void setSshServer(String sshServer) {

    }

    @Override
    public int getSshPort() {
        return 0;
    }

    @Override
    public void setSshPort(int sshPort) {

    }

    @Override
    public String getSshUser() {
        return null;
    }

    @Override
    public void setSshUser(String sshUser) {

    }

    @Override
    public String getSshPassword() {
        return null;
    }

    @Override
    public void setSshPassword(String sshPassword) {

    }

    @Override
    public boolean getKeepSshPassword() {
        return false;
    }

    @Override
    public void setKeepSshPassword(boolean keepSshPassword) {

    }

    @Override
    public String getSshPubKey() {
        return null;
    }

    @Override
    public void setSshPubKey(String sshPubKey) {

    }

    @Override
    public String getSshPrivKey() {
        return null;
    }

    @Override
    public void setSshPrivKey(String sshPrivKey) {

    }

    @Override
    public String getSshPassPhrase() {
        return null;
    }

    @Override
    public void setSshPassPhrase(String sshPassPhrase) {

    }

    @Override
    public boolean getUseSshPubKey() {
        return false;
    }

    @Override
    public void setUseSshPubKey(boolean useSshPubKey) {

    }

    @Override
    public int getSshRemoteCommandOS() {
        return 0;
    }

    @Override
    public void setSshRemoteCommandOS(int sshRemoteCommandOS) {

    }

    @Override
    public int getSshRemoteCommandType() {
        return 0;
    }

    @Override
    public void setSshRemoteCommandType(int sshRemoteCommandType) {

    }

    @Override
    public int getAutoXType() {
        return 0;
    }

    @Override
    public void setAutoXType(int autoXType) {

    }

    @Override
    public String getAutoXCommand() {
        return null;
    }

    @Override
    public void setAutoXCommand(String autoXCommand) {

    }

    @Override
    public boolean getAutoXEnabled() {
        return false;
    }

    @Override
    public void setAutoXEnabled(boolean autoXEnabled) {

    }

    @Override
    public int getAutoXResType() {
        return 0;
    }

    @Override
    public void setAutoXResType(int autoXResType) {

    }

    @Override
    public int getAutoXWidth() {
        return 0;
    }

    @Override
    public void setAutoXWidth(int autoXWidth) {

    }

    @Override
    public int getAutoXHeight() {
        return 0;
    }

    @Override
    public void setAutoXHeight(int autoXHeight) {

    }

    @Override
    public String getAutoXSessionProg() {
        return null;
    }

    @Override
    public void setAutoXSessionProg(String autoXSessionProg) {

    }

    @Override
    public int getAutoXSessionType() {
        return 0;
    }

    @Override
    public void setAutoXSessionType(int autoXSessionType) {

    }

    @Override
    public boolean getAutoXUnixpw() {
        return false;
    }

    @Override
    public void setAutoXUnixpw(boolean autoXUnixpw) {

    }

    @Override
    public boolean getAutoXUnixAuth() {
        return false;
    }

    @Override
    public void setAutoXUnixAuth(boolean autoXUnixAuth) {

    }

    @Override
    public String getAutoXRandFileNm() {
        return null;
    }

    @Override
    public void setAutoXRandFileNm(String autoXRandFileNm) {

    }

    @Override
    public String getSshRemoteCommand() {
        return null;
    }

    @Override
    public void setSshRemoteCommand(String sshRemoteCommand) {

    }

    @Override
    public int getSshRemoteCommandTimeout() {
        return 0;
    }

    @Override
    public void setSshRemoteCommandTimeout(int sshRemoteCommandTimeout) {

    }

    @Override
    public boolean getUseSshRemoteCommand() {
        return false;
    }

    @Override
    public void setUseSshRemoteCommand(boolean useSshRemoteCommand) {

    }

    @Override
    public String getSshHostKey() {
        return null;
    }

    @Override
    public void setSshHostKey(String sshHostKey) {

    }

    @Override
    public long getMetaListId() {
        return 0;
    }

    @Override
    public void setMetaListId(long metaListId) {

    }

    /**
     * Exports preferences to a file.
     * @param context
     * @param connections space separated list of connections
     * @param externalFileName file to save to
     * @return the full path to the file saved.
     * @throws JSONException 
     * @throws IOException 
     */
    public static String exportPrefsToFile(Context context, String connections, String externalFileName) throws JSONException, IOException {
        android.util.Log.d(TAG, "Exporting settings to file: " + externalFileName);
        connections += " " + RemoteClientLibConstants.DEFAULT_SETTINGS_FILE;
        String[] preferenceFiles = connections.split(" ");
        JSONObject allPrefs = new JSONObject();
        
        for (String file: preferenceFiles) {
            SharedPreferences sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
            JSONObject prefs = new JSONObject(sp.getAll());
            prefs.put("password", "");
            allPrefs.put(file, prefs);
        }

        File exportFile = new File(externalFileName);

        PrintWriter writer = new PrintWriter(new FileWriter(exportFile));
        writer.print(allPrefs.toString());
        writer.close();
        return exportFile.getPath();
    }


    /**
     * Imports preferences from a file.
     * @param context
     * @param externalFileName full path to file to load from
     * @return connection list as a space separated string
     * @throws IOException 
     * @throws JSONException 
     */
    public static String importPrefsFromFile(Context context, String externalFileName) throws IOException, JSONException {
        android.util.Log.d(TAG, "Importing settings from file: " + externalFileName);
        File importFile = new File(externalFileName);
        String connections = "";
        InputStream is = new FileInputStream(importFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        JSONObject allPrefs = new JSONObject(sb.toString());
        Iterator<String> allPrefsItr = allPrefs.keys();
        while(allPrefsItr.hasNext()) {
            String file = allPrefsItr.next();
            SharedPreferences sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
            Editor editor = sp.edit();

            JSONObject settings = allPrefs.getJSONObject(file);

            Iterator<String> keysItr = settings.keys();
            while(keysItr.hasNext()) {
                String key = keysItr.next();
                Object value = settings.get(key);

                if(value instanceof String) {
                    editor.putString(key, (String)value);
                } else if(value instanceof Integer) {
                    editor.putInt(key, (int)((Integer)value));
                } else if(value instanceof Boolean) {
                    editor.putBoolean(key, (boolean)((Boolean)value));
                } else if(value instanceof Float) {
                    editor.putFloat(key, (float)((Float)value));
                } else if(value instanceof Long) {
                    editor.putLong(key, (long)((Long)value));
                }
            }
            if (!file.equals(RemoteClientLibConstants.DEFAULT_SETTINGS_FILE)) {
                connections += " " + file;
            }
            editor.apply();
        }

        return connections.trim();
    }
}
