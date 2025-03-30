
package bookstoreapp;

/**
 *
 * @author user
 */
interface Status {
    double calculateFinalCost(Customer customer, double totalPrice);
    void updatePoints(Customer customer, double finalCost);
    @Override
    String toString();
}
