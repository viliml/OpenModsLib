package openmods.utils;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;

public class ItemUtils {
	public static final Random RANDOM = new Random();

	public static ItemStack consumeItem(ItemStack stack) {
		if (stack.stackSize == 1) {
			if (stack.getItem().hasContainerItem()) { return stack.getItem().getContainerItemStack(stack); }
			return null;
		}
		stack.splitStack(1);

		return stack;
	}

	public static NBTTagCompound getItemTag(ItemStack stack) {
		if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound("tag");

		return stack.stackTagCompound;
	}

	public static Integer getInt(ItemStack stack, String tagName) {
		NBTTagCompound tag = getItemTag(stack);
		NBTBase data = tag.getTag(tagName);
		return (data != null)? ((NBTTagInt)data).data : null;
	}

	public static EntityItem createDrop(Entity dropper, ItemStack is) {
		return createEntityItem(dropper.worldObj, dropper.posX, dropper.posY, dropper.posZ, is);
	}

	public static EntityItem createEntityItem(World world, double x, double y, double z, ItemStack is) {
		return new EntityItem(world, x, y, z, is.copy());
	}
}
