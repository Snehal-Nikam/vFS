

package io.github.snehal.logging;

import io.github.snehal.constants.GlobalConstants;
import io.github.snehal.utils.NativeHelperUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Logger implements Serializable {

    // Implementing Singleton Design Pattern
    private static Logger _instance = null;

    private FileWriter _writer;
    private BufferedWriter _buffer;

    private String path;

    private Logger() {
        path = NativeHelperUtils.getUserHomeDirectory() + GlobalConstants.pathEscape + GlobalConstants.logPath +
                GlobalConstants.pathEscape + GlobalConstants.libName + NativeHelperUtils.getDateOrTime(true) +
                NativeHelperUtils.getDateOrTime(false) + GlobalConstants.logFileExtension;
        try {
            _buffer = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (_instance == null) {
            _instance = new Logger();
        }
        return _instance;
    }

    public void Finish() {
        try {
            _buffer.flush();
            _buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogError(String error) {
        try {
            _buffer.write(GlobalConstants.ErrorString + error + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogDebug(String debug) {
        try {
            _buffer.write(GlobalConstants.DebugString + debug + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
