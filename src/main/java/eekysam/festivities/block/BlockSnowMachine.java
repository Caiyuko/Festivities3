package eekysam.festivities.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eekysam.festivities.tile.TileEntitySnowMachine;

public class BlockSnowMachine extends BlockFestiveContainer
{
	public static boolean givetip = true;

	public BlockSnowMachine(Material par2Material)
	{
		super(par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySnowMachine();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntitySnowMachine t = (TileEntitySnowMachine) world.getTileEntity(par2, par3, par4);
		if (t != null)
		{
			t.onClick(par5EntityPlayer, world);
		}

		return true;
	}

	@Override
	public void onBlockPreDestroy(World world, int par2, int par3, int par4, int meta)
	{
		TileEntitySnowMachine t = (TileEntitySnowMachine) world.getTileEntity(par2, par3, par4);
		if (t != null)
		{
			int snow = t.snowCount / t.ballSnow;
			while (snow > 0)
			{
				int size;
				if (snow > 16)
				{
					size = 16;
				}
				else
				{
					size = snow;
				}
				snow -= 16;

				float f = 0.7F;
				double dx = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
				double dy = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.2D + 0.6D;
				double dz = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
				EntityItem entityitem = new EntityItem(world, (double) par2 + dx, (double) par3 + dy, (double) par4 + dz, new ItemStack(Items.snowball, size));
				world.spawnEntityInWorld(entityitem);
			}
		}
		super.onBlockPreDestroy(world, par2, par3, par4, meta);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntitySnowMachine t = (TileEntitySnowMachine) world.getTileEntity(x, y, z);
		if (t != null)
		{
			t.spawnFX(random);
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public String[] getShiftTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Fill with Ice, Snow, or Snowballs", "Activate with redstone signal" };
	}

	@Override
	public String[] getTip(EntityPlayer player, ItemStack stack)
	{
		return new String[] { "Cover your house in snow!" };
	}
	
	@Override
	public boolean shouldRender3D()
	{
		return false;
	}
}
