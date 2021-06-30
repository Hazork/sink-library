package com.github.arthurfiorette.sinklibrary.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.arthurfiorette.sinklibrary.item.ItemBuilder;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;

public class BuildedStack implements MenuItem {

  private ItemBuilder builder;
  private ClickListener listener;

  public BuildedStack(final ItemBuilder builder) {
    this(builder, ClickListener.ignore());
  }

  public BuildedStack(final ItemBuilder builder, final ClickListener listener) {
    this.builder = builder;
    this.setListener(listener);
  }

  @Override
  public ItemStack getItem() {
    return this.getBuilder().build();
  }

  @Override
  public ClickListener getListener() {
    return this.listener;
  }

  public void setListener(final ClickListener listener) {
    this.listener = listener;
  }

  public ItemBuilder getBuilder() {
    return this.builder;
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setBuilder(final ItemBuilder builder) {
    this.builder = builder;
  }
}
