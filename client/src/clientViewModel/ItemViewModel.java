package clientViewModel;

import clientModel.CoreModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.Item;

public class ItemViewModel {

  private CoreModel model;
  private Item item;
  private StringProperty nameLabel;
  private StringProperty priceLabel;
  private StringProperty avialableQuantityLabel;
  private StringProperty sellerLabel;

  public ItemViewModel(CoreModel model, Item item)
  {
    this.model = model;
    this.item = item;

    nameLabel = new SimpleStringProperty(item.getName());
    priceLabel = new SimpleStringProperty(Double.toString(item.getPrice()));
    avialableQuantityLabel = new SimpleStringProperty(Integer.toString(item.getQuantity()));
    sellerLabel = new SimpleStringProperty("");
  }

  public StringProperty getpriceLabel()
  {
    return this.priceLabel;
  }
  public StringProperty getnameLabel()
  {
    return this.nameLabel;
  }
  public StringProperty getavialableQuantityLabel()
  {
    return this.avialableQuantityLabel;
  }
  public StringProperty getsellerLabel()
  {
    return this.sellerLabel;
  }

}
