package sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadUtils {
    public static String[] mergeArray(String[] firstArray, String[] secondArray) {
        int fal = firstArray.length;        //determines length of firstArray
        int sal = secondArray.length;   //determines length of secondArray
        String[] result = new String[fal + sal];  //resultant array of size first array and second array
        System.arraycopy(firstArray, 0, result, 0, fal);
        System.arraycopy(secondArray, 0, result, fal, sal);
        return result;
    }

    public static void updateArray(String[] firstArray, String[] secondArray) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("which array you want to edit FIRST or SECOND, or type QUIT to stop updating arrays");
        String command = "";
        while (!command.equalsIgnoreCase("quit")){
            command = br.readLine();
            if (command.equalsIgnoreCase("FIRST")) {
                System.out.println("This array has " + firstArray.length + " elements which one you want to change?");
                int index = Integer.parseInt(br.readLine());
                System.out.println("What do you want to write?");
                firstArray[index] = br.readLine();
            } else if (command.equalsIgnoreCase("SECOND")) {
                System.out.println("This array has " + secondArray.length + " elements which one you want to change?");
                int index = Integer.parseInt(br.readLine());
                System.out.println("What do you want to write?");
                secondArray[index] = br.readLine();
            }
        }

    }

    public static String[] mergeuniqueArray(String[] firstArray, String[] secondArray) {
        List<String> result =  Stream.concat(Stream.of(firstArray), Stream.of(secondArray))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        String[] test = result.toArray(new String[result.size()]);
        return test;
    }
    public static void prtintArray(String[] firstArray, String[] secondArray, DataOutputStream out) throws IOException {
        String one = Arrays.toString(firstArray);
        String two = Arrays.toString(secondArray);
        out.writeUTF(one+" "+two);
    }
    public static void prtintArray(String[] firstArray, DataOutputStream out) throws IOException {
        String one = Arrays.toString(firstArray);
        out.writeUTF(one);
    }





}
