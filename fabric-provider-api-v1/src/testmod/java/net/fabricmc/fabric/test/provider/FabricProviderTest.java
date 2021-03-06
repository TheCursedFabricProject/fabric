/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.test.provider;

import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.test.provider.api.ItemApis;
import net.fabricmc.fabric.test.provider.compat.InventoryExtractableProvider;
import net.fabricmc.fabric.test.provider.compat.InventoryInsertableProvider;

public class FabricProviderTest implements ModInitializer {
	public static final String MOD_ID = "fabric-provider-api-v1-testmod";

	public static final ChuteBlock CHUTE_BLOCK = new ChuteBlock(FabricBlockSettings.of(Material.METAL));
	public static final BlockItem CHUTE_ITEM = new BlockItem(CHUTE_BLOCK, new Item.Settings().group(ItemGroup.MISC));
	public static BlockEntityType<ChuteBlockEntity> CHUTE_BLOCK_ENTITY_TYPE;

	@Override
	public void onInitialize() {
		Identifier chute = new Identifier(MOD_ID, "chute");
		Registry.register(Registry.BLOCK, chute, CHUTE_BLOCK);
		Registry.register(Registry.ITEM, chute, CHUTE_ITEM);
		CHUTE_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, chute, BlockEntityType.Builder.create(ChuteBlockEntity::new, CHUTE_BLOCK).build(null));

		InventoryExtractableProvider extractableProvider = new InventoryExtractableProvider();
		InventoryInsertableProvider insertableProvider = new InventoryInsertableProvider();

		ItemApis.INSERTABLE.registerForBlockEntities(insertableProvider, BlockEntityType.CHEST, BlockEntityType.DISPENSER, BlockEntityType.DROPPER, BlockEntityType.HOPPER);
		ItemApis.EXTRACTABLE.registerForBlockEntities(extractableProvider, BlockEntityType.CHEST, BlockEntityType.DISPENSER, BlockEntityType.DROPPER, BlockEntityType.HOPPER);
	}
}
