import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.util.Random;

public class MyPhone {
      private int numOfTexts;
      private double dataConsumed;
      private double batteryLife;
      private String name;
      private String phoneNumber;
      private final double DATA_PER_MIN = 65 / 60.0;
      private final double MAX_MINUTES = 720.0;
      int textNum;

    public MyPhone(String name, String phoneNumber) {
          this.name = name;
          setPhoneNumber(phoneNumber);
          numOfTexts = 0;
          dataConsumed = 0;
          batteryLife = 0;
    }

    public int getNumOfTexts() {
        return numOfTexts;
    }

    public void setNumOfTexts(int numOfTexts) {
        this.numOfTexts = numOfTexts;
    }

    public double getDataConsumed() {
        //Converting MB to GB
        return dataConsumed;
    }

    public void chargeBattery(int mins) {
        if(mins <=0){
            batteryLife = batteryLife;
        }
        else {
            batteryLife = (Math.ceil(mins * 0.83)) * 0.01;
            if(batteryLife > 1.0) {
                batteryLife = 1.0;
            }
        }

    }
    public double getBatteryLife() {
        return batteryLife;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String n) {
        if(n.length() == 10) {
            phoneNumber = n;
        }
        else {
            phoneNumber = "9999999999";
        }
    }
    public void streamAudio(int mins) {
        if(mins <= 0){
            dataConsumed = 0;
        }
        else if(mins >= MAX_MINUTES){
            dataConsumed = DATA_PER_MIN * mins;
            batteryLife = 0.0;
            //if batteryLife = 0, display JOption warning
        }
        else {
            dataConsumed = DATA_PER_MIN * mins;
            batteryLife = batteryLife - Math.ceil((120/MAX_MINUTES) * mins);
            //if batteryLife = 0, display JOption warning
        }
    }
    public void sendText(String text) {
        if(text.length() > 0) {
            textNum = textNum + 1;
        }
        //Might add to main method
        JFrame frame = new JFrame("Text");
        JOptionPane.showMessageDialog(frame, "Text Messages: " + textNum);
    }
    public int getTextNum(){
        return textNum;
    }
    public void readText(){
        JFrame frame = new JFrame("Text");
        String option0, option1, option2, option3, option4;
        Random random = new Random();
        int choice = random.nextInt(5);

        switch (choice) {
            case 0:
                option0 = "Good morning :)";
                JOptionPane.showMessageDialog(frame, option0);
                break;
            case 1:
                option1 = "LOL";
                JOptionPane.showMessageDialog(frame, option1);
                break;
            case 2:
                option2 = "I miss you!";
                JOptionPane.showMessageDialog(frame, option2);
                break;
            case 3:
                option3 = "Hey, how is your day?";
                JOptionPane.showMessageDialog(frame, option3);
                break;
            case 4:
                option4 = "ANSWER YOUR PHONE!";
                JOptionPane.showMessageDialog(frame, option4);
                break;
        }
    }
    private void startNewMonth(){
        numOfTexts = 0;
        dataConsumed = 0;
    }
    private double calcAdditionalDataFee() {
        //converting MB to GB, rounded up!
        double additionalFees = 0.0;
        double GB = Math.ceil(dataConsumed * 0.001);
        if(GB > 2){
             additionalFees = (GB - 2) * 15.00;
        }
        return additionalFees;
    }
    private double calcUsageCharge(){
        double phoneService = 50 + calcAdditionalDataFee();
        double usageCharge = phoneService * 0.03;
        return usageCharge;
    }
    private double calcTotalFee(){
        double phoneService = 50 + calcAdditionalDataFee();
        double administrativeFee = 0.61;
        double totalFee = calcUsageCharge() + phoneService + administrativeFee;
        return totalFee;
    }
    private String calcfmtPhoneNumber() {
        String output = "(" + phoneNumber.substring( 0,3 ) + ") " + phoneNumber.substring( 3,6 ) + "-" + phoneNumber.substring( 6,10 );
        return output;
    }

    public void printStatement(){

        //use a numberformat
        NumberFormat format = NumberFormat.getCurrencyInstance();
        System.out.printf("%-10s %-10s", "Customer: ", name + "\n");
        System.out.printf("%-10s %-10s", "Number: ", phoneNumber + "\n");
        System.out.printf("%-10s %-10s", "Texts: ", numOfTexts + "\n");
        System.out.printf("%-10s %-10s", "Data Usage: ", dataConsumed + "\n");
        System.out.printf("%-10s %-10s", "2GB Plan: ", "$50" + "\n");
        System.out.printf("%-10s %-10s", "Additional Data Fee: ", calcAdditionalDataFee() + "\n");
        System.out.printf("%-10s %-10s", "Universal Usage (3%): ", calcUsageCharge() + "\n");
        System.out.printf("%-10s %-10s", "Administrative Fee: ", "$0.61" + "\n");
        System.out.printf("%-10s %-10s", "Total Charges: ", calcTotalFee() + "\n");
    }
}
