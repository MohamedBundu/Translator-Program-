public class Check {

  
  private Orders orders;
  private double tax_rate;
  private Menu menu;
  // Constructor
  public Check(Menu menu, Orders orders, double tax_rate) {
  // ------------------------------------------------------
  // Initalizes orders to parameter values passed.
  // ------------------------------------------------------
  this.menu = menu;
  this.orders = orders;
  this.tax_rate = tax_rate;
  }

  // Getter Methods
 
  public Orders getOrders() {
  // ------------------------------------------------------
  // Returns all orders on check.
  // ------------------------------------------------------
  // (to complete)
  return orders;
  }

  public double getTaxRate() {
  // ------------------------------------------------------
  // Returns the tax rate applied on checks.
  // ------------------------------------------------------
  // (to complete)
  return tax_rate;
  }

  // Method
  public double tax() {
  // ------------------------------------------------------
  // Returns the amount of tax for the ordered items.
  // ------------------------------------------------------
  // (to complete)
  double totalCost = subtotal();
  return totalCost * tax_rate;
  }

  // Method
  public double subtotal() {
    // ------------------------------------------------------
    // Returns total cost of orders on check (excluding tax).
    // ------------------------------------------------------
    // (to complete)
    double subtotal = 0.0;
    for (String orderid : orders.getOrders()) {
      if (orderid == null) break;
      subtotal += menu.getMenuItemByID(orderid).getPrice();
    }
    
    return subtotal;
  }

  // Method
  public double total() {
  // ------------------------------------------------------
  // Returns total cost of orders on check (including tax).
  // ------------------------------------------------------
  // (to complete)
  return subtotal() + tax();
  }

  public double compute_tip(int percent) {
  // ------------------------------------------------------
  // Returns the tip amount for percent given on pre-tax
  // total of the check.
  // ------------------------------------------------------
  // (to complete)
  double preTaxTotal = subtotal();
  double tip = preTaxTotal * (percent / 100.0);
  return tip;
  }
  
}