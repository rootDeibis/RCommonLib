package me.rootdeibis.commonlib.factory.gui.button;


import java.util.List;

public class GuiButtonData<T> {
    private T material;

    private int amount = 1;

    private String displayName;

    private List<String> lore;


    private String textures;
    public GuiButtonData() {}


    public GuiButtonData setMaterial(T material) {
        this.material = material;

        return this;
    }

    public T getMaterial() {
        return this.material;
    }

    public GuiButtonData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public GuiButtonData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public GuiButtonData setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setTextures(String textures) {
        this.textures = textures;
    }

    public String getTextures() {
        return textures;
    }
}
