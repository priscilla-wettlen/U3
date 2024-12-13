package controller;

//only imports what is strictly necessary from view-package
import model.*;
import view.CustomCakeFrame;
import view.MainFrame;
import view.ButtonType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as the central controller for the application, managing the interactions
 * between the model and view layers. It handles user inputs, updates the GUI,
 * and manages logic related to orders and items.

 * @author Yin Ting Chan, Priscilla Wettlén
 */
public class Controller {
    private MainFrame view;
    private CustomCakeFrame newCakeType;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private String [] orderHistory;
    private ItemManager itemManager;
    private OrderManager orderManager;
    private Order currentOrder;
    private List<Order> previousOrders;

    /**
     * This method constructs the Controller and initialises the application with the main GUI frame,
     * item and order managers, and sets up the initial state for the order and GUI.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
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

    /**
     * This method handles button press events from the GUI, representing functionality
     * based on the type of button pressed.
     *
     * @param button The type of button that was pressed
     * @author teacher
     */
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

    /**
     * This method adds the selected item to the current order.
     * It updates the right panel in the GUI to reflect the updated order.
     *
     * @param selectionIndex The index of the selected item in the left panel
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public void addItemToOrder(int selectionIndex) {
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

    /**
     * This method displays the items of the selected previous order in the right panel.
     * It updates the cost label to show the total cost of the selected order.
     *
     * @param selectionIndex The index of the selected order in the left panel.
     *                       It only works if the current menu is OrderHistory.
     * @author Priscilla Wettlén
     */
    public void viewSelectedOrder(int selectionIndex){
        String[] selectedOrderItems;
        double costSelectedOrder;
        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            String str = orderHistory[selectionIndex];
            String regex =",(?!\\s*\\d)";
            selectedOrderItems = str.split(regex);

            costSelectedOrder = previousOrders.get(selectionIndex).getTotalPrice();

            view.populateRightPanel(selectedOrderItems);
            view.setTextCostLabelRightPanel("Total cost of order: " + costSelectedOrder);
        }
    }

    /**
     * This method updates the left panel to display the cake menu and prepares the GUI for
     * adding cakes to the current order.
     * It disables specific buttons and updates the cost label with the total cost of the current order.
     *
     * @author Yin Ting Chan
     */
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

    /**
     * This method updates the left panel to display the per-unit item menu.
     * It prepares the GUI for adding per-unit items to the current order by populating
     * the left panel with the available items and their details.
     * It also updates the cost label on the right panel with the total cost of the current order,
     * and disables certain buttons to reflect the current menu state.
     *
     * @author Priscilla Wettlén
     */
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

    /**
     * This method displays the order history in the left panel, including the total cost of each
     * previous order. If there are no previous orders, a dialog is shown to notify the user.
     * It prepares the GUI for viewing order history by clearing the right panel and
     * updating the left panel with the order history, as well as
     * disabling certain buttons to reflect the current menu state.
     *
     * @author Priscilla Wettlén
     */
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
        view.populateLeftPanel(orderHistoryPrices);
        //view.populateRightPanel(orderHistory);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }

    /**
     * This method launches the `CustomCakeFrame` for creating a new custom cake and
     * switches to the cake menu to allow the user to add the new cake to the current order.
     * It enables all buttons after the operation.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public void addNewCake() {
        newCakeType = new CustomCakeFrame(this);
        setToCakeMenu();
        view.enableAllButtons();
    }

    /**
     * This method adds a new custom cake to the cake menu and updates the left panel to include the newly added cake.
     * It switches to the cake menu and displays the updated list of cakes, as well as
     * disables certain buttons to reflect the current menu state.
     *
     * @param newCake The new cake to be added to the cake menu
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public void addNewCakeToMenu(Cake newCake) {
        itemManager.addCakeToMenu(newCake);

        currentLeftMenu = ButtonType.Cake;

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

    /**
     * This method confirms the current order and adds it to the order history.
     * It clears the current order and updates the GUI to reflect the new state.
     * It ensures that an order contains at least one item before confirming.
     * It displays a dialog box for confirmation or errors.
     * It resets the right panel to reflect an empty current order and
     * enables all buttons for further operations.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public void placeOrder() {
        if (currentOrder.getCurrentOrderLength() == 0) {
            JOptionPane.showMessageDialog(null, "Your order must contain at least 1 item!");
            return;
        }

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