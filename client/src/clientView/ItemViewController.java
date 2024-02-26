package clientView;

import clientViewModel.ItemViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ItemViewController
{
  private ViewHandler handler;
  private ItemViewModel viewModel;
  private Region root;

  //Item info labels
  @FXML private Label nameLabel;
  @FXML private Label priceLabel;
  @FXML private Label avialableQuantityLabel;
  @FXML private Label sellerLabel;


  public void init (ViewHandler handler, ItemViewModel viewModel, Region root)
  {
    this.handler = handler;
    this.viewModel = viewModel;
    this.root = root;

    //binding labels
    nameLabel.textProperty().bind(viewModel.getnameLabel());
    priceLabel.textProperty().bind(viewModel.getpriceLabel());
    avialableQuantityLabel.textProperty().bind(viewModel.getavialableQuantityLabel());
    sellerLabel.textProperty().bind(viewModel.getsellerLabel());

  }
//  public void reset()
//  {
//    viewModel.reset();
//  }
//
//  public Region getRoot()
//  {
//    return root;
//  }
//
//  @FXML public void switchWindowToItems()
//  {
//    handler.openView("items");
//  }
//
//  @FXML public void swichWIndowToBasket()
//  {
//
//    handler.openView("basket");
//  }
//
//  @FXML public void AddToBasket()
//  {
//    viewModel.addToBasket();
//  }
}
