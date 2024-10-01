public class Orders {

  // Stores menu item IDs for all orders
  private String[] ordered_items;

  // Only to be used by iterator methods
  private int current_index = 0;

  // Constructor
  public Orders(int max_num_orders) {
  // ------------------------------------------------------
  // Initalizes item_numbers array to size max_num_orders
  // and initializes to all nulls.
  // ------------------------------------------------------
// (to complete)
ordered_items = new String[max_num_orders];
  }

  // Method
  public int numOrders() {
  // ------------------------------------------------------
  // Returns the current number of ordered items.
  // ------------------------------------------------------
  // (to complete)
int count = 0;
for (int i = 0; i < ordered_items.length; i++) {
  if (ordered_items[i] != null) {
    count++;
  }
}
return count;
  }

  // Method
  public void add(String item_number) 
                      throws NumberOrdersExceededException {
  // ------------------------------------------------------
  // Adds item_number to next available space in array 
  // ordered_items.
  //
  // Throws NumberOrdersExceededException if ordered_items
  // array is full.
  // ------------------------------------------------------
  // (to complete)
  if (numOrders() == ordered_items.length) {
    throw new NumberOrdersExceededException();
  }

  for (int i = 0; i < ordered_items.length; i++) {
    if(ordered_items[i] == null) {
      ordered_items[i] = item_number;
      break;
    }
  }
  }
  public String[] getOrders() {
      return ordered_items;
  }

  // ------------------------------------------------------
  // Iterator Methods
  // ------------------------------------------------------
  // These methods used to iterate over the ordered items.
  // Items may be iterated over multiple times by calling
  // reset to begin at the first menu item again.
  //
  // NOTE: These methods use instance variable current_index
  //       which no other methods should use.
  // ------------------------------------------------------

  public void reset() {
  // ------------------------------------------------------
  // Resets to first ordered item.
  // ------------------------------------------------------
  // (to complete)
  current_index = 0;
  }

  public boolean hasNext() {
  // ------------------------------------------------------
  // Returns true if there exists another ordered item to
  // retrieve. Otherwise, returns false.
  // ------------------------------------------------------
  // (to complete)
  for(int i = current_index; i < ordered_items.length; i++) {
    if(ordered_items[i] != null) {
      return true;
    }
  }
  return false;
  }

  public String next() {
  // ------------------------------------------------------
  // Returns next order item in array of ordered items.
  // (Must only be called when hasNext() is true.)
  // ------------------------------------------------------
  // (to complete)
  for(int i = current_index; i < ordered_items.length; i++) {
    if(ordered_items[i] != null) {
      String itemNumber = ordered_items[i];
      current_index = i + 1;
      return itemNumber;
    }
  }
  return null;
}

public void cancelOrder(int index) {
  if (index >= 0 && index < ordered_items.length && ordered_items[index] != null) {
    for (int i = index; i < ordered_items.length - 1; i++) {
      ordered_items[i] = ordered_items[i + 1];
    }
    ordered_items[ordered_items.length - 1] = null;
  }
}
public void cancel(){
  for (int i = 0; i < ordered_items.length; i++) {
      ordered_items[i]=null;
  }
  current_index = 0;
}
}