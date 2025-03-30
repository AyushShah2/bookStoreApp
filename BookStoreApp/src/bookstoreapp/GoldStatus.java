package bookstoreapp;

/**
 *
 * @author user
 */
public class GoldStatus implements Status {
    @Override
    public double calculateFinalCost(Customer customer, double totalPrice) {
        int pointsToDollars = (int)Math.floor(customer.getPoints() / 100);
        double finalCost = totalPrice - pointsToDollars;
        if (Math.max(finalCost, 0) == finalCost){
            customer.setPoints(0);
        }
        else{
            customer.setPoints(customer.getPoints() - (int)Math.floor(totalPrice*100));
        }
        return Math.max(finalCost, 0);
    }

    @Override
    public void updatePoints(Customer customer, double finalCost) {
        int pointsEarned = (int) Math.floor(finalCost * 10);
        customer.setPoints(customer.getPoints() + pointsEarned);
        
        if (customer.getPoints() < 1000){
            customer.setStatus(new SilverStatus());
        }
    }
    
    @Override
    public String toString(){
        return "Gold";
    }
}
