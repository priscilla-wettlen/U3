package controller;

//only imports what is strictly necessary from view-package
import model.*;
import view.CustomCakeFrame;
import view.MainFrame;
import view.ButtonType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MainFrame view;
    private CustomCakeFrame newCakeType;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private String [] orderHistory;
    private ItemManager itemManager;
    private OrderManager orderManager;
    private Order currentOrder;
    private List<Order> previousOrders;
    List<String> itemsToOrder;
    //private int orderCounter = 100;



    public Controller() {
        view = new MainFrame(1000, 500, this);
        itemManager = new ItemManager();
        orderManager = new OrderManager();
        currentOrder = new Order();
        previousOrders = new ArrayList<>();
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
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
            double itemPrice = 0;

            switch (currentLeftMenu) { //This might need to change depending on architecture
                case Cake:
                    Cake cake = itemManager.getCakesMenu().get(selectionIndex);
                    itemPrice = cake.calculatePrice();
                    cake.setPrice(itemPrice);
                    currentOrder.addItemToCurrentOrderArray(cake);
                    break;

                case PerUnitItem:
                    PerUnitItem perUnitItem = itemManager.getPerUnitItems().get(selectionIndex);
                    itemPrice = perUnitItem.calculatePrice();
                    perUnitItem.setPrice(itemPrice);
                    currentOrder.addItemToCurrentOrderArray(perUnitItem);
                    break;
            }

            List<String> orderItems = currentOrder.getOrderItems();
            double costCurrentOrder = currentOrder.getTotalPrice();
            String[] currentOrderArray = orderItems.toArray(new String[0]);

            view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }

    public void viewSelectedOrder(int selectionIndex){
        String[] selectedOrderItems;
        double costSelectedOrder;
        //selectedOrderItems = orderHistory[selectionIndex].split(",");
        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            selectedOrderItems = new String[]{orderHistory[selectionIndex]};
            for(String str : orderHistory){
                selectedOrderItems = str.split("\n");
            }

            costSelectedOrder = previousOrders.get(selectionIndex).getTotalPrice();

            view.populateRightPanel(selectedOrderItems);
            view.setTextCostLabelRightPanel("Total cost of order: " + costSelectedOrder);
        }
    }

    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;

        List<Cake> cakesMenu = itemManager.getCakesMenu();
        List<String> cakeMenuStringList = new ArrayList<>();

        for (Cake cake : cakesMenu) {
            cakeMenuStringList.add(cake.toString());
        }

        double costCurrentOrder = currentOrder.getTotalPrice();
        String[] cakeMenuString = cakeMenuStringList.toArray(new String[0]);

        view.populateLeftPanel(cakeMenuString);
        // view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;

        List<PerUnitItem> perUnitItems = itemManager.getPerUnitItems();
        List<String> perUnitItemMenuStringList = new ArrayList<>();

        for (PerUnitItem item : perUnitItems) {
            perUnitItemMenuStringList.add(item.toString());
        }

        double costCurrentOrder = currentOrder.getTotalPrice();
        String[] perUnitItemMenuString = perUnitItemMenuStringList.toArray(new String[0]);

        view.populateLeftPanel(perUnitItemMenuString);
        // view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        orderHistory = new String[previousOrders.size()];
        String[] orderHistoryPrices = new String[previousOrders.size()];

        if(orderManager.getOrderHistory().isEmpty() || previousOrders.isEmpty()){
            JOptionPane.showMessageDialog(null, "You have no previous orders.");
        }

        for (int i = 0; i < previousOrders.size(); i++) {
            Order order = previousOrders.get(i);
            String orderedItems = order.getOrderItems().toString();
            orderHistory[i] = orderedItems;
            orderHistoryPrices[i] = "Order#" + order.getOrderNumber() + ": " + order.getTotalPrice() + " kr";
        }


        view.clearRightPanel();
        //TODO show the entire cake info ob right panel instead of left
        view.populateLeftPanel(orderHistoryPrices);
        //view.populateRightPanel(orderHistory);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }

    public void addNewCake() {
        newCakeType = new CustomCakeFrame(this);
        setToCakeMenu();
        view.enableAllButtons();
    }

    public void addNewCakeToMenu(Cake newCake) {
        itemManager.addCakeToMenu(newCake);

        currentLeftMenu = ButtonType.Cake; //TODO CHECK WHY IT SAYS DUPLICATED CODE

        List<Cake> cakesMenu = itemManager.getCakesMenu();
        List<String> cakeMenuStringList = new ArrayList<>();

        for (Cake cake : cakesMenu) {
            cakeMenuStringList.add(cake.toString());
        }

        double costCurrentOrder = currentOrder.getTotalPrice();
        String[] cakeMenuString = cakeMenuStringList.toArray(new String[0]);


        view.populateLeftPanel(cakeMenuString);
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder));
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void placeOrder() {
        if (currentOrder.getCurrentOrderLength() == 0) {
            System.out.println("Your order must contain at least 1 item!");
            return;
        }

        //TODO
        itemsToOrder = currentOrder.getOrderItems();
        orderManager.addOrder(currentOrder);
        previousOrders.add(currentOrder);

        JOptionPane.showMessageDialog(null, "Your order has been confirmed!");
        currentOrder = new Order();
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: " + currentOrder.getTotalPrice());
        view.enableAllButtons();
        //view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }

}