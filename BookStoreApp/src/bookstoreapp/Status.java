
package bookstoreapp;

/**
 *
 * @author Ayush Shah
 */
interface Status {
    double calculateFinalCost(Customer customer, double totalPrice);
    void updatePoints(Customer customer, double finalCost);
    @Override
    String toString();
}
