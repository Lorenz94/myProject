package net.thumbtack.school.old.file;

import com.google.gson.Gson;
import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;
import net.thumbtack.school.old.figures.v3.Point2D;
import net.thumbtack.school.old.figures.v3.Rectangle;
import net.thumbtack.school.old.ttschool.Trainee;
import net.thumbtack.school.old.ttschool.TrainingErrorCode;
import net.thumbtack.school.old.ttschool.TrainingException;

import java.io.*;

public class FileService {

    private static Gson gson = new Gson();

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException{
        writeByteArrayToBinaryFile(fileFromString(fileName), array);
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException{
        checkFileForNull(file);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(array);
        }
    }


    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(fileFromString(fileName));
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        checkFileForNull(file);

        byte[] array = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(array);
        }
        return array;
    }


    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        byte[] byteArray;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write(array);
            byteArray = baos.toByteArray();
        }
        byte[] result = new byte[byteArray.length / 2];
        try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) bais.read();
                bais.skip(1);
            }
        }
        return result;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(fileFromString(fileName), array);
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        checkFileForNull(file);

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(fileFromString(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        checkFileForNull(file);
        byte[] array = new byte[(int) file.length()];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            bis.read(array);
        }
        return array;
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file,true))) {
            dos.writeInt(rect.getxLeft());
            dos.writeInt(rect.getyTop());
            dos.writeInt(rect.getxRight());
            dos.writeInt(rect.getyBottom());
        }
    }

    public static Rectangle  readRectangleFromBinaryFile(File file) throws ColorException, IOException {
        checkFileForNull(file);
        Rectangle rect;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            rect = new Rectangle(dis.readInt(),dis.readInt(),dis.readInt(),dis.readInt(),Color.RED);
        }
        return rect;
    }

    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) throws IOException {
        checkFileForNull(file);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file,true))) {
            for (Rectangle rect : rects) {
                dos.writeInt(rect.getxLeft());
                dos.writeInt(rect.getyTop());
                dos.writeInt(rect.getxRight());
                dos.writeInt(rect.getyBottom());
            }
        }
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException, ColorException {
        checkFileForNull(file);
        Rectangle[] rectArray = new Rectangle[(int)(file.length()/16)];
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            for (int i = rectArray.length - 1; 0 <= i; i--) {
                raf.seek(i*16);
                rectArray[(rectArray.length-1)- i] = new Rectangle(raf.readInt(),raf.readInt(),raf.readInt(),raf.readInt(),Color.RED);
            }
        }
        return rectArray;
    }

    public static void  writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        checkFileForNull(file);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(rect.getxLeft())).append(" ").append(String.valueOf(rect.getyTop())).append(" ").
                append(String.valueOf(rect.getxRight())).append(" ").append(String.valueOf(rect.getyBottom()));

            bw.write(sb.toString());
        }
    }

    public static Rectangle  readRectangleFromTextFileOneLine(File file) throws IOException, ColorException {
        checkFileForNull(file);
        Rectangle rect;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            String[] array = line.split(" ");
            Point2D leftTop = new Point2D(Integer.valueOf(array[0]),Integer.valueOf(array[1]));
            Point2D rightBottom = new Point2D(Integer.valueOf(array[2]),Integer.valueOf(array[3]));
            rect = new Rectangle(leftTop,rightBottom,Color.RED);
        }
        return rect;
    }

    public static void  writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        checkFileForNull(file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(rect.getxLeft()));
            writer.newLine();
            writer.write(String.valueOf(rect.getyTop()));
            writer.newLine();
            writer.write(String.valueOf(rect.getxRight()));
            writer.newLine();
            writer.write(String.valueOf(rect.getyBottom()));
        }
    }

    public static Rectangle  readRectangleFromTextFileFourLines(File file) throws IOException, ColorException {
        checkFileForNull(file);
        Rectangle rect;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Point2D leftTop = new Point2D(Integer.valueOf(reader.readLine()),Integer.valueOf(reader.readLine()));
            Point2D rightBottom = new Point2D(Integer.valueOf(reader.readLine()),Integer.valueOf(reader.readLine()));
            rect = new Rectangle(leftTop,rightBottom,Color.RED);
        }
        return rect;
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException, TrainingException{
        checkFileForNull(file);
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            StringBuilder sb = new StringBuilder();
            sb.append(trainee.getFirstName()).append(" ").append(trainee.getLastName()).append(" ").append(String.valueOf(trainee.getRating()));
            writer.write(sb.toString());
        }catch (NumberFormatException e){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING, e);
        }

    }

    public static Trainee  readTraineeFromTextFileOneLine(File file) throws TrainingException, IOException {
        checkFileForNull(file);
        Trainee trainee;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
            String line = reader.readLine();
            String[] array = line.split(" ");
            trainee = new Trainee(array[0],array[1],Integer.valueOf(array[2]));
        }catch (NumberFormatException e){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING, e);
        }
        return trainee;
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException{
        checkFileForNull(file);
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println(trainee.getFirstName());
            writer.println(trainee.getLastName());
            writer.println(String.valueOf(trainee.getRating()));
        }
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        checkFileForNull(file);
        Trainee trainee;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
            trainee = new Trainee(reader.readLine(),reader.readLine(),Integer.valueOf(reader.readLine()));
        }catch (NumberFormatException e){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING, e);
        }
        return trainee;
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        checkFileForNull(file);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        }
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        checkFileForNull(file);
        Trainee trainee;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            trainee = (Trainee) ois.readObject();
        }
        return trainee;
    }

    public static String  serializeTraineeToJsonString(Trainee trainee){
        return gson.toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json){
        return gson.fromJson(json, Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        checkFileForNull(file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(trainee,writer);
        }
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        checkFileForNull(file);
        Trainee trainee;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            trainee = gson.fromJson(reader,Trainee.class);
        }
        return trainee;
    }

    private static File fileFromString(String fileName) {
        if (fileName == null || fileName.equals("")) throw new NullPointerException();
        return new File(fileName);
    }

    private static void checkFileForNull(File file){
        if(file == null) throw new NullPointerException("File can't be null");
    }
}
