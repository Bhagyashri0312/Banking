
import java.io.BufferedReader;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String FilePath = "./";
        boolean b = true;
        while (b) {
            System.out.println("\n-----WELCOME TO SimpleBanking-----");
            System.out.println("1. Register New User");
            System.out.println("2. Login for existing user");
            System.out.println("3. Exit Program");
            System.out.print("Enter Choice : ");
            int choice = 3;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            }
            if (choice == 1) {
                System.out.println("Enter Name : ");
                sc.nextLine();
                String name = sc.nextLine();

                System.out.println("Enter Username : ");
                String username = sc.nextLine();

                File file = new File(FilePath + username + ".txt");
                    if (file.exists()) {
                        System.out.println("Username Already Taken !");
                        System.out.println("\nPress enter to continue");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                    }
                    System.out.println("Enter Password : ");
                    String password=sc.nextLine();

                    System.out.println("Enter Phone Number : ");
                    String number = sc.nextLine();

                    User reg = new User(name, username, password, number, "TRANSACTION:0", FilePath);
                    reg.message();

                    System.out.println("\nPress enter to continue");
                    try {
                        System.in.read();
                    } catch (Exception e) {}

                }
                else if (choice == 2) {
                    System.out.println();
                    System.out.println("Enter Username : ");
                    sc.nextLine();
                    String username = sc.nextLine();

                    System.out.println("Enter password");
                    String password = sc.next();

                    boolean bool = true;

                    File file = new File(FilePath + username + ".txt");
                    // If login successful
                    if (file.exists()) {
                        try {
                            Scanner dataReader = new Scanner(file);
                            String money = dataReader.nextLine();
                            int loginMoney = Integer.parseInt(money);
                            String loginName = dataReader.nextLine();
                            String loginUserName = dataReader.nextLine();
                            String loginPassword = dataReader.nextLine();
                            String number = dataReader.nextLine();
                            String loginTransaction = dataReader.nextLine();
                            if (username.equals(loginUserName) && password.equals(loginPassword)) {
                                boolean bo = true;
                                while (bo) {
                                    System.out.println("Welcome " + loginName + " !");
                                    System.out.println("\n**** OPERATIONS ****");
                                    System.out.println("1. Deposit Money");
                                    System.out.println("2. Withdraw Money");
                                    System.out.println("3. View Details/Balance");
                                    System.out.println("4. View Transaction History");
                                    System.out.println("5. Transfer to other account");
                                    System.out.println("6. Logout ");
                                    System.out.print("Enter Choice : ");
                                    int choice2 = sc.nextInt();
                                    System.out.println();

                                    if (choice2 == 1) {
                                        System.out.println("Enter Amount to Deposit : (Limit : 50,000)");
                                        int amount = sc.nextInt();
                                        // Validation
                                        if (amount < 0 || amount > 50000) {
                                            System.out.println("Enter correct amount !");
                                            System.out.println("\nPress enter to continue");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        } else {
                                            try {
                                                FileWriter fw = new FileWriter(FilePath + username + ".txt", true);
                                                String oldMoney = Integer.toString(loginMoney);
                                                loginMoney += amount;
                                                int temp = amount;
                                                String toBeDeposited = Integer.toString(loginMoney);
                                                modifyFile(FilePath + username + ".txt", oldMoney, toBeDeposited);

                                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                Date date = new Date();
                                                fw.write("Rs.(+" + temp + ") :: " + formatter.format(date) + " :: Self Deposit " + "\n");

                                                loginTransaction = "TRANSACTION:1";
                                                modifyFile(FilePath + username + ".txt", "TRANSACTION:0", "TRANSACTION:1");
                                                System.out.println("Rs. " + temp + " Deposited !");
                                                System.out.println("\nPress enter to continue");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }

                                                fw.close();
                                            } catch (IOException e) {
                                                System.out.println("User Data not found !");
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    // Withdraw method
                                    else if (choice2 == 2) {
                                        System.out.println("Enter Amount to Withdraw : (Limit : 0 to " + loginMoney + ")");
                                        int amountWithdraw = sc.nextInt();
                                        // Validation
                                        if (amountWithdraw < 0 || amountWithdraw > loginMoney) {
                                            System.out.println("Enter correct amount !");
                                            System.out.println("\nPress enter to continue");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        } else {
                                            try {

                                                FileWriter fw = new FileWriter(FilePath + username + ".txt", true);
                                                String oldMoney = Integer.toString(loginMoney);
                                                loginMoney -= amountWithdraw;
                                                int temp1 = amountWithdraw;
                                                String toBeWithdrawed = Integer.toString(loginMoney);
                                                modifyFile(FilePath + username + ".txt", oldMoney, toBeWithdrawed);

                                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                Date date = new Date();
                                                fw.write("Rs.(-" + temp1 + ") :: " + formatter.format(date) + " :: Self Withdraw" + "\n");

                                                loginTransaction = "TRANSACTION:1";
                                                modifyFile(FilePath + username + ".txt", "TRANSACTION:0", "TRANSACTION:1");

                                                System.out.println("Rs. " + temp1 + " Withdrawed !");

                                                System.out.println("\nPress enter to continue");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }

                                                fw.close();
                                            } catch (IOException e) {
                                                System.out.println("User Data not found !");
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    else if (choice2 == 3) {
                                        System.out.println("\nDetails :");
                                        System.out.println("1. Name      : " + loginName);
                                        System.out.println("2. Username  : " + loginUserName);
                                        System.out.printf("3. Password  : ");
                                        for (int i = 0; i < loginPassword.length(); i++) {
                                            System.out.printf("*");
                                        }
                                        System.out.println();
                                        System.out.println("4. Phone No. : " + number);
                                        System.out.println("5. Balance   : " + loginMoney + " Rs.");

                                        System.out.println("\nPress enter to continue");
                                        try {
                                            System.in.read();
                                        } catch (Exception e) {
                                        }
                                    }
                                    else if (choice2 == 4) {
                                        try {
                                            File f1 = new File(FilePath + username + ".txt");
                                            dataReader = new Scanner(f1);
                                            System.out.println("Transaction History : ");
                                            String temp = "TRANSACTION:0";
                                            if (loginTransaction.equals(temp)) {
                                                System.out.println("No Transaction History is there !");
                                                System.out.println("\nPress enter to continue");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }
                                            } else {
                                                for (int j = 0; j < 6; j++) {
                                                    dataReader.nextLine();
                                                }
                                                while (dataReader.hasNextLine()) {
                                                    String fileData = dataReader.nextLine();
                                                    System.out.println(fileData);
                                                }
                                                System.out.println("\nPress enter to continue");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }
                                            }
                                            dataReader.close();
                                        } catch (FileNotFoundException exception) {
                                            System.out.println("Unexcpected error occurred!");
                                            exception.printStackTrace();
                                            System.out.println("\nPress enter to continue");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        }
                                    }
                                    else if (choice2 == 5) {
                                        System.out.println();
                                        System.out.println("Enter Username of other account: ");
                                        sc.nextLine();
                                        String userNameTo = sc.nextLine();

                                        File fileToTransfer = new File(FilePath + userNameTo + ".txt");
                                        if (fileToTransfer.exists()) {
                                            dataReader = new Scanner(fileToTransfer);
                                            Scanner dataReader2 = new Scanner(fileToTransfer);
                                            String moneyOld = dataReader2.nextLine();
                                            String nameTransfer = dataReader2.nextLine();
                                            int moneyOldUser = Integer.parseInt(moneyOld);
                                            System.out.println("Enter Amount to Transfer : (Limit : 0 to " + loginMoney + ")");
                                            int amountTransferUpdate = sc.nextInt();

                                            if (amountTransferUpdate <= 0 || amountTransferUpdate > loginMoney) {
                                                System.out.println("Enter correct amount !");
                                                System.out.println("\nPress enter to continue");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }
                                            } else {
                                                String to_upd = Integer.toString(loginMoney);
                                                loginMoney -= amountTransferUpdate;
                                                String to_upd2 = Integer.toString(loginMoney);
                                                modifyFile(FilePath + username + ".txt", to_upd, to_upd2);

                                                String toUpdate = Integer.toString(moneyOldUser);
                                                moneyOldUser += amountTransferUpdate;
                                                String toUpdateTo = Integer.toString(moneyOldUser);
                                                modifyFile(FilePath + userNameTo + ".txt", toUpdate, toUpdateTo);
                                                modifyFile(FilePath + userNameTo + ".txt", "TRANSACTION:0", "TRANSACTION:1");
                                                try {
                                                    FileWriter f11 = new FileWriter(FilePath + userNameTo + ".txt", true);
                                                    SimpleDateFormat formatter = new SimpleDateFormat(
                                                            "dd/MM/yyyy HH:mm:ss");
                                                    Date date = new Date();
                                                    f11.write("Rs.(+" + amountTransferUpdate + ") :: " + formatter.format(date) +
                                                            " :: Transferred from " + username + " (" + loginName + ")\n");
                                                    f11.close();

                                                    FileWriter f12 = new FileWriter(FilePath + username + ".txt", true);
                                                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                    Date date2 = new Date();
                                                    f12.write("Rs.(-" + amountTransferUpdate + ") :: " + formatter2.format(date2) + " :: Transferred to "
                                                            + nameTransfer + " (" + nameTransfer + ")\n");
                                                    f12.close();

                                                    System.out.println("Rs.(" + amountTransferUpdate + ") Transferred to " + userNameTo + " ( " + nameTransfer + " )");
                                                    System.out.println("\nPress enter to continue");
                                                    try {
                                                        System.in.read();
                                                    } catch (Exception e) {
                                                    }

                                                } catch (IOException e) {
                                                    System.out.println("User Data not found !");
                                                    e.printStackTrace();
                                                }
                                            }

                                            dataReader2.close();
                                        } else {
                                            System.out.println("USER DON'T EXISTS !");
                                            System.out.println("\nPress enter to continue");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                sc.close();
                                bool = false;
                                break;
                            }

                            dataReader.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found !");
                            e.printStackTrace();
                            System.out.println("\nPress enter to continue");
                            try {
                                System.in.read();
                            } catch (Exception f) {
                            }
                        }
                    } else {
                        System.out.println("User not registered!");
                        System.out.println("\nPress enter to continue");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                    }

                    if (bool) {
                        System.out.println("Username or Password Incorrect !\nPlease Try Again");
                        System.out.println("\nPress enter to continue");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                    }
                } else if (choice == 3) {
                    System.out.println("\n***** Thank you for using ATM *****");
                    System.out.println("\nPress enter to continue");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    sc.close();
                    b = false;
                } else {
                    System.out.println("Enter correct number input !");
                    System.out.println("\nPress enter to continue");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
            }
            System.out.println();
        }
    static void modifyFile(String filePath, String oldString, String newString) {
        String oldContent = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceFirst(oldString, newString);
            new FileWriter(filePath, false).close();
            FileWriter writer = new FileWriter(filePath);
            writer.write(newContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}