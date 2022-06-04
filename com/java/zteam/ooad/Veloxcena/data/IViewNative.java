package data;

import com.sun.jna.Library;
import com.sun.jna.Structure;

import java.util.List;
import java.util.Arrays;

interface IViewNative extends Library {
  public static final int RET_SUCCESS = 1, // Intended functionality has been fulfilled

      // RET_SUCCESS 1
      // RET_NO_VALID_DATA 2
      // RET_CALIBRATION_ABORTED 3
      // RET_SERVER_IS_RUNNING 4
      // RET_CALIBRATION_NOT_IN_PROGRESS 5
      // RET_WINDOW_IS_OPEN 11
      // RET_WINDOW_IS_CLOSED 12
      //
      ERR_COULD_NOT_CONNECT = 100,
      // ERR_NOT_CONNECTED 101
      // ERR_NOT_CALIBRATED 102
      // ERR_NOT_VALIDATED 103
      ERR_EYETRACKING_APPLICATION_NOT_RUNNING = 104,
      // ERR_WRONG_COMMUNICATION_PARAMETER 105
      // ERR_WRONG_DEVICE 111
      ERR_WRONG_PARAMETER = 112,
      // ERR_WRONG_CALIBRATION_METHOD 113
      // ERR_CALIBRATION_TIMEOUT 114
      // ERR_TRACKING_NOT_STABLE 115
      // ERR_INSUFFICIENT_BUFFER_SIZE 116
      // ERR_CREATE_SOCKET 121
      // ERR_CONNECT_SOCKET 122
      // ERR_BIND_SOCKET 123
      // ERR_DELETE_SOCKET 124
      // ERR_NO_RESPONSE_FROM_IVIEWX 131
      // ERR_INVALID_IVIEWX_VERSION 132
      // ERR_WRONG_IVIEWX_VERSION 133
      // ERR_ACCESS_TO_FILE 171
      // ERR_SOCKET_CONNECTION 181
      // ERR_EMPTY_DATA_BUFFER 191
      // ERR_RECORDING_DATA_BUFFER 192
      // ERR_FULL_DATA_BUFFER 193
      // ERR_IVIEWX_IS_NOT_READY 194
      // ERR_PAUSED_DATA_BUFFER 195
      ERR_IVIEWX_NOT_FOUND = 201;
  // ERR_IVIEWX_PATH_NOT_FOUND 202
  // ERR_IVIEWX_ACCESS_DENIED 203
  // ERR_IVIEWX_ACCESS_INCOMPLETE 204
  // ERR_IVIEWX_OUT_OF_MEMORY 205
  // ERR_MULTIPLE_DEVICES 206
  // ERR_CAMERA_NOT_FOUND 211
  // ERR_WRONG_CAMERA 212
  // ERR_WRONG_CAMERA_PORT 213
  // ERR_USB2_CAMERA_PORT 214
  // ERR_USB3_CAMERA_PORT 215
  // ERR_COULD_NOT_OPEN_PORT 220
  // ERR_COULD_NOT_CLOSE_PORT 221
  // ERR_AOI_ACCESS 222
  // ERR_AOI_NOT_DEFINED 223
  // ERR_FEATURE_NOT_LICENSED 250
  // ERR_DEPRECATED_FUNCTION 300
  // ERR_INITIALIZATION 400
  // ERR_FUNC_NOT_LOADED 401

  public static class EyeData extends Structure {
    public static class ByReference extends EyeData implements Structure.ByReference {
    }

    protected List<String> getFieldOrder() {
      return Arrays.asList(new String[] {
          "gazeX",
          "gazeY",
          "pupilDiameter",
          "eyePositionX",
          "eyePositionY",
          "eyePositionZ",
      });
    }

    public double gazeX;
    public double gazeY;
    public double pupilDiameter; // pixel/mm
    public double eyePositionX;
    public double eyePositionY;
    public double eyePositionZ;
  }

  public static class CalibrationStruct extends Structure {
    public static class ByReference extends EyeData implements Structure.ByReference {
    }

    protected List<String> getFieldOrder() {
      return Arrays.asList(new String[] {
          "method",
          "visualization",
          "displayDevice",
          "speed",
          "autoAccept",
          "foregroundColor",
          "backgroundColor",
          "targetShape",
          "targetSize",
          "targetFilename",
      });
    }

    public int method;
    public int visualization;
    public int displayDevice;
    public int speed;
    public int autoAccept;
    public int foregroundColor;
    public int backgroundColor;
    public int targetShape;
    public int targetSize;
    public String targetFilename;
  }

  public static class SampleData extends Structure {
    public static class ByReference extends SampleData implements Structure.ByReference {
    }

    protected List<String> getFieldOrder() {
      return Arrays.asList(new String[] {
          "timestamp",
          "leftEye",
          "rightEye",
          "planeNumber",
      });
    }

    public long timestamp;
    public EyeData leftEye;
    public EyeData rightEye;
    public int planeNumber;
  }

  public int iV_Connect(String sendIP, int sendPort, String receiveIP, int receivePort);

  public int iV_SetLogger(int logLevel, String fileName);

  public int iV_SetupCalibration(CalibrationStruct pCalibrationStruct);

  public int iV_Calibrate();

  public int iV_Disconnect();

  public int iV_GetSample(SampleData pSampleData);
}
