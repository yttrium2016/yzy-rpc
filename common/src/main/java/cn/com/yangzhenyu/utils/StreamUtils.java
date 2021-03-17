package cn.com.yangzhenyu.utils;

import java.io.*;

/**
 * @author yzy
 */
public class StreamUtils {

    public static void putObject(ObjectOutputStream outputStream, Object obj) throws IOException {
        if (outputStream != null) {
            outputStream.writeObject(obj);
            outputStream.flush();
        }
    }

    public static Object getObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        if (inputStream != null) {
            return inputStream.readObject();
        }
        return null;
    }

    public static void close(InputStream stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(OutputStream stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
