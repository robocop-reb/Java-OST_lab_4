package sample;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class MonoThreadClientHandler implements Runnable {

    String[] strArray2;
    String[] strArray1;
    public MonoThreadClientHandler(Socket client,String[] strArray2, String[] strArray1) {
        MonoThreadClientHandler.clientDialog = client;
        this.strArray1 = strArray1;
        this.strArray2 = strArray2;

    }
    private static Socket clientDialog;
    @Override
    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");
            System.out.println("DataOutputStream  created");
            String clientCommand = in.readUTF();
            while (!clientCommand.equals("Kill")){
                in.readUTF();
                switch (clientCommand) {
                    case "Merge":
                        ThreadUtils.mergeArray(strArray1,strArray2);
                        break;
                    case "Update":
                        ThreadUtils.updateArray(strArray1,strArray2);
                        ThreadUtils.prtintArray(strArray1,strArray2,out);
                        break;
                    case "Merge n Delete":
                       ThreadUtils.prtintArray(ThreadUtils.mergeuniqueArray(strArray1,strArray2),out);
                        break;
                    case "Select":
                        ThreadUtils.prtintArray(strArray1,strArray2,out);
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
