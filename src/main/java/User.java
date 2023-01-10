//Importing files
import java.io.FileWriter;
import java.io.IOException;

//Class User_Info
class User extends Login {
    //Variable Money
    int money;

    //Constructor to create file username.txt and to write initial values in it
    User(String name, String username, String password, String number,String transaction,String FilePath) {
        super(name, username, password, number);
        this.money = 0;
        try {
            FileWriter fWrite = new FileWriter(FilePath + this.getUsername() + ".txt",true);
            fWrite.write(this.money + "\r\n" + this.getName() + "\r\n" + this.getUsername() + "\r\n"+ this.getPassword() + "\r\n" + this.getNumber() + "\r\n"+transaction+"\r\n");
            fWrite.close();
            // System.out.println("Content is successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
        }
    }

    //Message that will be shown on successful registration
    void message() {
        System.out.println("USER GENERATED SUCCESFULLY !");
    }
}
