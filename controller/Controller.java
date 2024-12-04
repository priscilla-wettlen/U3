package controller;

//only imports what is strictly necessary from view-package
import model.*;
import view.CustomCakeFrame;
import view.MainFrame;
import view.ButtonType;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MainFrame view;
    private CustomCakeFrame newCakeType;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;

    private String [] cakeMenuString; // for test purposes only
    private String [] perUnitItemMenuString; // for test purposes only
    private String [] orderHistoryMenuString; // for test purposes only
    private String [] order1Simulation; // for test purposes only
    private String [] currentOrderArray; // for test purposes only
    private double costCurrentOrder = 0; // for test purposes only
    private int nbrOfOrders = 0; // for test purposes only

    private ItemManager itemManager;
    private OrderManager orderManager;

    public Controller() {
        view = new MainFrame(1000, 500, this);
        itemManager = new ItemManager();
        orderManager = new OrderManager();

        loadStringTestValues(); //for test purposes - remove when not needed more
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }

    //This method is only used for test purposes - remove when no longer needed
    private void loadStringTestValues() {
        cakeMenuString = new String[10];
        perUnitItemMenuString = new String[10];
        orderHistoryMenuString = new String[10];
        order1Simulation = new String[10];
        currentOrderArray = new String[10];

        cakeMenuString[0] = "tårta0, storlek: 4 bitar, topping1, topping2, Pris0";
        cakeMenuString[1] = "tårta1, storlek: 6 bitar, topping1, topping3, Pris1";
        cakeMenuString[2] = "tårta2, storlek: 4 bitar, topping1, topping2, Pris2";
        cakeMenuString[3] = "tårta3, storlek: 12 bitar,topping1, topping3, Pris3";

        perUnitItemMenuString[0] = "Kanelbulle, 30";
        perUnitItemMenuString[1] = "Pepparkaka, 10";
        perUnitItemMenuString[2] = "Coca Cola 33cl, 17";
        perUnitItemMenuString[3] = "Ramlösa 33cl, 15";

        orderHistoryMenuString[0] = "order1: kostnad:100";
        orderHistoryMenuString[1] = "order2: kostand:200";

        order1Simulation[0] = "Order 1";
        order1Simulation[1] = "tårta1 Pris1";
        order1Simulation[2] = "tårta2 Pris2";
        order1Simulation[3] = "vetebulle Pris11";
    }

    //This method is called by class MinFrame when a button in teh GUI is pressed
    public void buttonPressed(ButtonType button){

        switch (button) {
            case Add:
                addItemToOrder(view.getSelectionLeftPanel());
                break;

            case Cake:
                setToCakeMenu();
                break;

            case PerUnitItem:
                setToPerUnitItemMenu();
                break;

            case MakeCake:
                addNewCake();
                break;

            case OrderHistory:
                setToOrderHistoryMenu();
                break;

            case Order:
                placeOrder();
                break;

            case ViewOrder:
                viewSelectedOrder(view.getSelectionLeftPanel());
                break;
        }
    }

    public void addItemToOrder(int selectionIndex) {
        //System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if (selectionIndex != -1){ // if something is selected in the left menu list
            String selectedItemDetails = "";
            double itemPrice = 0;

            switch (currentLeftMenu) { //This might need to change depending on architecture
                case Cake:
                    BakeryItem selectedItem = itemManager.getCakesMenu().get(selectionIndex);
                    selectedItemDetails = selectedItem.toString();
                    itemPrice = selectedItem.calculatePrice();
                    break;

                case PerUnitItem:
                    selectedItem = itemManager.getPerUnitItems().get(selectionIndex);
                    selectedItemDetails = selectedItem.toString();
                    itemPrice = selectedItem.calculatePrice();
                    break;
            }
            currentOrderArray[nbrOfOrders] = selectedItemDetails;
            nbrOfOrders++;
            costCurrentOrder += itemPrice;

            //nbrOfOrders++; //for test purposes - need to be removed or changed when model for handling orders is implemented
            //costCurrentOrder = costCurrentOrder + 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }

    public void viewSelectedOrder(int selectionIndex){
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed
        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            costCurrentOrder = 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(order1Simulation); //update left panel with order details - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }

    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;

        List<Cake> cakesMenu = itemManager.getCakesMenu();
        cakeMenuString = new String[cakesMenu.size()];

        for (int i = 0; i < cakesMenu.size(); i++) {
            cakeMenuString[i] = cakesMenu.get(i).toString();
        }

        view.populateLeftPanel(cakeMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;

        List<PerUnitItem> perUnitItems = itemManager.getPerUnitItems();
        perUnitItemMenuString = new String[perUnitItems.size()];

        for (int i = 0; i < perUnitItems.size(); i++){
            perUnitItemMenuString[i] = perUnitItems.get(i).toString();
        }

        view.populateLeftPanel(perUnitItemMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();
        view.populateLeftPanel(orderHistoryMenuString);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }

    public void addNewCake() {
        newCakeType = new CustomCakeFrame(this);
        //For grade VG: Add more code to save the new cake type and update menu,
        view.enableAllButtons();
    }

    public void placeOrder() {
        //System.out.println("Pressed Order to create a new order"); //for test purposes - remove when not needed more
        //currentOrderArray = new String[10];  // for test purposes - remove when not needed more

        if (nbrOfOrders == 0) {
            System.out.println("Your order must contain at least 1 item!");
            return;
        }

        List<String> itemsToOrder = new ArrayList<>();
        for (String item : currentOrderArray) {
            if (item != null) {
                itemsToOrder.add(item);
            }
        }

        Order newOrder = new Order(itemsToOrder, costCurrentOrder);
        orderManager.addOrder(newOrder);

        for (int i = 0; i < currentOrderArray.length; i++) {
            currentOrderArray[i] = null;
        }

        nbrOfOrders = 0;
        costCurrentOrder = 0;

        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }
}