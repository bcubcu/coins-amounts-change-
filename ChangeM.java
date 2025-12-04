package changeM;

public class ChangeM {
    private String name;
    private int amount;
    
    private int oneDollar, fiftyCents, twentyFiveCents, tenCents, fiveCents;
    
    // Default constructor
    public ChangeM() {
        this.name = "No Name";
        this.amount = 0;
        calculateChange();
    }
    
    // Parameterized constructor
    public ChangeM(String name, int amount) {
        this.name = name;
        this.amount = roundToNearestFive(amount);
        calculateChange();
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public int getOneDollar() {
        return oneDollar;
    }
    
    public int getFiftyCents() {
        return fiftyCents;
    }
    
    public int getTwentyFiveCents() {
        return twentyFiveCents;
    }
    
    public int getTenCents() {
        return tenCents;
    }
    
    public int getFiveCents() {
        return fiveCents;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAmount(int amount) {
        this.amount = roundToNearestFive(amount);
        calculateChange();
    }
    
    // Helper method to round to nearest 5
    private int roundToNearestFive(int value) {
        int remainder = value % 5;
        if (remainder >= 3) {
            return value + (5 - remainder); // round up
        } else {
            return value - remainder; // round down
        }
    }
    
    // Helper method to calculate change denominations
    private void calculateChange() {
        int remaining = amount;
        
        oneDollar = remaining / 100;
        remaining %= 100;
        
        fiftyCents = remaining / 50;
        remaining %= 50;
        
        twentyFiveCents = remaining / 25;
        remaining %= 25;
        
        tenCents = remaining / 10;
        remaining %= 10;
        
        fiveCents = remaining / 5;
    }
    
    // Method to display change (for client class use)
    public void displayChange() {
        System.out.println("Customer: " + name + " " + amount + " cents");
        System.out.println("Change:");
        
        boolean hasChange = false;
        if (oneDollar > 0) {
            System.out.println("1 dollar: " + oneDollar);
            hasChange = true;
        }
        if (fiftyCents > 0) {
            System.out.println("50 cents: " + fiftyCents);
            hasChange = true;
        }
        if (twentyFiveCents > 0) {
            System.out.println("25 cents: " + twentyFiveCents);
            hasChange = true;
        }
        if (tenCents > 0) {
            System.out.println("10 cents: " + tenCents);
            hasChange = true;
        }
        if (fiveCents > 0) {
            System.out.println("5 cents: " + fiveCents);
            hasChange = true;
        }
        
        if (!hasChange) {
            System.out.println("No change required");
        }
        System.out.println();
    }
}
        
        
        
        
               
        
        
        
 
  
    
    
    
        
        
        
    
   
    
    
  
    

