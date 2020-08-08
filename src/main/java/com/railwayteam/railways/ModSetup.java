package com.railwayteam.railways;

import com.railwayteam.railways.blocks.WayPointBlock;
import com.railwayteam.railways.items.WayPointToolItem;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import com.tterrag.registrate.Registrate;

public class ModSetup {
  public static ItemGroup itemGroup = new ItemGroup(Railways.MODID) {
    @Override
    public ItemStack createIcon() {
            return new ItemStack(Items.MINECART);
        }
  };

  // we cache Registry entries in case other mod components need a convenient reference (including Registrate itself)
  public static BlockEntry<WayPointBlock> R_BLOCK_WAYPOINT;

  public static ItemEntry<WayPointToolItem> R_ITEM_WAYPOINT_TOOL;

  public void init() {
  }

  public static void register (Registrate reg) {
    // set item group for the following registry entries
    reg.itemGroup(()->itemGroup, Railways.MODID);

    // right now we're registering a block and an item.
    // TODO: consider splitting into ::registerBlocks and ::registerItems, or even to dedicated files?
    R_BLOCK_WAYPOINT = reg.block(WayPointBlock.name, WayPointBlock::new)         // tell Registrate how to create it
      .properties(p->p.hardnessAndResistance(5.0f, 6.0f))    // set block properties
      .blockstate((ctx,prov) -> prov.simpleBlock(ctx.getEntry(),                 // block state determines the model
        prov.models().getExistingFile(prov.modLoc("block/"+ctx.getName())) // hence why that's tucked in here
      ))
      .simpleItem()     // nothing special about the item right now
      .lang("Waypoint") // give it a friendly name
      .register();      // pack it up for Registrate

    R_ITEM_WAYPOINT_TOOL = reg.item(WayPointToolItem.name, WayPointToolItem::new)
      .lang("Waypoint Tool")
      .register();
  }
}
