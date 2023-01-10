import java.io.FileWriter;
import java.io.IOException;

class User extends Login {
    int money;

    User(String name, String username, String password, String number,String transaction,String FilePath) {
        super(name, username, password, number);
        this.money = 0;
        try {
            FileWriter fWrite = new FileWriter(FilePath + this.getUsername() + ".txt",true);
            fWrite.write(this.money + "\r\n" + this.getName() + "\r\n" + this.getUsername() + "\r\n"+ this.getPassword() + "\r\n" + this.getNumber() + "\r\n"+transaction+"\r\n");
            fWrite.close();
        } catch (IOException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
        }
    }

    void message() {
        System.out.println("USER GENERATED SUCCESFULLY !");
    }
}
