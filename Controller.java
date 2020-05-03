
package sample;

import java.net.*;
import java.io.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    private static Socket clientSocket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    public Button select;
    public TextField portNum;
    public TextField ipField;
    public TextField numInput;
    public TextArea resultArea;

    public void handleConnectButtonClick() throws IOException {
        int port = Integer.parseInt(portNum.getText());
        String address = ipField.getText();
        clientSocket = new Socket(address, port);
    }

    public void handleSelectButtonClick() throws IOException {
        output = new DataOutputStream(Controller.clientSocket.getOutputStream());
        output.writeUTF("Select");
        getText();
    }
    public void handleMergeButtonClick() throws IOException {
        output = new DataOutputStream(Controller.clientSocket.getOutputStream());
        output.writeUTF("Merge n Delete");
        getText();
    }
    public void handleUpdateButtonClick() throws IOException {
        output = new DataOutputStream(Controller.clientSocket.getOutputStream());
        output.writeUTF("Update");
        getText();
    }

    public void getText() throws IOException {
        input = new DataInputStream(Controller.clientSocket.getInputStream());
        resultArea.setText(input.readUTF());
    }
}
