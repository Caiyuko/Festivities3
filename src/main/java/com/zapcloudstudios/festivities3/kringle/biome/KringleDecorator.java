package com.zapcloudstudios.festivities3.kringle.biome;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.zapcloudstudios.festivities3.kringle.Kringle;
import com.zapcloudstudios.festivities3.kringle.gen.feature.WorldGenChristmasTree;
import com.zapcloudstudios.festivities3.kringle.gen.feature.WorldGenIce;
import com.zapcloudstudios.festivities3.kringle.gen.feature.WorldGenPeppermintArch;
import com.zapcloudstudios.festivities3.kringle.gen.feature.WorldGenPeppermintPole;

public class KringleDecorator extends BiomeDecorator
{
	public int peppermintPolesPerChunk;
	public WorldGenerator peppermintPoleGen;
	
	public int christmasTreesPerChunk;
	public WorldGenerator christmasTreeGen;
	
	public int peppermintArchPerChunk;
	public WorldGenerator peppermintArchGen;
	
	public int peppermintCanePerChunk;
	public WorldGenerator peppermintCaneGen;
	
	public KringleDecorator()
	{
		super();
		this.reset();
		
		this.coalGen = new WorldGenMinable(Blocks.coal_ore, 10, Kringle.getStone());
		this.goldGen = new WorldGenMinable(Blocks.gold_ore, 10, Kringle.getStone());
		this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 4, Kringle.getStone());
		
		this.peppermintPoleGen = new WorldGenPeppermintPole(3, 5, 10);
		this.christmasTreeGen = new WorldGenChristmasTree(18, 8, 5, 3, 10);
		this.peppermintArchGen = new WorldGenPeppermintArch(5, 5, 4, 4, 8, false);
		this.peppermintCaneGen = new WorldGenPeppermintArch(5, 5, 4, 2, 8, true);
		
		this.peppermintPolesPerChunk = 8;
		this.peppermintCanePerChunk = 4;
		this.christmasTreesPerChunk = 0;
		this.peppermintArchPerChunk = 1;
	}
	
	public void reset()
	{
		this.sandGen = null;
		this.gravelAsSandGen = null;
		this.dirtGen = null;
		this.gravelGen = null;
		this.ironGen = null;
		this.redstoneGen = null;
		this.lapisGen = null;
		this.yellowFlowerGen = null;
		this.mushroomBrownGen = null;
		this.mushroomRedGen = null;
		this.bigMushroomGen = null;
		this.reedGen = null;
		this.cactusGen = null;
		this.waterlilyGen = null;
		this.flowersPerChunk = 0;
		this.grassPerChunk = 0;
		this.sandPerChunk = 0;
		this.sandPerChunk2 = 0;
		this.clayPerChunk = 0;
		this.generateLakes = false;
	}
	
	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
		
		this.generateOres();
		int x;
		int y;
		int z;
		
		boolean doGen;
		
		int trees = this.treesPerChunk;
		
		if (this.randomGenerator.nextInt(10) == 0)
		{
			++trees;
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.TREE);
		for (int i = 0; doGen && i < trees; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			WorldGenerator worldgenerator = biome.func_150567_a(this.randomGenerator);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.LAKE);
		if (doGen && this.generateLakes)
		{
			for (int i = 0; i < 50; ++i)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenIce(Blocks.ice)).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.CUSTOM);
		for (int i = 0; doGen && i < this.peppermintPolesPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.peppermintPoleGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.CUSTOM);
		for (int i = 0; doGen && i < this.peppermintCanePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			// this.peppermintCaneGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.CUSTOM);
		for (int i = 0; doGen && i < this.christmasTreesPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.christmasTreeGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.CUSTOM);
		for (int i = 0; doGen && i < this.peppermintArchPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			// this.peppermintArchGen.generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}
	
	@Override
	public void decorateChunk(World par1World, Random par2Random, BiomeGenBase biome, int par3, int par4)
	{
		if (this.currentWorld != null)
		{
			
		}
		else
		{
			this.currentWorld = par1World;
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.genDecorations(biome);
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}
	
	/**
	 * Generates ores in the current chunk
	 */
	@Override
	protected void generateOres()
	{
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
		if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.coalGen, this.chunk_X, this.chunk_Z, COAL))
		{
			this.genStandardOre1(20, this.coalGen, 0, 128);
		}
		if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.goldGen, this.chunk_X, this.chunk_Z, GOLD))
		{
			this.genStandardOre1(2, this.goldGen, 0, 32);
		}
		if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.diamondGen, this.chunk_X, this.chunk_Z, DIAMOND))
		{
			this.genStandardOre1(1, this.diamondGen, 0, 16);
		}
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}
}
