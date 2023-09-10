package me.rootdeibis.commonlib.factory.gui.holders;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;


public abstract class GuiContainer implements InventoryHolder {

    private final UUID containerId = UUID.randomUUID();
    private HashMap<Integer, GuiButton> buttons = new HashMap<>();

    private static final List<GuiContainer> containers = new ArrayList<>();

    private static final ItemStack AIR_STACK = new ItemStack(Material.AIR);

    private Inventory inventory;

    public GuiContainer() {

    }

    public String getTitle() {
        return "GUITitle";
    }

    public int getSize() {
        return 3 * 9;
    }

    public void setButtons(HashMap<Integer, GuiButton> buttons) {
        this.buttons = buttons;
    }

    public void addButton(int slot, GuiButton button) {
        this.buttons.put(slot, button);

    }

    public void removeButton(int buttonSlot) {
        if (this.inventory != null && this.buttons.containsKey(buttonSlot)) {
            this.inventory.setItem(buttonSlot, AIR_STACK);
        }
    }


    public GuiButton getButton(int slot) {
        return this.buttons.get(slot);
    }

    public void updateButton(int slot) {
        if (this.inventory != null) {

            if (this.buttons.containsKey(slot)) {

                ItemStack itemStack = this.buttons.get(slot).getItem();

                this.inventory.setItem(slot, itemStack);
            }

        }
    }


    public HashMap<Integer, GuiButton> getButtons() {
        return this.buttons;
    }

    private void build() {
        if (this.inventory == null)
            this.inventory = Bukkit.createInventory(this, getSize() * 9, getTitle());

        for (Map.Entry<Integer, GuiButton> e : this.buttons.entrySet()) {

            this.inventory.setItem(e.getKey(), e.getValue().getItem());

        }
    }

    public void show(Player player) {
        build();


        player.openInventory(getInventory());

        registerContainer(this);
    }

    public void close(Player player) {

        if (player.getOpenInventory().getTopInventory() instanceof GuiContainer) {
            GuiContainer container = (GuiContainer) player.getOpenInventory().getTopInventory();
            removeContainer(container);
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public static List<GuiContainer> getContainers() {
        return containers;
    }

    public static void registerContainer(GuiContainer container) {
        containers.add(container);
    }


    public UUID getContainerId() {
        return containerId;
    }

    public static void removeContainer(GuiContainer container) {
        containers.remove(container);
    }
}
